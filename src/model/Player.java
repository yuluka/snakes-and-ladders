package model;
import java.util.Random;
	//Hacer metodo que calcule el puntaje.
	//Hacer metodo que almacene los puntajes en ABB.
	//Hacer metodo que ordene inversamente el ABB.
	
public class Player {
	private String symbol;
	private int movements=0;
	private int position;
	private boolean turn;
	private int counter;
	private int pNumber;
	private int columns;
	private Player next;
	private int rows;
	private int snakes;
	private int ladders;
	private String players;
	

	

	public Player(String symbol,int a, int columns, int rows, int snakes, int ladders) {
		position = 1;
		turn = false;
		this.symbol = symbol;
		pNumber=a;
		this.columns=columns;
		this.rows=rows;
		this.snakes=snakes;
		this.ladders=ladders;
		players="";
	}
	
	public int getLadders() {
		return ladders;
	}

	public void setLadders(int ladders) {
		this.ladders = ladders;
	}


	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSnakes() {
		return snakes;
	}

	public void setSnakes(int snakes) {
		this.snakes = snakes;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}
	
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getpNumber() {
		return pNumber;
	}

	public void setpNumber(int pNumber) {
		this.pNumber = pNumber;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
		
	public void setNext(Player next) {
		this.next = next;
	}
	
	public Player getNext() {
		return next;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public boolean getTurn() {
		return turn;
	}
	
	public int launchDice() {
		Random r = new Random();
		
		counter= r.nextInt(6)+1;
		
		position += counter;
		movements++;
		
		/*if(next != null) {
			turn = false;
			next.setTurn(true);
		}*/
		
		return counter;
	}
	
	public void upgratePosition() {
		position += movements;
	}
	
	public void add(Player newPlayer) {
		if(next == null) {
			next = newPlayer;
		}else {
			next.add(newPlayer);
		}
	}
	
	public int getMovements() {
		return movements;
	}

	public void setMovements(int movements) {
		this.movements = movements;
	}
}