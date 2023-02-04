package yatzy;

import java.util.LinkedList;

//Needed?
public class Player {

	LinkedList<String> activeCategories = new LinkedList<>();

	public Player() {
		
	}
	
	//Creates the list of available categories used for dropDownMenu in Game object.
	public String[] setCategories(Object dice[][]) {

		LinkedList<Integer> temp = new LinkedList<>();
		for (int i = 0; i < dice.length; i++) {
			temp.add((int) dice[i][0]);
			//System.out.println(dice[i][0]);
		}
		//System.out.println("");
		
		//Ettor
		if (temp.contains(1)) {
			activeCategories.add("Ettor");
		}
		
		//Två + stegar
		if (temp.contains(2)) {
			activeCategories.add("Tvåor");
			if(temp.contains(3)) {
				if(temp.contains(4)) {
					if(temp.contains(5)) {
						if(temp.contains(6)) {
							activeCategories.add("Stor-Stege");
						}else if(temp.contains(1)) {
							activeCategories.add("Liten-Stege");
						}
					}	
				}	
			}
		}
		
		if (temp.contains(3)) {
			activeCategories.add("Treor");
		}
		if (temp.contains(4)) {
			activeCategories.add("Fyror");
		}
		if (temp.contains(5)) {
			activeCategories.add("Femmor");
		}
		if (temp.contains(6)) {
			activeCategories.add("Sexor");
		}
		
		int[] setArray = {0,0,0,0,0,0};
		for(int k = 0; k < dice.length; k++) {
			setArray[(int) dice[k][0]-1]++;
		}
		
		for(int ki = 0; ki < setArray.length; ki++) {
			if(setArray[ki] == 2) {
				if(!activeCategories.contains("Par")) {
					activeCategories.add("Par");
				}
			}
			if(setArray[ki] == 3) {
				if(!activeCategories.contains("Triss")) {
					activeCategories.add("Triss");
				}
			}
			if(setArray[ki] == 4) {
				if(!activeCategories.contains("Fyrtal")) {
					activeCategories.add("Fyrtal");
				}
			}
			if(setArray[ki] == 5) {
				if(!activeCategories.contains("Yatzy")) {
					activeCategories.add("Yatzy");
				}
			}
		}
		
		//Två par och Kåk
		if(activeCategories.contains("Par")) {
			for(int i = 0; i < setArray.length; i++) {
				for(int n = 0; n < setArray.length; n++) {
					if(setArray[i] == 2 && setArray[n] == 2 && i !=n ) {
						if(!activeCategories.contains("Två-Par")) {
							activeCategories.add("Två-Par");
						}
					}
					if(setArray[i] == 3 && setArray[n] == 2 && i !=n) {
						if(!activeCategories.contains("Kåk")) {
							activeCategories.add("Kåk");
						}
					}
				}
			}
		}
		
		activeCategories.add("Chans");		
		
		String[] returnArray = activeCategories.toArray(new String[activeCategories.size()]);
		activeCategories.clear();
		
		return returnArray;
	}

}
