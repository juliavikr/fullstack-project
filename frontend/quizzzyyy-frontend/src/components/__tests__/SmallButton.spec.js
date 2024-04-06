import { describe, it, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import SmallButton from '@/components/SmallButton.vue' // Adjust the import path as necessary

describe('SmallButton', () => {
  it('renders without the active class by default', () => {
    const wrapper = mount(SmallButton)
    expect(wrapper.classes()).toContain('small-button')
    expect(wrapper.classes()).not.toContain('active')
  })

  it('adds the active class when isActive is true', () => {
    const wrapper = mount(SmallButton, {
      props: { isActive: true }
    })
    expect(wrapper.classes()).toContain('active')
  })

  it('does not add the active class when isActive is false', () => {
    const wrapper = mount(SmallButton, {
      props: { isActive: false }
    })
    expect(wrapper.classes()).not.toContain('active')
  })

  it('emits a click event when clicked', async () => {
    const wrapper = mount(SmallButton)
    await wrapper.trigger('click')
    expect(wrapper.emitted()).toHaveProperty('click')
  })
})
