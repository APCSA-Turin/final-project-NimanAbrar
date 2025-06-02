package com.example;


public class PlayerStats {
   private double pointsPerGame;
   private double reboundsPerGame;
   private double assistsPerGame;
   private double turnoversPerGame;
   private double stealsPerGame;
   private double blocksPerGame;
   private double fieldGoalsAttempted;
   private double threePointersMade;
   private double freeThrowsAttempted;


   public PlayerStats(double points, double rebounds, double assists, double turnovers, double steals, double blocks, double fieldGoals, double threePointers, double freeThrows) {
       pointsPerGame = points;
       reboundsPerGame = rebounds;
       assistsPerGame = assists;
       turnoversPerGame = turnovers;
       stealsPerGame = steals;
       blocksPerGame = blocks;
       fieldGoalsAttempted = fieldGoals;
       threePointersMade = threePointers;
       freeThrowsAttempted = freeThrows;
   }


   public double getPoints() {
       return pointsPerGame;
   }


   public double getRebounds() {
       return Math.round(reboundsPerGame * 100) / 100;
   }


   public double getAssists() {
       return assistsPerGame;
   }


   public double getTurnovers() {
       return turnoversPerGame;
   }


   public double getSteals() {
       return stealsPerGame;
   }


   public double getBlocks() {
       return blocksPerGame;
   }


   public double getFieldGoalsAttempted() {
       return fieldGoalsAttempted;
   }


   public double getThreePointers() {
       return threePointersMade;
   }


   public double getFreeThrowsAttempted() {
       return freeThrowsAttempted;
   }


   public double getTrueShooting() { //stat to calculate player efficiency
       double number = (pointsPerGame / (2 * (getFieldGoalsAttempted() + 0.44 * getFreeThrowsAttempted()))) * 100;
       return Math.round(number * 100.0) / 100.0;
   }

   public double KOBE() { //my own algorithm to calculate player impact via blending multiple stats
    double KOBE = (((pointsPerGame + 1.24 * (reboundsPerGame + assistsPerGame)) + Math.abs((2 * (blocksPerGame + stealsPerGame)) - 0.32 * turnoversPerGame))) * (getTrueShooting() * 0.08);
    return KOBE;
   }
   public String toString() {
       String str = "";
       str += "Points Per Game: " + getPoints();
       str += "\nRebounds Per Game: " + getRebounds();
       str += "\nAssists Per Game: " + getAssists();
       str += "\nSteals Per Game: " + getSteals();
       str += "\nBlocks Per Game: " + getBlocks();
       str += "\nTurnovers Per Game: " + getTurnovers();
       str += "\n3-Pointers Per Game: " + getThreePointers();
       str += "\nTrue Shooting%: " + getTrueShooting();
       str += "\nKOBE: " + KOBE();
       return str;
   }
}
