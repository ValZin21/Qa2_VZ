Feature: Weather tests

  Scenario: Coordinates check
    Given city name: London,uk
    When we are requesting weather info
    Then lon is: -0.13
    And lat is: 51.51

  Scenario: Main parameters check
    Given city name: London,uk
    When we are requesting weather info
    Then temp is: 280.32
    And pressure is: 1012
    And humidity is: 81
    And temp_min is: 279.15
    And temp_max is: 281.15

  Scenario: Wind check
    Given city name: London,uk
    When we are requesting weather info
    Then speed is: 4.1
    And deg is: 80

  Scenario: Clouds check
    Given city name: London,uk
    When we are requesting weather info
    Then all is: 90

  Scenario: Sys check
    Given city name: London,uk
    When we are requesting weather info
    Then type is: 1
    And sys id is: 5091
    And message is: 0.0103
    And country is: GB
    And sunrise is: 1485762037
    And sunset is: 1485794875

  Scenario: Additional parameters check
    Given city name: London,uk
    When we are requesting weather info
    Then base is: stations
    And visibility is: 10000
    And dt is: 1485789600
    And additional id is: 2643743
    And name is: London
    And cod is: 200
