Feature: Roadkill Cafe Catering Login
	As a Customer, I wish to login to Roadkill Cafe Catering using proper credentials
	
Scenario Outline: Logging into Roadkill Cafe Catering
	Given a user is at the login page of Roadkill Cafe Catering
	When a user inputs their "<username>" "<password>"
	And submits the information
	Then the user is redirected to the Home page
	
	Examples:
	|	username	|	password	|
	|	bobbert		|	bobbert		|
	|	wonderboy	|	wonderboy	|
	