import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './SearchExams.scss';

const Search = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const navigate = useNavigate();

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearch = () => {
    if (searchQuery.trim() !== '') {
      navigate(`/results?search=${encodeURIComponent(searchQuery)}`);
    }
  };

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      handleSearch();
    }
  };

  return (
    <div className="search-container">
      <input
        type="text"
        value={searchQuery}
        onChange={handleSearchChange}
        onKeyDown={handleKeyDown}
        placeholder="Search exams"
      />
      <button onClick={handleSearch}>Search</button>
    </div>
  );
};

export default Search;