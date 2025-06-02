package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.management.RuntimeErrorException;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;


import java.util.Scanner;


public class Statify {


    private final static String start_URL = "https://basketball-head.p.rapidapi.com/players/"; //url
    private final static String end_URL_stats = "/stats/PerGame?seasonType=Regular";

    //colors via https://www.w3schools.blog/ansi-colors-java
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String RESET = "\033[0m";  // Text Reset


   public static String convertToId(String name) {// convert to id with last name + first two letters of first name + number
       String urlName = "";
           int idx = name.indexOf(" ");
       String lastName = name.substring(idx+1);
       if (lastName.length() <= 5) {
           urlName += lastName.toLowerCase();
       } else {
           urlName += lastName.substring(0, 5).toLowerCase();
       }
       urlName += name.substring(0, 2).toLowerCase();
       String numberPlayer = "01";
       urlName += numberPlayer;
       return urlName;
   }



   public static String toCapitalize(String name) {
    name = name.toLowerCase().trim();
    int idx = name.indexOf(" ");
    String toCap = name.substring(0,1).toUpperCase() + name.substring(1, idx);
    toCap += " " + name.substring(idx + 1,idx + 2).toUpperCase() + name.substring(idx + 2);
    return toCap;
   }

   public static String classifyScorer(PlayerStats stats) { //classify scorer based on number of points
    String output = "";
    if (stats.getPoints() >= 8) {
        output = " is an average scorer!";
    } if (stats.getPoints() >= 12) {
        output = " is a good scorer!";
    } if (stats.getPoints() >= 18) {
        output = " is a great scorer!";
    } if (stats.getPoints() >= 22) {
        output = " is an " + GREEN + "excellent" + RESET + " scorer!";
    } if (stats.getPoints() >= 25) {
        output = " is an " + RED + "ELITE" + RESET + " scorer!";
    } if (stats.getPoints() >= 28) {
        output = " is an " + PURPLE + "UNHOLY" + RESET + " scorer!";
    } if (stats.getPoints() >= 30) {
        output = " is a " + YELLOW + "MYTHICAL" + RESET + " scorer! You can't guard him!";
    } else if (stats.getPoints() < 8) {
        output = " is a " + RED + "poor" + RESET + " scorer!";
    }
    return output;
   }

   public static int compare(PlayerStats firstPlayer, PlayerStats secondPlayer) { //calculates number of stats first player leads in
    int count = 0;
    if (firstPlayer.getPoints() >= secondPlayer.getPoints()) {
        count++;
    } 
    if (firstPlayer.getRebounds() >= secondPlayer.getRebounds()) {
        count++;
    } 
    if (firstPlayer.getAssists() >= secondPlayer.getAssists()) {
        count++;
    } 
    if (firstPlayer.getBlocks() >= secondPlayer.getBlocks()) {
        count++;
    } 
    if (firstPlayer.getSteals() >= secondPlayer.getSteals()) {
        count++;
    } 
    if (firstPlayer.getTurnovers() <= secondPlayer.getTurnovers()) {
        count++;
    } 
    if (firstPlayer.getThreePointers() >= secondPlayer.getThreePointers()) {
        count++;
    } 
    if (firstPlayer.getTrueShooting() >= secondPlayer.getTrueShooting()) {
        count++;
    }
    if (firstPlayer.KOBE() >= secondPlayer.KOBE()) {
        count++;
    }
    return count;
   }

