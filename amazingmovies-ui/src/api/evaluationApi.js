import api from '../util/api'

const saveEvaluation = (evaluation, onSuccess, onError) => {
  api.axios.post('evaluations', evaluation, api.headers)
  .then(onSuccess)
  .catch(onError);
};

export default {
  saveEvaluation
};