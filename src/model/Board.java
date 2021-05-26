package model;

public class Board {
	private int rows; //n filas
	private int columns;//m columnas
	private int snakes;//s serpientes
	private int ladders;//e escaleras
	private int random;
	private char codes=(char)1;
	private boolean direction;//Es la direccion en la que esta yendo un jugador. Puede ser izquierda o derecha
	private Square firstSquare;//Es el primer cuadro del tablero
	private Square top;
	private Occupated occupated=new Occupated();
	private Occupated lastOccupated=new Occupated();
	private Player firstPlayer;//Son los jugadores del juego
	
	
	//Hacer metodo que lee la cadena en donde se le dice el tamano del tablero y el numero de e y s.
		//Hacer metodo que genere el tablero con el tamano especificado.(Hecho)
		//Hacer metodo que agregue las e al tablero.
		//Hacer metodo que agregue las s al tablero.
		//Hacer metodo que determine el numero de jugadores.
		//Hacer metodo que asigne el simbolo al jugador.
		//Hacer metodo que valida que ninguna casilla de inicio o fin de escalera o serpiente debe coincidir con otro inicio o fin de escalera o serpiente
		//Hacer metodo que lanza el dado, aleatoriamente, de 1 a 6.
		//Hacer metodo que determina si el jugador sube o baja segun si es e o s.
		//Hacer metodo que mueva el player.
		//Hacer metodo que valide si se escribio "num".
		//Hacer metodo que valide si se escribio "simul".
		//Hacer metodo que valide si se escribio "menu".
		//
	
	public Board(int rows, int columns, int snakes, int ladders) {
		this.rows = rows;
		this.columns = columns;
		this.snakes = snakes;
		this.ladders = ladders;
		occupated.setValue(1);
		lastOccupated.setValue(rows*columns);
		createBoard();
	}
	
	private void createBoard() {
		//System.out.println("matriz");
		firstSquare= new Square(0,0);
		firstSquare.setxPosition(0);
		firstSquare.setyPosition(0);
		firstSquare.setPosition(1);
		//System.out.println("Se crea el first");
		createRow(0,0, firstSquare);
	}
	
	private void createRow(int i, int j, Square first) {
		//System.out.println("Create row fila"+i);
		createCol(i,j+1, first, first.getUp());
		if(i+1<rows) {
			Square currentUp=new Square(i+1,j);
			currentUp.setxPosition(i+1);
			currentUp.setyPosition(j);
			currentUp.setPosition(order(j,i+1));
			currentUp.setDown(first);
			first.setUp(currentUp);
			createRow(i+1,j,currentUp);
		}
		
	}
	
	
	private void createCol(int i, int j,Square first, Square prevRow) {
		if(j<columns) {
			//System.out.println("     create col con la columna"+j);
			Square current= new Square(i,j);
			current.setxPosition(i);
			current.setyPosition(j);
			current.setPosition(order(j,i));
			current.setPrev(first);
			first.setNext(current);
			if(prevRow!=null) {
				Square temp=prevRow.getNext();
				current.setUp(temp);
				temp.setDown(current);
				
				
			}
			createCol(i,j+1,current,prevRow);
		}
	}
	
	public String getBoard() {
		String msg;
		getTop(firstSquare);
		msg= boardRow(top);
		return msg;
	}
	
	
	public String boardRow(Square topS) {
		String msg="";
		if(topS!=null) {
			msg= boardCol(topS)+"\n";
			msg+= boardRow(topS.getDown());
			boardRow(topS.getDown());
		}
		return msg;
	}

	private String boardCol(Square current) {
		String msg="";
		if(current!=null) {
			msg= current.getInfo();
			msg+=boardCol(current.getNext());
		}
		return msg;
	}
	
	public void getTop(Square firstSquare) {
		if(firstSquare.getUp()==null) {
			top=firstSquare;
		}
		else {
			getTop(firstSquare.getUp());
		}
		
	}
	
	public int order(int j,int i) {
		int first = 0;
		int change = 0;
		if(i % 2 ==0) {
			first = (columns*i);
			change = 1;
		}
		else {
			first = (columns*i)+(columns+1);
			change = -1;
		}
		return first+((j+1)*change);
	}
	//Create and put the ladders
	
	
	
