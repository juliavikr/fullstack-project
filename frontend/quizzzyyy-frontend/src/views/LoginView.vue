<template>
  <img src="@/assets/logo.svg" alt="Quizzzyyy Logo" />
  <AuthForm title="Log in" buttonType="login" buttonText="Log in" @submit="handleLogin" />
</template>

<script setup>
import AuthForm from '@/components/AuthForm.vue'

const handleLogin = () => {
  // Her vil du senere legge til autorisasjonslogikk.
  // For nå, naviger direkte til hjemmesiden.
}
</script>
