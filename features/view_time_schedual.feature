# Jonas
# this feature can be a bit overwhelming so I have left some comments
Feature: view time schedule
  Description: A project leader should be able to view which workers, that should be available a given week.
  We are gonna let anybody able to access the feature.
  Actor: project Leader

  Background:
    Given there is a worker with initials "jodl" logged in to the system
    And a worker with the initials "daha" exists
    And a worker with the initials "dahl" exists
    # Projects and activitys with planned time
    And a project named "project 0" with an activity "23001-001"
    And the activity is planned to start week 23 year 2023 and end week 23 year 2023
    And 23001 gets a new activity "23001-002"
    And the activity is planned to start week 20 year 2023 and end week 24 year 2023
    And a project named "project 1" with an activity "23002-001"
    And the activity is planned to start week 21 year 2023 and end week 25 year 2023
    # makes jodl project leader in both projects
    And "jodl" is assigned as project leader to the project with number 23001
    And "jodl" is assigned as project leader to the project with number 23002
    # add the workes to the
    And "jodl" is added to "23001-001"
    And "jodl" is added to "23001-002"
    And "jodl" is added to "23002-001"
    And "dahl" is added to "23002-001"


  Scenario: Get all activity's that is active doing a given week
    Given a worker want to know which workers work in week 21 year 2023
    # activity in the list is "23001-002" and "23002-001"
    Then a activityList will have length 2
    # The string is long an will be manually checked
    When the worker wants to view which workers are assigned the activity's a string is given
  Scenario: Get all activity's that is active doing a given week but its illegal
    Given a worker want to know which workers work in week 0 year 2023
    Then an error message "Invalid week: Week must be between 1-52" is given
  Scenario: Get all activity's that is active doing a given week but its illegal
    Given a worker want to know which workers work in week -23 year 2023
    Then an error message "Invalid week: Week must be between 1-52" is given
  Scenario: Get all activity's that is active doing a given week but its illegal
    Given a worker want to know which workers work in week 99 year 2023
    Then an error message "Invalid week: Week must be between 1-52" is given
  Scenario: Get all activity's that is active doing a given week but its illegal
    Given a worker want to know which workers work in week 21 year 202
    Then an error message "Invalid year: Year must be between 1000-9999" is given
  Scenario: Get all activity's that is active doing a given week but its illegal
    Given a worker want to know which workers work in week 21 year -2023
    Then an error message "Invalid year: Year must be between 1000-9999" is given
  Scenario: Get all activity's that is active doing a given week but its illegal
    Given a worker want to know which workers work in week 21 year 202222
    Then an error message "Invalid year: Year must be between 1000-9999" is given