import React from 'react';
import { makeStyles} from '@material-ui/core/styles';
import PropTypes from "prop-types";
import Snackbar from '@material-ui/core/Snackbar';
import OrigAlert from '@material-ui/lab/Alert';

function Alert(props) {
  return <OrigAlert elevation={6} variant="filled" {...props} />;
}

const useStyles = makeStyles((theme) => ({
    snackbar: {
      zIndex: theme.zIndex.drawer + 1
    },
    alert: {
        float: "right"
    }
  }));


  export default function Message(props){
    const classes = useStyles();

    return (
        <Snackbar open={props.open} autoHideDuration={props.wait} className={classes.snackbar} onClose={props.handler}>
            <Alert severity={props.type} className={classes.alert} onClose={props.handler}>
                {props.message}
            </Alert>
        </Snackbar>
    );
}

Message.defaultProps = {
    wait: 3000,
    open: false,
    type: "success",
    message: "Sucesso!"
};

Message.propTypes = {
    wait: PropTypes.number,
    open: PropTypes.bool,
    type: PropTypes.oneOf([
      "success",
      "error",
      "warning",
      "info"
    ]),
    message: PropTypes.string,
    handler: PropTypes.func
};