Feature: Registration test

  Scenario: I run Registration Test
    Given I go to Trashmail.com and get temp Email
    When Fill in registration form on Booking
    And Go to yandex.ru to check letter after registration
    And Click link with confirmation
    And Go to Booking Dashboard
    Then Alert message should not be existed
