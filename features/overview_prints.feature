# Jonas
Feature: print overview
  Description: A worker can view Strings of activitys and projects
  Actor: worker

  Background:
    Given there is a worker with initials "jodl" logged in to the system
    And a worker with the initials "Dahl" exists
    And a worker with the initials "daha" exists
    # Projects and activitys with planned time, next line adds daha to the activity
    And a project named "project 0" with an activity "23001-001"
    And the activity is planned to start week 23 year 2023 and end week 23 year 2023
    And 23001 gets a new activity "23001-002"
    And the activity is planned to start week 20 year 2023 and end week 24 year 2023
    # makes jodl project leader in both projects
    And "jodl" is assigned as project leader to the project with number 23001
    And the project leader tries to mark the project as finished
    # add the workes to the
    And "jodl" is added to "23001-001"
    And "jodl" is added to "23001-002"
    And "Dahl" is added to "23001-001"
    And "Dahl" is added to "23001-002"
    And "daha" is added to "23001-002"
    # adds some extra to 002
    And the activity has a description of "Debugging software"
    And the activity has a budget time of 100 hours
    # adds an empty project
    And there is a project 23002 in the system
    And there is a project 23003 in the system
    And the worker creates a new activity with the name "Testing" and the description "Do testing here, there and everywhere."


  Scenario: A manually test if the print looks okay, the scenaio helps get the string to print.
    Given there is 3 projects int the system
    Then a worker wants to get an overview of project 23001
    And a worker wants to get an overview of project 23002
    And a worker wants to get an overview of project 23003

  Scenario: A manually test if print of an activity
    Given a worker wants to get an overview of activity "23001-001"