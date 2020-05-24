import { useEffect, useState } from 'react';
import movieApi from '../../api/movieApi';

export default (props) => {
  const [response, setResponse] = useState({
    content:[],
    pageable: {
      pageNumber: 0,
      pageSize: 5
  }
  });
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const listMovies = (page, size) => {
    setIsLoading(true);

    const onSucces = (response) => {
      setIsLoading(false);
      setResponse(response.data);
    }

    const onError = (error) => {
      console.error(error);
      setIsLoading(false);
      setError({ message: 'Unable to load all users.' });
    }

    movieApi(page, size, onSucces, onError);
  };


  useEffect(() =>{ listMovies(page, size) }, [page, size]);

  return [response, isLoading, error, listMovies];
}