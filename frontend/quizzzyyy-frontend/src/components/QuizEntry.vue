<template>
  <div class="quiz-entry">
    <div class="right">
      <h3>{{ quiz.title }}</h3>
      <p>Category: {{ quiz.category }} - Difficulty: {{ quiz.difficulty }}</p>
    </div>
    <div class="buttons">
      <MediumButton @click="startQuiz">Play</MediumButton>
      <MediumButton @click="editQuiz">Edit</MediumButton>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'
import MediumButton from '@/components/MediumButton.vue'
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
  router.push({ name: 'EditQuiz', params: { id: props.quiz.id } })
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
