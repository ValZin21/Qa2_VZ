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
      | Petja: 23  |


  Scenario Outline: Check many students from examples
    Given students from example

    When we are requesting <names> and <ages> together from example

    Then response must be <students>

    Examples:
      | students   | names  | ages |
      | Valera: 12 | Valera | 12   |
      | Petja: 23  | Petja  | 23   |