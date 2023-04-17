Feature: Access hours overview
  Background:
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project X" with an activity "23001-001"
    And "jodl" worked for 5 hours and 30 minutes on activity "23001-001"

  Scenario: Employee gets a hours overview
    When the worker access hours overview for activity "23001-001"
    Then the worker should see "23001-001	project X	5.5 Hrs"