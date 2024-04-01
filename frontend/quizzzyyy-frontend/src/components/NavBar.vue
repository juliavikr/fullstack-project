<template>
  <nav class="navigation-bar">
    <div class="nav-group left">
      <img src="@/assets/logo.svg" class="logo" alt="QZY logo" />
      <router-link to="/home" class="nav-link">Home</router-link>
    </div>
    <div class="nav-group right">
      <router-link to="/library" class="nav-link">Your library</router-link>
      <div class="profile-icon">
        <UserIcon />
      </div>
    </div>
  </nav>
</template>

<script setup>
import UserIcon from './icons/UserIcon.vue'
</script>

<style scoped>
.navigation-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  top: 0; /* 0px fra toppen */
  left: 0; /* 0px fra venstre */
  right: 0; /* 0px fra høyre, sikrer at navigasjonsbaren strekker seg over hele bredden av siden */
  background-color: white; /* Bruk fargen fra din wireframe */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: sticky;
}

.nav-group {
  display: flex;
  align-items: center;
}

.left .logo {
  margin-right: 20px; /* Juster som nødvendig */
  width: 50px;
  height: auto;
}

.nav-link {
  text-decoration: none;
  margin-right: 1rem;
  color: #333; /* Bruk fargen fra din wireframe */
  font-size: 20px;
}

.right .profile-icon {
  display: flex;
  align-items: center;
  margin-left: 20px;
}

/* Juster link-stil for når de er aktive eller i fokus */
.nav-link:active,
.nav-link:focus {
  color: #000; /* Bruk en farge for aktiv/fokusert link */
}
</style>
