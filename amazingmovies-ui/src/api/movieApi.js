import api from '../util/api'

const listMovies = (page, size, onSuccess, onError) => {
  api.axios.get('movies?page='+page+'&size='+size)
  .then(onSuccess)
  .catch(onError);
};

const saveMovie = (movie, onSuccess, onError) => {
  api.axios.post('movies', movie, api.headers)
  .then(onSuccess)
  .catch(onError);
};

const changeMovie = (id, movie, onSuccess, onError) => {
  api.axios.put('movies/'+id, movie, api.headers)
  .then(onSuccess)
  .catch(onError);
};

const deleteMovie = (id, onSuccess, onError) => {
  api.axios.delete('movies/'+id)
  .then(onSuccess)
  .catch(onError);
};

const findMovie = (id, onSuccess, onError) => {
  api.axios.get('movies/'+id)
  .then(onSuccess)
  .catch(onError);
};

export default {
  listMovies,
  saveMovie,
  changeMovie,
  deleteMovie,
  findMovie
};