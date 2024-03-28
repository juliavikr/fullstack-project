<template>
  <div id="app">
    <!-- En header eller navigasjonslinje kan være her om nødvendig -->
    <header>
      <!-- Logo, navigasjonslenker, osv. -->
    </header>

    <!-- Innholdet som endres basert på ruten -->
    <router-view />

    <!-- En footer kan være her om nødvendig -->
    <footer>
      <!-- Footer-innhold -->
    </footer>
  </div>
</template>

<script setup>
// Script setup logikk kan være her om nødvendig
</script>

<style>
#app {
  font-family: 'Arial', sans-serif; /* Ditt valg av skrifttype */
  color: #333; /* Standard tekstfarge */
}
</style>
