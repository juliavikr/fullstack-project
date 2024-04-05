<template>
  <div class="play-view">
    <h2>{{ quizTitle }}</h2>
    <!-- Ensure you are using "question_text" to match the backend field name -->
    <div v-if="currentQuestion" class="question-card">
      <p>{{ currentQuestion.question_text }}</p>
      <input type="text" placeholder="Your answer" v-model="userAnswer" />
      <button @click="submitAndNext" v-if="!isLastQuestion">Next</button>
      <button @click="finishQuiz" v-if="isLastQuestion">Finish</button>
    </div>
    <div v-else>
      <!-- You can put a loading indicator here or a message if there are no questions -->
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

const quizTitle = computed(() => store.currentQuiz?.title || '')

const currentQuestion = computed(() => store.currentQuestion)

const isLastQuestion = computed(() => store.isLastQuestion)

// Submit answer and handle navigation
const submitAndNext = () => {
  console.log('Answer before submission:', userAnswer.value)
  store.submitAnswer(userAnswer.value)
  console.log('Index after submission:', store.currentQuestionIndex)
  userAnswer.value = ''
}

// Finish quiz
const finishQuiz = () => {
  console.log('Finishing quiz with last answer:', userAnswer.value)
  store.submitAnswer(userAnswer.value) // Make sure to submit the last answer
  console.log('Navigating to score view')
  router.push('/score')
}

// Fetch quizzes when component is mounted
onMounted(async () => {
  if (!store.currentQuiz) {
    // If there's no current quiz set, try to fetch the quizzes
    await store.fetchQuizzes()

    // After fetching, check if we successfully got quizzes
    if (store.quizzes.length > 0) {
      // Set the first quiz as current
      store.setCurrentQuiz(store.quizzes[0])
    } else {
      // Handle the case where no quizzes were fetched
      console.error('No quizzes were loaded')
      // Potentially set an error state here
    }
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
