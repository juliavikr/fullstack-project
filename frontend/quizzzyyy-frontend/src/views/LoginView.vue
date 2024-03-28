<template>
  <img src="@/assets/logo.svg" alt="Quizzzyyy Logo" />
  <AuthForm title="Log in" buttonType="login" buttonText="Log in" @submit="handleLogin" />
</template>

<script setup>
import AuthForm from '@/components/AuthForm.vue'

const handleLogin = (credentials) => {
  // Handle login logic with credentials
}
</script>
