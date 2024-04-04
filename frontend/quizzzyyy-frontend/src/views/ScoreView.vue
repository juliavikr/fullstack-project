<template>
  <div class="score-view">
    <h2>Quiz Completed!</h2>
    <div class="score-card">
      <p>Your Score:</p>
      <p class="score">{{ score }} / {{ totalQuestions }}</p>
      <button @click="acknowledgeScore">Great</button>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useQuizStore } from '@/stores/quizStore'
import { useRouter } from 'vue-router'

const quizStore = useQuizStore()
const router = useRouter()

const score = computed(() => quizStore.score.value)
const totalQuestions = computed(() => quizStore.currentQuiz.value.questions.length)

const acknowledgeScore = () => {
  quizStore.resetQuiz()
  router.push('/')
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
