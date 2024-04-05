<template>
  <img src="@/assets/logo.svg" alt="Quizzzyyy Logo" />
  <AuthForm title="Log in" buttonType="login" buttonText="Log in" @submit="handleLogin" />
</template>

<script setup>
import { useRouter } from 'vue-router'
import AuthForm from '@/components/AuthForm.vue'

const router = useRouter()

const handleLogin = () => {
  // Her vil du senere legge til autorisasjonslogikk.
  // For nå, naviger direkte til hjemmesiden.
}
</script>
