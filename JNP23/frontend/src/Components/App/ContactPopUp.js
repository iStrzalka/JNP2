import DialogTitle from '@material-ui/core/DialogTitle';
import Dialog from '@material-ui/core/Dialog';
import LocationCityIcon from '@material-ui/icons/LocationCity';
import PhoneIcon from '@material-ui/icons/Phone';
import {Grid} from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
    popup: {
        marginLeft: '4%',
        marginBottom: '5%',
        minWidth: '600px',
        minHeight: '250px',
        width: 'auto',
        height: 'auto',
    },

}));


function ContactPopUp(props) {
    const { onClose, open, text, popup_id } = props;
    const classes = useStyles();

    const handleClose = () => {
        onClose();
    };
    
    return (
        <Dialog onClose={handleClose} aria-labelledby={popup_id} open={open}>
        <DialogTitle id={popup_id}> {text} </DialogTitle>

        <Grid container className={classes.popup} spacing={1}>
            <Grid item xs={1}><LocationCityIcon/></Grid>
            <Grid item xs={3}>Warszawa</Grid>
            <Grid item xs={1}><PhoneIcon/></Grid>
            <Grid item xs={3}>998 997 999</Grid>
        </Grid>
        
        </Dialog>
    );
}

export default ContactPopUp