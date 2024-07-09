import React from 'react';

const AnimatedText = ({ text }) => {
  return (
    <div>
      {text.split('').map((char, index) => (
        <span key={index} className={`char char-${index}`}>
          {char}
        </span>
      ))}
    </div>
  );
};

export default AnimatedText;