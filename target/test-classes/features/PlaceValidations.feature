Feature: Validating Place API's

Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "POST" http Request
	Then The API call got succes with status code 200
	And "status" in Response body is "OK"
	And "scope" in Response body is "APP"
	And verify placeId created maps to "<name>" using "GetPlaceAPI"
	
	Examples: 
	|		name	|	 language	| 		address 			 |
	|	AAhouse	|  English	| World cross center |
	|	BBhouse	|  Spanish	| Sea cross center   |

Scenario: Verify if Delete Place functionality is working
	Given Delete Place Payload
	When User calls "DeletePlaceAPI" with "POST" http Request
	Then The API call got succes with status code 200
	And "status" in Response body is "OK"