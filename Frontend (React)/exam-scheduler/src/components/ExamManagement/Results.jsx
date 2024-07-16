import React, { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { useLazyQuery } from '@apollo/client';
import { SEARCH_EXAMS } from '../../graphql/queries';
import './Results.scss';

const Results = () => {
  const location = useLocation();

  const [triggerSearch, { loading, data, error }] = useLazyQuery(SEARCH_EXAMS);

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const query = params.get('search');
    if (query) {
      triggerSearch({ variables: { searchInput: query } });
    }
  }, [location.search, triggerSearch]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div className="results-container">
      <h1>Results</h1>
      {data?.searchExams.length === 0 ? (
        <h2>No results found.</h2>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Course</th>
              <th>Section</th>
              <th>Course Title</th>
              <th>Professor</th>
              <th>Weekday</th>
              <th>Date</th>
              <th>Start Time</th>
              <th>End Time</th>
              <th>Location</th>
            </tr>
          </thead>
          <tbody>
            {data?.searchExams.map((exam) => (
              <tr key={`${exam.examId.courseName}-${exam.examId.courseSection}`}>
                <td>{exam.examId.courseName}</td>
                <td>{exam.examId.courseSection}</td>
                <td>{exam.courseTitle}</td>
                <td>{exam.professorName}</td>
                <td>{exam.examWeekday}</td>
                <td>{exam.examDate}</td>
                <td>{exam.startTime}</td>
                <td>{exam.endTime}</td>
                <td>{exam.location}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default Results;