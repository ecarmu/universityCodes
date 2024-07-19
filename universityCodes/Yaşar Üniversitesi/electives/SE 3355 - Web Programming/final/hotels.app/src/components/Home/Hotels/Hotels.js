// Inside SearchResults.js
import React, {useState, useEffect} from "react";
import DatalistInput from 'react-datalist-input';
import './HotelsStyle.css';
import SearchCard from "./SearchCard/SearchCard";
import { gapi } from "gapi-script"
import useAuth from "../../customHook/useAuth";

function Hotels() {

    const [hotels, setHotels] = useState([]);

    const { decoded } = useAuth(); // Use the custom hook

    

    useEffect(() => {
        // Fetch data from the server when the component mounts

        if (decoded.locale == "tr")
            decoded.locale = "Turkey"
        else if (decoded.locale == "us")
            decoded.locale = "USA"


        const apiUrl = decoded.locale
            ? `http://localhost:5000/?country=${decoded.locale}`
            : 'http://localhost:5000/';

        console.log(decoded.locale)


        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                console.log(decoded)
                console.log(decoded.locale)
                console.log('Fetched data:', data);
                console.log(hotels)
                setHotels(data.hotels);
            })
            .catch(error => console.error('Error:', error));
    }, [decoded.country]);

    const renderSearchCard = (item) => {
        return <SearchCard key={item.id} data={item} />;
    };

    return (
        <div className="hotel_container">
            <h2 className="title_hotel">Bu hafta sonu tatil yapın!</h2>
            <p className="p_hotel">Şu tarih aralığı için fırsatlar gösteriliyor: <b> 18 Oca - 31 Oca</b> </p>

            <div className="card_container">
                {hotels.map(item => (
                    <SearchCard key={item.id} data={item} />
                ))}
            </div>
        </div>
    );
}

export default Hotels;
