import React, {useState, useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import Rating from '@material-ui/lab/Rating';
import IconButton from '@material-ui/core/IconButton';
import Delete from '@material-ui/icons/Delete';
import CreateIcon from '@material-ui/icons/Create';
import StarIcon from '@material-ui/icons/Star';

import movieApi from '../api/movieApi';
import Loading from './Loading';
import Message from './Message';
import ModalNewMovie from './ModalNewMovie';
import { Grid, Button } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
    title: {
      float: 'left'
    },
    button: {
        background: '#00f',
        color: '#fff'
    },
    grid: {
        width: '80%',
        float: 'right'
    },
    tablecontainer: {
        maxHeight: 500
    },
    newbutton: {
        background: '#00f',
        color: '#fff',
        float: 'left'
    },
    controle:{
        float: 'right'
    },
}));

/*const MyButton = styled(Button)({
    background: '#00f',
    color: '#fff',
    float: 'right'
});*/


const columns = [
  { key: 'name', 
    label: 'Título', 
    minWidth: 170 },

  { key: 'genre', 
    label: 'Gênero', 
    minWidth: 170 },

  { key: 'averageEvaluation', 
    label: 'Média\u00a0de\u00a0avaliações', 
    minWidth: 170,
    align: 'right',
    format: (value) => value.toFixed(2)},

  { key: 'releaseYear', 
    label: 'Ano\u00a0de\u00a0lançamento', 
    align: 'right',
    minWidth: 170 },

  { key: 'buttons', 
    label: 'Controle', 
    minWidth: 170,
    align: 'center' }
];


export default function MovieTable(props){
    const classes = useStyles();
// Hooks
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(5);
    const [data, setData] = useState({
        content:[],
        pageable: {
          pageNumber: 0,
          pageSize: 5
        },
        totalPages: 1,
        totalElements: 1
    });
    const [isLoading, setIsLoading] = useState(false);
    const [message, setMessage] = useState({
        show: false,
        value: '',
        type: 'success'
    });
    const [isOpenModal, setIsOpenModal] = useState(false);

    const clearMessage = () => {
        setMessage(
            {
                show: false,
                value: '',
                type: 'success'
            }
        );
    }

    useEffect(() => {
        listMovies(page, rowsPerPage);
    }, [page, rowsPerPage]);

//Api
    const listMovies = (page, size) => {
        setIsLoading(true);
    
        const onSucces = (response) => {
          setIsLoading(false);
          setData(response.data);
        }
    
        const onError = (error) => {
          console.error(error);
          setIsLoading(false);
          setData({
                content:[],
                pageable: {
                pageNumber: 0,
                pageSize: 5
                },
                totalPages: 1,
                totalElements: 1
            });
          setMessage({ 
            show: true,
            type: 'error',
            value: 'Não foi possível ler a lista de filmes!'
          });
        }
    
        movieApi.listMovies(page, size, onSucces, onError);
    };
//behavior
    const changePageEvent = (event, newPage) => {
        //Chamar o back aqui
        listMovies(newPage, rowsPerPage);
        setPage(newPage);
    }

    const changeRowsPerPageEvent = (event) => {
        setPage(0);
        const tmpR = +event.target.value;
        setRowsPerPage(tmpR);
        listMovies(page, tmpR);
    }

    const openModalNewMovie = () => {
        setIsOpenModal(true);
    }

    return (
        <div>
            <Grid container spacing={2} className = {classes.grid}>
            <Grid item xs={12} >
                <h1 className={classes.title}>Cadastro de filmes e suas avaliações</h1>
            </Grid>
            <Grid item xs={12} sm={11}>
            </Grid>
            <Grid item xs={12} sm={1}>
                <Button className = {classes.newbutton} onClick = {openModalNewMovie}>Novo</Button>
            </Grid>
            <Grid item xs={12}>
                <Paper>
                        <TableContainer className = {classes.tablecontainer}>
                            <Table stickyHeader aria-label="sticky table">
                            <TableHead>
                                <TableRow>
                                {columns.map((column) => (
                                    <TableCell
                                        key={column.key}
                                        align={column.align}
                                        style={{ minWidth: column.minWidth }}
                                    >
                                    {column.label}
                                    </TableCell>
                                ))}
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {(!isLoading)? data.content.map((row) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.id}>
                                    {columns.map((column) => {
                                        const value = row[column.key];
                                        return (
                                        <TableCell key={column.key} align={column.align}>
                                            {(column.key !== 'buttons' && column.key !== 'averageEvaluation')? 
                                                (column.format && typeof value === 'number' ? column.format(value) : value)
                                            :(
                                                (column.key === 'buttons')?
                                                (<div className = {classes.controle}>
                                                    <IconButton title='Editar' aria-label="Editar"> <CreateIcon color='primary' /> </IconButton>  
                                                    <IconButton title='Excluir' aria-label="Excluir"> <Delete color='secondary' /> </IconButton> 
                                                    <IconButton title='Avaliar' aria-label="Avaliar"> <StarIcon color='inherit' /> </IconButton>
                                                </div>)
                                                :(
                                                (column.key === 'averageEvaluation')?  
                                                (<div title={column.format(value)}>
                                                    <Rating
                                                            value={value}
                                                            precision={0.5}
                                                            readOnly={true}
                                                        />
                                                </div>):null
                                                )
                                            )
                                            }
                                        </TableCell>
                                        );
                                    })}
                                    </TableRow>
                                );
                                }):null}
                            </TableBody>
                            </Table>
                        </TableContainer>
                        <TablePagination
                            rowsPerPageOptions={[5, 10, 20]}
                            component="div"
                            count={data.totalElements}
                            rowsPerPage={rowsPerPage}
                            page={page}
                            onChangePage={changePageEvent}
                            onChangeRowsPerPage={changeRowsPerPageEvent}
                        />
                    </Paper>
                    </Grid>
                </Grid>
            <ModalNewMovie open={isOpenModal} />
            <Loading open={isLoading}/>
            <Message open={message.show} message={message.value} type={message.type} handler={clearMessage} />
        </div>
    );
}