import React from 'react';
import { BrowserRouter as Router, Routes, Route, Switch, Redirect } from 'react-router-dom';
import logo from './logo.svg';
import './App.css';
import Home from './components/Home';
import Title from './components/Home/Title/Title';
import SearchBar from './components/Home/SearchBar/SearchBar';
import Hotels from './components/Home/Hotels';
import SearchCard from './components/Home/Hotels/SearchCard';
import Login from './components/Login';
import SearchResults from './components/SearchResults/SearchResults';
import SearchResult from './components/SearchResults/SearchResult';
import HotelDetail from './components/HotelDetail';

function App() {
  return (
    <Router>
      <div className='app_container'>
        <Routes>
          {/*<Route path="/" element={<Redirect to="/home" />} />*/}

          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/search-results" element={<SearchResults />} />
          <Route path="/hotel-detail/:id" element={<HotelDetail />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;

{/*

    <Router>
      <Switch>
        <Route exact path="/">
          <Redirect to="/home" />
        </Route>

        <Route path="/home" component={Home} />
      </Switch>
    </Router>
*/}


{/*
    <div className='app_container'>
      {/*<Home/> *}
      <Title/>
      <SearchBar/>
      <Hotels/>
      <SearchCard/>
    </div >
*/}

{/*
    <div>
      <Login/>
    </div>
 */}