import React, { useState, useEffect } from 'react';
import DisposalGuidelinesService from '../services/DisposalGuidelinesService';

const DisposalGuidelines = () => {
  const [guidelines, setGuidelines] = useState([]);

  useEffect(() => {
    DisposalGuidelinesService.getDisposalGuidelines()
      .then(response => setGuidelines(response.data))
      .catch(error => console.error('Error fetching disposal guidelines:', error));
  }, []);

  return (
    <div className="container">
      <h2 className="my-4">Disposal Guidelines</h2>
      <ul className="list-group">
        {guidelines.map(guideline => (
          <li key={guideline.id} className="list-group-item">{guideline.description}</li>
        ))}
      </ul>
    </div>
  );
};

export default DisposalGuidelines;