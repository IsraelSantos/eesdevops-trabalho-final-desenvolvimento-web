import React, {useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from "prop-types";
import { Grid, Button, Select, InputLabel, TextField } from '@material-ui/core';
import movieApi from '../api/movieApi';
import OrigAlert from '@material-ui/lab/Alert';
import Loading from './Loading';

function Alert(props) {
  return <OrigAlert elevation={6} variant="filled" {...props} />;
}

const useStyles = makeStyles((theme) => ({
    button: {
        background: '#00f',
        color: '#fff',
        float: 'right'
    },
    error:{
        color: '#f00',
        fontSize: '0.7em'
    }
})); 


export default function FormCadastro(props){
    const classes = useStyles();
    //Hooks
    const {values, isChange, handleClose, outMessage} = props;
    const [sValues, setSValues] = useState(values);
    const [error, setError] = useState({});
    const [message, setMessage] = useState({});
    const [isLoading, setIsLoading] = useState(false);
    const averageEvaluation = useState((sValues.averageEvaluation+"").substring(0,4));

    //handlers
    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setError({});
        clearMessage();

        let tmpValues = cloneValues();

        tmpValues[name]=value;

        setSValues(tmpValues);

    }

    const cloneValues = () => {
        let tmpValues = {
            id: sValues.id,
            name: sValues.name,
            direction: sValues.direction,
            genre: sValues.genre,
            cast: sValues.cast,
            synopsis: sValues.synopsis,
            releaseYear: sValues.releaseYear,
            producer: sValues.producer
        };
        return tmpValues;
    }
// Api
    const saveMovie = () => {
        setIsLoading(true);
        const onSucces = (response) => {
          setIsLoading(false);
          handleClose();
          const messageA = `Filme ${(isChange)? 'editado': 'salvo'} com sucesso!`;
          outMessage(
            { 
                show: true,
                type: 'success',
                value: messageA
            }
          );
        }
    
        const onError = (error) => {
          setIsLoading(false);
          console.error(error);
          //console.log(JSON.stringify(error))
          const messageA = (error.message === "Request failed with status code 409")? 
                            'Existe outro livro com o mesmo nome cadastrado':
                            `Não foi possível ${(isChange)? 'editar': 'salvar'} o filme!`;
          setMessage({ 
            show: true,
            type: 'error',
            value: messageA
          });
        }
        if (isChange === true){
            const id = sValues.id;
            const tmp = cloneValues();
            delete tmp['id'];
            movieApi.changeMovie(id, tmp, onSucces, onError);
        }else{
            const tmp = cloneValues();
            delete tmp['id'];
            movieApi.saveMovie(tmp, onSucces, onError);
        }
    };
//behavior

    const clearMessage = () => {
        setMessage(
            {
                show: false,
                value: '',
                type: message.type
            }
        );
    }

    const validateForm = () => {

        let tmpV = sValues.name;
        if (tmpV === null || tmpV === undefined || tmpV === ''){ setError({name: 'Nome não pode ser vazio'}); return false;}

        tmpV = sValues.direction;
        if (tmpV === null || tmpV === undefined || tmpV === ''){ setError({direction: 'Direção não pode ser vazia'}); return false;}

        tmpV = sValues.genre;
        if (tmpV === null || tmpV === undefined || tmpV === '' || tmpV === 'n'){ setError({genre: 'Gênero não pode ser vazio'}); return false;}

        tmpV = sValues.cast;
        if (tmpV === null || tmpV === undefined || tmpV === ''){ setError({cast: 'Elenco não pode ser vazio'}); return false;}

        tmpV = sValues.synopsis;
        if (tmpV === null || tmpV === undefined || tmpV === ''){ setError({synopsis: 'Sinopse não pode ser vazia'}); return false;}

        tmpV = sValues.releaseYear;
        if (tmpV === null || tmpV === undefined || tmpV === '' || tmpV === 0){ setError({releaseYear: 'Ano de lançamento não pode ser vazio'}); return false;}

        tmpV = sValues.producer;
        if (tmpV === null || tmpV === undefined || tmpV === ''){ setError({producer: 'Produtora não pode ser vazia'}); return false;}

        return true;
    }

    const handleSubmit = () => {
        const result = validateForm();
        //See isChange before start
        if (result){
            saveMovie();
        }
    }

    return (
        <div>
            <form>
                <Grid container spacing={1}>
                        <Grid item xs={12} sm={4} >
                            <InputLabel htmlFor = 'name-id' > 
                                Nome*: 
                            </InputLabel>
                        </Grid>
                        <Grid item xs={12} sm={8} ><TextField required inputProps={{id: 'name-id'}} name='name' type="text" value={sValues.name} onChange={handleChange} /> </Grid>
                        <Grid item xs={12} sm={4}/>
                        <Grid item xs={12} sm={8}> {(error.name)?(<p className={classes.error}>{error.name}</p>):null} </Grid>
                        
                        <Grid item xs={12} sm={4} >
                            <InputLabel htmlFor = 'direction-id' > 
                                Direção*: 
                            </InputLabel>
                        </Grid>
                        <Grid item xs={12} sm={8} ><TextField required inputProps={{id: 'direction-id'}} name='direction' type="text" value={sValues.direction} onChange={handleChange} /> </Grid>
                        <Grid item xs={12} sm={4}/>
                        <Grid item xs={12} sm={8}> {(error.direction)?(<p className={classes.error}>{error.direction}</p>):null} </Grid>
                        
                        <Grid item xs={12} sm={4} >
                            <InputLabel htmlFor = 'genre-id' > 
                                Gênero*: 
                            </InputLabel>
                        </Grid>
                        <Grid item xs={12} sm={8} >
                            <Select 
                                required
                                value={sValues.genre}
                                onChange={handleChange}
                                name='genre'
                                inputProps={{id: 'genre-id'}}
                            >
                                <option defaultValue value={'n'}>Selecione</option>
                                <option value={'Romance'}>Romance</option>
                                <option value={'Aventura'}>Aventura</option>
                                <option value={'Ficção Científica'}>Ficção científica</option>
                                <option value={'Ficção e fantasia'}>Ficção e fantasia</option>
                                <option value={'Drama'}>Drama</option>
                            </Select>
                        </Grid>
                        <Grid item xs={12} sm={4}/>
                        <Grid item xs={12} sm={8}> {(error.genre)?(<p className={classes.error}>{error.genre}</p>):null} </Grid>
                        
                        <Grid item xs={12} sm={4} >
                            <InputLabel htmlFor = 'cast-id' > 
                                Elenco*: 
                            </InputLabel>
                        </Grid>
                        <Grid item xs={12} sm={8} ><TextField required inputProps={{id: 'cast-id'}}  name='cast' type="text" value={sValues.cast} onChange={handleChange} /> </Grid>
                        <Grid item xs={12} sm={4}/>
                        <Grid item xs={12} sm={8}> {(error.cast)?(<p className={classes.error}>{error.cast}</p>):null} </Grid>
                        
                        <Grid item xs={12} sm={4} >
                            <InputLabel htmlFor = 'releaseYear-id' > 
                                Ano de lançamento*: 
                            </InputLabel>
                        </Grid>
                        <Grid item xs={12} sm={8} ><TextField required type="number" inputProps={{id: 'releaseYear-id'}}  name='releaseYear' value={sValues.releaseYear} onChange={handleChange} /> </Grid>
                        <Grid item xs={12} sm={4}/>
                        <Grid item xs={12} sm={8}> {(error.releaseYear)?(<p className={classes.error}>{error.releaseYear}</p>):null} </Grid>
                        
                        <Grid item xs={12} sm={4} >
                            <InputLabel htmlFor = 'producer-id' > 
                                Produtora*: 
                            </InputLabel>
                        </Grid>
                        <Grid item xs={12} sm={8} ><TextField required inputProps={{id: 'producer-id'}}  name='producer' type="text" value={sValues.producer} onChange={handleChange} /> </Grid>
                        <Grid item xs={12} sm={4}/>
                        <Grid item xs={12} sm={8}> {(error.producer)?(<p className={classes.error}>{error.producer}</p>):null} </Grid>                        
                        
                        {(sValues.id !== undefined && sValues.id > 0) ? (
                        <Grid item xs={12} >
                            <Grid container spacing={0}>
                                <Grid item xs={12} >
                                    <InputLabel htmlFor = 'synopsis-id' >
                                        Média de avaliações (0-5):
                                    </InputLabel>
                                </Grid>
                                <Grid item xs={12} > {averageEvaluation} </Grid>
                            </Grid>
                        </Grid>) : null}

                        <Grid item xs={12} >
                            <InputLabel htmlFor = 'synopsis-id' >
                                Sinopse*:
                            </InputLabel>
                        </Grid>
                        <Grid item xs={12} ><textarea required style={{width: '100%', height: '10em', borderBlockColor: '#000'}} name='synopsis' type="text" value={sValues.synopsis} onChange={handleChange} /> </Grid>
                        <Grid item xs={12} > {(error.synopsis)?(<p className={classes.error}>{error.synopsis}</p>):null} </Grid>
                            
                        <Grid item xs={12} > <Button className = {classes.button} onClick={handleSubmit} >Salvar</Button> </Grid>
                        <Grid item xs={12} >
                            {(message.show)?            
                                (<Alert severity={message.type}>
                                    {message.value}
                                </Alert>)
                            :null}
                        </Grid>
                </Grid>
            </form>
            <Loading open={isLoading}/>
        </div>
    );
}

FormCadastro.defaultProps = {
    isChange: false,
    values: {
        id: -1,
        name: '',
        direction: '',
        genre: 'n',
        cast: '',
        synopsis: '',
        releaseYear: '',
        producer: '',
        averageEvaluation: 0.0
    }
};

FormCadastro.propTypes = {
    isChange: PropTypes.bool,
    values: PropTypes.object,
    handleClose: PropTypes.func,
    outMessage: PropTypes.func
};