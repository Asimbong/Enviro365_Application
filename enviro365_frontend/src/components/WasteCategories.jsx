import React, { useState, useEffect } from 'react';
import WasteCategoriesService from '../services/WasteCategoriesService';
import { Modal, Button, Form, Alert } from 'react-bootstrap';

const WasteCategories = () => {
  const [categories, setCategories] = useState([]);
  const [newCategory, setNewCategory] = useState('');
  const [editCategory, setEditCategory] = useState(null);
  const [editCategoryName, setEditCategoryName] = useState('');
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
    if (newCategory.trim() === '') {
      showError('Category name cannot be empty');
      return;
    }
    WasteCategoriesService.createCategory({ name: newCategory })
      .then(() => {
        fetchCategories();
        setNewCategory('');
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
    setEditCategoryName(category.name);
    setShowModal(true);
  };

  const updateCategory = () => {
    if (editCategoryName.trim() === '') {
      showError('Category name cannot be empty');
      return;
    }
    WasteCategoriesService.updateCategory(editCategory.id, { name: editCategoryName })
      .then(() => {
        fetchCategories();
        setEditCategory(null);
        setEditCategoryName('');
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
          placeholder="New Category"
          value={newCategory}
          onChange={(e) => setNewCategory(e.target.value)}
        />
        <Button className="mt-2" onClick={addCategory}>Add Category</Button>
      </div>
      <ul className="list-group">
        {categories.map(category => (
          <li key={category.id} className="list-group-item d-flex justify-content-between align-items-center">
            {category.name}
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
            value={editCategoryName}
            onChange={(e) => setEditCategoryName(e.target.value)}
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