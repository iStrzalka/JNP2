import  { Dialog, Button, TextField, DialogActions, DialogContent,
          Grid, DialogContentText, DialogTitle, Box } from '@material-ui/core';
import { React } from 'react';
// Icons





const NewAdvertForm = (props) => {

    const handleClickOpen = () => {
        props.setOpen(true);
    };
    const handleClose = () => {
        props.setOpen(false);
        // console.log(props.state)
    };
    const validateCorrectness = () => {
        if  (
            props.state['price'] <= 0 ||
            props.state['car_model_id'] < 0 ||
            props.state['dealership_id'] < 0
        ) return false;
        return true;
    }
    const handleCloseConfirm = () => {
        if (validateCorrectness()){
            handleClose();
            props.requestNewAdvert();
        }
    }

    return (
        <Box>
            <Button className={props.classes.button} variant="contained" color="primary" onClick={handleClickOpen} >
                    Dodaj ogłoszenie 
            </Button>
            <Dialog open={props.open} onClose={handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Dodaj Ogłoszenie</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Wypełnij poniższe pola, aby dodać ogłoszenie
                    </DialogContentText>
                    <Grid container spacing={2}>
                        <Grid item xs={4}>
                            <TextField
                                autoFocus
                                required
                                margin="dense"
                                id="price"
                                label="Cena"
                                type="number"
                                variant="outlined"
                                onChangeCapture={ (e) => props.setState({
                                        'price': e.target.value,
                                        'car_model_id': props.state['car_model_id'],
                                        'dealership_id': props.state['dealership_id'],
                                        'description': props.state['description'],
                                })}
                            />
                        </Grid>
                        <Grid item xs={4}>
                            <TextField
                                required
                                margin="dense"
                                id="car_model_id"
                                label="ID samochodu"
                                type="number"
                                variant="outlined"
                                onChangeCapture={ (e) => props.setState({
                                    'price': props.state['price'],
                                    'car_model_id': e.target.value,
                                    'dealership_id': props.state['dealership_id'],
                                    'description': props.state['description'],
                                })}
                            />
                        </Grid>
                        <Grid item xs={4}>
                            <TextField
                                required
                                margin="dense"
                                id="dealership_id"
                                label="ID wypozyczalni"
                                type="number"
                                variant="outlined"
                                onChangeCapture={ (e) => props.setState({
                                    'price': props.state['price'],
                                    'car_model_id': props.state['car_model_id'],
                                    'dealership_id': e.target.value,
                                    'description': props.state['description'],
                                })}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                margin="dense"
                                id="description"
                                label="Opis"
                                type="text"
                                variant="outlined"
                                fullWidth
                                multiline
                                rows={12}
                                rowsMax={24}
                                onChangeCapture={ (e) => props.setState({
                                    'price': props.state['price'],
                                    'car_model_id': props.state['car_model_id'],
                                    'dealership_id': props.state['dealership_id'],
                                    'description': e.target.value,
                                })}
                            />
                        </Grid>
                    </Grid>
                </DialogContent>
                <DialogActions>
                <Button onClick={handleClose} color="secondary">
                    Anuluj
                </Button>
                <Button onClick={handleCloseConfirm} color="primary">
                    Dodaj
                </Button>
                </DialogActions>
            </Dialog>
        </Box>
    );
};

export default NewAdvertForm