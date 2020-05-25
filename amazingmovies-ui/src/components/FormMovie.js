import React, {useState, useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from "prop-types";
import { Grid, Button, Select } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
    button: {
        background: '#00f',
        color: '#fff',
        float: 'right'
    }
})); 


  export default function FormCadastro(props){
    const classes = useStyles();
    //Hooks
    const {values} = props;
    const [sValues, setSValues] = useState(values);
    const [error, setError] = useState({});

    useEffect(()=>{

    }, []);


    //handlers
    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;

        switch (name) {
            case 'name':
              break;
            case 'direction':
                break;
            case 'genre':
              break;
            case 'cast':
              break;
            case 'synopsis':
                break;
            case 'releaseYear':
                break;
            case 'producer':
                break;
            default:
              
        }

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

        tmpValues[name]=value;

        setSValues(tmpValues);

    }

    const handleSubmit = (event) => {
        //See isChange before start
    }

    return (
            <form onSubmit={handleSubmit}>
                <Grid container >
                        <Grid item xs={12}>
                            <label>
                                <Grid container spacing={1} >
                                    <Grid item xs={12} sm={2} > Nome: </Grid>
                                    <Grid item xs={12} sm={10} ><input name='name' type="text" value={sValues.name} onChange={handleChange} /> </Grid>
                                    <Grid item xs={12} > {(error.name)?(<p>{error.name}</p>):null} </Grid>
                                </Grid>
                            </label>
                        </Grid>
                        <Grid item xs={12}>
                            <label>
                                <Grid container spacing={1} >
                                <Grid item xs={12} sm={3} > Direção: </Grid>
                                <Grid item xs={12} sm={9} ><input name='direction' type="text" value={sValues.direction} onChange={handleChange} /> </Grid>
                                <Grid item xs={12} > {(error.direction)?(<p>{error.direction}</p>):null} </Grid>
                                </Grid>
                            </label>
                        </Grid>
                        <Grid item xs={12}>
                            <label>
                                <Grid container spacing={1} >
                                <Grid item xs={12} sm={3} > Gênero:</Grid>
                                <Grid item xs={12} sm={9} >
                                    <Select 
                                        value={sValues.genre}
                                        onChange={handleChange}
                                        name='genre'
                                    >
                                        <option  value={'Romance'}>Romance</option>
                                        <option  value={'Aventura'}>Aventura</option>
                                        <option value={'Ficção Científica'}>Ficção científica</option>
                                        <option  value={'Ficção e fantasia'}>Ficção e fantasia</option>
                                        <option  value={'Drama'}>Drama</option>
                                    </Select>
                                </Grid>
                                <Grid item xs={12} > {(error.genre)?(<p>{error.genre}</p>):null} </Grid>
                                </Grid>
                            </label>
                        </Grid>
                        <Grid item xs={12} > <Button className = {classes.button} type="submit" >Salvar</Button> </Grid>
                </Grid>
            </form>
    );
}

FormCadastro.defaultProps = {
    isChange: false,
    values: {
        id: -1,
        name: null,
        direction: null,
        genre: 'Romance',
        cast: null,
        synopsis: null,
        releaseYear: null,
        producer: null
    }
};

FormCadastro.propTypes = {
    isChange: PropTypes.bool,
    values: PropTypes.object
};