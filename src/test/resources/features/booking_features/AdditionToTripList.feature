Feature: Addition to trip list

Scenario: I run AdditionToTripList Test
    Given I go to booking.com and sign in
    When I enter desired location
    And enter duration of my trip
    And enter number of Adults, Children, Rooms
    And click Search
    And add hotels to wish list
    Then Color of hearts should be red
    And These hotels should be appeared in My next Trip