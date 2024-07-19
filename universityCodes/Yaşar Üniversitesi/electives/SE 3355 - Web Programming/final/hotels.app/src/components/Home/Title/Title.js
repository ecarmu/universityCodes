import React from "react";
import Button from "../Button/Button"
import './TitleStyle.css'


function Title({isLogged, userName}){

    return(
        <div className="title_container">
            <img src="https://www.hotels.com/_dms/header/logo.svg?locale=en_IE&siteid=300000025&2&6f9ec7db"></img>
            <Button isLoginButton={true} isLoginToSeeButton={false} userName={userName} isLogged={isLogged}/>
        </div>
    );

}

export default Title;