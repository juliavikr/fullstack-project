<template>
  <div class="question-creation-box">
    <div class="input-group question">
      <label for="question">Question:</label>

      <input type="text" id="question" v-model="localQuestion.question_text" />
    </div>
    <div class="input-group answer">
      <label for="answer">Answer:</label>

      <input type="text" id="answer" v-model="localQuestion.answer" />
    </div>
    <button class="remove-question-button" @click="$emit('remove', localQuestion)">x</button>
  </div>
</template>

<script setup>
import { ref, watchEffect, defineEmits } from 'vue'

const emit = defineEmits(['update:question', 'remove'])
const props = defineProps({
  question: Object,
  index: Number
})

const localQuestion = ref({ ...props.question })

watchEffect(() => {
  emit('update:question', { ...localQuestion.value, index: props.index })
})
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
  justify-content: space-between;
  width: 95%;
  margin: auto;
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
  background-color: #ff8bc3;
  border: none;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
}

@media (max-width: 768px) {
  .question-creation-box {
    flex-direction: column;
    align-items: stretch;
  }

  .input-group {
    width: 100%;
  }

  .remove-question-button {
    align-self: center;
    margin-top: 10px;
  }
}
</style>
