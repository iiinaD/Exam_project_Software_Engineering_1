Feature: Create new worker
  Description: A worker that is not logged in can create a new worker
  Actors: Worker not logged in

# Danny
#Scenario: Worker creates a new worker using an unique name
#  Given a worker with the name "jodl" does not exist
#  When the worker creates a new worker by the name of "jodl"
#  Then a worker by the name of "jodl" has been created
#
## Danny
#Scenario: Worker creates a new worker using an already existing name
#  Given a worker with the name "jodl" exists
#  When the worker creates a new worker by the name of "jodl"
#  Then an error message "A worker with this name already exists." is given

# Jonas
  Scenario: create a worker to the system
    Given there is a worker with initials "jodl"
    When the worker is added to systems worker list
    Then the worker exist in systems worker List