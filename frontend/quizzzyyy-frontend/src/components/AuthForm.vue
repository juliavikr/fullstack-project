<template>
  <div class="auth-form">
    <h2>{{ title }}</h2>
    <form @submit.prevent="handleSubmit">
      <div class="input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" required />
        <div v-if="errorMessage  === 'username'" class="error-message">{{ errorMessage }}</div>
      </div>
      <div class="input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
        <div v-if="errorMessage === 'password'" class="error-message">{{ errorMessage }}</div>
      </div>
     <div class="button-container">
        <button type="submit" class="medium-button">{{ title }}</button>

        <router-link
          :to="props.buttonType === 'login' ? '/signup' : '/login'">
          {{ props.buttonType === 'login' ? 'Need an account? Sign Up' : 'Already have an account? Log In' }}
        </router-link>
      </div>
    </form>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/index.js';

const props = defineProps({
  title: String,
  buttonType: String,
  buttonText: String
});


const username = ref('');
const password = ref('');
const errorMessage = ref('');


// Form submit handler
const handleSubmit = async () => {
  if (props.buttonType === 'signup') {
    await handleSignUp();
  } else {
    await handleLogin();
  }
};

const handleSignUp = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/user/register', { username: username.value, password: password.value });
    if (response.data.success) {
      alert('Signup successful, please log in.');
      await router.push('/login')
    } else {
      alert('Signup failed: ' + "User already exists");
    }
  } catch (error) {
    alert('Signup failed: ' + "user already exists");
  }
};

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/user/login', {
        username: username.value,
        password: password.value
      })
      if (response.data.success) {
        localStorage.setItem('token', response.data.token)
        localStorage.setItem('username', username.value)
        alert('User logged in successfully')
        await router.push('/home') 
      } else {
        alert('Incorrect username or password')
      }
    } catch (error) {
      console.error(error)
      alert('Incorrect username or password')
    }
}
</script>

<style scoped>
.auth-form {
  max-width: 400px;
  margin: 100px auto; 
  padding: 2rem;
  background: #fff;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); 
  border-radius: 8px; 
  text-align: center; 
}
.button-container {
  display: flex;
  justify-content: space-between;

}

h2 {
  margin-bottom: 2rem; 
  color: #333; 
}

label {
  display: block;
  margin-bottom: 0.5rem; 
  color: #333; 
}

input[type='text'],
input[type='password'] {
  width: calc(100% - 1.5rem); 
  padding: 0.75rem;
  border: 2px solid #ccc; 
  border-radius: 4px; 
  margin-bottom: 1rem; 
}

.medium-button {
  display: block;
  width: 30%;
  padding: 1rem;
  border: none;
  border-radius: 4px;
  background-color: #f472b6; 
  color: white;
  margin-top: 1rem; 
  box-sizing: border-box;
  cursor: pointer; 
  font-size: 1rem;
}
.medium-button:hover {
  background-color: #f268aa; 
}

input[type='text']:focus,
input[type='password']:focus {
  outline: none;
  border-color: #f472b6; 
  box-shadow: 0 0 0 2px rgba(244, 114, 182, 0.5); 
}

@media (max-width: 768px) {
  .auth-form {
    margin: 50px auto;
  }
}
</style>
