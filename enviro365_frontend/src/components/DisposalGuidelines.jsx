import React, { useState, useEffect } from 'react';
import DisposalGuidelinesService from '../services/DisposalGuidelinesService';
import { Modal, Button, Form, Alert } from 'react-bootstrap';

const DisposalGuidelines = () => {
  const [guidelines, setGuidelines] = useState([]);
  const [newGuideline, setNewGuideline] = useState({ guideline: '', disposalMethod: '' });
  const [editGuideline, setEditGuideline] = useState(null);
  const [editGuidelineData, setEditGuidelineData] = useState({ guideline: '', disposalMethod: '' });
  const [error, setError] = useState('');
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    fetchGuidelines();
  }, []);

  const fetchGuidelines = () => {
    DisposalGuidelinesService.getDisposalGuidelines()
      .then(response => setGuidelines(response.data))
      .catch(() => showError('Error fetching disposal guidelines'));
  };

  const addGuideline = () => {
    if (newGuideline.guideline.trim() === '' || newGuideline.disposalMethod.trim() === '') {
      showError('Guideline and disposal method cannot be empty');
      return;
    }
    DisposalGuidelinesService.createGuideline(newGuideline)
      .then(() => {
        fetchGuidelines();
        setNewGuideline({ guideline: '', disposalMethod: '' });
      })
      .catch(() => showError('Error adding guideline'));
  };

  const deleteGuideline = (id) => {
    DisposalGuidelinesService.deleteGuideline(id)
      .then(() => fetchGuidelines())
      .catch(() => showError('Error deleting guideline'));
  };

  const startEditGuideline = (guideline) => {
    setEditGuideline(guideline);
    setEditGuidelineData({ guideline: guideline.guideline, disposalMethod: guideline.disposalMethod });
    setShowModal(true);
  };

  const updateGuideline = () => {
    if (editGuidelineData.guideline.trim() === '' || editGuidelineData.disposalMethod.trim() === '') {
      showError('Guideline and disposal method cannot be empty');
      return;
    }
    DisposalGuidelinesService.updateGuideline(editGuideline.id, editGuidelineData)
      .then(() => {
        fetchGuidelines();
        setEditGuideline(null);
        setEditGuidelineData({ guideline: '', disposalMethod: '' });
        setShowModal(false);
      })
      .catch(() => showError('Error updating guideline'));
  };

  const showError = (message) => {
    setError(message);
    setTimeout(() => setError(''), 3000);
  };

  return (
    <div className="container">
      <h2 className="my-4">Disposal Guidelines</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <div className="mb-3">
        <Form.Control
          type="text"
          placeholder="New Guideline"
          value={newGuideline.guideline}
          onChange={(e) => setNewGuideline({ ...newGuideline, guideline: e.target.value })}
        />
        <Form.Control
          type="text"
          placeholder="Disposal Method"
          value={newGuideline.disposalMethod}
          onChange={(e) => setNewGuideline({ ...newGuideline, disposalMethod: e.target.value })}
        />
        <Button className="mt-2" onClick={addGuideline}>Add Guideline</Button>
      </div>
      <ul className="list-group">
        {guidelines.map(guideline => (
          <li key={guideline.id} className="list-group-item d-flex justify-content-between align-items-center">
            {guideline.guideline} - {guideline.disposalMethod}
            <div>
              <Button variant="secondary" size="sm" className="me-2" onClick={() => startEditGuideline(guideline)}>Edit</Button>
              <Button variant="danger" size="sm" onClick={() => deleteGuideline(guideline.id)}>Delete</Button>
            </div>
          </li>
        ))}
      </ul>
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Guideline</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Control
            type="text"
            value={editGuidelineData.guideline}
            onChange={(e) => setEditGuidelineData({ ...editGuidelineData, guideline: e.target.value })}
          />
          <Form.Control
            type="text"
            value={editGuidelineData.disposalMethod}
            onChange={(e) => setEditGuidelineData({ ...editGuidelineData, disposalMethod: e.target.value })}
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>Cancel</Button>
          <Button variant="primary" onClick={updateGuideline}>Update Guideline</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default DisposalGuidelines;