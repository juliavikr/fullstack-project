// saves the users token in the local storage
// this is used to authenticate the user when they are logged in for retrieving
// stored data for the user

import axios from 'axios'

function login(credentials) {
  axios
    .post('/api/user/login', credentials)
    .then((response) => {
      const token = response.data.token
      localStorage.setItem('token', token)
      // Set the token as default header for future requests
      axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
      // Do something on successful login, like redirecting the user
    })
    .catch((error) => {
      console.error('Login failed:', error)
      // Handle login failure, like showing a message to the user
    })
}

// Log out the user and remove the token from local storage
function logout() {
  localStorage.removeItem('token')
  // Remove other user info if it was saved
  // localStorage.removeItem('userInfo');
  // Redirect to login page or handle logout view change
}
