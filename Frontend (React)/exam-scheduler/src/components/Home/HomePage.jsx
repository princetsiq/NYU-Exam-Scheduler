import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AnimatedText from "/Users/princetsiquaye/Documents/Exam-Scheduler/Frontend (React)/exam-scheduler/src/components/letterAnimation/AnimatedText.jsx";
import "./HomePage.scss";

const HomePage = () => {
  const [letterClass, setLetterClass] = useState("text-animate");
  const welcomeArray1 = "Welcome To".split("");
  const welcomeArray2 = "NYU Exam Scheduler!".split("");

  useEffect(() => {
    const timerId = setTimeout(() => {
      setLetterClass("text-animate-hover");
    }, 4000);

    return () => {
      clearTimeout(timerId);
    };
  }, []);

  return (
    <div className="container home-page">
      <div className="text-zone">
        <h1>
          <AnimatedText letterClass={letterClass} strArray={welcomeArray1} idx={12} />
          <br />
          <AnimatedText letterClass={letterClass} strArray={welcomeArray2} idx={14} />
        </h1>
        <h2>
          Add your exams to your calendar with ease! <br /> Just search, click add, and export...
          <br /><br /><br />
          <Link to="/search" className="flat-button">GET STARTED</Link>
        </h2>
      </div>
    </div>
  );
};

export default HomePage;