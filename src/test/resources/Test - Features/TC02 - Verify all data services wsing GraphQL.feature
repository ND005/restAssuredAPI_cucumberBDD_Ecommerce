@GQL
Feature: Web series - GraphQL data service API

  Scenario Outline: TS01 - Verify mutation process of Graph QL data service
    Given Verify the mutation data file and intiate the process
    #Prepare the GQL Mutaion with data
    And Create createLocation data using <LocationName>,<LocationType> and <LocationDimension> of the character
    And Create createCharacter data using <CharacterName>,<CharacterType>,<CharacterStatus>,<CharecterSpecies>,<CharecterGender>,CharecterImage,OriginId and LocationId of the character
    And Create createEpisode data using <EpisodeName>,<EpisodeAirDate>,<EpisodeID> of the episode
    Then Associate the episode and character using associateEpisodeCharacter GQL
    And Verify the file created with createLocationID,createCharacterID and createEpisodeID

    Examples: 
      | LocationName | LocationType | LocationDimension | CharacterName | CharacterType | CharacterStatus | CharecterSpecies | CharecterGender | EpisodeName | EpisodeAirDate | EpisodeID |
      | Goutham City | Fictional    | 100Sq KM          | Bat Man       | Hero          | Alive           | Human            | Male            | Dark Born   | 05-Jan-1998    |       007 |