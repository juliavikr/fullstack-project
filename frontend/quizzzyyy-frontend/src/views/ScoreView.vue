<template>
  <div class="score-view">
    <h2>Quiz Completed!</h2>
    <div class="score-card">
      <p>Your Score:</p>
      <!-- Remove the .value from score and totalQuestions -->
      <p class="score">{{ score }} / {{ totalQuestions }}</p>
      <SmallButton @click="acknowledgeScore">Great</SmallButton>
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed } from 'vue'
import { useQuizStore } from '@/stores/quizStore'
import { useRouter } from 'vue-router'
import SmallButton from '@/components/SmallButton.vue'

const quizStore = useQuizStore()
const router = useRouter()

// Access store properties directly, not with .value
const score = computed(() => {
  console.log('Final score:', quizStore.score)
  return quizStore.score
})

const totalQuestions = computed(() => {
  console.log('Total questions:', quizStore.currentQuiz.questions.length)
  return quizStore.currentQuiz.questions.length
})

const acknowledgeScore = () => {
  quizStore.resetQuiz()
  router.push('/home') // Or wherever you want to navigate after acknowledging the score
}

onMounted(() => {
  const quizStore = useQuizStore()
  quizStore.loadState()
})
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
