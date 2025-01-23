import React, { useState, useEffect } from 'react';
import WasteCategoriesService from '../services/WasteCategoriesService';
import { Modal, Button, Form, Alert } from 'react-bootstrap';

const WasteCategories = () => {
  const [categories, setCategories] = useState([]);
  const [newCategory, setNewCategory] = useState({ name: '', pricePerKg: '' });
  const [editCategory, setEditCategory] = useState(null);
  const [editCategoryData, setEditCategoryData] = useState({ name: '', pricePerKg: '' });
  const [error, setError] = useState('');
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = () => {
    WasteCategoriesService.getWasteCategories()
      .then(response => setCategories(response.data))
      .catch(() => showError('Error fetching waste categories'));
  };

  const addCategory = () => {
    if (newCategory.name.trim() === '' || newCategory.pricePerKg.trim() === '') {
      showError('Category name and price per kg cannot be empty');
      return;
    }
    WasteCategoriesService.createCategory(newCategory)
      .then(() => {
        fetchCategories();
        setNewCategory({ name: '', pricePerKg: '' });
      })
      .catch(() => showError('Error adding category'));
  };

  const deleteCategory = (id) => {
    WasteCategoriesService.deleteCategory(id)
      .then(() => fetchCategories())
      .catch(() => showError('Error deleting category'));
  };

  const startEditCategory = (category) => {
    setEditCategory(category);
    setEditCategoryData({ name: category.name, pricePerKg: category.pricePerKg });
    setShowModal(true);
  };

  const updateCategory = () => {
    if (editCategoryData.name.trim() === '' || editCategoryData.pricePerKg.trim() === '') {
      showError('Category name and price per kg cannot be empty');
      return;
    }
    WasteCategoriesService.updateCategory(editCategory.id, editCategoryData)
      .then(() => {
        fetchCategories();
        setEditCategory(null);
        setEditCategoryData({ name: '', pricePerKg: '' });
        setShowModal(false);
      })
      .catch(() => showError('Error updating category'));
  };

  const showError = (message) => {
    setError(message);
    setTimeout(() => setError(''), 3000);
  };

  return (
    <div className="container">
      <h2 className="my-4">Waste Categories</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <div className="mb-3">
        <Form.Control
          type="text"
          placeholder="New Category Name"
          value={newCategory.name}
          onChange={(e) => setNewCategory({ ...newCategory, name: e.target.value })}
        />
        <Form.Control
          type="text"
          placeholder="Price per Kg"
          value={newCategory.pricePerKg}
          onChange={(e) => setNewCategory({ ...newCategory, pricePerKg: e.target.value })}
        />
        <Button className="mt-2" onClick={addCategory}>Add Category</Button>
      </div>
      <ul className="list-group">
        {categories.map(category => (
          <li key={category.id} className="list-group-item d-flex justify-content-between align-items-center">
            {category.name} - R{category.pricePerKg}
            <div>
              <Button variant="secondary" size="sm" className="me-2" onClick={() => startEditCategory(category)}>Edit</Button>
              <Button variant="danger" size="sm" onClick={() => deleteCategory(category.id)}>Delete</Button>
            </div>
          </li>
        ))}
      </ul>
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Category</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Control
            type="text"
            value={editCategoryData.name}
            onChange={(e) => setEditCategoryData({ ...editCategoryData, name: e.target.value })}
          />
          <Form.Control
            type="text"
            value={editCategoryData.pricePerKg}
            onChange={(e) => setEditCategoryData({ ...editCategoryData, pricePerKg: e.target.value })}
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>Cancel</Button>
          <Button variant="primary" onClick={updateCategory}>Update Category</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default WasteCategories;