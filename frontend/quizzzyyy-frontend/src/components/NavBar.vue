<template>
  <nav class="navigation-bar">
    <div class="nav-group left">
      <img src="@/assets/logo.svg" class="logo" alt="QZY logo" />
      <router-link to="/home" class="nav-link">Home</router-link>
    </div>
    <div class="nav-group right">
      <router-link to="/library" class="nav-link">Your library</router-link>
      <div class="profile-icon">
        <UserIcon />
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import UserIcon from './icons/UserIcon.vue'
const username = ref('')

onMounted(() => {
  const storedUsername = localStorage.getItem('username')
  if (storedUsername) {
    username.value = storedUsername
  }
})
</script>

<style scoped>
.navigation-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  top: 0;
  left: 0;
  right: 0;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: sticky;
}

.nav-group {
  display: flex;
  align-items: center;
}

.left .logo {
  margin-right: 20px;
  width: 50px;
  height: auto;
}

.nav-link {
  text-decoration: none;
  margin-right: 1rem;
  color: #333;
  font-size: 20px;
}

.right .profile-icon {
  display: flex;
  align-items: center;
  margin-left: 20px;
}

.nav-link:active,
.nav-link:focus {
  color: #000;
}
</style>
