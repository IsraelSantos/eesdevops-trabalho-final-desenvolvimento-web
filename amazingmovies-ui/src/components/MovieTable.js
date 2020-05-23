import React from 'react';
import { styled } from '@material-ui/core/styles';
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


const MyPaper = styled(Paper)({
    width: '80%',
    float: 'right'
});

const MyTableContainer = styled(TableContainer)({
    maxHeight: 600
});


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
    minWidth: 170 }
];

function createData(id, name, genre, averageEvaluation, releaseYear) {
    const buttons = null;
    return {id, name, genre, averageEvaluation, releaseYear,  buttons};
}
  
const rows = [
    createData(0, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(1, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(2, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(3, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(4, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(5, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(6, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(7, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(8, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(9, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(10, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(11, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(12, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(13, 'Uma linda mulher', 'Romance', 2.9, 1986),
    createData(14, 'Uma linda mulher', 'Romance', 2.9, 1986),
];

export default class MovieTable{

    constructor(props){
        super(props);
        this.changePageEvent = this.changePageEvent.bind(this);
        this.changeRowsPerPageEvent = this.changeRowsPerPageEvent.bind(this);
        this.state = {
            page: 0,
            rowsPerPage: 6,
            rows: rows //Pegar do hoke
        };
    }

    changePageEvent(event, newPage){
        this.setState({
            page: newPage
        });
    }

    changeRowsPerPageEvent(event){
        this.setState({
            page: 0,
            rowsPerPage: +event.target.value
        });
    }

    render(){
        return (
                <MyPaper>
                    <MyTableContainer>
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
                            {this.state.rows.slice(this.state.page * this.state.rowsPerPage, this.state.page * this.state.rowsPerPage + this.state.rowsPerPage).map((row) => {
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
                                            (<div>
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
                            })}
                        </TableBody>
                        </Table>
                    </MyTableContainer>
                    <TablePagination
                        rowsPerPageOptions={[6, 10, 20]}
                        component="div"
                        count={this.state.rows.length}
                        rowsPerPage={this.state.rowsPerPage}
                        page={this.state.page}
                        onChangePage={this.changePageEvent}
                        onChangeRowsPerPage={this.changeRowsPerPageEvent}
                    />
                </MyPaper>
        );
    }
}