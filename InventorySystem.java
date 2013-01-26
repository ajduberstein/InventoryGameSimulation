/**
 * CSE 471 Assignment #2
 * Andrew Duberstein
 * 1-29-2013
 */

import java.util.Random;

public class InventorySystem{
	public static void main(String[] args){
		public Random rand(0);
		//Initial conditions
		final int BEGINNING_INVENTORY = 3;
		final int REPLENISHMENT = 8; 
		final int COST_STORAGE = 1;
		final int COST_BACKORDERED = 10;
		//Time periods
		final int NUM_DAYS = 1000;
		final int REVIEW_PERIOD = 5;
		final int MAX_INVENTORY = 11;
		final int SIMULATION_RUNS = 10;
		//Inclusive max and min for random number generator
		final int MIN = 1;
		final int MAX = 100;
		int inventory, randomNum, backorderedShortages, 
		    costs, currLead, shipment;
		for(int run = 0; run < SIMULATION_RUNS; run++){
			inventory = BEGINNING_INVENTORY;
			for(int day = 1; day <= NUM_DAYS; day++){
				if (currLead > 0){
					currLead--;
				}
				if (day == 3){
					inventory += REPLENISHMENT;			
				}
				//Demand
				randomNum = rand.nextInt(MAX -
					       	MIN) + MIN;
				inventory -= InventorySystem.getDailyDemand(
						randomNum);
				//Review period
				if(inventory % REVIEW_PERIOD == 0){
					//Lead-time distribution
					randomNum = rand.nextInt(MAX -
							MIN) + MIN;
					currLead = InventorySystem.getLead(
							randomNum);
					shipment = MAX_INVENTORY - inventory;
				}
			}
		}
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
