#Danny
Feature: Create worker
  Description: A worker or project leader can create a new worker to the system
  Actors: Worker or project leader

  Scenario: Worker creates a new worker using unique initials
    Given a worker with the initials "jodl" does not exist
    When a worker creates a new worker using these initials
    Then a worker with these initials exists in the system

  Scenario: Worker creates a new worker using existing initials
    Given a worker with the initials "jodl" exists
    When a worker creates a new worker using these initials
    Then an error message "A worker with these initials already exists in the system." is given

  Scenario: Worker creates a new worker using too many characters
    Given a worker with the initials "jodly" does not exist
    When a worker creates a new worker using these initials
    Then an error message "Worker initials can't contain less than 2 or more than 4 characters." is given

  Scenario: Worker creates a new worker using illegal characters
    Given a worker with the initials "#/;5" does not exist
    When a worker creates a new worker using these initials
    Then an error message "Worker initials can't contain numbers or special characters." is given



