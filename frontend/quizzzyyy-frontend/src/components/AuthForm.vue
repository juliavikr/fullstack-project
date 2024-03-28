<template>
  <div class="auth-form">
    <h2>{{ title }}</h2>
    <form @submit.prevent="onSubmit">
      <div class="input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div class="input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <MediumButton :type="buttonType">{{ buttonText }}</MediumButton>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import MediumButton from '@/components/MediumButton.vue'
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  title: String,
  buttonType: String,
  buttonText: String
})

const emits = defineEmits(['submit'])

const username = ref('')
const password = ref('')

const onSubmit = () => {
  emits('submit', { username: username.value, password: password.value })
}
</script>

<style scoped>
.auth-form {
  max-width: 600px;
  margin: auto;
  padding: 2rem;
  background: #fff;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  font-size: 1.1rem;
}
.input-group {
  margin-bottom: 1rem;
}
</style>
