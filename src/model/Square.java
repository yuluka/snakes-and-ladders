package model;

public class Square {
	private int id;
	private Square next;
	private Square prev;
	private Square up;
	private Square down;
	private int xPosition;
	private int yPosition;
	private int row;
	private int column;
	private int position;
	
	private boolean occupatedSnake; //Para validar si la casilla ya tiene una s o e.
	private boolean occupatedLadder;
	private boolean lower;
	private boolean upper;
	private int code;
	public Square(int m, int n) {
		row=m;
		column=n;
		xPosition=0;
		yPosition=0;
		position=0;
		occupatedSnake=false;
		occupatedLadder=false;
		lower=false;
		upper=false;
		
	}
	public String getInfo() {
		String info="";
		if(position<=9) {
			info="["+position+"   "+"]";
		}
		else if(position<=99){
			info="["+position+"  "+"]";
		}
		else {
			info="["+position+" "+"]";
		}
		return info;
	}
	
	public char getCol() {
		return (char) ('A'+column);
	}
	
	

	public int getId() {
		return id;
	}

	public Square getNext() {
		return next;
	}

	public Square getPrev() {
		return prev;
	}

	public Square getUp() {
		return up;
	}

	public Square getDown() {
		return down;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}
	
	public int getPosition() {
		return position;
	}
	
	public boolean getUpperOrLower() {
		if(upper=true) {
			return upper;
		}
		else {
			return lower;
		}
	}
	
	public boolean isOccupatedSnakes() {
		return occupatedSnake;
	}
	
	public boolean isOccupatedLadder() {
		return occupatedLadder;
	}

	public void setTrueUpper() {
		upper=true;
	}
	
	public void setTrueLower() {
		lower=true;
	}
	
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public void setOcupatedSnakes() {
		occupatedSnake=true;
	}
	
	public void setOcupatedLadder() {
		occupatedLadder=true;
	}
	
	public void setDown(Square down) {
		this.down = down;
	}
	
	public void setUp(Square up) {
		this.up = up;
	}
	
	public void setPrev(Square prev) {
		this.prev = prev;
	}
	
	public void setNext(Square next) {
		this.next = next;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	public void setPosition(int p) {
		position=p;
	}
}
