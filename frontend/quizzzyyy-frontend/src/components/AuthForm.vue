<template>
  <div class="auth-form">
    <h2>{{ title }}</h2>
    <form @submit.prevent="saveData">
      <div class="input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <MediumButton :type="buttonType">{{ buttonText }}</MediumButton>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import MediumButton from '@/components/MediumButton.vue'
import axios from "axios";

const props = defineProps({
  title: String,
  buttonType: String,
  buttonText: String
})

const user = ref({
  username: '',
  password: ''
})

const saveData = () => {
  axios.post('http://localhost:8080/api/user/register', user.value)
      .then(({ data }) => {
       console.log(data);
       try {
         alert("User registered successfully")
       } catch(err){
         alert("User registration failed")
       }
      })
}
</script>
/*const onSubmit = async () => {
  console.log('Submitting form...')
  if (props.buttonType === 'login'){
    try {
      const response = await AuthService.logIn({ username: username.value, password: password.value })
      // Her bør du lagre JWT token du får fra serveren, for eksempel i localStorage
      localStorage.setItem('token', response.data.jwt)
      await router.push('/home') // Naviger til hjemmesiden etter vellykket innlogging
    } catch (error) {
      alert('Feil ved innlogging') // Vis en feilmelding til brukeren
    }
  } else if (props.buttonType === 'signup') {
    try {
      const response = await AuthService.signUp({ username: username.value, password: password.value })
      alert('Registrering vellykket')
          console.log("successful registration")
      // Vis en suksessmelding
      await router.push('/login') // Naviger til innloggingssiden
    } catch (error) {
      alert('Feil ved registrering') // Vis en feilmelding til brukeren
    }
;
  }
}*/



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

/* Stil knappen slik at den matcher bildet */
.medium-button {
  display: block;
  width: 100%;
  padding: 1rem;
  border: none; /* Fjern border */
  border-radius: 4px;
  background-color: #f472b6; /* Velg en farge som matcher bildet */
  color: white;
  margin-top: 1rem; /* Avstand mellom passord felt og knapp */
  box-sizing: border-box; /* For å inkludere polstring i bredde-beregningen */
  cursor: pointer; /* Vis en peker hånd når du holder over knappen */
  font-size: 1rem; /* Standard tekststørrelse for knappen */
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
