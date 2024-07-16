import { useMutation } from '@apollo/client';
import { LOGOUT_USER } from '../../graphql/mutations';
import { useHistory } from 'react-router-dom';

const LogoutButton = () => {
  const [logout] = useMutation(LOGOUT_USER);
  const history = useHistory();

  const handleLogout = async () => {
    try {
      await logout();
      history.push('/login');
    } catch (error) {
      console.error("Logout failed", error);
    }
  };

  return (
    <button onClick={handleLogout}>
      Logout
    </button>
  );
};

export default LogoutButton;
