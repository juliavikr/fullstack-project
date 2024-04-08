<template>
  <div class="quiz-entry" v-if="quiz">
    <div class="right">
      <h3>{{ quiz.title }}</h3>
      <p>Category: {{ quiz.category }} - Difficulty: {{ quiz.difficulty }}</p>
      <button class="primary-button action-button delete-button" @click="deleteQuiz">Delete</button>
    </div>
    <div class="buttons">
      <button class="primary-button action-button play-button" @click="startQuiz">Play</button>
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

const deleteQuiz = async () => {
  try {
    const token = localStorage.getItem('token')
    if (!token) {
      throw new Error('No authentication token found')
    }
    await axios.delete(`http://localhost:8080/quiz/${props.quiz.id}`, {
      headers: {
        Authorization: 'Bearer ' + token
      }
    })
    console.log('Quiz deleted successfully')
    quizStore.removeQuiz(props.quiz.id)
    await quizStore.fetchQuizzes()
  } catch (error) {
    console.error('Failed to delete quiz:', error)

  }
}
</script>

<style scoped>
.quiz-entry {
  display: flex;
  padding: 0.5em;
  margin:  20px;
  border-radius: 4px;
  background-color: rgb(255, 255, 255);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.right {
  flex: 1;
  flex-direction: column;
}

.buttons {
  display: flex;
  gap: 5px;
  flex-direction: column;
}
.delete-button {
  top: 0;
  right: 0;
  background-color: rgb(255, 105, 135);
  border-radius: 10%;
  align-items: center;
}
.delete-button:hover {
  background-color: rgb(226, 62, 62);
}
.action-button {
  margin: 10px;

}
.play-button {
  width: 200px;
  margin-top: 50px;
  height: 50px;

}
/* Responsiv design for mindre skjermer */
@media (max-width: 768px) {
  .quiz-entry {
    flex-direction: column;
  }
}
</style>
