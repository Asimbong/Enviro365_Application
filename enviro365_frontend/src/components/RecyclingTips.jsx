import React, { useState, useEffect } from 'react';
import RecyclingTipsService from '../services/RecyclingTipsService';

const RecyclingTips = () => {
  const [tips, setTips] = useState([]);

  useEffect(() => {
    RecyclingTipsService.getRecyclingTips()
      .then(response => setTips(response.data))
      .catch(error => console.error('Error fetching recycling tips:', error));
  }, []);

  return (
    <div className="container">
      <h2 className="my-4">Recycling Tips</h2>
      <ul className="list-group">
        {tips.map(tip => (
          <li key={tip.id} className="list-group-item">{tip.description}</li>
        ))}
      </ul>
    </div>
  );
};

export default RecyclingTips;