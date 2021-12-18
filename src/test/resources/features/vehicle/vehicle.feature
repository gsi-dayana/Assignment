Feature: Add Vehicle 2.2
  As a: GoHeavy Admin / Fleet Owner
  I Want To: add a Vehicle
  So That: a new Vehicle is registered in the system.

  Background:
    Given Any "GoHeavy Admin / Fleet Owner" is logged
    And The user is in "Vehicles & Insurance List" view.

  Scenario: Add Vehicle 2.2 -- Add Vehicle

    When User clicks on "Add Vehicle" button.
    And User hover overs a Vehicle document image component with an image loaded
    And The user inserts valid data
    And User clicks on the "Done" button.
    Then System returns to the "Vehicles & Insurance List" view
    And System displays message "A new Vehicle was successfully created."
    And The System Creates a new Vehicle in "On-boarding" status.
    And The System registers the creation date.
