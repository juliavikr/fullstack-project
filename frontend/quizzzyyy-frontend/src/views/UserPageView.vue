<template>
  <NavBar />
  <div class="user-page-container">
    <div class="user-info">
      <h2>Username: {{ username }}</h2>
      <MediumButton type="primary" @click="logout">Log out</MediumButton>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import MediumButton from '@/components/MediumButton.vue'
import NavBar from '@/components/NavBar.vue'
import { useRouter } from 'vue-router'

const username = ref('')
const newPassword = ref('')
const showChangePassword = ref(false)
const router = useRouter()

onMounted(() => {
  // Hent brukernavnet fra localStorage og oppdater den reaktive referansen
  const storedUsername = localStorage.getItem('username')
  if (storedUsername) {
    username.value = storedUsername
  }
})

const changePassword = () => {
  // Kall til backend for å oppdatere passordet
  console.log('New password submitted:', newPassword.value)
  showChangePassword.value = false
  newPassword.value = ''
}

const logout = () => {
  // Logg ut logikk her
  console.log('Brukeren logget ut.')
  localStorage.removeItem('token')
  localStorage.removeItem('username') // Fjern brukernavnet fra localStorage
  router.push('/')
}
</script>


<style scoped>
.user-page-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* Start aligning content from the top */
  height: 100vh;
  align-items: center;
}

.user-info,
.change-password-modal {
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px; /* avstand mellom elementene */
  display: flex;
  flex-direction: column; /* Setter knappene under hverandre */
  align-items: center; /* Senterer knappene horisontalt */
  width: 300px; /* Setter bredden på knappene til å passe containeren */
  gap: 30px; /* Gir litt plass mellom knappene */
  margin-top: 40px; /* Eller bruk margin-top istedenfor padding-top om nødvendig */
  justify-content: center;
}

.change-password-button,
.submit-new-password {
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  margin-top: 10px; /* avstand fra tittel/tekstfelt */
  cursor: pointer;
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  margin-bottom: 5px;
}

input[type='password'] {
  padding: 10px;
  margin-bottom: 10px; /* avstand fra neste element */
  border: 1px solid #ccc;
  border-radius: 5px;
}

/* Tilpasse input-felter når de er i fokus */
input[type='text']:focus,
input[type='password']:focus {
  outline: none;
  border-color: #f472b6; /* Kanten farge for å matche knappen */
  box-shadow: 0 0 0 2px rgba(244, 114, 182, 0.5); /* Legg til en subtil skygge rundt fokusert input */
}

.btn-logout {
  position: absolute;
  right: 2rem; /* 2rem fra høyre kant av user-page-container */
  bottom: 2rem; /* 2rem fra bunnen av user-page-container */
  padding: 0.5rem 1rem;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}
</style>
