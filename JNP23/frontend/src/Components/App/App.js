import './App.css';
import React, { useState, useEffect } from 'react';
import { Grid, Container } from '@material-ui/core';
import axios from 'axios';

// Components
import Header from './Header'
import SideBar from './SideBar'
import ResultList from './ResultList'


function App(){

  const [sidebarState, setSidebarState] = useState({
    'distance': 20,
    'price': [0, 90],
    'rating': [0, 100],
    'engine_capacity': [10, 90],
  });

  const [newAdvertOpen, setNewAdvertOpen] = useState(false);
  const [newAdvertState, setNewAdvertState] = useState({
    'price': '-1',
    'description': '',
    'car_model_id': '-1',
    'dealership_id': '-1',
  });


  const onClickSearch = (sidebar_state) => {
    const params = {
      distance: sidebar_state.distance,
      min_engine_capacity: sidebar_state.engine_capacity[0],
      max_engine_capacity: sidebar_state.engine_capacity[1],
      min_price: sidebar_state.price[0],
      max_price: sidebar_state.price[1],
      min_rating: sidebar_state.rating[0],
      max_rating: sidebar_state.rating[1],
    };

    axios.get('http://localhost:8080/advert', {params})
        .then(response => setAdverts(response.data));
       console.log(adverts);
       
  };


  const requestNewAdvertHandler = () => {
    console.log(newAdvertState);


    axios({
      method: 'post',
      url: 'http://localhost:8080/create_advert',
      data: newAdvertState
    });
  };
  
  const [adverts, setAdverts] = useState([]);
  
  const [initiallyLoaded, setInitiallyLoaded] = useState(false);
  
  useEffect(() => {
    const params = {
      id: -1,
      distance: -1,
      min_engine_capacity: -1,
      max_engine_capacity: -1,
      min_price: -1,
      max_price: -1,
      min_rating: -1,
      max_rating: -1,
    };
  
    axios.get('http://localhost:8080/advert', {params})
      .then(response => setAdverts(response.data));

    setInitiallyLoaded(true);
  
  }, [initiallyLoaded]);


  return (
    <div>
      <Container>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Header
            newAdvertOpen={newAdvertOpen}
            setNewAdvertOpen={setNewAdvertOpen}
            newAdvertState={newAdvertState}
            setNewAdvertState={setNewAdvertState}
            requestNewAdvert={requestNewAdvertHandler}            
            />
          </Grid>
          <Grid item xs={4}>
            <SideBar
                state = {sidebarState}
                setState = {setSidebarState}
                onClickRefresh = {() => onClickSearch(sidebarState)} />
          </Grid>
          <Grid item xs={8}>
            <ResultList adverts={adverts}/>
          </Grid>
        </Grid>
      </Container>
      
    </div>

    
  );
}


export default App;