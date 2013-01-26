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
  final int NUM_DAYS = 1000;
  final int REVIEW_PERIOD = 5;
  final int MAX_INVENTORY = 11;
  //TODO Reset SIMULATION_RUNS to 10
  final int SIMULATION_RUNS = 1;
  for(int run = 1; run <= SIMULATION_RUNS; run++){
   int inventory = BEGINNING_INVENTORY;
   int randomNum;
   int currLead = 3;
   int shipment = REPLENISHMENT;
   int shortage = 0;
   int dayEndInventory = BEGINNING_INVENTORY;
   int costs = 0;
   for(int day = 1; day <= NUM_DAYS; day++){
     if (currLead > 0){
     currLead--;
    }
     
    if(currLead == 0){
     inventory = (inventory > 0 ? inventory : 0) + shipment;
     shipment = 0;
     currLead = -1;
    }
    
    //Demand
    randomNum = (int) Math.round(rand.nextDouble()*100);
    inventory -= InventorySystem.getDailyDemand(
      randomNum);  
    
    //KPIs
    shortage += (inventory < 0 ? inventory : 0);
    dayEndInventory += (inventory > 0 ? inventory : 0);
    costs += (shortage*COST_BACKORDERED > 0 ? shortage*COST_BACKORDERED : 0);
    costs += (dayEndInventory*COST_STORAGE > 0 ? dayEndInventory*COST_STORAGE : 0);
    
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
