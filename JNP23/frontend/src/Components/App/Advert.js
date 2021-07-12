import  { Grid, Popover, Typography, Box } from '@material-ui/core';
import React from 'react';

// Icons
// import LocationOnIcon from '@material-ui/icons/LocationOn';
import LocationCityIcon from '@material-ui/icons/LocationCity';
import ExploreIcon from '@material-ui/icons/Explore';
import DirectionsCarIcon from '@material-ui/icons/DirectionsCar';
import PhoneIcon from '@material-ui/icons/Phone';
import LocalOfferIcon from '@material-ui/icons/LocalOffer';
import LocalGasStationIcon from '@material-ui/icons/LocalGasStation';
import { makeStyles } from '@material-ui/core/styles';
import car_image from '../../images/car.png'


const useStyles = makeStyles((theme) => ({
    root: {
      margin: 0
    },
    img: {
      margin: 'auto',
      display: 'block',
      maxWidth: '100%',
      maxHeight: '100%',
      position: 'relative',

    },
    advert: {
      marginBottom: '30px',
    },
    iconField: {
      width: "auto",
    },
    typography: {
      padding: theme.spacing(2),
      minWidth: '450px',
      minHeight: '200px',
      width: 'auto',
      height: 'auto',
    },
    box: {
      cursor: 'pointer',
    },
    popover: {
      pointerEvents: 'none',
    },
}));



function haversine_distance(mk1, mk2) {
  var R = 3958.8; // Radius of the Earth in miles
  var rlat1 = mk1.lat * (Math.PI/180); // Convert degrees to radians
  var rlat2 = mk2.lat * (Math.PI/180); // Convert degrees to radians
  var difflat = rlat2-rlat1; // Radian difference (latitudes)
  var difflon = (mk2.lng-mk1.lng) * (Math.PI/180); // Radian difference (longitudes)

  var d = 2 * R * Math.asin(Math.sqrt(Math.sin(difflat/2)*Math.sin(difflat/2)+Math.cos(rlat1)*Math.cos(rlat2)*Math.sin(difflon/2)*Math.sin(difflon/2)));
  return d;
}


const Advert = (props) => {

  const classes = useStyles();
  const current_loc_props = {
    lat: 52.23084, 
    lng: 21.00988,
  };

  const advert_loc_props = {
    lat: props.advert.dealership.location.coordX,
    lng: props.advert.dealership.location.coordY,
  };

  const [anchorEl, setAnchorEl] = React.useState(null);

  const handlePopoverOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handlePopoverClose = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);

  let distance_km = Math.round(haversine_distance(current_loc_props, advert_loc_props)) + ' km';

  return (
    // <Box onClick={handleClick} onMouseOver={e => {
    //   // style stage container:
    //   const container = e.target.getStage().container();
    //   container.style.cursor = "pointer"
    // }}>
    <Box 
    className={classes.box} 
    aria-owns={open ? 'mouse-over-popover' : undefined}
    aria-haspopup="true"
    onMouseEnter={handlePopoverOpen}
    onMouseLeave={handlePopoverClose}
    >
    <Grid container className={classes.advert} spacing={5}>
      <Grid xs={5} item>
        <img className={classes.img} alt='car_image' src={car_image}/>
      </Grid>
      <Grid xs={7} item container justify_content='flex-start' >
        <Grid item xs={1}><LocationCityIcon/></Grid>
        <Grid item xs={5}>{props.advert.dealership.location.city}</Grid>
        <Grid item xs={1}><PhoneIcon/></Grid>
        <Grid item xs={5}>{props.advert.dealership.phoneNo}</Grid>
        <Grid item xs={1}><ExploreIcon/></Grid>
        <Grid item xs={5}>{distance_km}</Grid>
        <Grid item xs={1}><LocalOfferIcon/></Grid>
        <Grid item xs={5}>{props.advert.price + ' zł/miesiąc'}</Grid>
        <Grid item xs={1}><LocalGasStationIcon/></Grid>
        <Grid item xs={5}>{props.advert.carModel.engine.size/1000 + ' l'}</Grid>
        <Grid item xs={1}><DirectionsCarIcon/></Grid>
        <Grid item xs={5}>{props.advert.carModel.engine.hp + ' hp'}</Grid>
      </Grid>
    </Grid>
      <Popover
        id="mouse-over-popover"
        className={classes.popover}
        classes={{
          paper: classes.paper,
        }}
        open={open}
        anchorEl={anchorEl}
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'left',
        }}
        transformOrigin={{
          vertical: 'top',
          horizontal: 'left',
        }}
        onClose={handlePopoverClose}
        disableRestoreFocus
      >
        <Typography className={classes.typography}>
          {props.advert.description}
        </Typography>
      </Popover>
    </Box>
  )
}

export default Advert