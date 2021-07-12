import DialogTitle from '@material-ui/core/DialogTitle';
import Dialog from '@material-ui/core/Dialog';


function AboutPopUp(props) {
    const { onClose, open, text, popup_id } = props;
    
    const handleClose = () => {
        onClose();
    };
    
    return (
        <Dialog onClose={handleClose} aria-labelledby={popup_id} open={open}>
        <DialogTitle id={popup_id}>
            {text}
        </DialogTitle>
        </Dialog>
    );
}

export default AboutPopUp