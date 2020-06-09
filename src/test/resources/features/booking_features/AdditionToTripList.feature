Feature: Addition to trip list

  Scenario: I run AdditionToTripList Test
    Given I go to booking.com and sign in
    When I enter desired location
    And Enter duration of Trip
    And Enter number of Adults, Children, Rooms and click Search
    And Add hotels to wish list
    Then Color of hearts should be red
    And These hotels should be appeared in My next Trip