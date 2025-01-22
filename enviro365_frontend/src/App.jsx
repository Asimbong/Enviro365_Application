import React from 'react';
import WasteCategories from './components/WasteCategories';
import DisposalGuidelines from './components/DisposalGuidelines';
import RecyclingTips from './components/RecyclingTips';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';

function App() {
    return (
        <Router>
            <div className="App">
                <h1>Enviro365 Application</h1>
                <Routes>
                    <Route path="/" element={<Navigate to="/waste-categories" />} />
                    <Route path="/waste-categories" element={<WasteCategories />} />
                    <Route path="/disposal-guidelines" element={<DisposalGuidelines />} />
                    <Route path="/recycling-tips" element={<RecyclingTips />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;