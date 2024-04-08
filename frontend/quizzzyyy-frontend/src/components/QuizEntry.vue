<template>
  <div class="quiz-entry" v-if="quiz">
    <div class="right">
      <h3>{{ quiz.title }}</h3>
      <p>Category: {{ quiz.category }} - Difficulty: {{ quiz.difficulty }}</p>
    </div>
    <div class="buttons"><button class="primary-button action-button delete-button" @click="deleteQuiz">Delete</button>
      <button class="primary-button action-button play-button" @click="startQuiz">Play</button>
      <button class="primary-button preview-button" @click="toggleCheatSheet">show cheat sheet</button>
    </div>
    <div v-if="showCheatSheet" class="modal">
      <h2>Cheat Sheet</h2>
      <ul>
        <li v-for="(question, index) in quiz.questions" :key="index">
          <p>Question: {{ question.question_text }}</p>
          <p>Answer: {{ question.answer }}</p>
        </li>
      </ul>
      <button @click="toggleCheatSheet">Close</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
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
const showCheatSheet = ref(false)

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

const toggleCheatSheet = () => {
  showCheatSheet.value = !showCheatSheet.value
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

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 1em;
  border-radius: 4px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
button.modal{
  display: flex;
  gap: 5px;
  flex-direction: column;
}

.right {
  flex: 1;
  flex-direction: column;
}
.buttons {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  gap: 10px;
}

.primary-button {
  padding: 10px 15px;
  border: none;
  cursor: pointer;
  font-weight: bold;
  text-transform: uppercase;
  transition: background-color 0.3s ease;
}
.delete-button {
  background-color: rgb(255, 105, 135);
}

.delete-button:hover {
  background-color: rgb(226, 62, 62);
}

.play-button {
   background-color: rgb(135, 255, 105);

}

.play-button:hover {
  background-color: rgb(62, 226, 62);
}

.preview-button {
  background-color: rgb(105, 135, 255);
}

.preview-button:hover {
  background-color: rgb(62, 62, 226);

}

@media (max-width: 768px) {

  .quiz-entry {
    flex-direction: column;
    align-items: center;
  }

  .buttons {
    flex-direction: row;
    justify-content: center;
    width: 100%;
    padding: 10px 0;
  }

  .primary-button {
    width: auto;
    padding: 10px;
  }

  .delete-button, .play-button, .preview-button {
    flex-grow: 1;
    margin: 0 5px;
  }
}
</style>
