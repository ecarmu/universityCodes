import React, {useState, useEffect} from "react";
import './LoginStyle.css'
import { useNavigate } from "react-router-dom";
import {jwtDecode} from 'jwt-decode';
import {GoogleOAuthProvider} from "@react-oauth/google";
import { GoogleLogin } from "@react-oauth/google";
import useAuth from "../customHook/useAuth";


function Login(){

    
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleLogin = async () => {
        if (!email && !password) {
            // Perform login logic
            alert("Please fill in both email and password fields");
        } else {
            // Display an error message or prevent login
            try {
                const response = await fetch('http://localhost:5000/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ email, password }),
                });

                if (response.ok) {
                    const result = await response.json();
                    // Do something with the result from the backend
                    console.log(result);
                    const { name, surname, locale } = result;
                    
                    sessionStorage.setItem("isLoggedIn", true);
                    sessionStorage.setItem("decoded", JSON.stringify({ name, surname, locale }));
                    
                    navigate("/");

                } else {
                    console.error('Login failed');
                }
            } catch (error) {
                console.error('Error during login:', error);
            }
        }
        
    };

    //const google = window.google

    const clientID = "836984534330-ns995l4nbg1urgm4gpqti6pu8hpkguj7.apps.googleusercontent.com"


    function handleCallbackResponse(response) {
        console.log("Encoded JWT token: " + response.credential)
    }

