import { describe, expect, it } from 'vitest'
import { mount } from '@vue/test-utils'
import BigButton from '@/components/BigButton.vue'

describe('BigButton.vue', () => {
  it('renders with correct classes based on `type` prop', async () => {
    const type = 'success'
    const wrapper = mount(BigButton, {
      props: { type },
    })
    expect(wrapper.classes()).toContain('primary-button')
    expect(wrapper.classes()).toContain(`button-${type}`)
  })

  it('displays slot content', () => {
    const slotContent = 'Press Me'
    const wrapper = mount(BigButton, {
      slots: { default: slotContent },
    })
    expect(wrapper.text()).toContain(slotContent)
  })
})