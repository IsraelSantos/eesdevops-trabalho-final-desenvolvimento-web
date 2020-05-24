import React from 'react';
import { makeStyles} from '@material-ui/core/styles';
import PropTypes from "prop-types";
import Backdrop from '@material-ui/core/Backdrop';
import CircularProgress from '@material-ui/core/CircularProgress';

const useStyles = makeStyles((theme) => ({
    backdrop: {
      zIndex: theme.zIndex.drawer + 1,
      color: '#fff',
    },
  }));


export default function Loading(props){
    const classes = useStyles();

    return (
        <Backdrop open={props.open} className = {classes.backdrop}>
            <CircularProgress color="inherit" />
        </Backdrop>
    );
}

Loading.propTypes = {
    open: PropTypes.bool
};