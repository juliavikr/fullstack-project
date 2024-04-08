<template>
  <div class="play-view">
    <h2>{{ quizTitle }}</h2>

    <div v-if="currentQuestion" class="question-card">
      <p>{{ currentQuestion.question_text }}</p>
      <input type="text" placeholder="Your answer" v-model="userAnswer" />
       <p v-if="isCorrectAnswer !== null">
         Your answer is <strong>{{ isCorrectAnswer ? 'correct' : 'incorrect' }}</strong>.
        </p>
      <div class = "actions">
        <button @click="submitAndNext" v-if="!isLastQuestion">Submit</button>
        <button @click="submitAndNext" v-if="!isLastQuestion">Next</button>
        <button @click="finishQuiz" v-if="isLastQuestion">Finish</button>
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

const quizTitle = computed(() => store.currentQuiz?.title || '')

const currentQuestion = computed(() => store.currentQuestion)

const isLastQuestion = computed(() => store.isLastQuestion)

const submitAndNext = () => {
  console.log('Answer before submission:', userAnswer.value)
  const isCorrect = store.submitAnswer(userAnswer.value)
  isCorrectAnswer.value = isCorrect
  console.log('Index after submission:', store.currentQuestionIndex)
  userAnswer.value = ''
}

const finishQuiz = () => {
  console.log('Finishing quiz with last answer:', userAnswer.value)
  store.submitAnswer(userAnswer.value)
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
      store.setCurrentQuiz(store.quizzes[0])
    } else {
      console.error('No quizzes were loaded')
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
</style>
