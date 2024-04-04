<template>
  <NavBar />
  <div class="your-quizzes-page">
    <h2>YOUR QUIZZES</h2>
    <div class="filters">
      <select v-model="selectedCategory">
        <option value="">Category</option>
        <option v-for="category in categories" :key="category" :value="category">
          {{ category }}
        </option>
      </select>
      <select v-model="selectedDifficulty">
        <option value="">Difficulty</option>
        <option v-for="difficulty in difficulties" :key="difficulty" :value="difficulty">
          {{ difficulty }}
        </option>
      </select>
      <input type="text" v-model="searchTerm" placeholder="Search your quizzes" />
    </div>
    <QuizEntry v-for="quiz in filteredQuizzes" :key="quiz.id" :quiz="quiz" />
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { ref, computed } from 'vue'
import NavBar from '@/components/NavBar.vue'
import QuizEntry from '@/components/QuizEntry.vue'
import { useQuizStore } from '@/stores/quizStore'

const quizStore = useQuizStore()
const categories = [
  'History',
  'Geography',
  'Science',
  'Sports',
  'Music',
  'Movies',
  'Technology',
  'Arts & Literature',
  'General Knowledge'
]
const difficulties = ['Easy', 'Medium', 'Hard']
const selectedCategory = ref('')
const selectedDifficulty = ref('')
const searchTerm = ref('')

const filteredQuizzes = computed(() => {
  return quizStore.quizzes.filter((quiz) => {
    return (
      (!selectedCategory.value || quiz.category === selectedCategory.value) &&
      (!selectedDifficulty.value || quiz.difficulty === selectedDifficulty.value) &&
      (!searchTerm.value || quiz.title.toLowerCase().includes(searchTerm.value.toLowerCase()))
    )
  })
})

onMounted(() => {
  quizStore.fetchQuizzes() // This will fetch quizzes when the component is mounted
})
</script>

<style scoped>
.your-quizzes-page {
  padding: 2rem;
  max-width: 1200px;
  margin: auto;
}

h2 {
  text-align: center;
  color: #333;
  margin-bottom: 1rem;
}

.filters {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
}

.filters select,
.filters input {
  padding: 0.5rem;
  margin-right: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.filters input {
  flex-grow: 1;
}

.quiz-entry {
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 1rem;
  margin-bottom: 1rem;
  background-color: white;
}

.quiz-entry h3 {
  font-size: 1.2rem;
  color: #333;
  margin-bottom: 0.5rem;
}

.quiz-entry p {
  font-size: 1rem;
  margin-bottom: 0.5rem;
}

.play-button,
.edit-button {
  background-color: #ff8bc3;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  margin-right: 0.5rem;
  cursor: pointer;
  border-radius: 4px;
  text-transform: uppercase;
  font-weight: bold;
}

.play-button:hover,
.edit-button:hover {
  background-color: #f362a8;
}

/* Responsiv design for mindre skjermer */
@media (max-width: 768px) {
  .filters {
    flex-direction: column;
    align-items: flex-start;
  }

  .filters select,
  .filters input {
    margin-bottom: 0.5rem;
    width: 100%;
  }

  .play-button,
  .edit-button {
    width: 100%;
    margin-bottom: 0.5rem;
  }

  .quiz-entry {
    padding: 0.5rem;
  }
}
</style>
