<template>
  <div class="quiz-entry">
    <div class="right">
      <h3>{{ quiz.title }}</h3>
      <p>Category: {{ quiz.category }} - Difficulty: {{ quiz.difficulty }}</p>
    </div>
    <div class="buttons">
      <MediumButton type="play" @click="playQuiz(quiz.id)">Play</MediumButton>
      <MediumButton type="edit" @click="editQuiz(quiz.id)">Edit</MediumButton>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'
import MediumButton from './MediumButton.vue'

defineProps({
  quiz: {
    type: Object,
    required: true
  }
})

const emits = defineEmits(['play', 'edit'])

const playQuiz = (quizId) => {
  // Logic for playing a quiz can go here or you can emit an event
  emits('play', quizId)
}

const editQuiz = (quizId) => {
  // Logic for editing a quiz can go here or you can emit an event
  emits('edit', quizId)
}
</script>

<style scoped>
.quiz-entry {
  display: flex;
  padding: 1em;
  margin: 1em 0;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #fff;
}

.right {
  flex: 1;
  flex-direction: column;
}

.buttons {
  display: flex;
  gap: 1em;
  flex-direction: column;
}
</style>
