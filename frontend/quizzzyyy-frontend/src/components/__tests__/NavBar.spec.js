import { describe, it, expect, beforeEach, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import NavBar from '../NavBar.vue' // Adjust the path as necessary.
import UserIcon from '../icons/UserIcon.vue' // Adjust the path as necessary.
import { createRouter, createWebHistory } from 'vue-router'

// Define the routes used in your components
const routes = [
  { path: '/home', component: { template: 'TestComponent' } },
  { path: '/library', component: { template: 'TestComponent' } }
]

// Create the router instance
const router = createRouter({
  history: createWebHistory(),
  routes
})

// Mock modules or components if needed
vi.mock('@/icons/UserIcon.vue', () => ({
  name: 'UserIcon',
  template: '<span>UserIconStub</span>'
}))

describe('NavBar', () => {
  beforeEach(async () => {
    router.push('/')
    // Wait for the router to be ready before running the tests to ensure routes are properly initialized
    await router.isReady()
  })

  it('renders a navigation bar', () => {
    const wrapper = mount(NavBar, {
      global: {
        plugins: [router],
        stubs: {
          RouterLink: true // Replace RouterLink with a stub
        },
        components: {
          UserIcon // Use the real UserIcon component or a stub if preferred
        }
      }
    })
    expect(wrapper.find('.navigation-bar').exists()).toBe(true)
  })

  it('displays the logo', () => {
    const wrapper = mount(NavBar, {
      global: {
        plugins: [router],
        stubs: {
          RouterLink: true
        },
        components: {
          UserIcon
        }
      }
    })
    expect(wrapper.find('.logo').exists()).toBe(true)
  })

  it('has router-links to expected paths', () => {
    const wrapper = mount(NavBar, {
      global: {
        plugins: [router],
        stubs: {
          RouterLink: true
        },
        components: {
          UserIcon
        }
      }
    })
    const navLinks = wrapper.findAll('.nav-link')
    expect(navLinks[0].attributes('to')).toBe('/home')
    expect(navLinks[1].attributes('to')).toBe('/library')
  })

  it('renders the UserIcon component', () => {
    const wrapper = mount(NavBar, {
      global: {
        plugins: [router],
        stubs: {
          RouterLink: true
        },
        components: {
          UserIcon
        }
      }
    })
    expect(wrapper.findComponent(UserIcon).exists()).toBe(true)
  })
})