   public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       Boolean play = true;
       String searchOrCompare = "";
       String playerName = "";
       String response = "";
       String firstPlayer = "";
       String secondPlayer = "";
       while (play) {
        while(true) {
       System.out.println(YELLOW + "Search Player Stats or Compare Two Players: (search/compare)" + RESET);
       searchOrCompare = scan.nextLine().toLowerCase().trim();
       if (searchOrCompare.contains("search") || searchOrCompare.contains("compare")) {
        break;
       }
        }
       if (searchOrCompare.contains("search")) {
        System.out.println(RED + "Search Player Stats: " + RESET);
        playerName = scan.nextLine().toLowerCase().trim();
        while (true) {
       System.out.println(GREEN + "Career Stats/This Season Stats? (season/career)" + RESET);
       response = scan.nextLine().toLowerCase().trim();
       if (response.contains("career") || response.contains("season")) {
        break;
       }
        }
       if (response.contains("season")) {
           response = "season";
       } else if (response.contains("career")) {
           response = "career";
       }
    } else {
        System.out.println(RED + "Select the first player to compare: " + RESET);
        firstPlayer = scan.nextLine().trim();
        System.out.println(RED + "Select the second player to compare: " + RESET);
        secondPlayer = scan.nextLine().trim();
        System.out.println(GREEN + "Career Stats/This Season Stats? (season/career)" + RESET);
        while (true) {
        response = scan.nextLine().toLowerCase().trim();
        if (response.contains("career") || response.contains("season")) {
        break;
       }
    }
        if (response.contains("season")) {
            response = "season";
        } else if (response.contains("career")) {
            response = "career";
        }
    }
       try {
        if (searchOrCompare.equals("search")) {
           String matchedId = convertToId(playerName);
           if (matchedId != null) {
                String playerStats = API.getData(start_URL + matchedId + end_URL_stats);
               if (response.equals("career")) {
               PlayerStats stats = API.parsePlayerCareerStats(playerStats);
               System.out.println("------------------------------------------");
               System.out.println(playerName + "'s Stats: ");
               System.out.println("------------------------------------------");
               System.out.println(stats);
               System.out.println("------------------------------------------");
               System.out.println(YELLOW + playerName + RESET + classifyScorer(stats));
               System.out.println("------------------------------------------");
               } else {
                   PlayerStats stats = API.parsePlayerSeasonStats(playerStats);
                   System.out.println("------------------------------------------");
                   System.out.println(playerName + "'s Stats: ");
                   System.out.println("------------------------------------------");
               System.out.println(stats);
               System.out.println("------------------------------------------");
               System.out.println(YELLOW + playerName + RESET + classifyScorer(stats));
               System.out.println("------------------------------------------");
               }
           } else {
               System.out.println("Player is not found:");
           }
        } else {
            String firstMatchedId = convertToId(firstPlayer);
            String secondMatchedId = convertToId(secondPlayer);
            if (firstMatchedId != null && secondMatchedId != null) {
                String firstStats = API.getData(start_URL + firstMatchedId + end_URL_stats);
                String secondStats = API.getData(start_URL + secondMatchedId + end_URL_stats);
                if (response.equals("career")) {
                PlayerStats firstStat = API.parsePlayerCareerStats(firstStats);
                System.out.println("------------------------------------------");
                System.out.println(firstPlayer + "'s Stats: ");
                System.out.println("------------------------------------------");
                System.out.println(firstStat);
                System.out.println("------------------------------------------");
                PlayerStats secondStat = API.parsePlayerCareerStats(secondStats);
                System.out.println(secondPlayer + "'s Stats: ");
                System.out.println("------------------------------------------");
                System.out.println(secondStat);
                System.out.println("------------------------------------------");
                if (compare(firstStat, secondStat) > 4) {
                    System.out.println(YELLOW + firstPlayer + RESET + " leads in " + GREEN + compare(firstStat, secondStat) + RESET + " statistical categories!");
                    System.out.println("------------------------------------------");
                } else {
                    int num = 9 - compare(firstStat, secondStat);
                    System.out.println(YELLOW + secondPlayer + RESET + " leads in " + GREEN + num + RESET + " statistical categories!");
                    System.out.println("------------------------------------------");
                }
            } else {
                PlayerStats firstStat = API.parsePlayerSeasonStats(firstStats);
                System.out.println("------------------------------------------");
                System.out.println(firstPlayer + "'s Stats");
                System.out.println("------------------------------------------");
                System.out.println(firstStat);
                System.out.println("------------------------------------------");
                PlayerStats secondStat = API.parsePlayerSeasonStats(secondStats);
                System.out.println(secondPlayer + "'s Stats: ");
                System.out.println("------------------------------------------");
                System.out.println(secondStat);
                System.out.println("------------------------------------------");
                if (compare(firstStat, secondStat) > 4) {
                    System.out.println(YELLOW + firstPlayer + RESET + " leads in " + GREEN + compare(firstStat, secondStat) + RESET + " statistical categories!");
                    System.out.println("------------------------------------------");
                } else {
                    int num = 9 - compare(firstStat, secondStat);
                    System.out.println(YELLOW + secondPlayer + RESET + " leads in " + GREEN + num + RESET + " statistical categories!");
                    System.out.println("------------------------------------------");
                }
            }
        }
    }
       } catch (Exception e) {
           System.out.println("Error searching player: " + e.getMessage());
       }
    
       String yesOrNo = "";
       System.out.println("Would you like to continue? (yes/no)");
       yesOrNo = scan.nextLine().toLowerCase().trim();
       if (yesOrNo.equals("no")) {
           play = false;
       }
   }
       scan.close();
       System.out.println("Thank you for using Statify!");
   }
}


