#Daniel
Feature: Edit a project
  Description: A worker can edit an existing project in the management system
  Actors: Worker logged in, project leader

  Scenario: A worker can change the name of the project after project is created
    Given there is a worker with initials "jodl" logged in to the system
    And a project with the number 23001 and name "Web projekt" exists
    When the worker tries to change the name of the project 23001 to "Web projekt Google"
    Then the name of the project 23001 changes to "Web projekt Google"

  Scenario: A worker can assign a project leader to a project after project is created
    Given two workers with the names "daha" and "jodl" exists
    And the worker "daha" is logged in
    And a project with the number 23001 exists in the application
    When "jodl" is assigned as project leader to the project with number 23001
    Then "jodl" becomes the project leader of the project 23001

#  Scenario: some shit