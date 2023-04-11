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
  And "jodl" worked for 5 hours on activity "23001-001"
  When "jodl" changes his working hours for the activity to 37 hours
  Then worker "jodl" has spent 37 hours on activity "23001-001"

#Scenario: Project member edits working hours when no activities exists
#  Given the worker "jodl" has no activities in his activity list
#  When "jodl" tries to edit his working hours for activity "23001-001"
#  Then an error message "This worker doesn’t have any activities yet." is given
