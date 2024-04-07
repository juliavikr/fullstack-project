describe('PlayView', () => {
  beforeEach(() => {
    cy.visit('/play')
  })

  it('should render the view', () => {
    cy.get('.play-view').should('be.visible')
  })

  it('should display quiz title', () => {
    cy.get('h2').should('be.visible')
  })

  it('should display a question card when questions are loaded', () => {
    cy.get('.question-card').should('be.visible')
  })

  it('should have an input field for the user answer', () => {
    cy.get('input[type="text"]').should('be.visible')
  })

  it('should have a next button', () => {
    cy.get('button').contains('Next').should('be.visible')
  })

  it('should allow submitting an answer', () => {
    cy.get('input[type="text"]').type('Test answer')
    cy.get('button').contains('Next').click()
    cy.get('input[type="text"]').should('have.value', '') // check if the input field is cleared
  })
})