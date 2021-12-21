#Author: dayana@generalsoftwareinc.com

Feature: Go to manage Driver/Vehicle Documents
  As a GoHeavy Admin / Document Approver/ Fleet Owner
  I want to view the documents list of a selected driver/vehicle
  So that I can interact with the options allowed for the role.

  Background:
    Given Any "GoHeavy Admin / Fleet Owner" is logged
    And the user is on the "Driver List" view

    Scenario:
    When Clicks on the "Documents" icon that belong to a driver.
    Then The system displays the "Documents for DriverName" view.