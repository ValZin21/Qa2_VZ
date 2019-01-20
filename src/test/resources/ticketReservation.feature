Feature: Ticket Reservation

  Scenario: Ticket reservation check
    Given Deparutre airport: RIX
    And   Destination airport: MEL
    And User data is:
      | name           | Valera     |
      | surname        | Zinchenko  |
      | discountCode   | 12345      |
      | travellerCount | 2          |
      | childrenCount  | 1          |
      | luggageCount   | 3          |
      | nextFlight     | 15-05-2018 |
    And seatNumber is: 21
    # all given should be on the upper steps
    And we are on the home page

    When we are selecting airports
    And pressing on the GOGOGO button
    Then registration page appears

    When we are filling the registration form
    And we are pressing on the Get Price button
    Then our price will be 3070 EUR

    When we are pressing on the Book! button
    Then we can choose the seat

    When we are selecting our seat number: predefined
#    When we are selecting our seat number: random
    And we are clicking Book! button
    Then we are receiving successful registration page

    When we are requesting reservation list
    Then we can see our reservation in the list

    When we are deleting our reservation tikcet
    And requesting the reservation list again
    Then our reservation disappears from the list





