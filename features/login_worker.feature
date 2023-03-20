Feature: a worker can login

  #Jonas
  Scenario:
    Given there is a worker with initials "jodl"
    When the worker is added to systems worker list
    And the worker can login using his initial "jodl" to login
    Then the worker is logged in
    And systems has a logged in worker

