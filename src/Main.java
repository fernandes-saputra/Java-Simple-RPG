import java.io.IOException;
import java.util.*;
public class Main {
	private boolean gameStart = true;
	private Job playerClass;
	private int currentEXPBar = 0;
	private int level = 1;
	private int currentFloor = 1;
	
	public Main() throws IOException {
		
		mainMenu();
		
		while(gameStart) {
			action();
		}
	}
	public void mainMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		String[] menu = {"New Game", "Load Game", "Exit"};
		for(int i = 0; i < menu.length;i++) {
			System.out.println(i+1+". "+menu[i]);
		}
		System.out.print("What would you like to do?");
		int menuChosen = sc.nextInt();
		switch(menuChosen) {
		case 1:
			clearscreen();
			ClassSelection();
			break;
		case 2:
			clearscreen();
			Load load = new Load();
			ArrayList<String> dataList = load.LoadGame("Save01");
			playerClass = new Job();
			playerClass.setName(dataList.get(0));
			playerClass.setHeroType(dataList.get(1));
			currentFloor = Integer.parseInt(dataList.get(2));
			currentEXPBar = Integer.parseInt(dataList.get(3));
			playerClass.setStat(currentEXPBar/2);
		}
	}
	
	public void ViewProfile() {
		playerClass.setStat(level);
		System.out.println("You are a " + playerClass.getName());
		System.out.println("Your HP: " + playerClass.getHp());
		System.out.println("Your Armor: " + playerClass.getArmor());
		System.out.println("Your Attack: " + playerClass.getAttack());
		System.out.println("Your Str: " + playerClass.getStr());
		System.out.println("Your Agi: " + playerClass.getAgi());
		System.out.println("Your Int: " + playerClass.getIntel());
		System.out.println("Press enter to continue...");
	}
	
	public Job ClassSelection() {
		Scanner sc = new Scanner(System.in);
		String[][] job = {
				{"Warrior","STR"},
				{"Priest","INT"},
				{"Archer","AGI"}
		};
		int classChosen = 4;
		while(classChosen>3 || classChosen < 1) {
			for(int i = 0; i < job.length; i++) {
				System.out.println(i+1+". "+job[i][0]);
			}
			System.out.print("Select your class: ");
			classChosen = sc.nextInt();
		}
		
		
		playerClass = new Job();
		playerClass.setName(job[classChosen-1][0]);
		playerClass.setHeroType(job[classChosen-1][1]);
		playerClass.setStat(level);
		
		return playerClass;
	}
	
	public void menu() {
		String[] menu = {"Grind EXP", "Fight Floor Boss","View Profile","Save", "Load","Quit"};
		
		System.out.println("You have battled a total of: " + currentEXPBar + " times");
		System.out.println("You are at Floor " + currentFloor);
		for(int i = 0;i<menu.length;i++) {
			
			if(menu.length-i==1) {
				System.out.println("99. "+menu[i]);
			}else {
				System.out.println(i+1+". "+menu[i]);
			}
			
		}
		System.out.print("What would you like to do?");
	}
	
	
	public void action() throws IOException {
		Scanner sc = new Scanner(System.in);
	
		menu();
		
		int action = sc.nextInt();
		switch (action) {
		case 1: {
			currentEXPBar++;
			level = (int) (1 + Math.floor(currentEXPBar / 2));
			clearscreen();
			System.out.println("You killed a monster! You gained an amount of EXP");
			break;
		}
		case 2: {
			if(playerClass.getHp() - (currentFloor*1000) >= 0) {
				currentFloor++;
				clearscreen();
				System.out.println("You advanced to the next floor");
			}else {
				currentEXPBar = currentEXPBar/2;
				level = (int) (1 + Math.floor(currentEXPBar / 2));
				clearscreen();
				System.out.println("You have died and your battle progress is halved...");
			}
			break;
		}
		case 3: {
			clearscreen();
			
			ViewProfile();
			sc.nextLine();
			sc.nextLine();
			clearscreen();

			break;
		}
		case 4: {
			Save save = new Save("Save01", playerClass, currentFloor, currentEXPBar);
			break;
		}
		case 5: {
			Load load = new Load();
			ArrayList<String> dataList = load.LoadGame("Save01");
			playerClass.setName(dataList.get(0));
			playerClass.setHeroType(dataList.get(1));
			currentFloor = Integer.parseInt(dataList.get(2));
			currentEXPBar = Integer.parseInt(dataList.get(3));
			playerClass.setStat(currentEXPBar/2);
			break;
		}
		case 99: {
			gameStart=false;
			
			break;
		}
		default:
			clearscreen();
			System.out.println("Wrong Action");
		}
	}
	
	public void clearscreen() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}

	public static void main(String[] args) throws IOException {
		Main main = new Main();

	}

}
