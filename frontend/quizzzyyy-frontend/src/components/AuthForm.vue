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
        localStorage.setItem('username', username.value) // Lagre brukernavnet i localStorage
        alert('User logged in successfully')
        await router.push('/home') // Omdiriger til brukersiden etter vellykket innlogging
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
  max-width: 400px; /* Juster bredden slik det passer med designet */
  margin: 100px auto; /* Øk toppmarg for å sentrere vertikalt, juster etter behov */
  padding: 2rem;
  background: #fff;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Myk opp skyggen litt */
  border-radius: 8px; /* Avrund hjørnene for å matche designet */
  text-align: center; /* Sentrer tekst innenfor */
}
.button-container {
  display: flex;
  justify-content: space-between;

}


h2 {
  margin-bottom: 2rem; /* Gi mer plass under tittelen */
  color: #333; /* Mørkere farge for bedre lesbarhet */
}

label {
  display: block;
  margin-bottom: 0.5rem; /* Øk avstanden litt under label */
  color: #333; /* Samme som for h2 for konsistens */
}

input[type='text'],
input[type='password'] {
  width: calc(100% - 1.5rem); /* Trekk fra polstring for å unngå overflow */
  padding: 0.75rem;
  border: 2px solid #ccc; /* Tykkere border for å matche designet */
  border-radius: 4px; /* Litt mindre avrundet enn boksen */
  margin-bottom: 1rem; /* Legg til avstand mellom input-feltene og knappen */
}

.medium-button {
  display: block;
  width: 30%;
  padding: 1rem;
  border: none;
  border-radius: 4px;
  background-color: #f472b6; /* Velg en farge som matcher bildet */
  color: white;
  margin-top: 1rem; /* Avstand mellom passord felt og knapp */
  box-sizing: border-box; /* For å inkludere polstring i bredde-beregningen */
  cursor: pointer; /* Vis en peker hånd når du holder over knappen */
  font-size: 1rem; /* Standard tekststørrelse for knappen */
}
.medium-button:hover {
  background-color: #f268aa; /* Litt mørkere farge ved hover for en subtil effekt */
}

/* Tilpasse input-felter når de er i fokus */
input[type='text']:focus,
input[type='password']:focus {
  outline: none;
  border-color: #f472b6; /* Kanten farge for å matche knappen */
  box-shadow: 0 0 0 2px rgba(244, 114, 182, 0.5); /* Legg til en subtil skygge rundt fokusert input */
}

/* Gjør endringer her for å tilpasse mellomrom og responsive design */
@media (max-width: 768px) {
  .auth-form {
    margin: 50px auto; /* Mindre vertikal margin for mindre skjermer */
  }
}
</style>
