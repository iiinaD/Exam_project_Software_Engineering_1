Feature: Access hours overview
  Background:
    Given a worker with the name "jodl" exists
    And a project named "project 0" with an activity "23001-001"
    And "jodl" worked for 5 hours on activity "23001-001"

  Scenario: Employee gets a hours overview
    Given there is a worker with initials "jodl" logged in to the system
    When the worker access hours overview for activity "23001-001"
    Then the worker should see "23001-001	project 0	5 Hrs"