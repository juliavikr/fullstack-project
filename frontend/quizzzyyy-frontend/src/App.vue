<template>
  <div id="app">

    <header>

    </header>

    <router-view />

    <footer>

    </footer>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useQuizStore } from '@/stores/quizStore'


const quizStore = useQuizStore()

onMounted(() => {
  quizStore.loadState()
})
</script>

<style>
#app {
  font-family: 'Arial', sans-serif; 
  color: #333; 
}
</style>

