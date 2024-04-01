<template>
  <button class="small-button" :class="{ active: isActive }" @click="toggleActive">
    <slot></slot>
  </button>
</template>

<script setup>
import { ref } from 'vue'

defineProps({
  label: String
})

const isActive = ref(false)

const toggleActive = () => {
  isActive.value = !isActive.value
  // Her kan du også bruke en hendelse hvis du vil oppdatere en høyere tilstand
}
</script>

<style scoped>
.small-button {
  padding: 10px 15px; /* Gir knappene størrelse og plass */
  width: 70px; /* Standard bredde på knappene */
  height: 20px; /* Standard høyde på knappene */
  font-size: 1rem; /* Passende skriftstørrelse */
  border-radius: 5px; /* Avrundede hjørner */
  cursor: pointer; /* Endrer musepekeren til en peker */
  background-color: #ff8bc3; /* Pink color for the buttons */
  margin: 0 5px; /* Liten margin mellom knappene */
  text-align: center; /* Sentrerer tekst i knappen */
  transition: background-color 0.3s; /* Gjør en myk overgang for bakgrunnsfarge ved hover */
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
.small-button.active {
  border: #f451a0;
}
</style>
