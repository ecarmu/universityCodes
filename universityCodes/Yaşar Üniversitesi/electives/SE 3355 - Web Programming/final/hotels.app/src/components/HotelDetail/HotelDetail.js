import React, { useState, useEffect } from 'react'; 
import { useParams } from 'react-router-dom';
import './HotelDetailStyle.css'
import amentiesData from '../../amenties.json'

function HotelDetail(){

    const amentiesArray = amentiesData.amenties;

    const [hotels, setHotels] = useState([]);
    const [isLoaded, setLoaded] = useState(false)

    const { id } = useParams();

    useEffect(() => {
        // Fetch data from the server when the component mounts
        fetch(`http://localhost:5000/hotel-detail/${id}`)
            .then(response => response.json())
            .then(data => {
                console.log('Fetched data:', data);
                setLoaded(true)
                setHotels(data.hotels);
            })
            .catch(error => console.error('Error:', error));
    }, []);

    // Check if hotels data is still being fetched
    if (!isLoaded) {
        return <p>Loading...</p>;
    }
    
    const {
        imageLink,
        rating,
        hotelName,
        hotelDesc,
        location,
        price,
        stayDetails,
        locationLink,
        locationInfo,
        allAmenities,
        commentAmount
    } = hotels[0];

    console.log('Image URL:', imageLink);

    const allAmenitiesArray = JSON.parse(allAmenities)

    

    return(
        <div className="hotel_detail_container">
            <div className="upper_container">
                <img src={imageLink}/>
            </div>

            <div className="lower_container">
                <div className="left_side">
                    <div className="title">
                        <h1>{hotelName}</h1>
                        <p>{hotelDesc}</p>
                    </div>

                    <div className="top">
                        <div className="rating">
                            <div style={{ display: 'flex', alignItems: 'center', alignItems: "center", height: "70%", width: "8%" }}>
                                <span style={{ fontWeight: 500, fontSize: ".75rem", backgroundColor: rating < 8 ? '#dfe0e4' : '#227950', color: rating < 8 ? '#000' : '#fff', height: "100%", width: '100%', textAlign: "center", justifyContent: 'center', alignContent: 'center', borderRadius: "10%", paddingTop: "5%", height: "20px" }}>
                                    {rating}
                                </span>
                            </div>

                            <span style={{lineHeight: '30px', marginLeft:'2.25%'}}>
                                {rating >= 9
                                    ? 'Mükemmel'
                                    : rating >= 8
                                        ? 'Çok İyi'
                                        : 'İyi'}
                            </span>
                        </div>

                        <div>
                            <p style={{ color:"#1668e3" }}>{commentAmount} yorumun tümünü göster &gt;</p>
                        </div>
                        
                    </div>

                    <div className="mid">
                        <h4>Popüler konaklama yeri imkan ve özellikleri</h4>
   
                        <ul>
                            {allAmenitiesArray.map((amenity, index) => {
                                // Find the corresponding link based on the type
                                const matchingAmenty = amentiesArray.find(a => a.type === amenity);
                                if (matchingAmenty) {
                                    return (
                                        <li key={index} role="listitem">
                                            <div className="uitk-layout-flex">
                                                <svg
                                                    className="uitk-icon uitk-spacing uitk-spacing-padding-inlineend-two uitk-icon-default-theme"
                                                    aria-hidden="true"
                                                    viewBox="0 0 24 24"
                                                    xmlns="http://www.w3.org/2000/svg"
                                                    xmlnsXlink="http://www.w3.org/1999/xlink"
                                                >
                                                    <path
                                                        fillRule="evenodd"
                                                        d={matchingAmenty.link}
                                                        clipRule="evenodd"
                                                    />
                                                </svg>
                                                <span className="uitk-text uitk-type-300 uitk-text-default-theme">{matchingAmenty.type}</span>
                                            </div>
                                        </li>
                                    );
                                }
                                return null; // Handle the case where there is no matching link
                            })}
                        </ul>

                    </div>


                    <div className="bottom">
                        <p style={{fontSize: '25px'}}>{price} TL</p>
                        <p>{stayDetails}</p>
                        <p>Vergiler ve ücretler dahildir</p>
                    </div>
                    
                </div>

                <div className="right_side">
                    <iframe className="iFrame" src={locationLink}
                        width="400vw" height="50%" 
                        style= {{border:0, borderRadius: "4%", flex: "0.1"}} 
                        allowFullScreen="" loading="lazy" 
                        referrerPolicy="no-referrer-when-downgrade"/> 

                    <span>{locationInfo}</span>    
                    <p style={{ color: "#1668e3" }}>Haritada göster &gt;</p>               

                </div>
            </div>


        </div>
    )
}
export default HotelDetail;
