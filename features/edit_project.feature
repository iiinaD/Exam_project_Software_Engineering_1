#Daniel
#Feature: Edit a project
#  Description: A worker can edit an existing project in the management system
#  Actors: Worker logged in, project leader

#  Scenario: A worker can change the name of the project after project is created
#    Given a worker with the name "daha" is logged in
#    And a project with the number  23001 and  name "Web projekt" exists
#    When the worker tries to change the name of the project to "Web projekt Google"
#    Then the name of the project changes to "Web projekt Google"

#  Scenario: A worker can assign a project leader to a project after project is created
#    Given two workers with the names "daha" and "jodl" exists
#    And a project with the number  23001 exists
#    When "daha" tries to assign "jodl" as project leader
#    Then "jodl" becomes the project leader
