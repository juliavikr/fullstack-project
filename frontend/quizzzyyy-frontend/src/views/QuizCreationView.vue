<template>
  <NavBar />
  <div class="quiz-creation-view">
    <h1>Create a new quiz</h1>
    <QuizForm />
    <div class="question-creation-box" v-for="(question, index) in questions" :key="index">
      <!-- Legg til en lytter for remove-hendelsen og kall removeQuestion med index -->
      <QuestionCreationBox @remove="removeQuestion(index)" />
    </div>
    <AddQuestionButton @click="addQuestion" />
    <SmallButton class="save-quiz-button" @click="saveQuiz">Save Quiz</SmallButton>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import QuizForm from '@/components/QuizForm.vue'
import NavBar from '@/components/NavBar.vue'
import QuestionCreationBox from '@/components/QuestionCreationBox.vue'
import AddQuestionButton from '@/components/AddQuestionButton.vue'
import SmallButton from '@/components/SmallButton.vue'

const questions = ref([{}])

const addQuestion = () => {
  questions.value.push({})
}

const removeQuestion = (index) => {
  questions.value.splice(index, 1)
}

// Implementer saveQuiz-metoden for å håndtere lagring av quizen
const saveQuiz = () => {
  console.log('Quiz lagret') // Her kan du legge til logikken for å faktisk lagre quizen
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
}

.question-creation-box {
  width: 100%;
  margin-bottom: 1rem; /* Mellomrom mellom boksene */
  display: flex;
  justify-items: center;
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
</style>
