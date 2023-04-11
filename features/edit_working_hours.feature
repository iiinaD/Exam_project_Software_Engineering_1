#Danny
Feature: Edit working hours
  Description: Project members can edit their working hours on activities
  Actors: Worker or project leader

  Background:
    Given a worker with the initials "jodl" exists
    And "jodl" is logged in
    And there is a project 23001 in the system
    And the project has activity "23001-001" in its activity list

  Scenario: Project member edits working hours when an activity exists
    Given the worker has an activity "23001-001" in his activity list
    And the worker has worked for 0 hours and 0 minutes on the activity
    When the worker changes his working hours to 37 hours and 24 minutes
    Then the worker has spent 37 hours and 24 minutes on the activity

  Scenario: Project member edits working hours when no activities exists
    Given the worker has no activities in his activity list
    When the worker tries to edit his working hours
    Then an error message "This worker doesn’t have any activities yet." is given
