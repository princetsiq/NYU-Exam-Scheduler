import React, { useState } from 'react';
import './Sidebar.scss'; // Import the SCSS file

// const Sidebar = () => {
//   const [width, setWidth] = useState(250); // Initial width in pixels

//   const handleMouseDown = (e) => {
//     document.addEventListener('mousemove', handleMouseMove);
//     document.addEventListener('mouseup', handleMouseUp);
//   };

//   const handleMouseMove = (e) => {
//     const newWidth = e.clientX;
//     if (newWidth >= 0 && newWidth <= window.innerWidth / 4) {
//       setWidth(newWidth);
//     }
//   };

//   const handleMouseUp = () => {
//     document.removeEventListener('mousemove', handleMouseMove);
//     document.removeEventListener('mouseup', handleMouseUp);
//   };

//   return (
//     <div className="sidebar" style={{ width: width }}>
//       <div className="content">
//         <h2>Sidebar</h2>
    
//       </div>
//       <div className="resizer" onMouseDown={handleMouseDown} />
//     </div>
//   );
// };

const Sidebar = ({ width, setWidth }) => {
  const handleMouseDown = () => {
    document.addEventListener('mousemove', handleMouseMove);
    document.addEventListener('mouseup', handleMouseUp);
  };

  const handleMouseMove = (e) => {
    const newWidth = e.clientX;
    if (newWidth >= 0 && newWidth <= window.innerWidth / 4) {
      setWidth(newWidth);
    }
  };

  const handleMouseUp = () => {
    document.removeEventListener('mousemove', handleMouseMove);
    document.removeEventListener('mouseup', handleMouseUp);
  };

  return (
    <div className="sidebar" style={{ width: width }}>
      <div className="content">
        <h2>Sidebar</h2>
      </div>
      <div className="resizer" onMouseDown={handleMouseDown} />
    </div>
  );
};

export default Sidebar;