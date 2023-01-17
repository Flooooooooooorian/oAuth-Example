import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./Home";
import GithubRedirectPage from "./GithubRedirectPage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route index element={<Home />}/>
          <Route path={"/users/oauth/github"} element={<GithubRedirectPage />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
