import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from "prop-types";
import { Grid, Button } from '@material-ui/core';
import Rating from '@material-ui/lab/Rating';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Typography from '@material-ui/core/Typography';
import evaluationApi from '../api/evaluationApi';
import Loading from './Loading';

const useStyles = makeStyles((theme) => ({
    root: {
        maxWidth: "100%",
    },
    evaluationCell: {
        padding: '5px'
    },
    gridItem: {
        alignItems: 'center',
        justifyContent: 'center',
        textAlign: 'center'
    },
    button: {
        background: '#00f',
        color: '#fff',
        alignItems: 'center'
    },

})); 



export default function FormToEvaluateMovie(props){
    const classes = useStyles();
    const {values, handleClose, outMessage} = props;
    const [evaluation, setEvaluation] = useState("0.0");
    const [isLoading, setIsLoading] = useState(false);

    //Api

    const saveEvaluation = () => {
        setIsLoading(true);
        const res = {
            value: evaluation,
            idMovie: values.id
        };
    
        const onSucces = (response) => {
          setIsLoading(false);
          handleClose();
          outMessage({ 
            show: true,
            type: 'success',
            value: 'Filme avaliado com sucesso!'
          });
        }
    
        const onError = (error) => {
          console.error(error);
          handleClose();
          setIsLoading(false);
          outMessage({ 
            show: true,
            type: 'error',
            value: 'Não foi possível avaliar o filme!'
          });
        }
    
        evaluationApi.saveEvaluation(res, onSucces, onError);
    }



    //handlers
    const handleEvaluation = (event) => {
        const value = event.target.value;
        setEvaluation(value);
    }

    return (
        <div>
            <Card className={classes.root}>
                <CardHeader
                    title={values.name}
                    subheader={<div>
                                    {`Ano de lancamento: ${values.releaseYear}`}<br/>
                                    {`Elenco: ${values.cast}`}<br/>
                                    {`Direção: ${values.direction}`}
                                </div>}
                />
                <CardContent>
                    <Typography variant="body2" color="textSecondary" component="p">
                    {(values.synopsis.length < 500)? `Sinopse: ${values.synopsis}` : `Sinopse: ${values.synopsis.substring(0,499)}...`}
                    </Typography>
                </CardContent>
                <CardActions disableSpacing>
                        <Grid container spacing={0}>
                            <Grid item xs={12} className={classes.evaluationCell}>
                                <Card>
                                    <CardActions disableSpacing>
                                        <Grid container spacing={2}>
                                            <Grid item xs={12} className={classes.gridItem}>
                                                <h3>Avalie o filme</h3>
                                            </Grid>
                                            <Grid item xs={12} className={classes.gridItem}>
                                                <Rating
                                                    name='evaluation'
                                                    values={evaluation}
                                                    onChange={handleEvaluation}
                                                    precision={0.2}
                                                />
                                            </Grid>
                                            <Grid item xs={12} className={classes.gridItem}>
                                                <Button className={classes.button} onClick={saveEvaluation}>Avaliar</Button>
                                            </Grid>
                                        </Grid>
                                    </CardActions>
                                </Card>
                            </Grid>
                        </Grid>
                </CardActions>
            </Card>
            <Loading open={isLoading}/>
        </div>
    );
}

FormToEvaluateMovie.defaultProps = {
    values: {
        id: -1,
        name: '',
        direction: '',
        genre: '',
        cast: '',
        synopsis: '',
        releaseYear: '',
        producer: ''
    }
}

FormToEvaluateMovie.propTypes = {
    values: PropTypes.object,
    handleClose: PropTypes.func,
    outMessage: PropTypes.func
}