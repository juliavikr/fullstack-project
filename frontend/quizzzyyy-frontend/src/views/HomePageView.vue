<template>
  <div class="home-container">
    <NavBar />
    <div class="action-buttons">
      <BigButton text="PLAY!!" @click="playQuiz" />
      <BigButton text="CREATE A NEW QUIZ" @click="createQuiz" />
    </div>
  </div>
</template>

<script setup>
import NavBar from '../components/NavBar.vue'
import BigButton from '../components/BigButton.vue'

const playQuiz = () => {
  // Define logic for playing game
}

const createQuiz = () => {
  // Define logic for creating a quiz
}
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* Start aligning content from the top */
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
