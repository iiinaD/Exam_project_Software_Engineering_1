#Danny
Feature: Create worker
  Description: A worker or project leader can create a new worker to the system
  Actors: Worker or project leader

  Scenario: Worker creates a new worker using unique initials
    Given a worker with the initials "jodl" does not exist
    When the worker creates a new worker using these initials
    Then a worker with these initials exists in the system

  Scenario: Worker creates a new worker using existing initials
    Given a worker with the initials "jodl" exists
    When the worker creates a new worker using these initials
    Then an error message "A worker with these initials already exists in the system." is given

