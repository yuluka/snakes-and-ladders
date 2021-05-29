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
	
	private boolean occupatedSnake;
	private boolean occupatedLadder;
	private boolean occupated;
	private boolean lower;
	private boolean upper;
	private boolean head;
	private boolean tail;

	//private int code;
	
	private Player players;
	private String playersSymbols;

	public Square(int m, int n) {
		playersSymbols = "";
		row=m;
		column=n;
		xPosition=0;
		yPosition=0;
		position=0;
		occupatedSnake=false;
		occupatedLadder=false;
		occupated=false;
		head=false;
		tail=false;
		lower=false;
		upper=false;
		
		if((occupatedSnake==true)||(occupatedLadder==true)) {
			occupated=true;
		}
		else {
			occupated=false;
		}

		lower=false;
		upper=false;
		code="";
		head=false;
		tail=false;
		
	}
	
	public String getInfo2(){
		String a="";
		if((occupatedSnake==true)||(occupatedLadder==true)) {
			occupated=true;
		}
		else {
			occupated=false;
		}
		
		if(occupated==true) {
			a=code;
		}
		if(a=="") {
			a="  ";
		}
		
		String info="";
		if(position<=9) {
			info="["+a+"   "+ playersSymbols +"]";
		}
		else if(position<=99){
			info="["+a+"   "+ playersSymbols +"]";
		}
		else {
			info="["+a+"   "+ playersSymbols +"]";
		}
		return info;
	}
	
	public String getInfo() {
		String a="";
		if((occupatedSnake==true)||(occupatedLadder==true)) {
			occupated=true;
		}
		else {
			occupated=false;
		}
		
		if(occupated==true) {
			a=code;
		}
		if(playersSymbols.equals("")) {
			playersSymbols="  ";
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
	
	public boolean UpOrLow() {
		if(lower=true) {
			return true;
		}
		else{
			return false;
			}
		
		}
	public boolean HeadOrTail() {
		if(head==true) {
			return true;
		}
		else {
			return false;
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
	
	public boolean getHead() {
		return head;
	}
	
	public boolean getTail() {
		return tail;
	}
	
	public boolean getLower() {
		return lower;
	}
	
	public boolean getUpper() {
		return upper;
	}
	
	public void setTrueHead() {
		head=true;
	}
	
	public void setTrueTail() {
		tail=true;
		
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
		occupated=true;
	}
	
	public void setOcupatedLadder() {
		occupatedLadder=true;
		occupated=true;
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
		this.ladder=ladder;
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
	
	public void addCharacter(String symbol) {
		playersSymbols+=symbol;
	}
	
	public void deleteCharacter(String symbol) {
		playersSymbols=playersSymbols.replace(symbol,"");
	}
	
	public void addPlayer(Player newP) {
		if(players == null) {
			players = newP;
			//playersSymbols += players.getSymbol();
		}else {
			addPlayer(players.getNext(),newP);
		}
	}
	
	private void addPlayer(Player p, Player newP) {
		if(p == null) {
			p = newP;
			//playersSymbols += p.getSymbol();
		}else {
			addPlayer(p.getNext(),newP);
		}
	}
	public void runPlayers() {
		runPlayers(players);
	}
	private void runPlayers(Player players) {
		if(players!=null) {
		if(players.getNext()==null) {
			playersSymbols+=players.getSymbol();
		}
		else {
			playersSymbols+=players.getSymbol();
			runPlayers(players.getNext());
		}
	}
	}
	public String getPlayersS() {
		return playersSymbols;
	}
	
	/*public void removePlayer() {
		players=players.getNext();
		playersSymbols="";
		runPlayers();
	}*/
	
	public void removePlayer(String symbol) {
		if(players.getSymbol().equalsIgnoreCase(symbol)) {
			players = players.getNext();
			playersSymbols="";
			runPlayers();
			
		}else {
			players = players.getNext();
			playersSymbols="";
			runPlayers();
			removePlayer(symbol);
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isOccupatedSnake() {
		return occupatedSnake;
	}

	public void setOccupatedSnake(boolean occupatedSnake) {
		this.occupatedSnake = occupatedSnake;
	}

	public Player getPlayers() {
		return players;
	}

	public void setPlayers(Player players) {
		this.players = players;
	}

	public String getPlayersSymbols() {
		return playersSymbols;
	}

	public void setPlayersSymbols(String playersSymbols) {
		this.playersSymbols = playersSymbols;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setOccupatedLadder(boolean occupatedLadder) {
		this.occupatedLadder = occupatedLadder;
	}

	public void setOccupated(boolean occupated) {
		this.occupated = occupated;
	}

	public void setLower(boolean lower) {
		this.lower = lower;
	}

	public void setUpper(boolean upper) {
		this.upper = upper;
	}

	public void setHead(boolean head) {
		this.head = head;
	}

	public void setTail(boolean tail) {
		this.tail = tail;
	}
	
	
}
