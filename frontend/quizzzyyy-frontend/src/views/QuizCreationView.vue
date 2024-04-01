<template>
  <NavBar />
  <div class="quiz-creation-view">
    <h1>Create a new quiz</h1>
    <QuizForm />
    <QuestionCreationBox />
    <AddQuestionButton />
  </div>
</template>

<script setup>
import QuizForm from '@/components/QuizForm.vue'
import NavBar from '@/components/NavBar.vue'
import QuestionCreationBox from '@/components/QuestionCreationBox.vue'
import AddQuestionButton from '@/components/AddQuestionButton.vue'
</script>

<style scoped>
.quiz-creation-view {
  display: flex; /* Gjør dette elementet til en flex container */
  flex-direction: column; /* Stabler barna vertikalt */
  align-items: center;
}

h1 {
  display: flex;
  margin-bottom: 0.5rem;
  justify-content: center;
}
</style>
