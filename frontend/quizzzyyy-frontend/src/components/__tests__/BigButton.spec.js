import { mount } from '@vue/test-utils'
import BigButton from '@/components/BigButton.vue'
import { nextTick } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: []
})

describe('BigButton', () => {
  it('renders a button with the correct class based on type prop', () => {
    const type = 'primary'
    const wrapper = mount(BigButton, {
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
    const wrapper = mount(BigButton, {
      props: { to, type: 'primary' },
      global: {
        plugins: [router]
      }
    })

    await wrapper.find('button').trigger('click')
    await nextTick()
    expect(router.currentRoute.value.path).toBe(to)
  })

  it('does not navigate if "to" prop is not provided', async () => {
    const wrapper = mount(BigButton, {
      props: { type: 'primary' },
      global: {
        plugins: [router]
      }
    })

    await wrapper.find('button').trigger('click')
    await nextTick()
    expect(router.currentRoute.value.path).toBe('/')
  })
})
