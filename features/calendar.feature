Feature: Calendar

  Scenario: Get date from DateServer
    Given the date server is running
    When I request the date
    Then the day should be the current date