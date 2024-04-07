import { describe, it, expect, beforeEach, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import QuizEntry from '@/components/QuizEntry.vue'
import { createRouter, createWebHistory } from 'vue-router'
import { createPinia, setActivePinia } from 'pinia'

// Mock axios
vi.mock('axios', () => ({
  delete: vi.fn(() => Promise.resolve({ data: 'Quiz deleted successfully' }))
}))

const quiz = {
  id: 1,
  title: 'Test Quiz',
  category: 'General Knowledge',
  difficulty: 'Easy'
}

describe('QuizEntry.vue', () => {
  let wrapper

  beforeEach(() => {
    // Setup Pinia
    setActivePinia(createPinia())

    // Setup router
    const router = createRouter({
      history: createWebHistory(),
      routes: []
    })

    // Mount component with global plugins (router, pinia)
    wrapper = mount(QuizEntry, {
      global: {
        plugins: [router]
      },
      props: {
        quiz
      }
    })
  })

  it('renders quiz information', () => {
    expect(wrapper.text()).toContain(quiz.title)
    expect(wrapper.text()).toContain(
      'Category: ' + quiz.category + ' - Difficulty: ' + quiz.difficulty
    )
  })
})
