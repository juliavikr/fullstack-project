// saves the users token in the local storage
// this is used to authenticate the user when they are logged in for retrieving
// stored data for the user

import axios from 'axios'

// Login-funksjon
function login(credentials) {
    axios.post('/api/user/login', credentials)
        .then(response => {
            const {token, username} = response.data;
            localStorage.setItem('token', token);
            localStorage.setItem('username', username); // Lagrer brukernavnet
            // ... resten av koden
        })
        .catch(error => {
            console.error('Login failed:', error);
            // ... feilhåndtering
        });
}

// Log out the user and remove the token from local storage
function logout() {
  localStorage.removeItem('token')
  // Remove other user info if it was saved
  // localStorage.removeItem('userInfo');
  // Redirect to login page or handle logout view change
}
