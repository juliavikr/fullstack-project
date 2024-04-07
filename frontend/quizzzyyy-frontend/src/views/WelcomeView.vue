<template>
  <div class="welcome-container">
    <img src="@/assets/logo.svg" alt="QZY logo" />
    <h1>WELCOME TO QUIZZZYYY</h1>
    <AuthForm
      :title="formType === 'login' ? 'Log In' : 'Sign Up'"
      :buttonType="formType"
      :buttonText="formType === 'login' ? 'Log In' : 'Sign Up'"
      @update:mode="formType = $event"
    />
  </div>
</template>
<script setup>
import { ref } from 'vue';
import AuthForm from '@/components/AuthForm.vue';

const formType = ref('login');
</script>

<style scoped>
.welcome-container {
  text-align: center;
  padding-top: 100px; /* Adjust this value as needed for your layout */
  height: 100vh; /* Full height */
  font-family: 'Arial', sans-serif; /* Your font choice */
}

img {
  margin-bottom: 20px; /* Spacing below the logo */
}

h1 {
  color: #333; /* Text color for the title */
  margin-bottom: 40px; /* Spacing below the title */
  font-family: 'Inter', sans-serif; /* Font for the title */
  font-style: normal;
  font-weight: 400;
  font-size: 48px;
}

@media (max-width: 768px) {
  .welcome-container {
    flex-direction: column;
  }
}
</style>
