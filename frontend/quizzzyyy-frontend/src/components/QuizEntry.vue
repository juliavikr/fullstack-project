<template>
  <div class="quiz-entry">
    <div class="right">
      <h3>{{ quiz.title }}</h3>
      <p>Category: {{ quiz.category }} - Difficulty: {{ quiz.difficulty }}</p>
      <button @click="deleteQuiz">Delete Quiz</button>
    </div>
    <div class="buttons">
      <button @click="startQuiz">Play</button>
      <button @click="editQuiz">Edit</button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
import { defineProps } from 'vue'
import { useRouter } from 'vue-router'
import { useQuizStore } from '@/stores/quizStore'

const props = defineProps({
  quiz: {
    type: Object,
    required: true
  }
})

const router = useRouter()
const quizStore = useQuizStore()

const startQuiz = () => {
  quizStore.setCurrentQuiz(props.quiz)
  router.push('/play')
  console.log('Starting quiz...')
}

const editQuiz = () => {
  // Assuming you have a route named 'EditQuiz' and use the quiz ID as a parameter
  router.push({ name: 'CreateQuiz', params: { id: props.quiz.id } })
}

const deleteQuiz = async () => {
  try {
    const token = localStorage.getItem('token')
    if (!token) {
      throw new Error('No authentication token found')
    }

    const response = await axios.delete(`http://localhost:8080/quiz/${props.quiz.id}`, {
      headers: {
        Authorization: 'Bearer ' + token
      }
    })

    console.log('Quiz deleted successfully')
    quizStore.removeQuiz(props.quiz.id)
    quizStore.fetchQuizzes()
    // Optional: Redirect to another page if needed
    // router.push('/some-other-page');
  } catch (error) {
    console.error('Failed to delete quiz:', error)
    // Handle any additional error logging or user feedback
  }
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

/* Responsiv design for mindre skjermer */
@media (max-width: 768px) {
  .quiz-entry {
    flex-direction: column;
  }
}
</style>
