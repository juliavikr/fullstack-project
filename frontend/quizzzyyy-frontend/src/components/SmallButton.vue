<template>
  <button class="small-button" :class="{ active: isActive }" @click="$emit('click')">
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
  border-radius: 5px; /* Avrundede hjørner */
  cursor: pointer; /* Endrer musepekeren til en peker */
  background-color: #ff8bc3; /* Rosa farge for knappene */
  margin: 0 5px; /* Liten margin mellom knappene */
  border: none; /* Fjerner standard kant */
  transition: background-color 0.3s; /* Gjør en myk overgang for bakgrunnsfarge ved hover */
  text-decoration: none; /* Fjerner tekstdekorasjon som kan komme fra <a> elementer */
}
.small-button.active {
  border: 1px solid black; /* Mørkere rosa farge for aktive knapper */
}

.small-button:hover {
  background-color: #f362a8; /* Mørkere rosa farge for hover-effekt */
}
</style>
