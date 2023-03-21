Feature: a worker can login
  Description: a worker in the system can login using its initials
  Actor Worker

  #Jonas
  Scenario: a worker in the system can login using its initials
    Given there is a worker with initials "jodl"
    When the worker is added to systems worker list
    And the worker can login using his initial "jodl" to login
    Then the worker is logged in
    And systems has a logged in worker

  #Jonas
  Scenario: using <there is a worker with initials "jodl" logged in
  to the system> logins a worker if he exist in system.
    Given there is a worker with initials "jodl"
    When the worker is added to systems worker list
    And there is a worker with initials "jodl" logged in to the system
    Then the worker is logged in
    And systems has a logged in worker

  #Jonas
  Scenario: using <there is a worker with initials "jodl" logged in to the system> if the worker dont exist in system he will be added
    Given there is a worker with initials "jodl" logged in to the system
    Then the worker is logged in
    And systems has a logged in worker
