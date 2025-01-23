import React, { useState, useEffect } from 'react';
import RecyclingTipsService from '../services/RecyclingTipsService';
import { Modal, Button, Form, Alert } from 'react-bootstrap';

const RecyclingTips = () => {
  const [tips, setTips] = useState([]);
  const [newTip, setNewTip] = useState({ tip: '' });
  const [editTip, setEditTip] = useState(null);
  const [editTipData, setEditTipData] = useState({ tip: '' });
  const [error, setError] = useState('');
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    fetchTips();
  }, []);

  const fetchTips = () => {
    RecyclingTipsService.getRecyclingTips()
      .then(response => setTips(response.data))
      .catch(() => showError('Error fetching recycling tips'));
  };

  const addTip = () => {
    if (newTip.tip.trim() === '') {
      showError('Tip description cannot be empty');
      return;
    }
    RecyclingTipsService.createTip(newTip.tip)
      .then(() => {
        fetchTips();
        setNewTip({ tip: '' });
      })
      .catch(() => showError('Error adding tip'));
  };

  const deleteTip = (id) => {
    RecyclingTipsService.deleteTip(id)
      .then(() => fetchTips())
      .catch(() => showError('Error deleting tip'));
  };

  const startEditTip = (tip) => {
    setEditTip(tip);
    setEditTipData({ tip: tip.tip });
    setShowModal(true);
  };

  const updateTip = () => {
    if (editTipData.tip.trim() === '') {
      showError('Tip description cannot be empty');
      return;
    }
    RecyclingTipsService.updateTip(editTip.id, editTipData.tip)
      .then(() => {
        fetchTips();
        setEditTip(null);
        setEditTipData({ tip: '' });
        setShowModal(false);
      })
      .catch(() => showError('Error updating tip'));
  };

  const showError = (message) => {
    setError(message);
    setTimeout(() => setError(''), 3000);
  };

  return (
    <div className="container">
      <h2 className="my-4">Recycling Tips</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <div className="mb-3">
        <Form.Control
          type="text"
          placeholder="New Tip Description"
          value={newTip.tip}
          onChange={(e) => setNewTip({ tip: e.target.value })}
        />
        <Button className="mt-2" onClick={addTip}>Add Tip</Button>
      </div>
      <ul className="list-group">
        {tips.map(tip => (
          <li key={tip.id} className="list-group-item d-flex justify-content-between align-items-center">
            {tip.tip}
            <div>
              <Button variant="secondary" size="sm" className="me-2" onClick={() => startEditTip(tip)}>Edit</Button>
              <Button variant="danger" size="sm" onClick={() => deleteTip(tip.id)}>Delete</Button>
            </div>
          </li>
        ))}
      </ul>
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Tip</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Control
            type="text"
            value={editTipData.tip}
            onChange={(e) => setEditTipData({ tip: e.target.value })}
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>Cancel</Button>
          <Button variant="primary" onClick={updateTip}>Update Tip</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default RecyclingTips;