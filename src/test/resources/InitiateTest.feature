Feature: Initiate Test


  Scenario: Open tea pack
    Given Print test annotation Hi tea bag!


  Scenario: Check one student
    Given student:
      | name   | Valera |
      | age    | 12     |
      | weight | 123    |
      | height | 34     |


    When we requesting name and age together

    Then response must be Valera: 12


    Scenario Outline: Check many students
      Given students:
        | name   | Valera |
        | age    | 12     |
        | weight | 123    |
        | height | 34     |

      And students:
        | name   | Petja |
        | age    | 23    |
        | weight | 65    |
        | height | 176   |

      When we requesting names and ages together

      Then responses must be <students>

      Examples:
        | students   |
        | Valera: 12 |
        | Petj: 23  |