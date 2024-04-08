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
.btn {
  padding: 10px 15px; 
  width: 200px; 
  height: 50px; 
  font-size: 1rem; 
  border-radius: 5px; 
  text-align: center; 
}

@media (max-width: 768px) {
  .btn {
    min-width: 100px; 
    padding: 10px; 
    font-size: 0.875rem; 
    margin: 10px 0; 
  }
}
</style>
