Feature: WebService Test

  Scenario Outline: Find Users by names
    Given I start execution
    When I search user by "Vova" name
    Then I verify that I got "Vova"

    Examples:
      | index | resultName      |
      | 0     | "ALL_USERS"     |
      | 1     | "PARTIAL_SHORT" |
      | 2     | "FULL_SHORT"    |
      | 3     | "PARTIAL_LONG"  |
      | 4     | "FULL_LONG"     |