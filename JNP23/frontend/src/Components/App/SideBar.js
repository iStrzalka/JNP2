import { Container, Slider, Typography, Button} from '@material-ui/core';
import React from 'react';


function to_km(value) {
  return `${value} km`;
}
function to_zl(value) {
  return `${value*100} zł`;
}
function to_stars(value) {
  return `${value/20} ★`;
}
function to_engine_capacity(value){
  return `${value*100} cm3 `;
}
const marks_distance = [
  {
    value: 0,
    label: '0 km'
  },
  {
    value: 100,
    label: '100 km'
  }
]
const marks_price = [
  {
    value: 0,
    label: '0 zł'
  },
  {
    value: 100,
    label: '10000 zł'
  }
]
const marks_rating = [
  {
    value: 0,
    label: '0 ★'
  },
  {
    value: 100,
    label: '5 ★'
  }
]
const marks_engine_capacity = [
  {
    value: 0,
    label: '0 cm3'
  },
  {
    value: 100,
    label: '10000 cm3'
  }
]

const SideBar = (props) => { 

  return (
    <Container className="App">
      <Typography>
        Odległość
      </Typography>
      <Slider
          defaultValue={20}
          valueLabelFormat={to_km}
          aria-labelledby="distance"
          step={1}
          valueLabelDisplay="auto"
          marks={marks_distance}
          onChangeCommitted={ (e, value) => props.setState({
            'distance': value,
            'price': props.state['price'],
            'rating': props.state['rating'],
            'engine_capacity': props.state['engine_capacity'],
          })}
      />
      <Typography id="price" gutterBottom>
      Cena
      </Typography>
      <Slider
          defaultValue={[0, 90]}
          valueLabelFormat={to_zl}
          aria-labelledby="price"
          step={1}
          valueLabelDisplay="auto"
          marks={marks_price}
          onChangeCommitted={ (e, value) => props.setState({
            'distance': props.state['distance'],
            'price': value,
            'rating': props.state['rating'],
            'engine_capacity': props.state['engine_capacity'],
          })}
      />
      <Typography id = "rating" gutterBottom>
      Ocena
      </Typography>
      <Slider
          defaultValue={[0, 100]}
          valueLabelFormat={to_stars}
          aria-labelledby="rating"
          step={2}
          valueLabelDisplay="auto"
          marks={marks_rating}
          onChangeCommitted={ (e, value) => props.setState({
            'distance': props.state['distance'],
            'price': props.state['price'],
            'rating': value,
            'engine_capacity': props.state['engine_capacity'],
          })}
      />
      <Typography id = "engine_capacity" gutterBottom>Rozmiar silnika</Typography>
      <Slider
          defaultValue={[10, 90]}
          valueLabelFormat={to_engine_capacity}
          aria-labelledby="engine_capacity"
          step={1}
          valueLabelDisplay="auto"
          marks={marks_engine_capacity}
          onChangeCommitted={ (e, value) => props.setState({
            'distance': props.state['distance'],
            'price': props.state['price'],
            'rating': props.state['rating'],
            'engine_capacity': value,
          })}
      />
      <Button
          onClick={props.onClickRefresh}>
      szukaj
      </Button>
    </Container>
  );
}



export default SideBar