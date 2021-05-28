package model;
import java.lang.System;

public class Board {
	private int rows; //n filas
	private int columns;//m columnas
	private int snakes;//s serpientes
	private int ladders;//e escaleras
	private int random;
	private int counterS=65;
	private char codes;
	private int counter=1;
	private boolean direction;//Es la direccion en la que esta yendo un jugador. Puede ser izquierda o derecha
	private Square firstSquare;//Es el primer cuadro del tablero
	private Square top;
	private Occupated occupated;
	private Player firstPlayer;//Son los jugadores del juego
	
	
	//Hacer metodo que lee la cadena en donde se le dice el tamano del tablero y el numero de e y s.
		//Hacer metodo que genere el tablero con el tamano especificado.(Hecho)
		//Hacer metodo que agregue las e al tablero.(Hecho)
		//Hacer metodo que agregue las s al tablero.(Hecho)
		//Hacer metodo que determine el numero de jugadores.
		//Hacer metodo que asigne el simbolo al jugador.
		//Hacer metodo que valida que ninguna casilla de inicio o fin de escalera o serpiente debe coincidir con otro inicio o fin de escalera o serpiente(Hecho)
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
		occupated=new Occupated(1);
		occupated.setNext(new Occupated(rows*columns));
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
		getTop(firstSquare);
		putLadders(ladders);
		putSnakes(snakes);
		
		
	}
	
	public void getInfo(Occupated occupated) {
		while((occupated!=null)){
			System.out.println( occupated.getValue());
			occupated=occupated.getNext();
		}
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
	//Create and put the snakes
	
	
	public void putSnakes(int amount) {
		int snakes=0;
		if(amount>snakes) {
			
			if(setSnakes(counterS)) {
				counterS++;
				putSnakes(amount-1);
			}
			else {
				System.out.println("There was an error at the time of set the snakes");
			}
		}
		else {
			System.out.println("The snakes are setted");
			
		}
	}
	
	private boolean setSnakes(int counterS) {
		int tail=getRandomNumber();
		int head=getRandomNumberUpper(tail);
		if(head<tail) {
			head=retry(tail, head);
		}
		codes=(char)counterS;
		findSquare(tail,top).setOcupatedSnakes();
		findSquare(tail,top).setTrueTail();
		findSquare(tail,top).setCode(""+codes);
		
		findSquare(head,top).setOcupatedSnakes();
		findSquare(head,top).setTrueHead();
		findSquare(head,top).setSnake(findSquare(tail,top));
		findSquare(head,top).setCode(""+codes);
		return true;
	}
	//It´s Finished
	//Create and put the ladders
	


	public void putLadders(int amount) {
		int ladders=0;
		if(amount>ladders) {
			if(setLadder(counter)) {
				counter++;
				putLadders(amount-1);
			}
			else {
				System.out.println("There was an error at the time of create ladders");
				System.out.println("Verify if the amount of ladders is correctly");
			}
		}
		else {
			System.out.println("The ladders are setted");
		}
	}
	
	//Set the lower ladder
	public boolean setLadder(int counter) {
		
		int number=getRandomNumber();
		//System.out.println("useless");
		int number2=getRandomNumberUpper(number);
		if(number2<number) {
			//desOccupated(number2, occupated);
			number=retry(number2, number);
		}
		
		//System.out.println(number+"f");
		//System.out.println(number2+"l");
		
			findSquare(number,top).setOcupatedLadder();
			findSquare(number,top).setTrueLower();
			findSquare(number,top).setLadder(findSquare(number2,top));
			//findSquare(number,top).getLadder().setCode(""+counter);
			findSquare(number,top).setCode(""+counter);
			
			findSquare(number2,top).setOcupatedLadder();
			findSquare(number2,top).setTrueUpper();
			findSquare(number2,top).setCode(""+counter);
			
			return true;
			
		}
	
	/*public boolean desOccupated(int a, Occupated occupated) {
		Occupated b=new Occupated(0);
		if(occupated.getValue()==a) {
			b.setNext(occupated.getNext());
			occupated=b;
			return true;
		}
		else {
			return desOccupated(a, occupated.getNext());
		}
	}*/
		
	public int retry (int a, int b) {
		//desOccupated(a, occupated);
		a=getRandomNumberUpper(b);
		if(a<b) {
			retry(a,b);
		}
		else {
			return a;
		}
		return retry(a,b);
		
	}
	
	//Method implemented by search a node with It´s position
	public Square findSquare(int number,Square sTop) {
		
		if(sTop.getDown()==null){
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
			if((rTop.getPosition()==number)) {
				return rTop;
				
			}
			else {
				return null;
			}
					
		}
		else {
			if((rTop.getPosition()==number)) {
				return rTop;
			}
			else {
				return find2(rTop.getNext(),number);
			}
		}
	}
	
		//Generate a random number for the ladders
	public int getRandomNumberUpper(int limit) {
		int min=getNextNumber(limit)+1;
		int random=0;
		int size=(rows*columns);
		random=(int) (Math.random()*(min-size)+size);
		int temp=min;
		int a=0;
		//System.out.println(random+"r");
		random=place(random, temp, a);
		if((limit==random)||(random<limit)) {
			//System.out.println("Entron al random upper");
		getRandomNumberUpper(limit);
		
		}
		
		return random;
	}
	
	//Get the correct position on the board to put the upper ladder
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
	
	//Get a random number for the ladders
	public int getRandomNumber() {
		int min=columns*2;
		int random=0;
		int size=(columns*rows);
		size=size-min;
		random=(int)(Math.random()*(2-size)+size);
		//System.out.println(random+"r1");
		if(random>((columns*rows)-(columns*2))){
			getRandomNumber();
		}
		int a=1;
		int temp=random;
		random=place(random,temp, a);
		return random;
	}
	
	
	//Choose the correct number to be placed
	public int place(int number, int temp, int a) {
		if(!verify(number, occupated)) {
		//	System.out.println(number+"ENTRO");
			forbidden(number);
			return number;
		}
		else {
			//System.out.println(number+"NO ENTRO");
			if(a==0) {
				int size=(rows*columns)-1;
				number=(int) (Math.random()*(temp-size)+size);
				//System.out.println(number+" ==0");
			}
			else if(a==1){
				int size=(rows*columns)-(columns*2);
				number =  (int) (Math.random()*(2-size)+size);
				//System.out.println(number+" ==1");
			}
			
			//System.out.println(number+" RANDOM");
			return place(number, temp, a);
		}	
	}
	
	
	//Save a value in the forbidden numbers
	public void forbidden(int number) {
		Occupated temp = new Occupated(number);
		temp.setNext(occupated);
		occupated=temp;
	}
	
	//Verify if the number is located in the forbidden numbers
	public boolean verify(int number,Occupated value) {
		if(value==null) {
			//System.out.println("False1");
			return false;
		}
		else if(number == value.getValue()) {
			//System.out.println("True1");
			return true;
		}
		else{
			return verify(number,value.getNext());
		}
	}

		/*public Square getNode2(x,y,firstSquare.getDown());
		return firstSquare;
	}*/
	
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
	
	public void setPlayers(String playersSymbols) {
		if(playersSymbols.length() == 1) {
			if(firstPlayer == null) {
				firstPlayer = new Player(playersSymbols);
			}else {
				Player newP = new Player(playersSymbols);
				firstPlayer.add(newP);
			}
		}else {
			String nextSymbol = String.valueOf(playersSymbols.charAt(0));
			setPlayers(nextSymbol);
			setPlayers(playersSymbols.substring(1,playersSymbols.length()));
		}
	}
}
