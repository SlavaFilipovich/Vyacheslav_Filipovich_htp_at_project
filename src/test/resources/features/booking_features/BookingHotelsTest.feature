Feature: Booking Hotels test

  Scenario: I run Moscow Test
    Given I go to booking.com
    When I enter desired location 'Moscow'
    And Enter duration of Trip 10 days in 5 days
    And Enter number of Adults 4, Children 0, Rooms 2 and click Search
    And I set filter with Min price per night
    Then I verify that price from checkbox-menu greater than from hotel-list with duration 10 days

  Scenario: I run Paris Test
    Given I go to booking.com
    When I enter desired location 'Paris'
    And Enter duration of Trip 3 days in 7 days
    And Enter number of Adults 4, Children 0, Rooms 2 and click Search
    And I set filter with Max price per night
    And Sort list from low to high price
    Then I verify that price from checkbox-menu greater than from hotel-list with duration 3 days

  Scenario: I run Oslo Test
    Given I go to booking.com
    When I enter desired location 'Oslo'
    And Enter duration of Trip 1 days in 1 days
    And Enter number of Adults 2, Children 1, Rooms 1 and click Search
    And I set star 3 for hotels
    And I set star 4 for hotels
    And Set background color as green
    And Set color of text as red
    Then Color of background should be green
    And Color of text should be red