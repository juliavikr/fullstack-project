<template>
  <NavBar />
  <div class="user-page-container">
    <div class="user-info">
      <h2>Username: {{ username }}</h2>
      <MediumButton type="primary-button log-out" @click="logout">Log out</MediumButton>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import MediumButton from '@/components/MediumButton.vue'
import NavBar from '@/components/NavBar.vue'
import { useRouter } from 'vue-router'

const username = ref('')
const router = useRouter()

onMounted(() => {
  const storedUsername = localStorage.getItem('username')
  if (storedUsername) {
    username.value = storedUsername
  }
})

const logout = () => {
  console.log('user logged out')
  localStorage.removeItem('token')
  localStorage.removeItem('username')
  router.push('/')
}
</script>


<style scoped>
.user-page-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  height: 100vh;
  align-items: center;
}

label {
  margin-bottom: 5px;
}

input[type='password'] {
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.user-info {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  align-items: center;
  justify-content: center;
}

input[type='text']:focus,
input[type='password']:focus {
  outline: none;
  border-color: #f472b6;
  box-shadow: 0 0 0 2px rgba(244, 114, 182, 0.5);
}

</style>
