
Feature: Edit working hours
  Description: A worker or project leader can edit their working hours on activities
  Actors: Worker or project leader

  #Danny
  Background:
    Given a worker with the initials "jodl" exists
    And "jodl" is logged in
    And there is a project 23001 in the system
    And the project has activity "23001-001" in its activity list

  #Danny
  Scenario: Worker edits working hours when the worker has activities
    Given the worker has an activity "23001-001" in his activity list
    And the worker has worked for 10 hours and 44 minutes on the activity
    When the worker increments his working hours to 20 hours and 14 minutes
    Then the worker has spent 30 hours and 30 minutes on the activity

  #Jonas
  Scenario: Worker edits working hours when the worker has no activities,
  then the activity will be added to him. Because a worker can work on an
  activity even though it don't exist.
    Given the worker has no activities in his activity list
    When he registers he has worked for 5 hours and 16 minutes on activity "23001-001"
    Then the worker has worked 5.5 hours on activity "23001-001"

   #Jonas
  Scenario: Worker can degresse time by entering a negative number
    Given the worker has no activities in his activity list
    When he registers he has worked for 5 hours and 16 minutes on activity "23001-001"
    Then the worker has worked 5.5 hours on activity "23001-001"
    When he registers he has worked for -11 hours and 0 minutes on activity "23001-001"
    Then the worker has worked 0 hours on activity "23001-001"

