Feature: Create new worker
  Description: A worker that is not logged in can create a new worker
  Actors: Worker not logged in


Scenario: create a worker to the system
  Given there is a worker with initials "jodl"
  When the worker is added to systems worker list
  Then the worker exist in systems worker List


Scenario:
  Given a worker with initial "jodl" is in system worker list
  Then the worker exist in systems worker List


#Scenario: Worker creates a new worker using an unique name
#  Given a worker with the name “jodl” doesn’t exist
#  When the worker creates a new worker by the name of “jodl”
#  Then a worker by the name of of “jodl” has been created


#Scenario: Worker creates a new worker using an already existing name
#  Given a worker with the name “jodl” exists
#  When the worker creates a new worker by the name of “jodl”
#  Then the exception “A worker with this name already exists.” is thrown
