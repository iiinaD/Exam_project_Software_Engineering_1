Feature: Create worker
  Description: A worker or project leader can create a new worker
  Actors: Worker or project leader

# Danny
  Scenario: Worker creates a new worker using unique initials
    Given a worker with the initials "jodl" does not exist
    When the worker creates a new worker using these initials
    Then a worker with these initials exist in the system

# Danny
  Scenario: Worker creates a new worker using existing initials
    Given a worker with the initials "jodl" exist
    When the worker creates a new worker using these initials
    Then an error message "A worker with these initials already exist." is given

# Jonas
  Scenario: create a worker to the system
    Given there is a worker with initials "jodl"
    When the worker is added to systems worker list
    Then the worker exist in systems worker List