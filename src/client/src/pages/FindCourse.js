import React, {useState} from 'react';
import {Link, Redirect} from 'react-router-dom';

export default function FindCourse(props) {
    const [start, setStart] = useState("");
    const [destination, setDest] = useState("");
    const [redirect, setRedirect] = useState(false);

    const handleSubmit = e => {
        e.preventDefault();
        setRedirect(true);
    };

    return (
        <>  
            {redirect ? <Redirect to={`/cities/${start}/${destination}`} /> : null}

            <div className="title">
                <Link to="/">Find a course</Link>
            </div>
        
            <form className="body" onSubmit={handleSubmit} >
                <div className="wrapper">
                    <div className="input">
                        <input type="text" name="start" placeholder="Starting point" className="text_input" onChange={e => setStart(e.target.value)} />
                    </div>

                    <div className="input">
                        <input type="text" name="destination" placeholder="Destination" className="text_input" onChange={e => setDest(e.target.value)} />
                    </div>

                    <input type="submit" className="baton" value="Find" />
                </div>
            </form>
        </>
    );
}