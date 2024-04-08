<template>
<div class="play-view">
    <h2>{{ quizTitle }}</h2>

    <div v-if="currentQuestion" class="question-card">
      <p>{{ currentQuestion.question_text }}</p>
      <input type="text" placeholder="Your answer" v-model="userAnswer" :disabled="hasSubmitted" />
      <p v-if="isCorrectAnswer !== null">
        Your answer was <strong>{{ isCorrectAnswer ? 'correct' : 'incorrect' }}</strong>
        <span v-if="!isCorrectAnswer"> | The correct answer is <strong> {{ currentCorrectAnswer }}</strong></span>
      </p>
      <div class="actions">
        <button class=" primary-button quizBtn" @click="submitAnswer" :disabled="userAnswer === '' || hasSubmitted">Submit</button>
        <button class=" primary-button quizBtn" @click="nextQuestion" v-if="!isLastQuestion" :disabled="userAnswer === '' || isCorrectAnswer === null">Next</button>
        <button class=" primary-button quizBtn" @click="finishQuiz" v-if="isLastQuestion" :disabled="userAnswer === '' || isCorrectAnswer === null">Finish</button>
      </div>
    </div>
    <div v-else>
      <p>Loading questions...</p>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue'
import { useQuizStore } from '@/stores/quizStore'
import { useRouter } from 'vue-router'

const store = useQuizStore()
const router = useRouter()
const userAnswer = ref('')
const isCorrectAnswer = ref(null)
const hasSubmitted = ref(false) // New reactive variable

const quizTitle = computed(() => store.currentQuiz?.title || '')

const currentQuestion = computed(() => store.currentQuestion)

const isLastQuestion = computed(() => store.isLastQuestion)
const currentCorrectAnswer = computed(() => store.currentQuestion.answer)

const submitAnswer = () => {
  console.log('Answer before submission:', userAnswer.value)
  const isCorrect = store.submitAnswer(userAnswer.value)
  isCorrectAnswer.value = isCorrect
  console.log('Index after submission:', store.currentQuestionIndex)
  hasSubmitted.value = true // Set to true after submitting an answer
}

const nextQuestion = () => {
  if (!isLastQuestion.value) {
    store.currentQuestionIndex++
    userAnswer.value = ''
    isCorrectAnswer.value = null
    hasSubmitted.value = false // Reset to false when moving to the next question
  } else {
    finishQuiz()
  }
}
const finishQuiz = () => {
  console.log('Finishing quiz with last answer:', userAnswer.value)
  console.log('Navigating to score view')
  store.recordActivity(quizTitle.value, store.score) // Record the activity
  router.push('/score') // Navigate to the score page
}

onMounted(() => {
  const savedIndex = localStorage.getItem('currentQuestionIndex');
  if (savedIndex !== null) {
    store.currentQuestionIndex = parseInt(savedIndex, 10);

  }
})
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
  margin: 2rem;
  border: 1px solid #ccc;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

input[type='text'] {
  width: calc(100% - 2rem);
  padding: 1rem;
  margin-top: 1rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.quizBtn:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

</style>
