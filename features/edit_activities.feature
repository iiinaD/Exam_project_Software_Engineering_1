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
    Then an error message "Only project leaders can assign workers to activities." is given

  #Danny
  Scenario: Add Worker to an activity when no project leader exists
    Given there is a worker with initials "jodl" logged in to the system
    And there is a project 23001 in the system
    And the project has activity "23001-001" in its activity list
    And the project has no project leader
    When "jodl" assigns the worker "jodl" to the activity
    Then an error message "Only project leaders can assign workers to activities." is given

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
    Then an error message "The activity with id: 23001-002 does not exist" is given

  #Jonas
  Scenario: project leader changes adds a worker to an activity that is completed
    Given there is a worker with initials "jodl" logged in to the system
    And a worker with the initials "Dahl" exists
    And a project named "project 0" with an activity "23001-001"
    When "jodl" is assigned as project leader to the project with number 23001
    Then he marks project 23001 as finished
    When "Dahl" is added to "23001-001"
    Then an error message "The project 23001 is marked as finished" is given

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

  # Gee
  Scenario: Add Worker to an Activity but the worker is already in the list so what's the point?
    Given there is a worker with initials "jodl" logged in to the system
    And a worker with the initials "daha" exists
    And a project named "project 0" with an activity "23001-001"
    When the project leader "jodl" assigns the worker "daha" to the activity
    Then the worker "daha" is assigned to the activity
    And "daha" has activity "23001-001" in his activity list
    When the project leader "jodl" assigns the worker "daha" to the activity
    Then an error message "daha is already in the list!" is given