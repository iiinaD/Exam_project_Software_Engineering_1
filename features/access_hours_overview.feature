Feature: Access hours overview
  Background:
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project 0" with an activity "23001-001"
    And add a activity "23001-002""
    And "jodl" worked for 5 hours and 30 minutes on activity "23001-001"
    And "jodl" worked for 3 hours and 30 minutes on activity "23001-002"

  Scenario: Employee gets a hours overview
    When the worker access hours overview for activity "23001-001"
    Then the worker should see
    """
    23001-001	project 0	5,5 Hrs
    """

  Scenario: Employee gets personal hours overview
    When the worker access personal hours overview
    Then the worker should see
    """
    23001-001	project 0	5,5 Hrs
    23001-002	project 0	3,5 Hrs

    """
  Scenario: Employee gets activity hours overview
    When the worker access activity hours overview
    Then the worker should see
    """
    23001-001	project 0
    jodl	5,5 Hrs

    """

  Scenario: Employee gets a hours overview but its illegal
    When the worker access hours overview for activity "23001-00"
    Then an error message "Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]" is given
  Scenario: Employee gets a hours overview but its illegal
    When the worker access hours overview for activity "2300-001"
    Then an error message "Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]" is given
  Scenario: Employee gets a hours overview but its illegal
    When the worker access hours overview for activity "23001001"
    Then an error message "Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]" is given
  Scenario: Employee gets a hours overview but its illegal
    When the worker access hours overview for activity "abcde-abc"
    Then an error message "Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]" is given
  Scenario: Employee gets a hours overview but its illegal
    When the worker access hours overview for activity "23001-"
    Then an error message "Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]" is given
  Scenario: Employee gets a hours overview but its illegal
    When the worker access hours overview for activity "-001"
    Then an error message "Activity ID invalid: Incorrect format. Should be [Project Number]-[Activity ID]" is given