import { describe, it, expect, vi } from 'vitest'
import { mount } from '@vue/test-utils'
import axios from 'axios'
import QuizEntry from '@/components/QuizEntry.vue' // Adjust the import path as necessary

// Mocking Axios
vi.mock('axios', () => ({
  delete: vi.fn(() => Promise.resolve({ status: 204 }))
}))

// Mocking Vue Router
const mockRouterPush = vi.fn()
vi.mock('vue-router', () => ({
  useRouter: () => ({
    push: mockRouterPush
  })
}))

vi.mock('@/components/MediumButton.vue', () => ({
  name: 'MediumButton',
  // Simulate a component that emits 'click' event
  methods: {
    click: vi.fn()
  },
  render() {
    return vi.fn() // Stub the render function if necessary
  }
}))

// Mocking Quiz Store
const mockSetCurrentQuiz = vi.fn()
const mockRemoveQuiz = vi.fn()
const mockFetchQuizzes = vi.fn()
vi.mock('@/stores/quizStore', () => ({
  useQuizStore: () => ({
    setCurrentQuiz: mockSetCurrentQuiz,
    removeQuiz: mockRemoveQuiz,
    fetchQuizzes: mockFetchQuizzes
  })
}))

describe('QuizEntry.vue', () => {
  const quiz = {
    id: '1',
    title: 'Vue Basics',
    category: 'Programming',
    difficulty: 'Easy'
  }

  it('renders quiz details', () => {
    const wrapper = mount(QuizEntry, {
      props: { quiz }
    })

    expect(wrapper.text()).toContain(quiz.title)
    expect(wrapper.text()).toContain(`Category: ${quiz.category}`)
    expect(wrapper.text()).toContain(`Difficulty: ${quiz.difficulty}`)
  })

  it('deletes quiz on delete button click', async () => {
    window.localStorage.setItem('token', 'dummy-token') // Setup local storage for token
    const wrapper = mount(QuizEntry, {
      props: { quiz },
      global: {
        mocks: {
          axios
        }
      }
    })

    await wrapper.find('button').trigger('click')
    expect(axios.delete).toHaveBeenCalled()
    window.localStorage.removeItem('token') // Cleanup
  })

  it('navigates to play quiz on Play button click', async () => {
    const wrapper = mount(QuizEntry, {
      props: { quiz }
    })

    await wrapper.findAllComponents({ name: 'MediumButton' })[0].trigger('click')
    expect(mockRouterPush).toHaveBeenCalledWith('/play')
  })

  it('navigates to edit quiz on Edit button click', async () => {
    const wrapper = mount(QuizEntry, {
      props: { quiz }
    })

    await wrapper.findAllComponents({ name: 'MediumButton' })[1].trigger('click')
    expect(mockRouterPush).toHaveBeenCalledWith({ name: 'CreateQuiz', params: { id: quiz.id } })
  })
})
