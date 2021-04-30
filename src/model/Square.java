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
	
	private boolean ocupated; //Para validar si la casilla ya tiene una s o e.
	
	public Square(int m, int n) {
		row=m;
		column=n;
		xPosition=0;
		yPosition=0;
		
	}
	public String getInfo() {
		String info="[info]";
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

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}
	
	public boolean isOcupated() {
		return ocupated;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public void setOcupated(boolean ocupated) {
		this.ocupated = ocupated;
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

}
