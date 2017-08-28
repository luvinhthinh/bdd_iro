Feature: Home / Landing page

Scenario: Others
  Given I successfully log in with account CCELVT to FBS sit
  When on the home content, I click Others
  Then on the top menu, I should see options Home,Calendar View,Block Booking,myBooking,Reports,Logout 
    And on the monthly calendar, row 1 should displaying Sun,Mon,Tue,Wed,Thu,Fri,Sat

  