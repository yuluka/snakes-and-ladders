package model;

public class Board {
	private int row;
	private int column;
	private int snakes;
	private int ladders;
	private boolean direction;
	
	
	private Square firstSquare;
	
	private Player players;
	
	//Hacer metodo que lee la cadena en donde se le dice el tamano del tablero y el numero de e y s.
		//Hacer metodo que genere el tablero con el tamano especificado.
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
	
	public Board(int m, int n) {
		row=m;
		column=n;
		createBoard();
	}
	
	private void createBoard() {
		System.out.println("matriz");
		firstSquare= new Square(0,0);
		System.out.println("Se crea el first");
		createRow(0,0, firstSquare);
	}
	
	private void createRow(int i, int j, Square first) {
		System.out.println("Create row fila"+i);
		createCol(i,j+1, first, first.getUp());
		if(i+1<row) {
			Square currentDown=new Square(i+1,j);
			currentDown.setUp(first);
			first.setDown(currentDown);
			createRow(i+1,j,currentDown);
		}
		
	}
	
	private void createCol(int i, int j,Square first, Square prevRow) {
		if(j<column) {
			System.out.println("     create col con la columna"+j);
			Square current= new Square(i,j);
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
	
	
}
