Feature: Booking Hotels test

  Scenario: I run Moscow Test
    Given I go to booking.com
    When I enter desired location
    And Enter duration of Trip
    And Enter number of Adults, Children, Rooms and click Search
    And I set filter with Min price per night
    Then I verify that price from checkbox-menu greater than from hotel-list

  Scenario: I run Paris Test
    Given I go to booking.com
    When I enter desired location
    And Enter duration of Trip
    And Enter number of Adults, Children, Rooms and click Search
    And I set filter with Max price per night
    And Sort list from low to high price
    Then Filter price should be greater than hotel price in list

  Scenario: I run Oslo Test
    Given I go to booking.com
    When I enter desired location
    And Enter duration of Trip
    And Enter number of Adults, Children, Rooms and click Search
    And I set stars of hotels
    And Set background color as green
    And Set color of text as red
    Then Color of background should be green
    And Color of text should be red