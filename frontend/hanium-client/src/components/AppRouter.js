import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignUp from "../routes/SignUp";
import SignIn from "../routes/SignIn";
import Home from "../routes/Home";
import Profile from "../routes/Profile";
import ItemNavigation from "./ItemNavigation";
import Navigation from "./Navigation";
const AppRouter = ({ isLoggedIn, userObj }) => {
    return (
        <Router>
            <ItemNavigation />
            <div className="router">
                <Routes>
                    <>
                        <Route path='/sign-up' element={<SignUp/>}/>
                        <Route path='/sign-in' element={<SignIn/>}/>
                        <Route path='/profile' element={<Profile userObj={userObj}/>}/>
                        <Route path='/' element={<Home/>}/>
                    </>
                </Routes>
            </div>
        </Router>
    )
}

export default AppRouter