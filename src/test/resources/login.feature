Feature: Login

Scenario: Successful log in
  Given FBS sit environment
  When I login with account CCELVT
  Then on the main content, I should see Select the option that you would like to book
  Then I log out
  
Scenario: Empty id and password
  Given FBS sit environment
  When on the login page, I enter user id as <empty> 
    And on the login page, I enter password as <empty>
    And on the login page, I click Login
  Then I should be alerted with message Please enter user id and password 
  Then on alert dialog, I click OK
  
Scenario: Empty id
  Given FBS sit environment
  When on the login page, I enter user id as <empty> 
    And on the login page, I enter password as abc
    And on the login page, I click Login
  Then I should be alerted with message Please enter user id.
  Then on alert dialog, I click OK
  
Scenario: Empty password
  Given FBS sit environment
  When on the login page, I enter user id as abc
    And on the login page, I enter password as <empty>
    And on the login page, I click Login
  Then I should be alerted with message Please enter password
  Then on alert dialog, I click OK
  
Scenario: Unsuccessful log in
  Given FBS sit environment
  When on the login page, I enter user id as abc
    And on the login page, I enter password as 123
    And on the login page, I click Login
  Then I should see error message You have entered an invalid Login ID and Password.