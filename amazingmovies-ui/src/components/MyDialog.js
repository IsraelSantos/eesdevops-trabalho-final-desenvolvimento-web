import React from 'react';
import PropTypes from "prop-types";
import { makeStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles((theme) => ({
    button: {
        background: '#00f',
        color: '#fff',
        float: 'right'
    }
})); 

  export default function MyDialog(props){
    const classes = useStyles();
    const { open, title, handleAgree, handleClose, message, messageAgree, messageDisagree } = props;

    return (
        <Dialog
            open={open}
            onClose={handleClose}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
            >
            <DialogTitle id="alert-dialog-title">{title}</DialogTitle>
            <DialogContent>
            <DialogContentText id="alert-dialog-description">
                {message}
            </DialogContentText>
            </DialogContent>
            <DialogActions>
            <Button className={classes.button} onClick={handleClose}>
                {messageDisagree}
            </Button>
            <Button className={classes.button} onClick={handleAgree} color="primary" autoFocus>
                {messageAgree}
            </Button>
            </DialogActions>
        </Dialog>
    );
}

MyDialog.defaultProps = {
    open: false,
};

MyDialog.propTypes = {
    open: PropTypes.bool,
    title: PropTypes.string,
    message: PropTypes.string,
    messageAgree: PropTypes.string,
    messageDisagree: PropTypes.string,
    handleClose: PropTypes.func,
    handleAgree: PropTypes.func
};