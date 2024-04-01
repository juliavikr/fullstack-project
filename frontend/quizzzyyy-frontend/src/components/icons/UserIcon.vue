<template>
  <div @click="navigateToUserPage" class="user-icon">
    <svg width="42" height="50" viewBox="0 0 62 70" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M31 30L61.3109 69.75H0.689112L31 30Z" fill="currentColor" />
      <ellipse cx="30.5" cy="17.5" rx="18.5" ry="17.5" fill="currentColor" />
    </svg>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const navigateToUserPage = () => {
  router.push({ name: 'UserPage' })
}
</script>

<style scoped>
.user-icon {
  cursor: pointer;
  color: #ff8bc3; /* Original farge */
}

.user-icon:hover {
  color: #eb68a3; /* Hover farge */
}
</style>
