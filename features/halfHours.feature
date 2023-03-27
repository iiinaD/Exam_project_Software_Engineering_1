Feature: half hours is a class
  Description: when a worker need to register its time he can do it with 30mins acssuy

  Scenario: class test a worker can input his worked hours
    Given console takes input 3 hours 22 miniuts
    Then halfHours is 3.5