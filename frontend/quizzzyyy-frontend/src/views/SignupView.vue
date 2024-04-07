<template>
 <div class="auth-container">
    <img src="@/assets/logo.svg" alt="Quizzzyyy Logo" />

    <div class="buttons-container">
      <AuthForm title="sign up" buttonType="signup" buttonText="sign up in"  />
    </div>
  </div>
</template>

<script setup>
import AuthForm from '@/components/AuthForm.vue'

</script>
