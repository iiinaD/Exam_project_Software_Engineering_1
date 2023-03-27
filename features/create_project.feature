# Daniel
Feature: Create a project
  Description: A worker can create a project in the management system
  Actors: Worker logged in

  Scenario: Create a project when logged in
    Given a worker with the name "daha" exists
    And the worker is logged in
    When the worker tries to create a new project with the number 23001
    Then the new project gets created

#  Scenario: Create a project when not logged in
#    Given a worker with the name "daha" exists
#    And the worker is not logged in
#    When the worker tries to create a new project with the number 23001
#    Then the new project does not get created