Feature: Create a project
  Description: A worker can create a project in the management system
  Actors: Worker logged in

  #Daniel
  Scenario: Create a project when logged in
    Given a worker with the name "daha" exists
    And the worker is logged in
    When the worker tries to create a new project with the number 23001
    Then the new project gets created
    And project 23002 does not exist

  #Daniel
  Scenario: Create a project when not logged in
    Given a worker with the name "daha" exists
    And the worker is not logged in
    When the worker tries to create a new project with the number 23001
    Then an error message "You must be logged in to access this feature. Please log in and try again." is given
