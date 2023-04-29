
Feature: half hours is a class
  Description: when a worker need to register its time he can do it with 30mins acssuy,
  the time will be stores using halfHours

  # Jonas
  Scenario: class test a worker can input his worked hours
    Given console takes input 3 hours 22 minuts
    Then halfHours is 3.5

  # Jonas
  Scenario: second class test a worker can input his worked hours
    Given console takes input 3 hours 14 minuts
    Then halfHours is 3.0

  # Jonas
  Scenario: second class test a worker can input his worked hours
    Given console takes input 3 hours 46 minuts
    Then halfHours is 4

  # Jonas
  Scenario: time is not 0 and need incremented
    Given console takes input 3 hours 46 minuts
    Then halfHours is 4
    When halfHours is ingrementes with 3 hours 46 minuts
    Then halfHours is 8

  # Jonas
  Scenario: when time is below 0 it will be 0
    Given console takes input 3 hours 46 minuts
    Then halfHours is 4
    When halfHours is ingrementes with -3 hours -46 minuts
    Then halfHours is 0