import axios from 'axios';

const API_URL = 'http://localhost:9191/enviro365db/disposal-guidelines';

const getDisposalGuidelines = () => {
  return axios.get(API_URL);
};

export default {
  getDisposalGuidelines,
};