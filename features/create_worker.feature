Feature: Create worker
  Description: A worker that is not logged in can create a new worker
  Actors: Worker not logged in

# Danny
  Background:
    Given the worker is not logged in

# Danny
  Scenario: Worker creates a new worker using unique initials
    Given a worker with the initials "jodl" does not exist
    When the worker creates a new worker with the initials "jodl"
    Then a worker with the initials "jodl" has been created

# Danny
  Scenario: Worker creates a new worker using existing initials
    Given a worker with the initials "jodl" exists
    When the worker creates a new worker with the initials "jodl"
    Then an error message "A worker with these initials already exists." is given

# Jonas
  Scenario: create a worker to the system
    Given there is a worker with initials "jodl"
    When the worker is added to systems worker list
    Then the worker exist in systems worker List