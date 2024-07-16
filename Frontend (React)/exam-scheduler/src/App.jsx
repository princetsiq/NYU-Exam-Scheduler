import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import HomePage from './components/Home/HomePage';
import FlipCardForm from './components/Authentication/FlipCardForm';
import Results from './components/ExamManagement/Results';
import Search from './components/Search/SearchExams';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<HomePage />} />
          <Route path="auth" element={<FlipCardForm />} />
          <Route path="search-exams" element={<Search />} />
          <Route path="results" element={<Results />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;