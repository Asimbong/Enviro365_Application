import axios from 'axios';

const API_URL = 'http://localhost:9191/api/waste-categories';

const getWasteCategories = () => {
  return axios.get(API_URL);
};

const createCategory = (category) => {
  return axios.post(API_URL, category);
};

const updateCategory = (id, category) => {
  return axios.put(`${API_URL}/${id}`, category);
};

const deleteCategory = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};

export default {
  getWasteCategories,
  createCategory,
  updateCategory,
  deleteCategory,
};