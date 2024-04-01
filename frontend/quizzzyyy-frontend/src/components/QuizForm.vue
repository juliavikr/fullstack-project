<template>
  <div class="quiz-creation-view">
    <div class="input-fields">
      <div class="form-group">
        <label for="quiz-title">Enter quiz title:</label>
        <input type="text" id="quiz-title" placeholder="Quiz Title" />
      </div>
      <div class="form-group">
        <label for="quiz-category">Category:</label>
        <select id="quiz-category">
          <option>Select Category</option>
          <!-- Alternativer her -->
        </select>
      </div>
      <div class="difficulty-container">
        <label for="difficulty-buttons">Difficulty:</label>
        <div class="difficulty-buttons">
          <SmallButton :is-active="difficulty === 'hard'" @click="setDifficulty('hard')"
            >Hard</SmallButton
          >
          <SmallButton :is-active="difficulty === 'medium'" @click="setDifficulty('medium')"
            >Medium</SmallButton
          >
          <SmallButton :is-active="difficulty === 'easy'" @click="setDifficulty('easy')"
            >Easy</SmallButton
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import SmallButton from '@/components/SmallButton.vue'
import { ref } from 'vue'

const difficulty = ref('') // starter uten en valgt vanskelighetsgrad

function setDifficulty(level) {
  difficulty.value = level
}
</script>

<style scoped>
.quiz-creation-view {
  margin: auto;
  padding: 2rem;
  align-content: center;
  align-items: center;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;
  align-items: center;
}

.form-group label {
  margin-bottom: 0.5rem;
}

.form-group input[type='text'],
.form-group select {
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.difficulty-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 2rem;
}

.difficulty-container label {
  margin-bottom: 1rem;
}

.difficulty-buttons {
  display: flex;
  justify-content: center;
  gap: 10px; /* Gir litt plass mellom knappene */
}

.add-question {
  display: flex;
  justify-content: center;
  margin-top: 2rem;
}

/* Resten av dine SmallButton og MediumButton stiler */
/* Tilpass etter behov basert på ditt designsystem eller eksisterende stilguide */
</style>
