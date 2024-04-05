import { createRouter, createWebHistory } from 'vue-router'
import WelcomeView from '@/views/WelcomeView.vue'
import LoginView from '@/views/LoginView.vue'
import SignupView from '@/views/SignupView.vue'
import HomePageView from '@/views/HomePageView.vue'
import UserPageView from '@/views/UserPageView.vue'
import QuizCreationView from '@/views/QuizCreationView.vue'
import LibraryView from '@/views/LibraryView.vue'
import YourQuizzesView from '@/views/YourQuizzesView.vue'
import PlayView from '@/views/PlayView.vue'
import ScoreView from '@/views/ScoreView.vue'
import { useQuizStore } from '@/stores/quizStore'

const routes = [
  {
    path: '/',
    name: 'Welcome',
    component: WelcomeView
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'Signup',
    component: SignupView
  },
  {
    path: '/home',
    name: 'HomePage',
    component: HomePageView
  },
  {
    path: '/user',
    name: 'UserPage',
    component: UserPageView
  },
  {
    path: '/create-quiz',
    name: 'CreateQuiz',
    component: QuizCreationView
  },
  {
    path: '/library',
    name: 'Library',
    component: LibraryView
  },
  {
    path: '/your-quizzes',
    name: 'YourQuizzes',
    component: YourQuizzesView
  },
  {
    path: '/play',
    name: 'Play',
    component: PlayView
  },
  {
    path: '/score',
    name: 'Score',
    component: ScoreView
  }
  // ...andre ruter
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const store = useQuizStore()

  // Check if trying to leave the quiz page and a quiz is in progress
  if (
    from.path === '/quiz' &&
    store.currentQuiz &&
    store.currentQuestionIndex < store.currentQuiz.questions.length
  ) {
    if (window.confirm('Are you sure you want to leave the quiz? Your progress will be lost.')) {
      store.resetQuiz() // Reset the quiz if leaving the page
      next()
    } else {
      next(false) // Cancel the navigation
    }
  } else {
    next() // Proceed with navigation for all other cases
  }
})

export default router
