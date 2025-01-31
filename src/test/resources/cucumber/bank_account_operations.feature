Feature: Bank account operations
  Checking bank account operations

  Scenario: Successfully withdraw money when balance is enough
    Given Account with a balance of 1000
    When Trying to withdraw 500
    Then Account balance should be 500

  Scenario: Cannot withdraw more money than the account balance
    Given Account with a balance of 1000
    When Trying to withdraw 1001
    Then Operation should be denied due to insufficient funds
    And Account balance should remain 1000

  Scenario: Cannot withdraw money when sum is negative
    Given Account with a balance of 200
    When Trying to withdraw -100
    Then Operation should be denied due to negative withdraw
    And Account balance should remain 200

  Scenario: Cannot withdraw money when sum is null
    Given Account with a balance of 200
    When Trying to withdraw 0
    Then Operation should be denied due to null withdraw
    And Account balance should remain 200

  Scenario: Successfully deposit money when sum is not negative
    Given Account with a balance of 1000
    When Trying to deposit 500
    Then Account balance should be 1500

  Scenario: Cannot deposit money when sum is negative
    Given Account with a balance of 200
    When Trying to deposit -100
    Then Operation should be denied due to negative sum
    And Account balance should remain 200

  Scenario: Cannot deposit money when sum is null
    Given Account with a balance of 200
    When Trying to deposit 0
    Then Operation should be denied due to null sum
    And Account balance should remain 200
