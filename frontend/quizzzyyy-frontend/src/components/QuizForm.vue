<template>
  <div class="quiz-form">
    <h1>Create a New Quiz</h1>
    <div class="form-group">
      <label for="quiz-title">Enter quiz title:</label>
      <input type="text" id="quiz-title" placeholder="Quiz Title" />
    </div>
    <div class="form-group">
      <label for="quiz-category">Category:</label>
      <select id="quiz-category">
        <option selected>Select Category</option>
        <!-- Dynamisk liste over kategorier -->
      </select>
    </div>
    <div class="form-group difficulty">
      <label>Difficulty:</label>
      <SmallButton label="Hard">Hard</SmallButton>
      <SmallButton label="Medium">Medium</SmallButton>
      <SmallButton label="Easy">Easy</SmallButton>
    </div>
    <QuestionBox />
    <!-- Andre elementer... -->
  </div>
</template>

<script setup>
import SmallButton from '@/components/SmallButton.vue'
import QuestionBox from '@/components/QuestionBox.vue'
// Fortsett med resten av scriptet ditt
</script>

<style scoped>
.quiz-form {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  display: flex;
  flex-direction: column;
}

.quiz-form h1 {
  color: #333;
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input[type='text'],
.form-group select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 5px; /* If you have an error message or additional text */
}

.difficulty label {
  margin-bottom: 10px;
}

.difficulty SmallButton {
  margin-right: 5px;
}

/* Tilpass SmallButton stiler etter behov */

/* Stil for QuestionBox etter behov */
</style>
