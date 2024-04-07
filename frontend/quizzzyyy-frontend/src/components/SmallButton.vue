<template>
  <button class="primary-button small-button" :class="{ active: isActive }" @click="$emit('click')">
    <slot></slot>
  </button>
</template>

<script setup>
defineProps({
  isActive: Boolean
})
</script>

<style scoped>
.small-button {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 15px;
  min-width: 70px;
  height: 40px;
  font-size: 1rem;
  text-decoration: none;
}
.small-button.active {
  border: 1px solid black;
}

</style>
