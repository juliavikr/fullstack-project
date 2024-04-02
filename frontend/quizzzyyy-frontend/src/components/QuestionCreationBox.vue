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
    <!-- Erstattet TrashIcon med en "-" knapp -->
    <button class="remove-question-button" @click="$emit('remove')">-</button>
  </div>
</template>

<style scoped>
.question-creation-box {
  display: flex;
  align-items: center;
  padding: 10px;
  gap: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #fff;
  justify-content: space-between; /* Endret til space-between for å flytte knappen til høyre */
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

.remove-question-button {
  cursor: pointer;
  background-color: #ff8bc3; /* Samme rosa farge som før */
  border: none;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
}

/* Media query for mindre skjermer */
@media (max-width: 768px) {
  .question-creation-box {
    flex-direction: column; /* Endrer layouten til vertikal for mindre skjermer */
    align-items: stretch; /* Strekker barna for å fylle containerens bredde */
  }

  .input-group {
    width: 100%; /* Sørger for at input-feltene tar full bredde */
  }

  .remove-question-button {
    align-self: center; /* Sentrerer fjern-knappen */
    margin-top: 10px; /* Gir litt plass over knappen */
  }
}
</style>
