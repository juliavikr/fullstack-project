<template>
  <div class="home-container">
    <NavBar />
    <div class="action-buttons">
        <p>Hello, {{ username }}</p>
      <BigButton to="/your-quizzes">Your quizzes</BigButton>
      <BigButton to="/create-quiz">Create a new quiz</BigButton>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import NavBar from '../components/NavBar.vue'
import BigButton from '../components/BigButton.vue'

const username = ref('')

onMounted(() => {
  const storedUsername = localStorage.getItem('username')
  if (storedUsername) {
    username.value = storedUsername
  }
})
</script>

<style scoped>
.home-container {
  font: 20px 'Poppins', sans-serif;
  font-weight: 400;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  height: 100vh;
}

.action-buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  gap: 30px;
  margin-top: 150px;
}


</style>
