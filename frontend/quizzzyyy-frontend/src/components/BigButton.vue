<template>
  <button :class="`button ${type}`" @click="navigate">
    <slot></slot>
    <!-- Use slot for button text -->
  </button>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { defineProps } from 'vue'

// Definerer en prop for å motta rutebanen
const props = defineProps({
  to: String,
  type: String
})

const router = useRouter()

// Navigasjonsfunksjonen
const navigate = () => {
  if (props.to) {
    router.push(props.to)
  }
}
</script>

<style scoped>
button {
  padding: 10px 15px; /* Gir knappene størrelse og plass */
  width: 500px; /* Standard bredde på knappene */
  height: 100px; /* Standard høyde på knappene */
  font-size: 1rem; /* Passende skriftstørrelse */
  border: none; /* Ingen ramme */
  border-radius: 5px; /* Avrundede hjørner */
  cursor: pointer; /* Endrer musepekeren til en peker */
  background-color: #ff8bc3; /* Pink color for the buttons */
  margin: 0 5px; /* Liten margin mellom knappene */
  text-align: center; /* Sentrerer tekst i knappen */
  transition: background-color 0.3s; /* Gjør en myk overgang for bakgrunnsfarge ved hover */
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

button:hover {
  background-color: #f362a8;
}

@media (max-width: 768px) {
  button {
    width: 300px; /* Mindre bredde på mobile enheter */
    padding: 10px; /* Mindre polstring på mobile enheter */
    font-size: 0.875rem; /* Mindre skriftstørrelse på mobile enheter */
    margin: 10px 0; /* Litt større margin på mobile enheter */
  }
}
</style>
