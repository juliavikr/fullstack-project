// saves the users token in the local storage
// this is used to authenticate the user when they are logged in for retrieving
// stored data for the user

import axios from 'axios'

function login(credentials) {
  axios
    .post('/api/user/login', credentials)
    .then((response) => {
      const { token, username } = response.data;
      localStorage.setItem('token', token);
      localStorage.setItem('username', username); // Lagre brukernavnet
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
      // Gjør noe etter vellykket innlogging, som å videresende brukeren
    })
    .catch((error) => {
      console.error('Login failed:', error);
      // Håndter innloggingsfeil, som å vise en melding til brukeren
    });
}


// Log out the user and remove the token from local storage
function logout() {
  localStorage.removeItem('token')
  // Remove other user info if it was saved
  // localStorage.removeItem('userInfo');
  // Redirect to login page or handle logout view change
}
