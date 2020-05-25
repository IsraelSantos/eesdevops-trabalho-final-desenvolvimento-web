import React, {useState, useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from "prop-types";
import Paper from '@material-ui/core/Paper';
import Modal from '@material-ui/core/Modal';
import { Grid, Button, Select } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
    button: {
        background: '#00f',
        color: '#fff',
        float: 'right'
    },
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
    },
})); 

/* const MyButton = styled(Button)({
    background: '#00f',
    color: '#fff',
    float: 'right'
});

const MyPaper = styled(Button)`
  ${({ theme }) => `
  position: absolute;
  width: 400;
  background-color: ${theme.palette.background.paper};
  border: 2px solid #000;
  box-shadow: ${theme.shadows[5]};
  padding: ${theme.spacing(2, 4, 3)};
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  `}
`; */

/*
const MyPaper = styled(Paper)({
    position: 'absolute',
    width: 400,
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)'
});
*/


  export default function ModalNewMovies(props){
    const classes = useStyles();
    //Hooks
    const {open, values, handleClose, isEditar} = props;
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

        let tmpValues = sValues;
        tmpValues[name]=value;

        setSValues(tmpValues);

    }

    const handleSubmit = (event) => {

    }

    return (
        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="simple-modal-title"
            aria-describedby="simple-modal-description"
            >
            <Paper className = {classes.paper}>
                <form onSubmit={handleSubmit}>
                    <Grid container spacing={0} >
                        <Grid item xs={12}><h3>{(isEditar)?'Edição de filme':'Cadastro de filme'}</h3></Grid>
                        <Grid item xs={12}>
                            <label>
                                <Grid container spacing={1} >
                                    <Grid item xs={12} sm={2} > Nome: </Grid>
                                    <Grid item xs={12} sm={10} ><input type="text" value={sValues.name} onChange={handleChange} /> </Grid>
                                    <Grid item xs={12} > {(error.name)?(<p>{error.name}</p>):null} </Grid>
                                </Grid>
                            </label>
                        </Grid>
                        <Grid item xs={12}>
                            <label>
                                <Grid container spacing={1} >
                                <Grid item xs={12} sm={3} > Descrição: </Grid>
                                <Grid item xs={12} sm={9} ><input type="text" value={sValues.direction} onChange={handleChange} /> </Grid>
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
                                    >
                                        <option  value='Romance'>Romance</option>
                                        <option  value='Aventura'>Aventura</option>
                                        <option value='Ficção Científica'>Ficção científica</option>
                                        <option  value='Ficção e fantasia'>Ficção e fantasia</option>
                                        <option  value='Drama'>Drama</option>
                                    </Select>
                                </Grid>
                                <Grid item xs={12} > {(error.genre)?(<p>{error.genre}</p>):null} </Grid>
                                </Grid>
                            </label>
                        </Grid>
                        <Grid item xs={12} > <Button className = {classes.button} type="submit" >Salvar</Button> </Grid>
                    </Grid>
                </form>
            </Paper>
        </Modal>
    );
}

ModalNewMovies.defaultProps = {
    open: false,
    values: {
        id: -1,
        name: null,
        direction: null,
        genre: null,
        cast: null,
        synopsis: null,
        averageEvaluation: null,
        releaseYear: null,
        producer: null
    },
    isEditar: false
};

ModalNewMovies.propTypes = {
    open: PropTypes.bool,
    values: PropTypes.object,
    handleClose: PropTypes.func,
    isEditar: PropTypes.bool
};