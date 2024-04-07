<template>
  <button :class="['primary-button', `button-${type}`]" @click="navigate">
    <slot></slot>
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
.btn {
  padding: 10px 15px; /* Gir knappene størrelse og plass */
  width: 200px; /* Standard bredde på knappene */
  height: 50px; /* Standard høyde på knappene */
  font-size: 1rem; /* Passende skriftstørrelse */
  border-radius: 5px; /* Avrundede hjørner */
  text-align: center; /* Sentrerer tekst i knappen */
}

@media (max-width: 768px) {
  .btn {
    min-width: 100px; /* Mindre bredde på mobile enheter */
    padding: 10px; /* Mindre polstring på mobile enheter */
    font-size: 0.875rem; /* Mindre skriftstørrelse på mobile enheter */
    margin: 10px 0; /* Litt større margin på mobile enheter */
  }
}
</style>
