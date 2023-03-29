# Daniel
#Feature: Create a project
#  Description: A worker can create a project in the management system
#  Actors: Worker logged in

  # Daniel
#  Scenario: Create a project when logged in
#    Given a worker with the name "daha" exists
#    And the worker is logged in
#    When the worker tries to create a new project with the number 23001
#    Then the new project gets created

<<<<<<< HEAD
  # Daniel
#  Scenario: Create a project when not logged in
#    Given a worker with the name "daha" exists
#    And the worker is not logged in
#    When the worker tries to create a new project with the number 23001
#    Then the new project does not get created
#    And the error message "Login is required to create project" is given
=======
  Scenario: Create a project when not logged in
    Given a worker with the name "daha" exists
    And the worker is not logged in
    When the worker tries to create a new project with the number 23001
    Then the new project 23001 does not get created
    And an error message "project 23001 dont exist" is given

  #Jonas
  Scenario: Create a project when not logged in
    Given a worker with the name "jodl" exists
    And the worker is not logged in
    When the worker tries to create a new project with the number 23001
    And an error message "no worker is logged in" is given


>>>>>>> Activitylist
