import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

export default function Destination({match}) {
    useEffect(() => {
        fetchCities();
    }, []);

    const [cities, setCities] = useState([]);

    const fetchCities = () => {
        axios
            .get("http://localhost:8080/api/cities")
            .then(res => {
                setCities(res.data.cities);
            })
            .catch(err => {
                console.log(err);
                return null;
            });
    };

    return (
        <>
            <div className="title">
                <Link to="/">Pick your destination</Link>
            </div>

            <div className="body">
                {cities.map(city => (
                    <h1 key={city}>
                        <Link to={`/cities/${match.params.start}/${city}`} >{city}</Link>
                    </h1>
                ))}
            </div>
        </>
    );
}