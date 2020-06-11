Feature: Addition to trip list

  Scenario: I run AdditionToTripList Test
    Given I go to booking.com and sign in
    When I enter desired location 'Madrid'
    And Enter duration of Trip 5 days in 30 days
    And Enter number of Adults 2, Children 0, Rooms 1 and click Search
    And Add hotels to wish list
    Then Color of hearts should be red
    And These hotels should be appeared in My next Trip