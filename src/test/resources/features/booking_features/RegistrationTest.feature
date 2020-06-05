Feature: Registration test

  Scenario: I run Registration Test
    Given I get temporary Email from TrashMail.com
    When I go to booking.com and fill in registration form
    And Go to yandex.ru to check letter after registration
    And Click link with confirmation
    And Go to Booking Dashboard
    Then Alert message should not be existed
   