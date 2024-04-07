describe('YourQuizzesView', () => {
  beforeEach(() => {
    cy.visit('/your-quizzes') // replace with the actual route to YourQuizzesView in your application
  })

  it('should render the view', () => {
    cy.get('.your-quizzes-page').should('be.visible')
  })

  it('should display page title', () => {
    cy.get('h2').contains('YOUR QUIZZES').should('be.visible')
  })

  it('should have category and difficulty filters', () => {
    cy.get('select').should('have.length', 2)
  })

  it('should have a search input', () => {
    cy.get('input[type="text"]').should('be.visible')
  })

  it('should allow filtering quizzes', () => {
    cy.get('select').first().select('History')
    cy.get('select').last().select('Easy')
    cy.get('input[type="text"]').type('Test quiz')
  })
})