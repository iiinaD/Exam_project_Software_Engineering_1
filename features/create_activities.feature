Feature: Create activities
Actor: Worker

  #Gee (And there is a project is done illigal)
  Scenario: A worker can create a new activity if logged in
    Given a worker with the name “jodl” exists.
    And “jodl” is logged in.
    And there is a project "23001"
    And the project has an empty activity list
    When the worker creates a new Activity to the project.
    Then the project has activity "23001-001" in its activity list.

   #Jonas
  Scenario: A worker can create a new activity if logged in
    Given there is a worker with initials "jodl" logged in to the system
    And there is a project 23001 in the system
    And the project has an empty activity list
    When the worker creates a new Activity to the project.
    Then the project has activity "23001-001" in its activity list.


#  Scenario: A worker can not create a new activity if not logged in.
#    Given a worker with the name “jodl” exists.
#    And “jodl” is not logged in.
#    And there is a project "23001".
#    When the worker creates a new Activity  "23001-001" to the project.
#    Then the exception “Need to login a worker before adding an activity to the project” is thrown.