Feature: half hours is a class
  Description: when a worker need to register its time he can do it with 30mins acssuy

  Scenario: class test a worker can input his worked hours
    Given console takes input 3 hours 22 minuts
    Then halfHours is 3.5

  Scenario: second class test a worker can input his worked hours
    Given console takes input 3 hours 14 minuts
    Then halfHours is 3.0

  Scenario: second class test a worker can input his worked hours
    Given console takes input 3 hours 46 minuts
    Then halfHours is 4