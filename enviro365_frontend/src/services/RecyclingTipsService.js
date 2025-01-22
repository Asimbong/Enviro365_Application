import axios from 'axios';

const API_URL = 'http://localhost:9191/enviro365db/recycling-tips';

const getRecyclingTips = () => {
  return axios.get(API_URL);
};

export default {
  getRecyclingTips,
};