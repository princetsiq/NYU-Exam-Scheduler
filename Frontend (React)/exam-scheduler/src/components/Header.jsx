import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => (
  <header>
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/signup">Sign Up</Link></li>
        <li><Link to="/login">Login</Link></li>
        <li><Link to="/exams">Exams</Link></li>
        <li><Link to="/admin">Admin</Link></li>
      </ul>
    </nav>
  </header>
);

export default Header;