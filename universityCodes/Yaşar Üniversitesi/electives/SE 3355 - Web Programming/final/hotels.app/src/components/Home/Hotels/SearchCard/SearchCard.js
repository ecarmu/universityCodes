import React from "react";
import { Link, useNavigate } from "react-router-dom"; 
import Button from "../../Button";
import useAuth from "../../../customHook/useAuth"; // Import the custom hook

function SearchCard({ data }){
    const navigate = useNavigate();

    const {
        id,
        imageLink,
        rating,
        hotelName,
        location,
        price,
        stayDetails,
        isCheapToMembers,
        commentAmount
    } = data;

    
    const { isLoggedIn } = useAuth(); // Use the custom hook

    const handleCardClick = () => {
        // Handle any logic you want to perform when the card is clicked
        // Programmatically navigate to the HotelDetail page
        navigate(`/hotel-detail/${id}`);
    };

    return(

        <div className="search-card-container">
            <div className="card_item" onClick={handleCardClick}>
                <div>
                    <img
                        src={imageLink}
                        style={{ maxWidth: "100%", borderRadius: "5%" }}
                        className="card_image"
                    />
                </div>

                <div>
                    <span style={{ fontWeight: 700 }}>{rating}/10</span>
                    <span style={{ lineHeight: '30px', marginLeft: '2.25%' }}>
                        {rating >= 9
                            ? 'Mükemmel'
                            : rating >= 8
                                ? 'Çok İyi'
                                : 'İyi'}
                    </span>
                    <span style={{ lineHeight: '30px', marginLeft: '2.25%' }}>
                        ({commentAmount} yorum)
                    </span>
                </div>

                <div>
                    <h3 style={{ marginTop: 5, marginBottom: 5 }}>{hotelName}</h3>
                    <h4 style={{ marginTop: 5, marginBottom: 18 }}>{location}</h4>
                </div>

                <div>
                    <span style={{ fontWeight: 700, fontSize: '22.5px' }}>{price} TL</span>
                </div>

                <div style={{ fontWeight: 400 }}>
                    <span style={{ display: "block" }}>{stayDetails}</span>
                    <span style={{ display: "block" }}>Gecelik için {price / 2}TL</span>
                    <span style={{ display: "block" }}>Vergiler ve ücretler dahildir</span>
                </div>

                <div style={{ backgroundColor: '#7b1fa2', color: '#fff', borderRadius: '0.25rem', display: 'inline-block', paddingRight: '5%', marginTop: '5%', width: '75%' }}>
                    {isCheapToMembers === "true" ? (
                        <span style={{ color: 'white' }} className="uitk-badge uitk-badge-large uitk-badge-deal-member uitk-badge-has-text uitk-spacing uitk-spacing-margin-inlineend-two uitk-spacing-margin-blockstart-two">
                            <svg className="uitk-icon uitk-icon-small" aria-describedby="mod-description" aria-label="mod" role="img" viewBox="0 0 24 24">
                                <title id="mod-title">mod</title>
                                <desc id="mod-description">mod</desc>
                                <path fillRule="evenodd" d="m21.41 11.58-9-9A1.99 1.99 0 0 0 11 2H4a2 2 0 0 0-2 2v7a2 2 0 0 0 .59 1.42l9 9a1.99 1.99 0 0 0 2.82-.01l7-7c.37-.36.59-.86.59-1.41 0-.55-.23-1.06-.59-1.42ZM5.5 7a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3Z" clipRule="evenodd"></path>
                            </svg>
                            <span className="uitk-badge-text" aria-hidden="false">Üye Fiyatından yararlanılabilir</span>
                        </span>
                    ) : null}
                </div>
            </div>

            <div>
                {isCheapToMembers === "true" && (
                    isLoggedIn ? <p style={{ fontSize: '23.5px', color: '#1668e3'}}>Üye Fiyatı: {price*9/10}</p> : <Button isLoginButton={false} isLoginToSeeButton={true} /> 
                )}
            </div>

        </div>
    )
}

export default SearchCard;

