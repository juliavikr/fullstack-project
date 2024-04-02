<template>
  <NavBar />
  <div class="library-view">
    <h2>YOUR LIBRARY</h2>
    <div class="activity-section">
      <h3>Activity:</h3>
      <ul class="activity-list">
        <!-- Anta at disse dataene kommer fra din brukers aktivitetslogg -->
        <li v-for="(activity, index) in activities" :key="index">
          {{ activity.quizTitle }}: {{ activity.score }}
        </li>
      </ul>
    </div>
    <div class="quizzes-list">
      <QuizEntry v-for="quiz in quizzes" :key="quiz.id" :quiz="quiz" />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import QuizEntry from '@/components/QuizEntry.vue'
import NavBar from '@/components/NavBar.vue'

// Mock data
const activities = ref([
  { quizTitle: 'THE US DURING THE 80’S', score: '10/10' },
  { quizTitle: 'THE US DURING THE 80’S', score: '9/10' },
  { quizTitle: 'THE US DURING THE 80’S', score: '7/10' }
  // ...andre aktiviteter
])

const quizzes = ref([
  // Mock data for quizer
  { id: 1, title: 'THE US DURING THE 80’S', category: 'History', difficulty: 'Hard' }
  // ...andre quizer
])
</script>

<style scoped>
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

.activity-section,
.quizzes-list {
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
