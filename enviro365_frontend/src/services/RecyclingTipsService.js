import axiosInstance from './axiosInstance';

const getRecyclingTips = () => {
  return axiosInstance.get('/recycling-tips/get-all');
};

const createTip = (tip) => {
  return axiosInstance.post('/recycling-tips/create', { tip });
};

const updateTip = (id, tip) => {
  return axiosInstance.put(`/recycling-tips/update/${id}`, { tip });
};

const deleteTip = (id) => {
  return axiosInstance.delete(`/recycling-tips/delete/${id}`);
};

export default {
  getRecyclingTips,
  createTip,
  updateTip,
  deleteTip,
};