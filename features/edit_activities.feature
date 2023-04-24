Feature: Edit activities

  #Gee
  Scenario: Change description of an activity
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project 0" with an activity "23001-001"
    And the activity has a description of "Debugging software"
    When the worker set the description of an activity of "Do not give up on life"
    Then the description of the activity should be "Do not give up on life"

  #Gee
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
    And "daha" has activity "23001-001" in his activity list

  #Daniel
  Scenario: Add Worker to an Activity when not project leader
    Given there is a worker with initials "jodl" logged in to the system
    And a worker with the initials "daha" exists
    And a project named "project 0" with an activity "23001-001"
    When "daha" assigns the worker "daha" to the activity
    Then an error message "Only project leaders can assign workers to activities" is given

  #Jonas
  Scenario: The acticity needs planning when it starts and ends
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project 0" with an activity "23001-001"
    And "jodl" is assigned as project leader to the project with number 23001
    When the activity is planned to start week 20 year 2023 and end week 19 year 2025
    Then the planned number of weeks is 103

  #Jonas
  Scenario: worker tries to access an activity that does not exist
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project 0" with an activity "23001-001"
    When the worker try to acces activity "23001-002"
    Then an error message "23001-002 dont exist" is given

  #Jonas
  Scenario: A worker with an acticty in his workeractivity list, try to acces an activity that the worker dont have
    Given there is a worker with initials "jodl" logged in to the system
    And a worker with the initials "daha" exists
    And a project named "project 0" with an activity "23001-001"
    When the project leader "jodl" assigns the worker "daha" to the activity
    Then the worker "daha" is assigned to the activity
    And "daha" has activity "23001-002" in his activity list
    Then an error message "daha dont have activity: 23001-002 in its workerActivityList" is given

  # Daniel
  Scenario: Change activity name after activity has been created
    Given there is a worker with initials "jodl" logged in to the system
    And a project named "project 0" with an activity named "frontend"
    When a worker changes the activity name to "backend"
    Then the activity name has changed to "backend"