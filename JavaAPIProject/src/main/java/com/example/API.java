package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;


import javax.management.RuntimeErrorException;





public class API {
   public static String getData(String endpoint) throws Exception {
       URL url = new URL(endpoint);
       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       connection.setRequestMethod("GET");


       connection.setRequestProperty("X-RapidAPI-Host", "basketball-head.p.rapidapi.com");
       connection.setRequestProperty("X-RapidAPI-Key", "cab1efd48dmshb8932ee98bfee27p1e8946jsnd71d581238d3");


       BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
       String inputLine;


       StringBuilder content = new StringBuilder();
       while ((inputLine = buff.readLine()) != null) {
           content.append(inputLine);
       }
       buff.close();
       connection.disconnect();
       return content.toString();
   }


   public static Player parsePlayer(String jsonResponse) {
       Gson gson = new Gson();
       JsonObject root = gson.fromJson(jsonResponse, JsonObject.class);


       JsonObject player = root.getAsJsonObject("body");


       String name = player.get("firstName").getAsString() + " " + player.get("lastName").getAsString();
       String id = player.get("playerId").getAsString();
  
       String position = player.get("positions").getAsString();


       return new Player(name, id, position);
   }


   public static PlayerStats parsePlayerCareerStats(String jsonResponse) {
       Gson gson = new Gson();
       JsonObject root = gson.fromJson(jsonResponse, JsonObject.class);


       JsonArray statsArray = root.getAsJsonArray("body");


       JsonObject statsData = statsArray.get(statsArray.size() - 1).getAsJsonObject();
      
       double ppg = statsData.get("pointsPerGame").getAsDouble();
       double rpg = statsData.get("defensiveReboundsPerGame").getAsDouble() + statsData.get("offensiveReboundsPerGame").getAsDouble();
       double apg = statsData.get("assistsPerGame").getAsDouble();
       double tov = statsData.get("turnoversPerGame").getAsDouble();
       double spg = statsData.get("stealsPerGame").getAsDouble();
       double bpg = statsData.get("blocksPerGame").getAsDouble();
       double fga = statsData.get("fieldGoalAttemptsPerGame").getAsDouble();
       double tpm = statsData.get("threePointFieldGoalsMadePerGame").getAsDouble();
       double fta = statsData.get("freeThrowAttemptsPerGame").getAsDouble();


       return new PlayerStats(ppg, rpg, apg, tov, spg, bpg, fga, tpm, fta);
   }


   public static PlayerStats parsePlayerSeasonStats(String jsonResponse) {
       Gson gson = new Gson();
       JsonObject root = gson.fromJson(jsonResponse, JsonObject.class);


       JsonArray statsArray = root.getAsJsonArray("body");


       JsonObject statsData = statsArray.get(statsArray.size() - 2).getAsJsonObject();
      
       double ppg = statsData.get("pointsPerGame").getAsDouble();
       double rpg = statsData.get("defensiveReboundsPerGame").getAsDouble() + statsData.get("offensiveReboundsPerGame").getAsDouble();
       double apg = statsData.get("assistsPerGame").getAsDouble();
       double tov = statsData.get("turnoversPerGame").getAsDouble();
       double spg = statsData.get("stealsPerGame").getAsDouble();
       double bpg = statsData.get("blocksPerGame").getAsDouble();
       double fga = statsData.get("fieldGoalAttemptsPerGame").getAsDouble();
       double tpm = statsData.get("threePointFieldGoalsMadePerGame").getAsDouble();
       double fta = statsData.get("freeThrowAttemptsPerGame").getAsDouble();


       return new PlayerStats(ppg, rpg, apg, tov, spg, bpg, fga, tpm, fta);
   }


   public static void main(String[] args) {
       try {
           String apiURL = "https://basketball-head.p.rapidapi.com/players/jamesle01";
           String playerResponse = getData(apiURL);
           Player player = parsePlayer(playerResponse);
           String statsURL = "https://basketball-head.p.rapidapi.com/players/jamesle01/stats/PerGame?seasonType=Regular";
           String statsResponse = getData(statsURL);
           System.out.println(statsResponse);
           PlayerStats stats = parsePlayerCareerStats(statsResponse);
           //PlayerStats stats = parsePlayerSeasonStats(statsResponse);
           System.out.println(player);
           System.out.println(stats);
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
   }
}

