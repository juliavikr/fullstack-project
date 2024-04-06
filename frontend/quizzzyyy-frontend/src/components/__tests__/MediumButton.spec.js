import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount } from '@vue/test-utils'
import MediumButton from '../MediumButton.vue'

const push = vi.fn() // Mock function for router push

// Mock useRouter
vi.mock('vue-router', () => ({
  useRouter: () => ({
    push
  })
}))

// Reset the push mock before each test
beforeEach(() => {
  push.mockClear()
})

describe('MediumButton', () => {
  it('renders a button with the correct class based on type prop', () => {
    const type = 'secondary'
    const wrapper = mount(MediumButton, {
      props: { type }
    })

    const button = wrapper.find('button')
    expect(button.classes()).toContain('btn')
    expect(button.classes()).toContain(type)
  })

  it('renders slot content', () => {
    const slotContent = 'Click Me'
    const wrapper = mount(MediumButton, {
      slots: { default: slotContent }
    })

    expect(wrapper.text()).toContain(slotContent)
  })

  it('navigates to the "to" prop when clicked', async () => {
    const toPath = '/another-path'
    const wrapper = mount(MediumButton, {
      props: { to: toPath, type: 'secondary' }
    })

    await wrapper.find('button').trigger('click')
    expect(push).toHaveBeenCalledWith(toPath)
  })

  it('does not navigate if "to" prop is not provided', async () => {
    const wrapper = mount(MediumButton, {
      props: { type: 'secondary' }
    })

    await wrapper.find('button').trigger('click')
    expect(push).not.toHaveBeenCalled()
  })
})
