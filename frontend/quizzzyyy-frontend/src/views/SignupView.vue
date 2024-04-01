<template>
  <img src="@/assets/logo.svg" alt="Quizzzyyy Logo" />
  <AuthForm title="Sign up" buttonType="signup" buttonText="Sign up" @submit="handleSignUp" />
</template>

<script setup>
import AuthForm from '@/components/AuthForm.vue'

const handleSignUp = () => {
  // Handle sign up logic with credentials
}
</script>
