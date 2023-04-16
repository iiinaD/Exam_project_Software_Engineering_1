Feature: Edit activities

#   Scenario: Remove an activity
#     Given a worker with the name "jodl" exists
#     And "jodl" is logged in
#     And there is a project "23001" with an activity "23001-001"
#     When the worker removes the activity named "23001-001"
#     Then the activity named "23001-001" should not exist in the activity list of project "23001"
# ^^^This belongs to project features, TODO^^^

  Scenario: Change description of an activity
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project 0" with an activity "23001-001"
    And the activity has a description of "Debugging software"
    When the worker set the description of an activity of "Do not give up on life"
    Then the description of the activity should be "Do not give up on life"

  Scenario: Change budget time on an activity
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project 0" with an activity "23001-001"
    And the activity has a budget time of 100 hours
    When the worker changes the budget time to 420 hours
    Then the budget time of the activity should be 420 hours

  #Daniel
  Scenario: Add Worker to an Activity
    Given there is a worker with initials "jodl" logged in to the system
    And a worker with the initials "daha" exists
    And a project named "project 0" with an activity "23001-001"
    When the project leader "jodl" assigns the worker "daha" to the activity
    Then the worker "daha" is assigned to the activity

  Scenario: Add Worker to an Activity when not project leader
    Given there is a worker with initials "jodl" logged in to the system
    And a worker with the initials "daha" exists
    And a project named "project 0" with an activity "23001-001"
    When "jodl" assigns the worker "daha" to the activity
    Then an error message "Only project leaders can assign workers to activities" is given

  Scenario: The acticity needs planning when it starts and ends
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project 0" with an activity "23001-001"
    And "jodl" is assigned as project leader to the project with number 23001
    When the activity is planned to start week 50 year 2023 and end week 5 year 2024
    Then the planned number of weeks is 7
