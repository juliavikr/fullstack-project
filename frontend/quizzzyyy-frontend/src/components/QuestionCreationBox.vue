<template>
  <div class="question-creation-box">
    <div class="input-group question">
      <label for="question">Question:</label>
      <input type="text" id="question" />
    </div>
    <div class="input-group answer">
      <label for="answer">Answer:</label>
      <input type="text" id="answer" />
    </div>
    <div class="icons">
      <ImageIcon />
      <TrashIcon />
    </div>
  </div>
</template>

<script setup>
import TrashIcon from './icons/TrashIcon.vue'
import ImageIcon from './icons/ImageIcon.vue'
</script>

<style scoped>
.question-creation-box {
  display: flex;
  align-items: center;
  padding: 10px;
  gap: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #fff;
  justify-content: center;
  width: 95%;
  margin: auto; /* Sentrerer boksen horisontalt */
}

.input-group {
  flex: 1;
}

.input-group label {
  display: block;
  margin-bottom: 5px;
}

.input-group input[type='text'] {
  width: 95%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.icons {
  display: flex;
  align-items: center;
  gap: 40px;
}

@media (max-width: 768px) {
  .question-creation-box {
    flex-direction: column;
    width: 90%;
  }

  .icons {
    flex-direction: row;
    justify-content: flex-end;
    width: 90%;
  }

  .icons > * {
    margin-bottom: 0;
  }

  .input-group {
    width: 95%;
  }

  .icons {
    gap: 100px;
  }
}
</style>