	//Set the lower ladder
	public boolean setLadder() {
		int number=getRandomNumber();
		int number2=getRandomNumberUpper(number);
		if(near(findSquare(number,top), occupated)&&(near(findSquare(number2,top), occupated))){
			setLadder();	
		}
		else {
		
			findSquare(number,top).setOcupatedLadder();
			findSquare(number,top).setTrueLower();
			findSquare(number,top).setLadder(findSquare(number2,top));
			findSquare(number,top).setCode(""+codes);
			findSquare(number2,top).setLadder(findSquare(number,top));
			findSquare(number2,top).setOcupatedLadder();
			findSquare(number2,top).setTrueUpper();
			return true;
		}
		return false;
	}
	//It's used for take a look at the right and left position of the square, noticing if there are a snake or a ladder next to the local Square
	public boolean near(Square a, Occupated o) {
		if(o!=null) {
		if(a.getPosition()-1==(o.getValue())||(a.getPosition()+1==(o.getValue()))) {
			return true;
		}
		else if(near(a,o.getNext())){
			 return true;
		}
		else {
			return false;
		}
		}
		else {
			return false;
		}
	
	}
	
	//Method implemented by search a node with It´s position
	public Square findSquare(int number,Square sTop) {
		if(sTop.getDown()==null) {
			return find2(sTop,number);
		}
		else {
			if(find2(sTop,number)!=null){
				return find2(sTop,number);
			}
			else {
				return findSquare(number,sTop.getDown());
			}
			
		}
	}

// IN this method you search a square by using It´s position
	public Square find2(Square rTop,int number) {
		if(rTop.getNext()==null) {
			if(rTop.getPosition()==number) {
				return rTop;
			}
			else {
				return null;
			}
					
		}
		else {
			if(rTop.getPosition()==number) {
				return rTop;
			}
			else {
				return find2(rTop.getNext(),number);
			}
		}
	}
	
	//Generate a random number for the ladders
	public int getRandomNumberUpper(int limit) {
		int min=getNextNumber(limit);
		int random=0;
		random=(int)(min + (Math.random() * (rows*columns)));
		random=place(random);
		return random;
	}
	
	
	public int getNextNumber(int start) {
		int number=(findSquare(start,top).getXPosition());
		int temp=0;
		if((number+1)%2==0) {
			temp=order(0,number+1);
			return temp;
		}
		else {
			temp=order(columns,number+1);
			return temp;
		}
	}
	
	public int getRandomNumber() {
		int min=2;
		int random=0;
		random=(int)(min + (Math.random() * ((rows*columns)-columns-1)));
		random=place(random);
		return random;
	}
	
	
	//Choose the correct number to be placed
	public int place(int number) {
		if(!verify(number, occupated)) {
			forbidden(number);
			return number;
		}
		else {
			number = (int)(1 + (Math.random() * (columns*rows)));
			return place(number+1);
		}	
	}
	
	
	//Save a value in the forbidden numbers
	public void forbidden(int number) {
		Occupated temp = new Occupated();
		temp.setNext(occupated);
		occupated = temp;
	}
	
	//Verify if the number is located in the forbidden numbers
	public boolean verify(int number,Occupated value) {
		if(value==null) {
			return false;
		}
		if(number == value.getValue()) {
			return true;
		}
		else{
			return verify(number,value.getNext());
		}

		getNode2(x,y,firstSquare.getDown());
		return firstSquare;
	}
	
	public void createPlayers(String symbol) {
		// Los simbolos que se pueden usar son: * ! O X % $ # + &.
		
		Player newPlayer = new Player(symbol);
		
		if(firstPlayer == null) {
			firstPlayer = newPlayer;
		}else {
			createPlayers(firstPlayer,newPlayer);
		}
	}
	
	private void createPlayers(Player current, Player newPlayer) {
		if(current.getNext() == null) {
			current.setNext(newPlayer);
		}
		else {
			current = current.getNext();
			createPlayers(current,newPlayer);
		}
	}
	
	
	public void movePlayer() {
		if(firstPlayer.getTurn()) {
			firstPlayer.launchDice();
		}else {
			Player current = firstPlayer.getNext();
			movePlayer(current);
		}
	}
	
	private void movePlayer(Player current) {
		if(current.getTurn()) {
			Square aux = findSquare(current.getPosition(), firstSquare);
			aux.removePlayer(current.getSymbol());
			
			current.launchDice();
			movePlayer(current);
			
		}else {
			current = current.getNext();
			movePlayer(current);
		}
	}
	
	public void movePlayerOnSquare(Player current) {
		int position = current.getPosition();
		
		Square aux = findSquare(position,firstSquare);
		
		aux.setPlayer(current);
		
	}
	
	public Square findSquareYulu(int plPosition,Square currentSq) {		
		if(currentSq.getPosition() == plPosition) {
			return currentSq;
		}else {
			currentSq = currentSq.getNext();
			return findSquareYulu(plPosition, currentSq);
		}
	}
	
	//Hacer que devuelva los players.
	
	public void getPlayers() {
		
	}
	
	
}
