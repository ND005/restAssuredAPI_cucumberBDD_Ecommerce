@GML
Feature: Web series - GraphQL data service API

  Scenario Outline: TS01 - Verify mutation process of Graph QL data service
    Given Verify the mutation data file and truncate the old file
    #Prepare the GQL Mutaion with data
    When Create the data file and intiate the process
    And Create createLocation data using <LocationName>,<LocationType> and <LocationDimension> of the character
    And Create createCharacter data using <CharacterName>,<CharacterType>,<CharacterStatus>,<CharecterSpecies>,<CharecterGender>,CharecterImage,OriginId and LocationId of the character
    And Create createEpisode data using <EpisodeName>,<EpisodeAirDate> and <EpisodeID> of the episode
    Then Associate the episode and character using associateEpisodeCharacter GQL
    And Verify the file created with createLocationID,createCharacterID and createEpisodeID

    Examples: 
      | LocationName | LocationType | LocationDimension | CharacterName | CharacterType | CharacterStatus | CharecterSpecies | CharecterGender | EpisodeName | EpisodeAirDate | EpisodeID |
      | Goutham City | Fictional    | 100Sq KM          | Bat Man       | Hero          | Alive           | Human            | Male            | Dark Born   | 05-Jan-1998    |       007 |
@GQL
  Scenario Outline: TS02 - Verify Graph QL data using data query service
    Given Verify the query data file and intiate the process
    When Procced the GQL flow based on data create through mutation
    Then Verify location data :<LocationName>,<LocationType> and <LocationDimension>
    Then Verify charactar data :<CharactarName>,<CharactarType>,<CharactarStatus>,<CharactarSpecies> and <CharactarGender>
    Then Verify episode data :<EpisodeName>,<EpisodeAirDate> and <EpisodeID>

    Examples: 
      | LocationName | LocationType | LocationDimension | CharactarName | CharactarType | CharactarStatus | CharactarSpecies | CharactarGender | EpisodeName | EpisodeAirDate | EpisodeID |
      | Goutham City | Fictional    | 100Sq KM          | Bat Man       | Hero          | Alive           | Human            | Male            | Dark Born   | 05-Jan-1998    |       007 |
