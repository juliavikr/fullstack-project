describe('QuizCreationView', () => {
  beforeEach(() => {
    cy.visit('/create-quiz')
  })

  it('should render the form', () => {
    cy.get('.quiz-creation-view').should('be.visible')
  })

  it('should have quiz title and category fields', () => {
    cy.get('input#quiz-title').should('be.visible')
    cy.get('select#quiz-category').should('be.visible')
  })

  it('should have difficulty buttons', () => {
    cy.get('.difficulty-buttons').should('be.visible')
  })

  it('should have a save quiz button', () => {
    cy.get('.save-quiz-button').should('be.visible')
  })
})