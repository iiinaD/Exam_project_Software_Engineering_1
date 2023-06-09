  Feature: Edit a project
    Description: A worker can edit an existing project in the management system
    Actors: Worker logged in, project leader

    #Daniel
    Scenario: A worker can change the name of the project after project is created
      Given there is a worker with initials "daha" logged in to the system
      And a project with the number 23001 and name "Web projekt" exists
      When the worker tries to change the name of the project 23001 to "Web projekt Google"
      Then the name of the project 23001 changes to "Web projekt Google"

    #Daniel
    Scenario: A worker tries to change the name of the project after created but inputs wrong project number
      Given there is a worker with initials "daha" logged in to the system
      And a project with the number 23001 and name "Web projekt" exists
      And a project with the number 23005 does not exist
      When the worker tries to change the name of the project 23005 to "Web projekt Google"
      Then an error message "No project with the id 23005 exists in the system" is given

    #Daniel
    Scenario: A worker can assign a project leader to a project after project is created
      Given two workers with the names "daha" and "jodl" exists
      And the worker "daha" is logged in
      And a project with the number 23001 exists in the application
      And "jodl" hasn't already been assigned as the project leader
      When "jodl" is assigned as project leader to the project with number 23001
      Then "jodl" becomes the project leader of the project 23001

    #Daniel
    Scenario: A worker tries to a non-existing worker as project leader
      Given two workers with the names "daha" and "jodl" exists
      And the worker "daha" is logged in
      And a project with the number 23001 exists in the application
      When "ufts" is assigned as project leader to the project with number 23001
      Then an error message "No worker with the initials ufts exists in the system" is given

    #Daniel
    Scenario: The project leader can reassign a new project leader
      Given two workers with the names "daha" and "jodl" exists
      And the worker "daha" is logged in
      And a project with the number 23001 and name "Web projekt" and leader "daha" exists
      When "jodl" is assigned as project leader to the project with number 23001
      Then "jodl" becomes the project leader of the project 23001

    #Daniel
    Scenario: A non-project leader cannot reassign a new project leader
      Given two workers with the names "daha" and "jodl" exists
      And the worker "daha" is logged in
      And a project with the number 23001 and name "Web projekt" and leader "jodl" exists
      When "daha" is assigned as project leader to the project with number 23001
      Then an error message "Only the current project leader can assign a new leader" is given

    #Daniel
    Scenario: A project leader can mark the project finished
      Given there is a worker with initials "daha" logged in to the system
      And a project with the number 23001 and name "Web projekt" and leader "daha" exists
      When the project leader tries to mark the project as finished
      Then the project is finished

    #Daniel
    Scenario: A non project leader marks a project as finished
      Given two workers with the names "daha" and "jodl" exists
      And the worker "jodl" is logged in
      And a project with the number 23001 and name "Web projekt" and leader "daha" exists
      When the non project leader tries to mark the project as finished
      Then an error message "Only project leaders can finish projects" is given

    #Gee
    Scenario: A worker cannot change the name of the project if the number is illegal(not enough digits)
      Given there is a worker with initials "daha" logged in to the system
      And a project with the number 23001 and name "Web projekt" exists
      When the worker tries to change the name of the project 1 to "Web projekt Google"
      Then an error message "Project number invalid: Incorrect format. Should be [2-digit Year]+[3-digit number]" is given

    #Gee
    Scenario: A worker cannot change the name of the project if the number is illegal(too much digits)
      Given there is a worker with initials "daha" logged in to the system
      And a project with the number 23001 and name "Web projekt" exists
      When the worker tries to change the name of the project 1 to "Web projekt Google"
      Then an error message "Project number invalid: Incorrect format. Should be [2-digit Year]+[3-digit number]" is given

    #Gee
    Scenario: A worker cannot change the name of the project if the number is illegal(Negative integer)
      Given there is a worker with initials "daha" logged in to the system
      And a project with the number 23001 and name "Web projekt" exists
      When the worker tries to change the name of the project -23001 to "Web projekt Google"
      Then an error message "Project number invalid: Incorrect format. Should be [2-digit Year]+[3-digit number]" is given

