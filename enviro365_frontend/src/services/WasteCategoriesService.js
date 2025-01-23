import axiosInstance from './axiosInstance';

const getWasteCategories = () => {
  return axiosInstance.get('/waste-categories/get-all');
};

const createCategory = (category) => {
  return axiosInstance.post('/waste-categories/create', category);
};

const updateCategory = (id, category) => {
  return axiosInstance.put(`/waste-categories/update/${id}`, category);
};

const deleteCategory = (id) => {
  return axiosInstance.delete(`/waste-categories/delete/${id}`);
};

export default {
  getWasteCategories,
  createCategory,
  updateCategory,
  deleteCategory,
};