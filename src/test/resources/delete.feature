Feature: Delete my booking

Scenario: General facility Landing page
  Given I successfully log in with account CCELVT to FBS sit
    And a booking with reference number 9999999
  When on the home content, I click General facility
    And on the top menu, I click on myBooking link
    And in the myBooking content, I enter Booking Ref No as 9999999
    And in the myBooking content, I click Search button
    And in the result area, I click item with reference number 9999999
    And in the main page, I click Delete booking button
  Then I should be alerted with message Are you sure? do you want to delete?
  When on alert dialog, I click OK
  Then I will be redirected to myBooking page
    And I should see text displayed as No records match the search criteria.