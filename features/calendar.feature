Feature: Calendar

  Scenario: Get date from DateServer
    Given the date server is running
    When I request the date
    Then the day should be the current date

  Scenario: test if Dateserver methods works
    Given the date server is running
    Then it works


  Scenario: year x has y weeks
    Given the date server is running
    Then year 2024 there is 52 weeks
