package model;
import java.util.Random;
	//Hacer metodo que calcule el puntaje.
	//Hacer metodo que almacene los puntajes en ABB.
	//Hacer metodo que ordene inversamente el ABB.
	
public class Player {
	private String symbol;
	private int movements;
	//private int turn;
	
	private Player next;
	
	public Player(String symbol) {
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
	
	public int launchDice() {
		Random r = new Random();
		
		movements = r.nextInt(6)+1;
		
		return movements;
	}
}
