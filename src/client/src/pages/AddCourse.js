import React, {useState} from 'react';
import {Link, Redirect} from 'react-router-dom';
import axios from 'axios';

export default function AddCourse(props) {
    const [start, setStart] = useState("");
    const [destination, setDest] = useState("");
    const [price, setPrice] = useState(0);
    const [time, setTime] = useState(0);
    const [redirect, setRedirect] = useState(false);

    const handleSubmit = e => {
        e.preventDefault();

        const data = {
            start,
            destination,
            price,
            time
        };

        axios
            .post("http://localhost:8080/api/saveCourse", data)
            .then(res => {
                if (res.status === 201) {
                    console.log("Dodano!");
                    setRedirect(true);
                }
            })
            .catch(err => {
                console.log(err);
                return null;
            });
    };

    return (
        <>
            {redirect ? <Redirect to="/" /> : null}
            
            <div className="title">
                <Link to="/">Add a course</Link>
            </div>
            
            <div className="body" onSubmit={handleSubmit} >
                <form className="wrapper">
                    <div className="input">
                        <input type="text" name="start" placeholder="Starting point" className="text_input" onChange={e => setStart(e.target.value)} />
                    </div>

                    <div className="input">
                        <input type="text" name="destination" placeholder="Destination" className="text_input" onChange={e => setDest(e.target.value)}/>
                    </div>

                    <div className="input">
                        <span className="short_input">
                            <input type="number" step="0.01" name="price" placeholder="Price" className="text_input" onChange={e => setPrice(parseFloat(e.target.value))} />
                            <img className="icon" src="currency.png" alt="$"/> 
                        </span>

                        <span className="short_input">
                            <input type="number" step="0.01" name="time" placeholder="Time" className="text_input" onChange={e => setTime(parseFloat(e.target.value))} />
                            <span className="icon">pc</span> 
                        </span>
                    </div>
                    
                    <input type="submit" className="baton" value="Add" />
                </form>
            </div>
        </>
    );
}