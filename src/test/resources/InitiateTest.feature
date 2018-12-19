Feature: Initiate Test


  Scenario: Open tea pack
    Given Print test annotation Hi tea bag!


  Scenario: Open tea pack
    Given student:
      | name   | Valera |
      | age    | 12     |
      | weight | 123    |
      | height | 34     |


    When we requesting name and age together

    Then response must be Valera: 12