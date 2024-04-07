describe('HomePageView', () => {
  beforeEach(() => {
    cy.visit('/home') // replace with the actual path to HomePageView
  })

  it('displays the username from local storage', () => {
    const username = 'testuser'
    window.localStorage.setItem('username', username)
    cy.reload()

    cy.get('.home-container .action-buttons p').should('contain', `Hello, ${username}`)
  })

  it('navigates to "/your-quizzes" when the first BigButton is clicked', () => {
    cy.get('.home-container .action-buttons')
      .find('button')
      .first()
      .click()

    cy.url().should('include', '/your-quizzes')
  })

  it('navigates to "/create-quiz" when the second BigButton is clicked', () => {
    cy.get('.home-container .action-buttons')
      .find('button')
      .eq(1)
      .click()

    cy.url().should('include', '/create-quiz')
  })
})