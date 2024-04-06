<template>
  <NavBar />
  <div class="library-view">
    <h2>YOUR LIBRARY</h2>
    <div class="activity-section">
      <h3>Activity:</h3>
      <div class="scroll-box">
        <ul class="activity-list">
          <li v-for="activity in activities" :key="activity.id">
            {{ activity.quizTitle }}: {{ activity.score }}
          </li>
        </ul>
      </div>
    </div>
    <div class="quizzes-section">
      <h3>Available Quizzes:</h3>
      <div class="quizzes-list">
        <QuizEntry v-for="quiz in quizzes" :key="quiz.id" :quiz="quiz" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed } from 'vue'
import NavBar from '@/components/NavBar.vue'
import QuizEntry from '@/components/QuizEntry.vue'
import { useQuizStore } from '@/stores/quizStore'

const quizStore = useQuizStore()
const activities = computed(() => quizStore.activities)
const quizzes = computed(() => {
  return quizStore.quizzes
})

onMounted(() => {
  // Assume fetchQuizzes is an action that fetches quizzes and updates the store
  const quizStore = useQuizStore()
  quizStore.loadState()
  quizStore.fetchQuizzes() // Fetch quizzes when the component mounts
})
</script>

<style scoped>
.activity-section {
  /* Styles for the activity section */
  max-height: 200px; /* Or any other value */
}

.activity-scroll-box {
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 20px; /* Add space between the activity list and the quizzes list */
  overflow-y: auto; /* This will create a scrollbar if content overflows */
}

.activity-list {
  /* Styles for the activity list */
  list-style-type: none; /* Remove list bullets */
  padding: 0; /* Remove padding */
  margin: 0; /* Remove margins */
}

.activity-list li {
  /* Styles for each activity list item */
  margin-bottom: 10px; /* Add space between items */
}

.library-view {
  padding: 2rem;
  max-width: 1200px;
  margin: auto;
}

h2 {
  text-align: center;
  color: #333;
  margin-bottom: 1rem;
}

.activity-section {
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-bottom: 1rem;
  padding: 1rem;
}

.activity-list {
  list-style-type: none;
  padding: 0;
}

.activity-list li {
  margin-bottom: 0.5rem;
}

/* Legg til ytterligere stiler etter behov */
</style>
