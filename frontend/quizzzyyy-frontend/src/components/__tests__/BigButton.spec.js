import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount } from '@vue/test-utils'
import BigButton from '../BigButton.vue'
// Mock useRouter
const push = vi.fn() // Mock function for router push

vi.mock('vue-router', () => ({
  useRouter: () => ({
    push
  })
}))

// Reset the push mock before each test
beforeEach(() => {
  push.mockClear()
})

describe('BigButton', () => {
  it('renders a button with the correct class based on type prop', () => {
    const type = 'primary'
    const wrapper = mount(BigButton, {
      props: { type }
    })

    const button = wrapper.find('button')
    expect(button.classes()).toContain('button')
    expect(button.classes()).toContain(type)
  })

  it('navigates to the "to" prop when clicked', async () => {
    const wrapper = mount(BigButton, {
      props: { to: '/path', type: 'primary' }
    })

    await wrapper.find('button').trigger('click')
    expect(push).toHaveBeenCalledWith('/path')
  })

  it('does not navigate if "to" prop is not provided', async () => {
    const wrapper = mount(BigButton, {
      props: { type: 'primary' }
    })

    await wrapper.find('button').trigger('click')
    expect(push).not.toHaveBeenCalled()
  })

  // Additional tests could be added here to test slot content, other props, etc.
})
