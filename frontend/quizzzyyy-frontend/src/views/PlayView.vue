<template>
  <div class="play-view">
    <h2>THE US DURING THE 80'S</h2>
    <div class="question-card">
      <p>Who was the US president during the 80's?</p>
      <input type="text" placeholder="Answer:" v-model="userAnswer" />
      <MediumButton @click="submitAnswer">Next</MediumButton>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import MediumButton from '@/components/MediumButton.vue'

const userAnswer = ref('')

const submitAnswer = () => {
  // Logikk for å behandle svaret
  // For eksempel, valider svaret og gå til neste spørsmål eller avslutt quizen
}
</script>

<style scoped>
.play-view {
  max-width: 600px;
  margin: 2rem auto;
  text-align: center;
}

.question-card {
  background-color: #fff;
  padding: 2rem;
  margin-top: 2rem;
  border: 1px solid #ccc;
  border-radius: 10px;
}

input[type='text'] {
  width: 100%;
  padding: 1rem;
  margin-top: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
