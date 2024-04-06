<template>
  <NavBar />
  <div class="quiz-creation-view">
    <h1>Create a new quiz</h1>
    <div class="input-fields">
      <div class="form-group">
        <label for="quiz-title">Enter quiz title:</label>
        <input type="text" id="quiz-title" v-model="quiz.title" placeholder="Quiz Title" />
      </div>
      <div class="form-group">
        <label for="quiz-category">Category:</label>
        <select id="quiz-category" v-model="quiz.category">
          <option value="">Select Category</option>
          <option value="general_knowledge">General Knowledge</option>
          <option value="music">Music</option>
          <option value="sports">Sports</option>
          <option value="history">History</option>
          <option value="science">Science</option>
          <option value="geography">Geography</option>
          <option value="arts_literature">Arts & Literature</option>
          <option value="movies">Movies</option>
          <option value="technology">Technology</option>
        </select>
      </div>
      <div class="difficulty-container">
        <label for="difficulty-buttons">Difficulty:</label>
        <div class="difficulty-buttons">
          <SmallButton :is-active="quiz.difficulty === 'hard'" @click="setDifficulty('hard')"
            >Hard</SmallButton
          >
          <SmallButton :is-active="quiz.difficulty === 'medium'" @click="setDifficulty('medium')"
            >Medium</SmallButton
          >
          <SmallButton :is-active="quiz.difficulty === 'easy'" @click="setDifficulty('easy')"
            >Easy</SmallButton
          >
        </div>
      </div>
    </div>
    <div class="question-list">
      <QuestionCreationBox
        v-for="(question, index) in quiz.questions"
        :key="index"
        :question="question"
        :index="index"
        @remove="removeQuestion(index)"
        @update:question="updateQuestion"
      />
    </div>
    <AddQuestionButton @click="addQuestion" />
    <SmallButton type="button" class="save-quiz-button" :disabled="isSaving" @click="saveQuiz">
      Save Quiz
    </SmallButton>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import axios from 'axios'
import NavBar from '@/components/NavBar.vue'
import QuestionCreationBox from '@/components/QuestionCreationBox.vue'
import AddQuestionButton from '@/components/AddQuestionButton.vue'
import SmallButton from '@/components/SmallButton.vue'
import router from '@/router/index.js'

// Reactive state for the new quiz
const quiz = reactive({
  title: '',
  category: '',
  difficulty: '',
  questions: [{ question_text: '', answer: '' }]
})

const setDifficulty = (difficultyLevel) => {
  quiz.difficulty = difficultyLevel
}

const addQuestion = () => {
  quiz.questions.push({ question_text: '', answer: '' })
}

const removeQuestion = (index) => {
  quiz.questions.splice(index, 1)
}

const updateQuestion = (updatedQuestion) => {
  const { index, ...questionData } = updatedQuestion
  quiz.questions.splice(index, 1, questionData)
}

let isSaving = false

const saveQuiz = async () => {
  if (isSaving) return
  console.log('Attempting to save quiz', quiz)
  isSaving = true

  // Retrieve the token from local storage
  const token = localStorage.getItem('token')
  axios.defaults.headers.common['Authorization'] = `Bearer ${token}`

  try {
    // Include the Authorization header with the token
    const response = await axios.post('http://localhost:8080/quiz', quiz, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    console.log('Quiz saved', response.data)

    // Show the success message
    alert('Quiz saved successfully!')

    // Redirect the user to the "yourquizzes" page
    router.push('/your-quizzes')
  } catch (error) {
    console.error('There was an error saving the quiz', error)
    // Show the error message
    alert('There was an error saving the quiz.')
  } finally {
    isSaving = false
  }
}
</script>

<style scoped>
.quiz-creation-view {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%; /* Full skjermbredde */
  padding: 20px; /* Litt padding rundt hele skjemaet, juster dette etter behov */
  box-sizing: border-box; /* Sørger for at padding ikke legger til bredden */
  gap: 20px; /* Mellomrom mellom elementene */
}

.question-list {
  width: 95%;
  margin-bottom: 1rem; /* Mellomrom mellom boksene */
  display: flex;
  justify-items: center;
  gap: 20px;
  flex-direction: column;
}

.add-question-button {
  align-self: center; /* Sentrerer knappen i flex-kontaineren */
  /* ... resten av dine stiler ... */
}

h1 {
  text-align: center; /* Sentrerer tekst innenfor h1 */
  width: 100%; /* Full bredde */
  margin-bottom: 1rem; /* Mellomrom under h1 */
}

.save-quiz-button {
  position: fixed; /* Holder knappen fast på skjermen */
  bottom: 20px; /* 20px fra bunnen av skjermen */
  right: 20px; /* 20px fra høyre side av skjermen */
  padding: 10px 20px; /* Padding rundt tekst */
  border: none; /* Fjerner standard kant */
  cursor: pointer; /* Endrer musepekeren til en peker */
}

.difficulty-buttons {
  display: flex;
  gap: 10px;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;
  align-items: center;
}

.form-group label {
  margin-bottom: 0.5rem;
}

.form-group input[type='text'],
.form-group select {
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.difficulty-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 2rem;
}

.difficulty-container label {
  margin-bottom: 1rem;
}

.difficulty-buttons {
  display: flex;
  justify-content: center;
  gap: 10px; /* Gir litt plass mellom knappene */
}
</style>
