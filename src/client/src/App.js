import React, { Component } from 'react';
import './App.css';
import AddCourse from './pages/AddCourse';
import FindCourse from './pages/FindCourse';
import Course from './pages/Course';
import Cities from './pages/Cities';
import Destination from './pages/Destination';
import {Switch, Route, BrowserRouter as Router, Link} from 'react-router-dom';

const Home = props => (
    <>
        <div className="title">
            Travel as cheap as possible
        </div>
        
        <div className="body">
            <div className="wrapper">
                <Link to="/add">
                    <div className="baton">
                        Add a course
                    </div>
                </Link>
                <Link to="/find">
                    <div className="baton">
                        Find a course
                    </div>
                </Link> 
                <Link to="/cities">
                    <div className="baton">
                        Access points
                    </div>
                </Link>
            </div>
        </div>
    </>
);

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/" exact component={Home} />
                    <Route path="/add" component={AddCourse} />
                    <Route path="/find" component={FindCourse} />
                    <Route path="/cities" exact component={Cities} />
                    <Route path="/cities/:start" exact component={Destination} />
                    <Route path="/cities/:start/:dest" component={Course} />
                </Switch>
            </Router>
        );
    }
}

export default App;