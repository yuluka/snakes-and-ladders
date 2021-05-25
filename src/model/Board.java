package model;

public class Board {
	private int rows; //n filas
	private int columns;//m columnas
	private int snakes;//s serpientes
	private int ladders;//e escaleras
	private boolean direction;//Es la direccion en la que esta yendo un jugador. Puede ser izquierda o derecha
	private Square firstSquare;//Es el primer cuadro del tablero
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
		createBoard();
	}
	
	private void createBoard() {
		System.out.println("matriz");
		firstSquare= new Square(0,0);
		firstSquare.setxPosition(0);
		firstSquare.setyPosition(0);
		System.out.println("Se crea el first");
		createRow(0,0, firstSquare);
	}
	
	private void createRow(int i, int j, Square first) {
		System.out.println("Create row fila"+i);
		createCol(i,j+1, first, first.getUp());
		if(i+1<rows) {
			Square currentDown=new Square(i+1,j);
			currentDown.setxPosition(i+1);
			currentDown.setyPosition(j);
			currentDown.setUp(first);
			first.setDown(currentDown);
			createRow(i+1,j,currentDown);
		}
		
	}
	
	private void createCol(int i, int j,Square first, Square prevRow) {
		if(j<columns) {
			System.out.println("     create col con la columna"+j);
			Square current= new Square(i,j);
			current.setxPosition(i);
			current.setyPosition(j);
			current.setPrev(first);
			first.setNext(current);
			
			if(prevRow!=null) {
				prevRow=prevRow.getNext();
				current.setUp(prevRow);
				prevRow.setDown(current);
				
				
			}
			createCol(i,j+1,current,prevRow);
		}
	}
	
	public String getBoard() {
		String msg;
		msg= boardRow(firstSquare);
		return msg;
	}
	
	public String boardRow(Square rowFirst) {
		String msg="";
		if(rowFirst!=null) {
			msg= boardCol(rowFirst)+"\n";
			msg+= boardRow(rowFirst.getDown());
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
	
	//Create and put the ladders
	
	private void setLadders(int ladders) {
		int min=1;
		if((ladders)>min) {
		int random=(int)((int) 0 + (Math.random() * rows-1));  
		int random2=(int)((int) 0 + (Math.random() * columns));
		if((random==rows)&&random2==0) {
			random2=1;
		}
		if((random==0)&&random2==columns) {
			random2=columns-1;
		}
		
		setLadders(ladders-1);
		}
	}
	//Method implemented by search a node with It�s coordinates x and y
	public void setLowerLadder(int x, int y, Square firstSquare) {
			
			if((firstSquare.getXPosition()==x)&&(firstSquare.isOccupatedLadder()==false)) {
				if(firstSquare.getYPosition()==y) {
					firstSquare.setOcupatedLadder();
					firstSquare.setTrueLower();
				}
				setLowerLadder(x,y,firstSquare.getNext());
			}
			setLowerLadder(x,y,firstSquare.getDown());
		
	}
	
	public Square getNode2(int x, int y, Square firstSquare) {
		
		if((firstSquare.getXPosition()==x)) {
			if(firstSquare.getYPosition()==y) {
				firstSquare.setOcupatedLadder();
				return firstSquare;
			}
			getNode2(x,y,firstSquare.getNext());
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
		}else {
			current = current.getNext();
			createPlayers(current,newPlayer);
		}
	}
	
}
