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
  display: flex; /* Setter display til flex for å tillate flexbox egenskaper */
  align-items: center; /* Sentrerer barn vertikalt i flex container */
  justify-content: center; /* Sentrerer barn horisontalt i flex container */
  padding: 10px 15px; /* Gir knappene størrelse og plass */
  min-width: 70px; /* Minimumsbredde på knappene */
  height: 40px; /* Justert høyde for bedre estetikk */
  font-size: 1rem; /* Passende skriftstørrelse */
  text-decoration: none; /* Fjerner tekstdekorasjon som kan komme fra <a> elementer */
}
.small-button.active {
  border: 1px solid black; /* Mørkere rosa farge for aktive knapper */
}

</style>