/*
    useEffect(() => {
        /*global google, google.accounts*/
        const google = window.google
        console.log(google)/*
            google.accounts.id.initialize({
            client_id: clientID,
            callback: handleCallbackResponse
        });

        google.accounts.id.renderButton(
            document.getElementById("signInDiv"),
            { theme: "outline", size: "large" }
        )
    }, [])
*/
    // Define a mapping of countries to their respective cities
    const citiesByCountry = {
        Turkey: ["Istanbul", "Ankara", "Izmir"],
        USA: ["New York", "Los Angeles", "Chicago"],
    };

    // Event handler to update the selected country
    const handleCountryChange = (event) => {
        setSelectedCountry(event.target.value);
    };

    const handleCityyChange = (event) => {
        setSelectedCity(event.target.value);
    };

    const onSuccess = (res) => {
        console.log("LOGIN SUCCESS! Current user: ", res.profileObj);
        Login(res.credential)
        navigate('/');
    }
    const onFailure = (res) => {
        console.log("LOGIN FAILED! res: ", res)
    }

    const [nameRegister, setNameRegister] = useState("");
    const [surnameRegister, setSurnameRegister] = useState("");
    const [emailRegister, setEmailRegister] = useState("");
    const [passwordRegister, setPasswordRegister] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [selectedCountry, setSelectedCountry] = useState("Turkey");
    const [selectedCity, setSelectedCity] = useState(0);
    const [openRegisterMenu, setOpenRegisterMenu] = useState(false);
    const [error, setError] = useState("");

    const validateForm = () => {
        if (!emailRegister || !passwordRegister || !confirmPassword) {
            alert("All fields must be filled");
            return false;
        }

        if (passwordRegister.length < 8 || !/\d/.test(passwordRegister) || !/\W/.test(passwordRegister)) {
            alert("Password must be at least 8 characters and include a number and a non-alphanumeric character");
            return false;
        }

        if (passwordRegister !== confirmPassword) {
            alert("Passwords do not match");
            return false;
        }

        // Additional email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(emailRegister)) {
            alert("Invalid email address");
            return false;
        }

        setError("");
        return true;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateForm()) {
            // Perform registration logic here
            alert("Registration successful");
            sendRegisterDataToBackEnd()
        }
    };

    async function sendRegisterDataToBackEnd(){
        const registrationData = {
            name: nameRegister,
            surname: surnameRegister,
            email: emailRegister,
            password: passwordRegister,
            country: selectedCountry,
            city: selectedCity
        };

        try {
            const response = await fetch('http://localhost:5000/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(registrationData),
            });

            if (response.ok) {
                const result = await response.json();
                console.log(result);  // Handle the response from the backend as needed
                
                const { name, surname, locale } = result;

                sessionStorage.setItem("isLoggedIn", true);
                sessionStorage.setItem("decoded", JSON.stringify({ name, surname, locale }));
                navigate('/')
            } else {
                console.error('Registration failed');
            }
        } catch (error) {
            console.error('Error during registration:', error);
        }
    };

    

    return(
        <div className="login_container">

            <div style={{width: "40%"}}>

                <div>
                    {/*<GoogleLogin
                        clientID = {clientID}
                        buttonText = "Google ile Giriş Yap"
                        onSuccess={onSuccess}
                        onFailure={onFailure}
                        cookiePolicy={'single_host_origin'}
                        isSignedIn={true}
    />*/}
                    <GoogleOAuthProvider clientId={clientID}>
                        <GoogleLogin
                            clientID={clientID}
                            onSuccess={(credentialResponse) => {
                                console.log(credentialResponse)
                                var decoded = jwtDecode(credentialResponse.credential)
                                console.log(decoded)
                                sessionStorage.setItem("isLoggedIn", true);
                                sessionStorage.setItem("decoded", JSON.stringify(decoded));
                                navigate("/");
                            }}
                            onFailure={() => {
                                console.log('Login failed')
                            }}
                            cookiePolicy={'single_host_origin'}
                            
                                
                        />
                    </GoogleOAuthProvider>
                </div>

                <div className="login_element_container">
                    <p>Kullanıcı email: </p>
                    <input type="email" value={email} onChange={handleEmailChange} />
                </div>
                
                <div className="login_element_container">
                    <p>Şifre: </p>
                    <input type="password" value={password} onChange={handlePasswordChange} />
                </div>
                <div className="login_element_container" style={{flexDirection: "row", justifyContent: "flex-end"}}>
                    <button onClick={handleLogin}>Giriş</button>
                    <button onClick={() => setOpenRegisterMenu(true)}>Üye Ol</button>
                </div>
            </div>

            <div style={{ width: "50%" }}>
                {openRegisterMenu && (
                    <div>
                        <div className="login_element_container">
                            <p>Ad: </p>
                            <input value={nameRegister} onChange={(e) => setNameRegister(e.target.value)} />
                        </div>

                        <div className="login_element_container">
                            <p>Soyad: </p>
                            <input value={surnameRegister} onChange={(e) => setSurnameRegister(e.target.value)} />
                        </div>

                        <div className="login_element_container">
                            <p>Kullanıcı email: </p>
                            <input type="email" value={emailRegister} onChange={(e) => setEmailRegister(e.target.value)} />
                        </div>

                        <div className="login_element_container">
                            <p>Şifre: </p>
                            <input type="password" value={passwordRegister} onChange={(e) => setPasswordRegister(e.target.value)} />
                        </div>

                        <div className="login_element_container">
                            <p>Tekrar: </p>
                            <input type="password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
                        </div>

                        <div className="login_element_container">
                            <div style={{
                                display: "flex", flex: "0.6",
                                margin: "2 % 2 % 0 % 2 %",
                                float: "right",
                                justifyContent: "space-between"
                            }}>
                                <p style={{ flex: "1", margin: "2% 2% 2% 2%" }}> Ülke: </p>
                                <select id="checkbox" value={selectedCountry} onChange={handleCountryChange}>
                                    <option value="Turkey">Turkey</option>
                                    <option value="USA">USA</option>
                                </select>

                                <br />
                            </div>

                            <p style={{ flex: "0.1", margin: "2% 0% 0% 0%" }}> Şehir:</p>
                            <select id="checkbox">
                                {citiesByCountry[selectedCountry].map((city) => (
                                    <option key={city} value={city} onChange={selectedCity}>
                                        {city}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div className="login_element_container" style={{ justifyContent: "space-evenly" }}>
                            <button onClick={handleSubmit}>Kayıt ol</button>
                        </div>
                    </div>
                )}
            </div>


            

        </div>
    )
}

export default Login;