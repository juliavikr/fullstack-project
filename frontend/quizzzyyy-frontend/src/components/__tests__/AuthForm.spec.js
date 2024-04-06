import { describe, it, expect, vi, afterEach } from 'vitest'
import { mount } from '@vue/test-utils'
import axios from 'axios'
import AuthForm from '../AuthForm.vue'
import MediumButton from '../MediumButton.vue'

// Mocking axios post calls and the router push method.
vi.mock('axios', () => ({
  default: {
    post: vi.fn(() => Promise.resolve({ data: { success: true, token: 'fake_token' } }))
  }
}))
vi.mock('@/router/index.js', () => ({
  push: vi.fn()
}))

// Reset mocks after each test
afterEach(() => {
  vi.resetAllMocks()
})

describe('AuthForm', () => {
  it('renders title, username and password inputs', () => {
    const wrapper = mount(AuthForm, {
      props: {
        title: 'Test Form',
        buttonType: 'login',
        buttonText: 'Sign In'
      },
      global: {
        stubs: {
          MediumButton
        }
      }
    })

    expect(wrapper.text()).toContain('Test Form')
    expect(wrapper.find('input#username').exists()).toBe(true)
    expect(wrapper.find('input#password').exists()).toBe(true)
  })

  it('submits the form and handles login', async () => {
    const wrapper = mount(AuthForm, {
      props: {
        title: 'Login',
        buttonType: 'login',
        buttonText: 'Sign In'
      },
      global: {
        stubs: {
          MediumButton
        }
      }
    })

    await wrapper.find('input#username').setValue('testuser')
    await wrapper.find('input#password').setValue('testpass')
    await wrapper.find('form').trigger('submit.prevent')

    expect(axios.post).toHaveBeenCalled()
    expect(wrapper.vm.username).toBe('testuser')
    expect(wrapper.vm.password).toBe('testpass')
  })

  // You would add more tests here for different scenarios,
  // such as unsuccessful login, signup, and form validation.
})
