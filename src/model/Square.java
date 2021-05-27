package model;

public class Square {
	private int id;
	private Square next;
	private Square prev;
	private Square up;
	private Square down;
	private Square snake;
	private Square ladder;
	private int xPosition;
	private int yPosition;
	private int row;
	private int column;
	private int position;
	private String code;
	
	private boolean occupatedSnake; //Para validar si la casilla ya tiene una s o e.
	private boolean occupatedLadder;
	private boolean occupated;
	private boolean lower;
	private boolean upper;

	//private int code;
	
	private Player players;
	private String playersSymbols;

	public Square(int m, int n) {
		row=m;
		column=n;
		xPosition=0;
		yPosition=0;
		position=0;
		occupatedSnake=false;
		occupatedLadder=false;
		setPlayerSymbol();
		if((occupatedSnake==true)||(occupatedLadder==true)) {
			occupated=true;
		}
		else {
			occupated=false;
		}
		lower=false;
		upper=false;
		code="";
		
	}
	
	public String getInfo() {
		String a="";
		if(occupatedLadder==true) {
			a=code;
		}
		if(upper==true) {
			a+=" u";
		}
		if(lower==true) {
			a+=" l";
		}
		String info="";
		if(position<=9) {
			info="["+position+a+"   "+ playersSymbols +"]";
		}
		else if(position<=99){
			info="["+position+a+"  "+ playersSymbols +"]";
		}
		else {
			info="["+position+a+" "+ playersSymbols +"]";
		}
		return info;
	}
	
	public char getCol() {
		return (char) ('A'+column);
	}
	

	public String getCode() {
		return code;
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
	
	public Square getSnake() {
		return snake;
	}
	
	public Square getLadder() {
		return ladder;
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
	
	public boolean getOccupated() {
		return occupated;
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
	
	public void setSnake(Square snake) {
		this.snake=snake;
	}
	
	public void setLadder(Square ladder) {
		this.ladder=snake;
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
	
	public void setCode(String code) {
		this.code=code;
	}
	
	public void setPlayerSymbol() {
		if(players == null) {
			playersSymbols = "";
			
		}else {
			setPlayerSymbol(players);
		}
	}
	
	private void setPlayerSymbol(Player current) {
		if(current.getNext() != null) {
			playersSymbols = current.getSymbol();
			setPlayerSymbol(current.getNext());
		}else {
			playersSymbols += "";
		}
	}
	
	public void setPlayer(Player players) {
		if(players == null) {
			this.players = players;
		}else {
			players = players.getNext();
			setPlayer(players);
		}
	}
	
	public void removePlayer(String symbol) {
		if(players.getSymbol().equalsIgnoreCase(symbol)) {
			players = players.getNext();
			
		}else {
			players = players.getNext();
			removePlayer(symbol);
		}
	}
}
