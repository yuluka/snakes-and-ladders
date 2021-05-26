package model;

import java.util.Scanner;

public class Menu {
	/** Menu:
	 * 1) Jugar.
	 * 2) Ver el tablero de posiciones.
	 * 3) Salir del programa.
	 */
	
	//Hacer metodo para ver la seleccion del 
	//Hacer metodo para jugar.
	//Hacer metodo para ver las posiciones.
	//Hacer metodo para salir.
	//Hacer metodo que lea la cadena que tiene el tamano, e, s y p.
	
	
	private int selection;
	private Scanner in = new Scanner(System.in);
	private Board b;
	
	public Menu() {
		selection = 0;
	}
	
	public void menu() {
		System.out.println("What do you want to do:");
		System.out.println("1) Play.\n2) See positions.\n3) Exit.");
		
		selection = Integer.parseInt(in.nextLine());
		
		switch (selection) {
		case 1:
			readSize();
			break;
		
		case 2:
			getPositions();
			break;
			
		case 3:
			exit();
			break;
			
		default:
			System.out.println("Your selection is not available. Try again!.");
			menu();
			break;
		}
	}
	
	public void play() {
		System.out.println("The game begins.");
		
	}
	
	public void getPositions() {
		
	}
	
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
		
		String[] players = parametersArray[4].split("");
		int amountPlayers = players.length;//Determina el numero de jugadores mirando la cantidad de simbolos que 
											//escribio el usuario.
		
		aux(amountPlayers, players);
	}
	
	public void aux(int p, String[] players){//Metodo recursivo hecho para crear los jugadores.
		//Recibe el numero de jugadores a crear, al que le resta 1 cada vez que se crea un jugador.
		if(p == 0) {//Cuando se hayan creado todos los jugadores, pasa a jugar el juego.
			play();
		}else {//Si no se han creado los jugadores, vuelve a ejecutar el metodo que crea un jugador.
			String symbol = players[p-1];
			createPlayer(symbol, p, players);
		}
	}
	
	private void createPlayer(String symbol, int p, String[] players) {
		b.createPlayers(symbol);
		p--;//Como se acaba de crear un player, le resta un a p.
		aux(p,players);
	}
	
	
}
