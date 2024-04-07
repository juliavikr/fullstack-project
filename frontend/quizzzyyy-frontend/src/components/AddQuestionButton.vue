<template>
  <button class="primary-button add-question-button" @click="addQuestion"> + </button>
</template>


<script setup>
const emit = defineEmits(['click'])

const addQuestion = () => {
  emit('click')
}
</script>

<style scoped>
.add-question-button {
  background: #ff8bc3;
  border-radius: 50%;
  margin: 20px;
  padding: 10px;
  font-size: 2rem;
  width: 60px;
  height: 60px;
  border: 1px solid black;
}
</style>
