import React, { useState, useEffect } from "react";

function SearchBox({ onChange, onChange2, onChange3, onChange4, iconLink, type, title, placeholder, isTarih}){

    const handleChange = (e) => {
        const value = e.target.value;
        onChange(value);
    };

    const months = [
        { value: "01", label: "Ocak" },
        { value: "02", label: "Şubat" },
        { value: "03", label: "Mart" },
        { value: "04", label: "Nisan" },
        { value: "05", label: "Mayıs" },
        { value: "06", label: "Haziran" },
        { value: "07", label: "Temmuz" },
        { value: "08", label: "Ağustos" },
        { value: "09", label: "Eylül" },
        { value: "10", label: "Ekim" },
        { value: "11", label: "Kasım" },
        { value: "12", label: "Aralık" },
    ];

    const [startDay, setStartDay] = useState('');
    const [startMonth, setStartMonth] = useState('');
    const [endDay, setEndDay] = useState('');
    const [endMonth, setEndMonth] = useState('');

    const days = Array.from({ length: 31 }, (_, index) => index + 1);

    return (
        <>
            {type === 1 && (
                <div className="input-container" style={{ backgroundColor: "white" }}>
                    <svg
                        className="uitk-icon uitk-field-icon"
                        aria-hidden="true"
                        viewBox="0 0 24 24"
                        xmlns="http://www.w3.org/2000/svg"
                        xmlnsXlink="http://www.w3.org/1999/xlink"
                    >
                        <path fillRule="evenodd" d={iconLink} clipRule="evenodd"></path>
                    </svg>
                    <input
                        type="text"
                        className="custom-input"
                        placeholder={placeholder}
                        style={{ border: "none", flex: '1' }}
                        onChange={handleChange}
                    />
                </div>
            )}

            {type === 2 && isTarih && (
                <div className="input-container" style={{ backgroundColor: "white" }}>
                    <svg
                        className="uitk-icon uitk-field-icon"
                        aria-hidden="true"
                        viewBox="0 0 24 24"
                        xmlns="http://www.w3.org/2000/svg"
                        xmlnsXlink="http://www.w3.org/1999/xlink"
                    >
                        <path fillRule="evenodd" d={iconLink} clipRule="evenodd"></path>
                    </svg>
                    <div style={{ display: 'block', fontWeight: '400', inlineSize: '100%', lineHeight: '1.25rem'}}>
                        <span style={{ display: 'block', fontSize: '1rem', }}>
                            {title}
                        </span>

                        <div style={{ display: 'flex' }}>
                            <div style={{ display: 'flex', marginRight: '1%' }}>
                                <select
                                    id="startDaySelect"
                                    style={{ border: 'none' }}
                                    onChange={(e) => {
                                        const value = e.target.value;
                                        onChange(value);
                                        setStartDay(value)
                                    }}
                                    value={startDay}
                                >
                                    <option value="">Gün</option>
                                    {days.map((day) => (
                                        <option key={day} value={day}>
                                            {day}
                                        </option>
                                    ))}
                                </select>

                                <select
                                    id="startMonthSelect"
                                    style={{ border: 'none', width: '45%' }}
                                    onChange={(e) => {
                                        const value = e.target.value;
                                        onChange2(value);
                                        setStartMonth(value)
                                    }}
                                    value={startMonth}
                                >
                                    <option value="">Ay</option>
                                    {months.map((month) => (
                                        <option key={month.value} value={month.value}>
                                            {month.label}
                                        </option>
                                    ))}
                                </select>
                            </div>

                            <label style={{ fontSize: '15px', height: '15px', paddingBottom: '1%' }}> | </label>

                            <div style={{ display: 'flex', marginLeft: '1%' }}>
                                <select
                                    id="endDaySelect"
                                    style={{ border: 'none' }}
                                    onChange={(e) => {
                                        const value = e.target.value;
                                        onChange3(value);
                                        setEndDay(value)
                                    }}
                                    value={endDay}
                                >
                                    <option value="">Gün</option>
                                    {days.map((day) => (
                                        <option key={day} value={day}>
                                            {day}
                                        </option>
                                    ))}
                                </select>

                                <select
                                    id="endMonthSelect"
                                    style={{ border: 'none', width: '45%' }}
                                    onChange={(e) => {
                                        const value = e.target.value;
                                        onChange4(value);
                                        setEndMonth(value)
                                    }}
                                    value={endMonth}
                                >
                                    <option value="">Ay</option>
                                    {months.map((month) => (
                                        <option key={month.value} value={month.value}>
                                            {month.label}
                                        </option>
                                    ))}
                                </select>
                            </div>
                        </div>
                        
                    </div>
                </div>
            )}

            {type === 2 && !isTarih && (
                <div className="input-container" style={{ backgroundColor: "white" }}>
                    <svg
                        className="uitk-icon uitk-field-icon"
                        aria-hidden="true"
                        viewBox="0 0 24 24"
                        xmlns="http://www.w3.org/2000/svg"
                        xmlnsXlink="http://www.w3.org/1999/xlink"
                    >
                        <path fillRule="evenodd" d={iconLink} clipRule="evenodd"></path>
                    </svg>
                    <div style={{ display: 'block', fontWeight: '400', inlineSize: '100%', lineHeight: '1.25rem' }}>
                        <span style={{ display: 'block', fontSize: '1rem',}}>
                            {title}
                        </span>
                        <input
                            type="text"
                            className="custom-input"
                            placeholder= {placeholder}
                            style={{ border: "none", flex: '1', height: '40%', display: 'block'}}
                            onChange={handleChange}
                        />
                    </div>
                </div>
            )}
        </>
    );

}

export default SearchBox;


/*

{ type === 1 || type === 2 ? (
            <div className="input-container" style={{ backgroundColor: "white" }}>
                <svg
                    className="uitk-icon uitk-field-icon"
                    aria-hidden="true"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                    xmlnsXlink="http://www.w3.org/1999/xlink"
                >
                    <path fillRule="evenodd" d={iconLink} clipRule="evenodd"></path>
                </svg>
                <div style={{display: 'block'}}>
                    {type === 2 && <span>Misafir sayısı</span>}
                    <input
                        type="text"
                        className="custom-input"
                        placeholder="Enter text"
                        style={{ border: "none", overflow: 'hidden' }}
                        onChange={handleChange}
                    />
                </div>

            </div>
        ) : null}
        */

