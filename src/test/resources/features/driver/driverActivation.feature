#Author: dayana@generalsoftwareinc.com

Feature: Activate a Driver
  As a GoHeavy Admin / Fleet Owner
  I want to add a Driver
  So that a new Driver is registered in the system

  Background:
    Given Any "GoHeavy Administrator / Fleet Owner" is logged

  Scenario: Activate a Driver
    Given a driver is created
    And a new vehicle is associated to a driver
    When the driver and the vehicle documents gets approved
    Then the system will update the driver status to GoHeavy Ready
