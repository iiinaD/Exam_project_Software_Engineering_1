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

  Scenario: Worker edits working hours when no worker activities exist
    Given the worker has no activities in his activity list
#    When the worker tries to edit his working hours
#    Then an error message "This worker doesn’t have any activities yet." is given
