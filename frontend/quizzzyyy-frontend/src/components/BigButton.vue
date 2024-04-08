<template>
  <button :class="['primary-button', `button-${type}`]" @click="navigate">
    <slot></slot>
  </button>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { defineProps } from 'vue'

const props = defineProps({
  to: String,
  type: String
})

const router = useRouter()

const navigate = () => {
  if (props.to) {
    router.push(props.to)
  }
}
</script>

<style scoped>
button {
  padding: 10px 15px;
  width: 500px; 
  height: 100px; 
  font-size: 1.2rem; 
  border: none; 
  border-radius: 5px; 

  margin: 0 5px; 
  text-align: center; 
}


@media (max-width: 768px) {
  button {
    width: 300px; 
    padding: 10px; 
    font-size: 0.875rem; 
    margin: 10px 0; 
  }
}
</style>
