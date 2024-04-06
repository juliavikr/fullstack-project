import { describe, it, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import { nextTick } from 'vue'
import QuestionCreationBox from '../QuestionCreationBox.vue' // Adjust the path as necessary.

describe('QuestionCreationBox', () => {
  it('renders input fields for question and answer', () => {
    const wrapper = mount(QuestionCreationBox, {
      props: {
        question: { question_text: '', answer: '' },
        index: 0
      }
    })
    expect(wrapper.find('input#question').exists()).toBe(true)
    expect(wrapper.find('input#answer').exists()).toBe(true)
  })

  it('emits an update:question event when input is given', async () => {
    const wrapper = mount(QuestionCreationBox, {
      props: {
        question: { question_text: 'Initial Question', answer: 'Initial Answer' },
        index: 0
      }
    })

    await wrapper.find('input#question').setValue('What is Vue?')
    await nextTick() // Ensure Vue processes the change

    // Check if any event was emitted after the change
    expect(wrapper.emitted()['update:question']).toBeTruthy()

    // If the event was emitted, check the detail
    if (wrapper.emitted()['update:question']) {
      const latestEmission = wrapper.emitted()['update:question'].pop()
      expect(latestEmission[0].question_text).toBe('What is Vue?')
    }
  })

  it('emits a remove event with the local question when the remove button is clicked', async () => {
    const testQuestion = { question_text: 'Test Question?', answer: 'Test Answer' }
    const wrapper = mount(QuestionCreationBox, {
      props: {
        question: testQuestion,
        index: 1
      }
    })

    await wrapper.find('.remove-question-button').trigger('click')
    expect(wrapper.emitted()['remove']).toBeTruthy()

    const emittedEvent = wrapper.emitted()['remove'][0][0]
    expect(emittedEvent).toEqual(testQuestion)
  })
})
