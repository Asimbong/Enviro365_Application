import axiosInstance from './axiosInstance';

const getDisposalGuidelines = () => {
  return axiosInstance.get('/disposal-guidelines/get-all');
};

const createGuideline = (guideline) => {
  return axiosInstance.post('/disposal-guidelines/create', guideline);
};

const updateGuideline = (id, guideline) => {
  return axiosInstance.put(`/disposal-guidelines/update/${id}`, guideline);
};

const deleteGuideline = (id) => {
  return axiosInstance.delete(`/disposal-guidelines/delete/${id}`);
};

export default {
  getDisposalGuidelines,
  createGuideline,
  updateGuideline,
  deleteGuideline,
};