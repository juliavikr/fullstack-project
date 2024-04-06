import { describe, it, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import AddQuestionButton from '../AddQuestionButton.vue' // Adjust the path as necessary.

describe('AddQuestionButton', () => {
  it('renders a button', () => {
    const wrapper = mount(AddQuestionButton)
    expect(wrapper.find('.add-question-button').exists()).toBe(true)
  })

  it('emits a "click" event when clicked', async () => {
    const wrapper = mount(AddQuestionButton)
    await wrapper.find('.add-question-button').trigger('click')
    expect(wrapper.emitted()).toHaveProperty('click')
  })
})
