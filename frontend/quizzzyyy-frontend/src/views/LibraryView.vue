<template>
  <NavBar />
  <div class="library-view">
    <h2>YOUR LIBRARY</h2>
    <div class="activity-section">
      <h3>Quiz activity since logged in:</h3>
      <div class="scroll-box">
        <ul class="activity-list">
          <li v-for="(activity, index) in activities" :key="index">
        You scored {{ activity.score }} out of {{ activity.totalQuestions }} on the quiz "{{ activity.quizTitle }}" at {{ new Date(activity.timestamp).toLocaleString() }}
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
  const quizStore = useQuizStore()
  quizStore.loadState()
  quizStore.fetchQuizzes()
})

</script>

<style scoped>

.scroll-box {
  max-height: 100px;
  overflow-y: auto; 
  overflow-x: hidden; 
  margin: 10px 0; 
  padding: 10px; 
}

.activity-section {
  max-height: 200px;
}

.activity-scroll-box {
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 20px;
  overflow-y: auto;
}

.activity-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.activity-list li {
  margin-bottom: 10px;
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

</style>
