#Statify

#1 Project Overview

-To program the project, I had to use the basketballhead API which offers a wide array of data ranging from team information, player information, player stats, and pretty much anything pertaining to basketball information. 
-However, this project only required player information and player stats. My program has 2 features: one feature is to search an individual player's stats based on the user input, and the other is to output two players's stats to compare them directly. -The program is very user-friendly and can be used for anyone with at least SOME knowledge of basketball but it's mostly intended for stat heads who have a passion to look at stats, and it's also for NBA GMs who are in the search for the next addition for their team. For example, the Phoenix Suns are currently in the seller's market which means they are looking to trade their star players. The Phoenix Suns can use this tool to determine which players could hurt their team the most. 

(SCREENSHOTS)

[Kevin Durant's Stats] https://imgur.com/a/OyZNRUL
[Bradley Beal's Stats] https://imgur.com/a/z9cDfeq


-As you can see, Kevin Durant leads in more statistical categories, so if the Phoenix Suns were to trade him over Bradley Beal, it should be more detrimental to their success.

#2 Code Breakdown

(API Class)

* getData(String endpoint)
-sends a GET request to the API service via the authentication key
-returns the raw response of the API data

* parsePlayer(String jsonresponse)
-identifies player information within the data and creates a new Player object with name, playerID, and position

* parsePlayerCareerStats(String jsonresponse)
-identifies player's career stats by using the last index of the stats array which the API uses to signify career stats

* parsePlayerSeasonStats(String jsonresponse) 
-identifies player's season stats by using the second to last index of the stats array which the API uses to signify stats from the 2024-25 NBA season

(Player Class)

-contains player name, playerID which is essential to extract data of a player, and position

(PlayerStats Class)

-contains all essential player stats from points, rebounds, assists, steals, blocks, turnovers, three-pointers, field goals and free throws attempted

* getTrueShooting() 
-calculates a player's overall efficiency which is a more accurate measure than field goal percentage

* KOBE() 
-my personal algorithm which calculates a player's overall impact based on stats

(Statify Class)

* main(String[] args)
-asks user for input if they want to either search an individual player or compare to players
-asks for additional input if they want to search for career or season stats
-outputs stats based on user input
-user-friendly interface and organizes information

* convertToId(String name)
-takes the string parameter and converts it to an id by taking the last name with the first two letters of the first name concatenated, followed with a number. 

* classifyScorer(PlayerStats stats) 
-takes the points of a player's stats and determines how prolific of a scorer they are. the greater the points, the more exciting the response

* compare(PlayerStats firstPlayer, PlayerStats secondPlayer)
-tallies how many statistical categories firstPlayer leads in over secondPlayer

#3 Features Implemented

(Base Project) 88%
-Uses an external API (Basketballhead API)
-Uses multiple Java methods and classes
-Parses JSON response and uses it meaningfully to parse stats and player information
-User-friendly interface
-Very interactive and the user can personalize their experience with multiple inputs
-Asks if user would wish to replay

(Basic Statistics/Machine Learning) 6%
-Calculates True Shooting% which is a well-known metric for player efficiency based on the equation:
(pointsPerGame / (2 * (getFieldGoalsAttempted() + 0.44 * getFreeThrowsAttempted()))) * 100
-Calculates "KOBE" which is my personal metric of evaluating a player's performance by blending all statistical categories accordingly:
(((pointsPerGame + 1.24 * (reboundsPerGame + assistsPerGame)) + Math.abs((2 * (blocksPerGame + stealsPerGame)) - 0.32 * turnoversPerGame))) * (getTrueShooting() * 0.08)

#4 Output Example: 

https://imgur.com/a/wUG5TD3

#5 What I Learned

-I learned how to connect to a public API
-I learned how to parse JSON data and use that data meaningfully
-I learned how to build a complex project with real-world applications

