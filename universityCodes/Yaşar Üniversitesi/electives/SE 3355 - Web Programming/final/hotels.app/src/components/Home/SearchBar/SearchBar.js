// SearchBar.js

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import SearchBox from "./SearchBox";
import './SearchBarStyle.css';
import useAuth from "../../customHook/useAuth";

function SearchBar() {
    const navigate = useNavigate();
    const [searchText, setSearchText] = useState('');
    const [available_room_amount, setRoomAmount] = useState(null);
    const [enteredFromDay, setEnteredFromDay] = useState(null);
    const [enteredFromMonth, setEnteredFromMonth] = useState(null);

    const [enteredToDay, setEnteredToDay] = useState(null);
    const [enteredToMonth, setEnteredToMonth] = useState(null);

    const { decoded } = useAuth();

/*
    const handleSearchButtonClick = () => {
        // Check if available_room_amount is greater than or equal to 1
        if (available_room_amount >= 1 || available_room_amount == null) {
            // Navigate to the SearchResults page with both search queries as parameters
            console.log(available_room_amount);

            if (available_room_amount == null)
                navigate(`/search-results?search=${searchText}&availableRoomAmount=${available_room_amount}&enteredFrom=${enteredFrom}&enteredTo=${enteredTo}`);
            else
                navigate(`/search-results?search=${searchText}&availableRoomAmount=${available_room_amount}&enteredFrom=${enteredFrom}&enteredTo=${enteredTo}`);
        } else {
            // Show an alert with an error message
            alert('Error: availableRoomAmount must be greater than or equal to 1');
            // You can customize the alert message as needed
        }
    };
    */

    const handleSearchButtonClick = () => {
        // Check if available_room_amount is greater than or equal to 1
        if (available_room_amount >= 1 || available_room_amount == null) {
            console.log(enteredFromMonth)
            console.log(enteredToMonth)
            // Navigate to the SearchResults page with both search queries as parameters

            let link = `/search-results?search=${searchText}`;

            if (available_room_amount == null)
                link +=`&availableRoomAmount=${1}`;
            else
                link += `&availableRoomAmount=${available_room_amount}`;


            if (enteredFromMonth !== null && enteredFromDay !== null) {
                link += `&enteredFrom=2024.${enteredFromMonth}.${enteredFromDay}`;  
            } 
            else {
                link += `&enteredFrom=`;
            }

            if (enteredToMonth !== null && enteredToDay !== null) {
                link += `&enteredTo=2024.&enteredTo=2024.${enteredToMonth}.${enteredFromDay }`;
            }
            else{
                link += `&enteredTo=`;
            }
            if (decoded.locale!==null){
                if (decoded.locale) {
                    if (decoded.locale == "tr")
                        decoded.locale = "Turkey"
                    else if (decoded.locale == "us")
                        decoded.locale = "USA"
                }
                link += `&locale=${decoded.locale}`;
            }
            else {
                link += `&locale=`;
            }

            navigate(link);


        } else {
            // Show an alert with an error message
            alert('Error: availableRoomAmount must be greater than or equal to 1');
            // You can customize the alert message as needed
        }
    };





    const icon1 = "M12 2a7 7 0 0 0-7 7.01c0 4.18 4.42 9.94 6.24 12.13a1 1 0 0 0 1.53 0C14.58 18.94 19 13.19 19 9.01A7 7 0 0 0 12 2zm0 9.5a2.5 2.5 0 1 1 0-5 2.5 2.5 0 0 1 0 5z"
    const icon2 = "M19 4h-1v-1a1 1 0 0 0-1-1 1 1 0 0 0-1 1v1H8V3a1 1 0 0 0-1-1 1 1 0 0 0-1 1v1H5a2 2 0 0 0-1.99 2L3 20a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2zm-1 16H6a1 1 0 0 1-1-1V9h14v10a1 1 0 0 1-1 1zM8 11h3a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H8a1 1 0 0 1-1-1v-3a1 1 0 0 1 1-1z"
    const icon3 = "M12 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm0 2c-2.67 0-8 1.34-8 4v1a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1v-1c0-2.66-5.33-4-8-4z"

    return (
        <div className="search_bar_container">
            <div className="search_bar_title">
                <span>Nereye?</span>
            </div>

            <div className="search_bar_bars_container">
                <SearchBox iconLink = {icon1} onChange={(value) => setSearchText(value)} type={1} placeholder="Varış noktası"/>
                <SearchBox iconLink={icon2} type={2} title="Tarihler" placeholder="Tarih" isTarih={true} 
                    onChange={(value) => {
                        setEnteredFromDay(value)
                    }} 
                    onChange2={(value) => {
                        setEnteredFromMonth(value)
                    }} 
                    onChange3={(value) => {
                        setEnteredToDay(value)
                    }} 
                    onChange4={(value) => {
                        setEnteredToMonth(value)
                    }} />
                <SearchBox iconLink={icon3} type={2} title="Misafir Sayısı" onChange={(value) => setRoomAmount(value)} isTarih={false} placeholder="Misafir" />

                <button className="searchBarButton"
                    onClick={handleSearchButtonClick}
                >
                    Ara
                </button>
            </div>
        </div>
    );
}

export default SearchBar;
