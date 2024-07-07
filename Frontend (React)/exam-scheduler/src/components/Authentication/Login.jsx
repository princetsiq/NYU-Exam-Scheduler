import React, { useState } from 'react';
import { useMutation } from '@apollo/client';
import { LOGIN_USER } from '/Users/princetsiquaye/Documents/Exam-Scheduler/Frontend (React)/exam-scheduler/src/graphql/mutations.js';

const Login = () => {
  const [formState, setFormState] = useState({ email: '', password: '' });
  const [loginUser, { data, error }] = useMutation(LOGIN_USER);

  const handleSubmit = (e) => {
    e.preventDefault();
    loginUser({ variables: { ...formState } });
  };

  if (data) {
    console.log("User logged in:", data.loginUser.user);
    console.log("Token:", data.loginUser.token);
  }

  return (
    <form onSubmit={handleSubmit}>
      <input type="email" placeholder="Email" value={formState.email} onChange={(e) => setFormState({ ...formState, email: e.target.value })} />
      <input type="password" placeholder="Password" value={formState.password} onChange={(e) => setFormState({ ...formState, password: e.target.value })} />
      <button type="submit">Login</button>
      {error && <p>Error: {error.message}</p>}
    </form>
  );
};

export default Login;