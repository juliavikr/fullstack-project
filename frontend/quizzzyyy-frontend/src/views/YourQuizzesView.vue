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
    <div v-if="filteredQuizzes.length === 0" class="no-quizzes-message">
      No quizzes found. Try adjusting your search or filter criteria.
    </div>
    <QuizEntry v-for="quiz in filteredQuizzes" :key="quiz.id" :quiz="quiz" />
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue'
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
  'Arts_Literature',
  'General_Knowledge'
]
const difficulties = ['Easy', 'Medium', 'Hard']
const selectedCategory = ref('')
const selectedDifficulty = ref('')
const searchTerm = ref('')

const filteredQuizzes = computed(() => {
  return quizStore.quizzes.filter((quiz) => {
    const categoryMatch =
      !selectedCategory.value ||
      quiz.category.toLowerCase() === selectedCategory.value.toLowerCase()
    const difficultyMatch =
      !selectedDifficulty.value ||
      quiz.difficulty.toLowerCase() === selectedDifficulty.value.toLowerCase()
    const searchTermMatch =
      !searchTerm.value || quiz.title.toLowerCase().includes(searchTerm.value.toLowerCase())
    return categoryMatch && difficultyMatch && searchTermMatch
  })
})

onMounted(() => {
  quizStore.fetchQuizzes().then(() => {
    console.log('Quizzes after fetch:', quizStore.quizzes)
  })
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

.quiz-entry h3 {
  font-size: 1.2rem;
  color: #333;
  margin-bottom: 0.5rem;
}

.quiz-entry p {
  font-size: 1rem;
  margin-bottom: 0.5rem;
}

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
}
</style>
