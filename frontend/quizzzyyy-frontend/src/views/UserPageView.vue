<template>
  <div class="user-page-container">
    <NavBar />
    <div class="user-info">
      <div class="user-details">
        <h2>Username: {{ username }}</h2>
        <MediumButton type="primary" @click="showChangePassword = true">
          Change password
        </MediumButton>
      </div>
    </div>
    <div v-if="showChangePassword" class="change-password-modal">
      <form @submit.prevent="changePassword">
        <div class="form-group">
          <label for="new-password">New password:</label>
          <input type="password" id="new-password" v-model="newPassword" required />
        </div>
        <MediumButton type="primary" @click="changePassword"> Submit new password </MediumButton>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import MediumButton from '@/components/MediumButton.vue'
import NavBar from '@/components/NavBar.vue'

const username = ref('Andramun') // Dette vil til slutt komme fra din brukerstatustjeneste
const newPassword = ref('')
const showChangePassword = ref(false)

const changePassword = () => {
  // Kall til backend for å oppdatere passordet
  console.log('New password submitted:', newPassword.value)
  showChangePassword.value = false // Lukk modalen
  newPassword.value = '' // Tilbakestill passordfeltet
}
</script>

<style scoped>
.user-page-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* Start aligning content from the top */
  height: 100vh;
}

.user-info,
.change-password-modal {
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px; /* avstand mellom elementene */
  width: 300px; /* eller tilpasset bredden din */
}

.change-password-button,
.submit-new-password {
  background-color: #ff8bc3;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  margin-top: 10px; /* avstand fra tittel/tekstfelt */
  cursor: pointer;
}

.change-password-button:hover,
.submit-new-password:hover {
  background-color: #d76b9c; /* en mørkere nyanser av knappens farge */
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
</style>
