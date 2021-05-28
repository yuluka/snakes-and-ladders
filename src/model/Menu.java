package model;

import java.util.Scanner;

public class Menu {
	
	private int selection;
	private Scanner in = new Scanner(System.in);
	private Board b;
	
	public Menu() {
		selection = 0;
	}
	
	public void menu() {
		System.out.println("\nWhat do you want to do?");
		System.out.println("1) Play.\n2) See positions.\n3) Exit.");
		
		selection = Integer.parseInt(in.nextLine());
		
		switch (selection) {
		case 1:
			readSize();
			break;
		
		case 2:
			//getPositions();
			break;
			
		case 3:
			exit();
			break;
			
		default:
			System.out.println("\nYour selection is not available. Try again!.");
			menu();
			break;
		}
	}
	
	public void play() {
		System.out.println("\nType a line break to launch the dice.");
		
		String aux = in.nextLine();
		
		if(!aux.equalsIgnoreCase("")) {
			if(aux.equalsIgnoreCase("num")) {
				System.out.println("\nShowing the first board.");
				showFirstBoard();
				
			}else if(aux.equalsIgnoreCase("simul")) {
				System.out.println("\nStarting the simulation mode.");
				
			}else if(aux.equalsIgnoreCase("menu")) {
				System.out.println("\nGoing to main menu.");
				menu();
				
			}else {
				System.out.println("\nYour selection is invalid.");
				play();
				
			}
		}else {
			b.movePlayer();
			play();
		}
	}
	
	public void showFirstBoard() {
		System.out.println(b.getBoard());
		System.out.println("\nType a line break to continue with the game.");
		
		String aux = in.nextLine();
		
		if(aux.equalsIgnoreCase("")) {
			play();
		}else {
			System.out.println("\nIllegal expression. Try again!");
			showFirstBoard();
		}
	}
	
	//public void getPositions() {
		
	//}
	
	public void exit() {
		System.out.println("Bye!\n:-)");
	}
	
	public void readSize() {
		System.out.println("Introduce the parameters of the game:"); //Recibe los parametros del juego que se va a crear.
		String parameters = in.nextLine();
		
		String[] parametersArray = parameters.split(" ");//Guarda los parametros y crea un tablero.
		int rows = Integer.parseInt(parametersArray[0]);
		int columns = Integer.parseInt(parametersArray[1]);
		int snakes = Integer.parseInt(parametersArray[2]);
		int ladders = Integer.parseInt(parametersArray[3]);
		
		b = new Board(rows,columns,snakes,ladders);
		
		b.setPlayers(parametersArray[4]);
		b.getFirstPlayer().setTurn(true);
		
		System.out.println("The game begins.");
		System.out.println(b.getBoard());
		System.out.println(b.getPlayersSq());
		System.out.println("The players are: " + b.getPlayersB());
		play();
	}
}
