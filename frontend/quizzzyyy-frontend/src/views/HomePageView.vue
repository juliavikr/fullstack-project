<template>
  <div class="home-container">
    <NavBar />
    <div class="action-buttons">
      <BigButton to="/your-quizzes">Your quizzes</BigButton>
      <BigButton to="/create-quiz">Create a new quiz</BigButton>
    </div>
  </div>
</template>

<script setup>
import NavBar from '../components/NavBar.vue'
import BigButton from '../components/BigButton.vue'
</script>

<style scoped>
.home-container {
  font: 16px 'Poppins', sans-serif;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  height: 100vh;
}

.action-buttons {
  display: flex;
  flex-direction: column; /* Setter knappene under hverandre */
  align-items: center; /* Senterer knappene horisontalt */
  width: 100%; /* Setter bredden på knappene til å passe containeren */
  gap: 30px; /* Gir litt plass mellom knappene */
  margin-top: 150px; /* Eller bruk margin-top istedenfor padding-top om nødvendig */
  align-items: center;
}
</style>
