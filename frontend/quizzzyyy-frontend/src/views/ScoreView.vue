<template>
  <div class="score-view">
    <h2>THE US DURING THE 80'S</h2>
    <div class="score-card">
      <p>Score:</p>
      <p class="score">8/10</p>
      <MediumButton @click="acknowledgeScore">Great</MediumButton>
    </div>
  </div>
</template>

<script setup>
//import { ref } from 'vue'
import MediumButton from '@/components/MediumButton.vue'

const acknowledgeScore = () => {
  // Logikk for å håndtere når brukeren aksepterer sin score
  // For eksempel, omdiriger til en annen side eller vis en melding
}
</script>

<style scoped>
.score-view {
  max-width: 600px;
  margin: 2rem auto;
  text-align: center;
}

.score-card {
  background-color: #fff;
  padding: 2rem;
  margin-top: 2rem;
  border: 1px solid #ccc;
  border-radius: 10px;
}

.score {
  font-size: 2rem;
  color: #333;
  margin: 2rem 0;
}
</style>
