import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from "prop-types";
import Paper from '@material-ui/core/Paper';
import Modal from '@material-ui/core/Modal';
import IconButton from '@material-ui/core/IconButton';
import ClearIcon from '@material-ui/icons/Clear';
import { Grid } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
    paper: {
      position: 'absolute',
      width: 400,
      backgroundColor: theme.palette.background.paper,
      border: '2px solid #000',
      boxShadow: theme.shadows[5],
      padding: theme.spacing(2, 4, 3),
      top: '50%',
      left: '50%',
      transform: 'translate(-50%, -50%)'
    }
})); 


  export default function MyModal(props){
    const classes = useStyles();
    //Hooks
    const {open, handleClose, title, form} = props;


    return (
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="simple-modal-title"
            aria-describedby="simple-modal-description"
            >
            <Paper className = {classes.paper}>
                <Grid container >
                    <Grid item xs={12} sm={10}><h3>{title}</h3></Grid>
                    <Grid item xs={12} sm={2}> 
                            <IconButton 
                                aria-label="Close Modal"
                                aria-labelledby="close-modal"
                                style={{float: 'right'}}
                                onClick={handleClose}
                            >
                                <ClearIcon fontSize='small'/>
                            </IconButton>
                    </Grid>
                    <Grid item xs={12}>
                            {form}
                    </Grid>
                </Grid>
            </Paper>
        </Modal>
    );
}

MyModal.defaultProps = {
    open: false,
    title: ""
};

MyModal.propTypes = {
    open: PropTypes.bool,
    handleClose: PropTypes.func,
    title: PropTypes.string,
    form: PropTypes.object
};