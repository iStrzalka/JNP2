import logo from '../../images/szybkie_logo.png'
import { Button, Grid } from '@material-ui/core';
import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import ContactPopUp from './ContactPopUp'
import AboutPopUp from './AboutPopUp'
import NewAdvertForm from './NewAdvertForm'


const useStyles = makeStyles(
    {
      img: {
          width: 'auto',
          height: 'auto',
          maxWidth: '90%',
      },
      button: {
          margin: 'auto',
          width: '100%',
      },
      header: {
        minHeight: '200px',
        height: 'auto',
      },
    }
);


const Header = (props) => { 
    const classes = useStyles();
    const [openOnas, setOpenOnas] = React.useState(false);
    const [openKontakt, setOpenKontakt] = React.useState(false);

    const onas_text = [<h1 key={1}>Szybkie.pl</h1>, "Nasza firma jest wiodącą marką na rynku motoryzacji"]
    const kontakt_text = [<h1 key={2}>Szybkie.pl</h1>, "Skontaktuj się z nami"]


    return (
        <Grid container className={classes.header} spacing={3}>
            <Grid item xs={4}>
                <img className={classes.img} src={logo} alt='logo'/>
            </Grid>
            <Grid container item xs={8} spacing={3} alignItems='center'>
                <Grid item xs={4}>
                    <NewAdvertForm
                    open={props.newAdvertOpen}
                    setOpen={props.setNewAdvertOpen}
                    state={props.newAdvertState}
                    setState={props.setNewAdvertState}
                    requestNewAdvert={props.requestNewAdvert}
                    classes={classes}
                    />
                </Grid>
                <Grid item xs={4}>
                    <Button className={classes.button} variant="contained" color="primary" onClick={() => setOpenOnas(true)}>
                        O nas
                    </Button>
                    <AboutPopUp open={openOnas} onClose={() => setOpenOnas(false)} text={onas_text} popup_id="o-nas" />
                </Grid>
                <Grid item xs={4}>
                    <Button className={classes.button} variant="contained" color="primary" onClick={() => setOpenKontakt(true)}>
                        Kontakt
                    </Button>
                    <ContactPopUp open={openKontakt} onClose={() => setOpenKontakt(false)} text={kontakt_text} popup_id="kontakt" />
                </Grid>
            </Grid>
        </Grid>
            
    )
}

export default Header

