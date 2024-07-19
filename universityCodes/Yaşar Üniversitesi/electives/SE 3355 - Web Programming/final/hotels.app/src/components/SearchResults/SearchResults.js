import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import './SearchResultsStyles.css';
import SearchResult from "./SearchResult/SearchResult";
import useAuth from "../customHook/useAuth";

const SearchResults = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const searchQuery = new URLSearchParams(location.search);
    const searchText = searchQuery.get('search');
    const availableRoomAmount = searchQuery.get('availableRoomAmount');
    const enteredFrom = searchQuery.get('enteredFrom');
    const enteredTo = searchQuery.get('enteredTo');
    const [hotels, setHotels] = useState([]);
    const { decoded } = useAuth();

    useEffect(() => {
        let apiUrl = `http://localhost:5000/search-results?search=${searchText}&availableRoomAmount=${availableRoomAmount}&enteredFrom=${enteredFrom}&enteredTo=${enteredTo}`;

        // Append decoded.locale to apiUrl if it exists
        if (decoded.locale) {
            if (decoded.locale == "tr")
                decoded.locale = "Turkey"
            else if (decoded.locale == "us")
                decoded.locale = "USA"
            apiUrl += `&locale=${decoded.locale}`;
        }
        else{
            apiUrl += `&locale=`;  
        }

        // Fetch data from the server with both search queries as parameters
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                console.log('Fetched data:', data);
                console.log(decoded.locale)
                setHotels(data.hotels);
            })
            .catch(error => console.error('Error:', error));
    }, [searchText, availableRoomAmount, enteredFrom, enteredTo, decoded.locale]);

    const renderSearchResult = (item) => {
        return <SearchResult key={item.id} data={item} />;
    };

    return (
        <div className="search_results_container">
            <div className="results_container">
                {hotels.map(item => (
                    <SearchResult key={item.id} data={item} className="result_container" />
                ))}
            </div>
        </div>
    );
}

export default SearchResults;
