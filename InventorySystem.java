/**
 * CSE 471 Assignment #2
 * Andrew Duberstein
 * 1-29-2013
 */

import java.util.Random;

public class InventorySystem{
 public static void main(String[] args){
  Random rand = new Random(189);
  //Initial conditions
  final int BEGINNING_INVENTORY = 3;
  final int REPLENISHMENT = 8; 
  final int COST_STORAGE = 1;
  final int COST_BACKORDERED = 10;
  //Time periods
  //TODO Reset NUM_DAYS = 1000
  final int NUM_DAYS = 10 /*00*/;
  final int REVIEW_PERIOD = 5;
  final int MAX_INVENTORY = 11;
  final int SIMULATION_RUNS = 10;
  for(int run = 0; run < SIMULATION_RUNS; run++){
   int inventory = BEGINNING_INVENTORY;
   int randomNum;
   int currLead = -1;
   int shipment = 0;
   int shortage = 0;
   int dayEndInventory = BEGINNING_INVENTORY;
   int costs = 0;
   for(int day = 1; day <= NUM_DAYS; day++){
     //TODO turn-off
     System.out.println("*** DAY " + day + " ***");
     System.out.println("CURRENT LEAD: " + currLead); 
    //Arrival of shipment in currLead days
    if (currLead > 0){
     currLead--;
    }
    //One-shot replenishment at beginning of simulation
    if (day == 3){
     inventory += REPLENISHMENT;   
    }
    System.out.println("BEGINNING INVENTORY: " + inventory);
    //Demand
    randomNum = (int) Math.round(rand.nextDouble()*100);
    inventory -= InventorySystem.getDailyDemand(
      randomNum); 
    System.out.println("ENDING INVENTORY: " + inventory); 
    
    //KPIs
    shortage += (inventory < 0 ? inventory : 0);
    dayEndInventory += (inventory > 0 ? inventory : 0);
    costs += (shortage*COST_BACKORDERED > 0 ? shortage*COST_BACKORDERED : 0);
    costs += (dayEndInventory*COST_STORAGE > 0 ? dayEndInventory*COST_STORAGE : 0);
    if(currLead == 0){
     inventory = (inventory > 0 ? inventory : 0) + shipment;
     shipment = 0;
     currLead = -1;
    }
    //TODO Remove println's
     System.out.println("SHORTAGE " + shortage + " DayEndInventory " + dayEndInventory + " costs " + costs);
     System.out.println("SHIPMENTS " + shipment);
    //Review period
    if(day % REVIEW_PERIOD == 0){
     //Lead-time distribution
     randomNum = (int) Math.round(rand.nextDouble()*100);
     currLead = InventorySystem.getLead(
       randomNum);
     shipment = MAX_INVENTORY - inventory;
    }
   }
   System.out.println("RESULTS FOR RUN " + run);
   System.out.println("Total Shortage: " + shortage);
   System.out.println("Total EoD Inventory: " + dayEndInventory);
   System.out.println("Total cost: " + costs);
  }
 }

    public static int getDailyDemand(int rand){
     if(0 <= rand && rand < 10){
      return 0;
     }
     else if(10 <= rand && rand < 35){
      return 1;
     }
     else if(35 <= rand && rand < 70){
      return 2;
     }
     else if(70 <= rand && rand < 91){
      return 3;
     }
     else{
      return 4;
     }
    }

    public static int getLead(int rand){
     if(0 <= rand && rand < 60){
      return 1;
     }
     else if(60 <= rand && rand < 90){
      return 2;
     }
     else{
      return 3;
     }
    }
} 
