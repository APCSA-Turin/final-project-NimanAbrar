package com.example;


public class Player {
   private String name;
   private String id;
   private String team;
   private String position; //instance variables


   public Player(String name, String id, String position) { //constructor
       this.name = name;
       this.id = id;
       this.position = position;
   }


   public String getName() { //getter variables
       return name;
   }


   public String getId() {
       return id;
   }


   public String getTeam() {
       return team;
   }


   public String getPosition() {
       return position;
   }


   public String toString() {
       String str = "";
       str += "Name: " + name;
       str += "\nPosition: " + getPosition();
       return str;
   }
}
