Feature: a worker can login

  #Jonas
  Scenario: logging a given worker into the system
    Given there is a worker with initials "jodl"
    When the worker is added to systems worker list
    And the worker can login using his initial "jodl" to login
    Then the worker is logged in
    And systems has a logged in worker


  Scenario: using <there is a worker with initials "jodl" logged in
  to the system> logins a worker if he exist in system.
    Given there is a worker with initials "jodl"
    When the worker is added to systems worker list
    And there is a worker with initials "jodl" logged in to the system
    Then a worker with initial "jodl" is logged in
    And systems has a logged in worker

  Scenario: using <there is a worker with initials "jodl" logged in
  to the system> if the worker dont exist in system he will be added
    Given there is a worker with initials "jodl" logged in to the system
    Then a worker with initial "jodl" is logged in
    And systems has a logged in worker
