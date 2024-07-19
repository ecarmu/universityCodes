import { useState, useEffect } from "react";
import { jwtDecode } from 'jwt-decode';


const useAuth = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(
        sessionStorage.getItem("isLoggedIn") === "true"
    );
    const [decoded, setDecoded] = useState(
        JSON.parse(sessionStorage.getItem("decoded")) || {}
    );

    useEffect(() => {
        setIsLoggedIn(sessionStorage.getItem("isLoggedIn") === "true");
        setDecoded(JSON.parse(sessionStorage.getItem("decoded")) || {});
    }, []);

    const login = (credentialResponse) => {
        const decoded = jwtDecode(credentialResponse.credential);
        const fullName = `${decoded.name} ${decoded.surname}`;

        sessionStorage.setItem("isLoggedIn", true);
        sessionStorage.setItem("decoded", fullName);
        setIsLoggedIn(true);
        setDecoded({ name: decoded.name, surname: decoded.surname, locale: decoded.locale });
    };

    const logout = () => {
        sessionStorage.removeItem("isLoggedIn");
        sessionStorage.removeItem("decoded");
        setIsLoggedIn(false);
        setDecoded({});
    };

    return { isLoggedIn, decoded, login, logout };
};

export default useAuth;