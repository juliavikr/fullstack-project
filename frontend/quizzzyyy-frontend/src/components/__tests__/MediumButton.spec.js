import {describe, expect, it } from 'vitest'
import { mount } from '@vue/test-utils'
import MediumButton from '@/components/MediumButton.vue'

describe('MediumButton.vue', () => {
  // Test 1: Renders with correct classes based on `type` prop
  it('renders with correct classes', () => {
    const type = 'success'
    const wrapper = mount(MediumButton, {
      props: { type },
    })
    expect(wrapper.classes()).toContain('primary-button')
    expect(wrapper.classes()).toContain(`button-${type}`)
  })

  // Test 2: Displays slot content
  it('displays slot content', () => {
    const slotContent = 'Click Me'
    const wrapper = mount(MediumButton, {
      slots: { default: slotContent },
    })
    expect(wrapper.text()).toContain(slotContent)
  })
})