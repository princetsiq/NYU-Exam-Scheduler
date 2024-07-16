import React, { useState, useEffect } from 'react';
import { useMutation } from '@apollo/client';
import { useHistory } from 'react-router-dom';
import { CREATE_USER, LOGIN_USER } from '../../graphql/mutations';
import '/Users/princetsiquaye/Documents/Exam-Scheduler/Frontend (React)/exam-scheduler/src/components/Authentication/FlipCardForm.scss';

const FlipCardForm = () => {
  const [formState, setFormState] = useState({ email: '', password: '', firstName: '', lastName: '' });
  const [isLogin, setIsLogin] = useState(true);
  const [loginUser, { data: loginData, error: loginError }] = useMutation(LOGIN_USER);
  const [createUser, { data: signUpData, error: signUpError }] = useMutation(CREATE_USER);
  const history = useHistory();

  useEffect(() => {
    if (loginData) {
      const token = loginData.loginUser.token;
      localStorage.setItem('authToken', token);
      history.push('/');
    }

    if (signUpData) {
      const token = signUpData.createUser.token;
      localStorage.setItem('authToken', token);
      history.push('/');
    }
  }, [loginData, signUpData, history]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (isLogin) {
      loginUser({ variables: { email: formState.email, password: formState.password } });
    } else {
      createUser({ variables: { firstName: formState.firstName, lastName: formState.lastName, email: formState.email, password: formState.password } });
    }
  };

  const toggleForm = () => {
    setIsLogin(!isLogin);
    setFormState({ email: '', password: '', firstName: '', lastName: '' });
  };

  return (
    <div className="wrapper">
      <div className="card-switch">
        <label className="switch">
          <input type="checkbox" className="toggle" onChange={toggleForm} checked={!isLogin} />
          <span className="slider"></span>
          <span className="card-side"></span>
          <div className="flip-card__inner">
            <div className="flip-card__front">
              <div className="title">Log in</div>
              <form className="flip-card__form" onSubmit={handleSubmit}>
                <input className="flip-card__input" name="email" placeholder="Email" type="email" value={formState.email} onChange={(e) => setFormState({ ...formState, email: e.target.value })} />
                <input className="flip-card__input" name="password" placeholder="Password" type="password" value={formState.password} onChange={(e) => setFormState({ ...formState, password: e.target.value })} />
                <button className="flip-card__btn">Let's go!</button>
                {loginError && <p>Error: {loginError.message}</p>}
                {loginData && <p>Login successful!</p>}
              </form>
            </div>
            <div className="flip-card__back">
              <div className="title">Sign up</div>
              <form className="flip-card__form" onSubmit={handleSubmit}>
                <input className="flip-card__input" placeholder="First Name" type="text" value={formState.firstName} onChange={(e) => setFormState({ ...formState, firstName: e.target.value })} />
                <input className="flip-card__input" placeholder="Last Name" type="text" value={formState.lastName} onChange={(e) => setFormState({ ...formState, lastName: e.target.value })} />
                <input className="flip-card__input" name="email" placeholder="Email" type="email" value={formState.email} onChange={(e) => setFormState({ ...formState, email: e.target.value })} />
                <input className="flip-card__input" name="password" placeholder="Password" type="password" value={formState.password} onChange={(e) => setFormState({ ...formState, password: e.target.value })} />
                <button className="flip-card__btn">Confirm!</button>
                {signUpError && <p>Error: {signUpError.message}</p>}
                {signUpData && <p>Sign up successful!</p>}
              </form>
            </div>
          </div>
        </label>
      </div>
    </div>
  );
};

export default FlipCardForm;