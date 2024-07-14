import React, { useState } from 'react';
import './Layout.scss'; 
import { Outlet } from 'react-router-dom';
import Navbar from '/Users/princetsiquaye/Documents/Exam-Scheduler/Frontend (React)/exam-scheduler/src/components/Navbar/Navbar.jsx';

const Layout = () => {
  const [sidebarWidth, setSidebarWidth] = useState(0);

  const handleSidebarToggle = (isOpen) => {
    setSidebarWidth(isOpen ? 250 : 0);
  };

  return (
    <div className="App">
      <Navbar onSidebarToggle={handleSidebarToggle} />
      {/* <div className="page" style={{ width: `calc(100% - ${sidebarWidth}px)` }}> */}
      <div className="page" style={{ marginLeft: `${sidebarWidth}px`, width: `calc(100% - ${sidebarWidth}px)` }}>
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;