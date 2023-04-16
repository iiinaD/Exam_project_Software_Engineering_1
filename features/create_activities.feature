Feature: Create activities
Actor: Worker

   #Jonas
  Scenario: A worker can create a new activity to a project in the system if logged in
    Given there is a worker with initials "jodl" logged in to the system
    And there is a project 23001 in the system
    And the project has an empty activity list
    When the worker creates a new Activity to the project.
    Then the project has activity "23001-001" in its activity list.

  #Jonas
  Scenario: A worker can not create a new activity if not logged in.
    Given a worker with the name "jodl" exists
    And the worker is not logged in
    And there is a project 23001 in the system
    And the project has an empty activity list
    When the worker creates a new Activity to the project.
    Then an error message "You must be logged in to access this feature. Please log in and try again." is given