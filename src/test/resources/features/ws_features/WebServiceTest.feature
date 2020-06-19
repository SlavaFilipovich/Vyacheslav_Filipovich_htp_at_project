Feature: WebService Test

  Scenario: I look for all users from web service
    Given I start test...
    When I get users by condition 0
    Then I compare gotten amount of Users with expected result
    
  Scenario Outline: Find Users by user_names
    Given I start test...
    When I get users by condition <index>
    Then I compare gotten usernames with expected result for <searchData>

    Examples:
      | index | searchData      |
      | 1     | "PARTIAL_LONG"  |
      | 2     | "FULL_LONG"     |
      | 3     | "PARTIAL_SHORT" |
      | 4     | "FULL_SHORT"    |