package ui;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
	
	private int selection;
	private Scanner in = new Scanner(System.in);
	private Board b;
	private Winner playerW;
	private String all;
	
	public Menu() {
		selection = 0;
		playerW=new Winner();
		all="";
		restore();

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
			if(playerW != null){
				loofForScoreBoard(playerW.getWinnerPlayers());
			}
			else {
				System.out.println("No scores");
			}
			menu();
			break;
			
		case 3:
			storeGame();
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
				simul();
				
				
			}else if(aux.equalsIgnoreCase("menu")) {
				System.out.println("\nGoing to main menu.");
				menu();
				
			}else {
				System.out.println("\nYour selection is invalid.");
				play();
				
			}
		}
		else {
			//System.out.println(b.getBoard());
			/*if(b.gameWon()) {
				System.out.println("Somebody won.");
				exit();
			}else {*/
				String message=b.movePlayer();
				if(message.contains("won the game.")){
					System.out.println(message);
					System.out.println(b.getBoard(aux));
					getName();
					System.out.println("The winner player was saved to the scoreboard");
					menu();
				}
				else {
					System.out.println(message);
					System.out.println(b.getBoard(aux));
					play();
				}
				
			//}
			
		}
	}
	
	public void loofForScoreBoard(Score wPlay) {
			if(wPlay!=null) {
				loofForScoreBoard(wPlay.getLeft());
				String a="";
				a+="\nStartig parameters: ROWS: "+wPlay.getRows()+" COLUMNS: "+wPlay.getColumns()+" LADDERS: "+wPlay.getLadders()+" SNAKES: "+wPlay.getSnakes()+" PLAYERS: "+wPlay.getPlayers();
				a+="\nSymbols: "+playerW.getWinnerPlayers().getSymbols();
				a+="\nBoard Winner: "+wPlay.getName()+" with the symbol "+wPlay.getSymbolW();
				a+="\nScore: "+wPlay.getScore();
				System.out.println(a);
				loofForScoreBoard(wPlay.getRight());
			}
		}
	
	
	public void simul() {
		try {
			TimeUnit.SECONDS.sleep(2);
			String moves=b.movePlayer();
			if(moves.contains("won the game.")){
				System.out.println(moves);
				System.out.println(b.getBoard(""));
				getName();
				System.out.println("The winner player was saved to the scoreboard");
				menu();
			}
			else {
				System.out.println(moves);
				System.out.println(b.getBoard(""));
				simul();
			}
		} catch (InterruptedException e) {
			System.out.println("There was an error, at the time of doing the simul; Try again");
		}
	}
	
	
	public void showFirstBoard() {
		System.out.println(b.getBoard("a"));
		System.out.println("\nType a line break to continue with the game.");
		
		String aux = in.nextLine();
		
		if(aux.equalsIgnoreCase("")) {
			play();
		}else {
			System.out.println("\nIllegal expression. Try again!");
			showFirstBoard();
		}
	}
	
	public void getName() {
		System.out.println("Please give us the winner's name");
		String name=in.nextLine();
		Player a=b.getWinner();
		a.setPlayers(all);
		playerW.receiveWinningPlayer(a, name);
	}
	
	//public void getPositions() {
		
	//}
	
	public void exit() {
		System.out.println("\nBye!\n:-)");
		System.exit(0);
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
		//b.fillSymbols(b.getSymbol(), parametersArray[4]);
		b.setPnumber(parametersArray[4]);
		b.setPlayers(parametersArray[4]);
		all=parametersArray[4];
		
		b.getFirstPlayer().setTurn(true);
		//b.linkPlayers();
		
		System.out.println("The game begins.");
		System.out.println(b.getBoard("a"));
		System.out.println(b.getPlayersSq());
		System.out.println("The players are: " + b.getPlayersB());
		play();
	}
	
	public void storeGame() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("data/Scores.FML"));
			oos.writeObject(playerW);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void restore() {
		File guardado = new File("data/Scores.FML");
		if (guardado .exists()) {
			ObjectInputStream O;
			try {
				O = new ObjectInputStream(new FileInputStream(guardado));
				playerW = (Winner) O.readObject();
				O.close();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("There was an error at the time of load the file");
			}
		}
	}
}
