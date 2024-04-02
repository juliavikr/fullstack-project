import { createRouter, createWebHistory } from 'vue-router'
import WelcomeView from '@/views/WelcomeView.vue'
import LoginView from '@/views/LoginView.vue'
import SignupView from '@/views/SignupView.vue'
import HomePageView from '@/views/HomePageView.vue'
import UserPageView from '@/views/UserPageView.vue'
import QuizCreationView from '@/views/QuizCreationView.vue'
import LibraryView from '@/views/LibraryView.vue'
import YourQuizzesView from '@/views/YourQuizzesView.vue'

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
  }
  // ...andre ruter
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
