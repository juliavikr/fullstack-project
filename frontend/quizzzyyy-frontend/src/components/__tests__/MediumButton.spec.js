import { mount } from '@vue/test-utils'
import MediumButton from '@/components/MediumButton.vue'
import { nextTick } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: []
})

describe('MediumButton', () => {
  it('renders a button with the correct class based on type prop', () => {
    const type = 'secondary'
    const wrapper = mount(MediumButton, {
      props: { type },
      global: {
        plugins: [router]
      }
    })

    const button = wrapper.find('button')
    expect(button.classes()).toContain('primary-button')
    expect(button.classes()).toContain(`button-${type}`)
  })

  it('navigates to the "to" prop when clicked', async () => {
    const to = '/path'
    const wrapper = mount(MediumButton, {
      props: { to, type: 'secondary' },
      global: {
        plugins: [router]
      }
    })

    await wrapper.find('button').trigger('click')
    await nextTick()
    expect(router.currentRoute.value.path).toBe(to)
  })

  it('does not navigate if "to" prop is not provided', async () => {
    const wrapper = mount(MediumButton, {
      props: { type: 'secondary' },
      global: {
        plugins: [router]
      }
    })

    await wrapper.find('button').trigger('click')
    await nextTick()
    expect(router.currentRoute.value.path).toBe('/')
  })
})
