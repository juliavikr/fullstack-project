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
  padding-top: 100px;
  height: 100vh;
  font-family: 'Arial', sans-serif;
}

img {
  margin-bottom: 20px;
}

h1 {
  color: #333;
  margin-bottom: 40px;
  font-family: 'Inter', sans-serif;
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
