Feature: Create activities
Actor: Worker

   #Jonas
  Scenario: A worker can create a new activity to a project in the system if logged in
    Given there is a worker with initials "jodl" logged in to the system
    And there is a project 23001 in the system
    And the project has an empty activity list
    When the worker creates a new Activity to the project.
    Then the project has the activity "23001-001" in its activity list.

  #Jonas
  Scenario: A worker can not create a new activity if not logged in.
    Given a worker with the name "jodl" exists
    And the worker is not logged in
    And there is a project 23001 in the system
    And the project has an empty activity list
    When the worker creates a new Activity to the project.
    Then an error message "You must be logged in to access this feature. Please log in and try again." is given

  #Danny
  Scenario: Worker creates a new activity with a given name and description
    Given there is a worker with initials "jodl" logged in to the system
    And there is a project 23001 in the system
    And the project has an empty activity list
    When the worker creates a new activity with the name "Testing" and the description "Do testing here, there and everywhere."
    Then the project has activity "23001-001" in its activity list with the given name and description

  Scenario: Worker creates a new activity to a completed project which is disrespectful
    Given there is a worker with initials "jodl" logged in to the system
    And there is a project 23001 in the system
    And the project has an empty activity list
    And the worker creates a new activity with the name "Testing" and the description "Do testing here, there and everywhere."
    And the project has activity "23001-001" in its activity list with the given name and description
    And "jodl" is assigned as project leader to the project with number 23001
    And the project leader tries to mark the project as finished
    When the worker creates a new activity with the name "placeholder" and the description "doesn't matter"
    Then an error message "Cannot add activity: Project is completed!" is given

  Scenario: Worker creates a new activity to a completed project which is disrespectful(This time without a description)
    Given there is a worker with initials "jodl" logged in to the system
    And there is a project 23001 in the system
    And the project has an empty activity list
    And the worker creates a new Activity to the project.
    And the project has activity "23001-001" in its activity list with the given name and description
    And "jodl" is assigned as project leader to the project with number 23001
    And the project leader tries to mark the project as finished
    When the worker creates a new Activity to the project.
    Then an error message "Cannot add activity: Project is completed!" is given