import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

export default function Course({match}) {
    useEffect(() => {
        fetchCourse();
    }, []);

    const [start] = useState(match.params.start);
    const [destination] = useState(match.params.dest);
    const [cities, setCities] = useState([]);
    const [value, setValue] = useState(0);

    const fetchCourse = () => {
        const data = {
            start,
            destination
        };

        axios
            .post("http://localhost:8080/api/findCourse", {start: match.params.start, destination: match.params.dest})
            .then(res => {
                setCities(res.data.cities);
                setValue(res.data.value);
                console.log("Znalazłem!");
            })
            .catch(err => {
                console.log(err);
                return null;
            });
            
    };

    return (
        <>
            <div className="title">
                    <Link to="/">Result</Link>
            </div>

            <div className="body">
                {cities.reverse().map(city => (
                    <h1 key={city}>{city}</h1>
                ))}
                <br />
                <h1>Cost: {value} credits</h1>
            </div>
        </>
    );
}
