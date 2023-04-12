#Danny
Feature: Edit working hours
  Description: A worker or project leader can edit their working hours on activities
  Actors: Worker or project leader


  Background:
    Given a worker with the initials "jodl" exist
    And "jodl" is logged in
    And there is a project 23001 in the system
    And the project has activity "23001-001" in its activity list

  Scenario: Worker edits working hours when a worker activity exist
    Given the worker has an activity "23001-001" in his activity list
    And the worker has worked for 10 hours and 44 minutes on the activity
    When the worker increments his working hours to 20 hours and 14 minutes
    Then the worker has spent 30 hours and 30 minutes on the activity

    #think the above should be somethif more like this:
#  Scenario: Worker edits working hours when a worker activity exist
#    Given the worker has an activity "23001-001" in his activity list
#    Then the worker can register he has spent 20 hours and 30 minutes the activity
#    Then the worker has spent 20.5 hours on the activity


  # this is not how the app is meant to work
  Scenario: Worker edits working hours when no worker activities exist
    Given the worker has no activities in his activity list
#    When the worker tries to edit his working hours
#    Then an error message "This worker doesn’t have any activities yet." is given

# Jonas not to implement but show the orginal
#  Scenario: A worker can register his time spent on an activity
#    Given there is a project "23001" with an activity "23001-001".
#    And a worker with the name “jodl” exists
#    And “jodl” is logged in
#    When the worker in the company is working on activity "23001-001".
#    Then the worker has an activity "23001-001" in his activity list.
#    When the day ends.
#    Then the worker can register he has spent 2.5 hours on activity  "23001-001"
#    And worker “jodl” has spent 2.5 hours on activity "23001-001".