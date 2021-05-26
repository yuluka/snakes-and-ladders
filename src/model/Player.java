package model;
import java.util.Random;
	//Hacer metodo que calcule el puntaje.
	//Hacer metodo que almacene los puntajes en ABB.
	//Hacer metodo que ordene inversamente el ABB.
	
public class Player {
	private String symbol;
	private int movements;
	private int position;
	private boolean turn;
	
	private Player next;
	
	public Player(String symbol) {
		position = 1;
		turn = true;
		this.symbol = symbol;
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
		
		movements = r.nextInt(5)+1;
		turn = false;
		
		return movements;
	}
	
	public void upgratePosition() {
		position += movements;
	}
}