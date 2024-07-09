import React, { useState } from 'react';
import { useMutation } from '@apollo/client';
import { CREATE_USER } from '/Users/princetsiquaye/Documents/Exam-Scheduler/Frontend (React)/exam-scheduler/src/graphql/mutations.js';
import '/Users/princetsiquaye/Documents/Exam-Scheduler/Frontend (React)/exam-scheduler/src/components/Authentication/SignUp.scss';

const SignUp = () => {
  const [formState, setFormState] = useState({ firstName: '', lastName: '', email: '', password: '', role: 'student' });
  const [createUser, { data, error }] = useMutation(CREATE_USER);

  const handleSubmit = (e) => {
    e.preventDefault();
    createUser({ variables: { ...formState } });
  };

  if (data) {
    console.log("User created:", data.createUser.user);
    console.log("Token:", data.createUser.token);
  }

  return (
    <form onSubmit={handleSubmit}>
      <input type="text" placeholder="First Name" value={formState.firstName} onChange={(e) => setFormState({ ...formState, firstName: e.target.value })} />
      <input type="text" placeholder="Last Name" value={formState.lastName} onChange={(e) => setFormState({ ...formState, lastName: e.target.value })} />
      <input type="email" placeholder="Email" value={formState.email} onChange={(e) => setFormState({ ...formState, email: e.target.value })} />
      <input type="password" placeholder="Password" value={formState.password} onChange={(e) => setFormState({ ...formState, password: e.target.value })} />
      <select value={formState.role} onChange={(e) => setFormState({ ...formState, role: e.target.value })}>
        <option value="student">Student</option>
        <option value="professor">Professor</option>
      </select>
      <button type="submit">Sign Up</button>
      {error && <p>Error: {error.message}</p>}
    </form>
  );
};

export default SignUp;