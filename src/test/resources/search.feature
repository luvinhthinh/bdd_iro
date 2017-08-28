Feature: Search my booking

Scenario: Search all booking
  Given I successfully log in with account CCELVT to FBS sit
  When on the home content, I click General facility
    And on the top menu, I click on myBooking link
    And in the myBooking content, I click Search button
  Then I should see all of my booking 
  
Scenario: Search booking by reference number
  Given I successfully log in with account CCELVT to FBS sit
  	And a booking with reference number 9999999
  When on the home content, I click General facility
    And on the top menu, I click on myBooking link
    And in the myBooking content, I click Search button
    And in the result area, I click item with reference number 9999999
  Then I should see information of my booking
    And I should see booking purpose as Test purpose
  