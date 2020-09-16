Feature: Watch Videos (https://www.youtube.com/)
  Scenario: Open video from the home page
    Given I am on the home page
    And there is a video on the home page
    When I click on a video's title or thumbnail
    Then the video should open and start playing
  Scenario: Pausing a playing video
    Given I have a video open
    And the video is playing
    When I click on the video
    Then the video should pause
  Scenario: Playing a paused video
    Given I have a video open
    And the video is paused
    When I click on the video
    Then the video should play
