import api from '../util/api'

const listMovies = (page, size, onSuccess, onError) => {
  api.get('movies?page='+page+'&size='+size)
  .then(onSuccess)
  .catch(onError);
};

export default {listMovies};