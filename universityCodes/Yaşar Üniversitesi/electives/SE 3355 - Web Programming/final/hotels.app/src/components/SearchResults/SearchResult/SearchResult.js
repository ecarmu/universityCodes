import React from "react";
import {useNavigate } from "react-router-dom";
import './SearchResultStyles.css';
import amentiesData from "../../../amenties.json"

function SearchResult({data}) {

    const navigate = useNavigate();

    const {
        id,
        imageLink,
        rating,
        hotelName,
        location,
        price,
        stayDetails,
        commentAmount,
        allAmenities
    } = data;

    const handleCardClick = () => {
        // Handle any logic you want to perform when the card is clicked
        // Programmatically navigate to the HotelDetail page
        navigate(`/hotel-detail/${id}`);
    };

    const amentiesArray = amentiesData.amenties;
    console.log(allAmenities)
    const allAmenitiesArray = JSON.parse(allAmenities)


    return (
        <div className="search_result_container" onClick={handleCardClick}>
            <div className="search_result_image">
                <img
                    src={imageLink}
                    alt="Hotel"
                    style={{ maxWidth: "100%", display: "block", height: "100%" }}
                />
            </div>

            <div className="search_result_info_container">
                <div className="search_result_info_container_upper">
                    <div>
                        <h3 style={{ marginTop: 5, marginBottom: 5 }}>{hotelName}</h3>
                        <h4 style={{ marginTop: 5, marginBottom: 18 }}>{location}</h4>
                    </div>

                    <div style={{display: "flex"}}>
                        <ul>
                            {allAmenitiesArray.map((amenity, index) => {
                                // Check if the index is 1 or 2 (2nd and 3rd indexes)
                                if (index === 1 || index === 2) {
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
                                }
                                return null; // Handle the case where there is no matching link or index is not 1 or 2
                            })}
                        </ul>

                    </div>
                </div>

                

                <div className="search_result_info_container_lower">
                    <div className="search_result_info_container_lower_upper">
                        <div style={{ display: 'flex', alignItems: 'center' , justifyContent: 'space-between'}}>

                            <div>
                                <img
                                    src="https://a.travel-assets.com/egds/marks/brands/hotels/loyalty.svg"
                                    style={{ objectFit: "fill", width: "4%", maxHeight: '326px' }}
                                    alt="Loyalty"
                                    className="search_result_info_image"
                                />
                                <span style={{ lineHeight: '1', marginLeft: '5px' }}>Puan toplayın</span>
                            </div>
                            

                            <div style={{ float: "right" }}>
                                <span style={{ fontWeight: 700 }}>{price} TL</span>
                            </div>
                        </div>

                        
                    </div>


                    <div className="search_result_info_container_lower_lower">
                        <div className="rating">
                            
                            <div style={{ display: 'flex', alignItems: 'center', height: '23px', width: '31px'}}>
                                <span className="ratingArea" style={{ backgroundColor: rating < 8 ? '#dfe0e4' : '#227950' , color: rating < 8 ? '#000' : '#fff', textAlign: 'center', justifyContent: 'center', alignItems: 'center', borderRadius: '10%', paddingTop: '5%', width: '100%', height: '100%' }}>
                                    {rating}
                                </span>
                            </div>



                            <div style={{display: 'flex', flexDirection: 'column', fontWeight: '400'}}>
                                <span>
                                    {rating >= 9
                                        ? 'Mükemmel'
                                        : rating >= 8
                                            ? 'Çok İyi'
                                            : 'İyi'}
                                </span>
                                <span style={{ lineHeight: '20px' }}>{commentAmount} yorum</span>
                            </div>
                        </div>

                        <div className = "details">
                            <span style={{ display: "block", textAlign: "right" }}>{stayDetails} için</span>
                            <span style={{ display: "block" }}>Vergiler ve ücretler dahildir</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SearchResult;
