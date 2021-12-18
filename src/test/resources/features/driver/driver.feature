#Author: dayana@generalsoftwareinc.com

Feature: Add a new Driver
  As a GoHeavy Admin / Fleet Owner
  I want to add a Driver
  So that a new Driver is registered in the system

  Background:
    Given Any "GoHeavy Admin / Fleet Owner" is logged

  Scenario: Successfully create a new Driver
    Given the user is on the "Driver List" view
    And clicks on the "Add Driver" button
    When insert valid data
    And clicks on the "Add" button
    Then the system will add the new driver into the Driver List
    And will redirect to the previous view