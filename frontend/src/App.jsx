import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './pages/Login';
import Order from './pages/Order';
import orderr from './pages/orderr';

function App() {
    return (
        <Router>
          <Routes>
              <Route path="/login" element={<Login />} />
              <Route path="/" element={<Order />} />
          </Routes>
        </Router>
    );
}

export default App;