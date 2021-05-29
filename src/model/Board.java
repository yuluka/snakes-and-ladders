package model;

public class Board {
	private int rows; 
	private int columns;
	private int snakes;
	private int ladders;
	private int random;
	private int counterS=65;
	private char codes;
	private int counter=1;
	private int amountP;
	private int auxP=1;
	private int tempP;
	private boolean direction;
	private Square firstSquare;
	private Square top;
	private Occupated occupated;
	private Player firstPlayer;
	private Player winner;
	

	
	
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
		tempP=1;
		winner=null;
		
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
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getSnakes() {
		return snakes;
	}

	public int getLadders() {
		return ladders;
	}

	public void setLadders(int ladders) {
		this.ladders = ladders;
	}

	public int getRandom() {
		return random;
	}

	public void setRandom(int random) {
		this.random = random;
	}

	public int getCounterS() {
		return counterS;
	}

	public void setCounterS(int counterS) {
		this.counterS = counterS;
	}

	public char getCodes() {
		return codes;
	}

	public void setCodes(char codes) {
		this.codes = codes;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public Square getFirstSquare() {
		return firstSquare;
	}

	public void setFirstSquare(Square firstSquare) {
		this.firstSquare = firstSquare;
	}

	public Square getTop() {
		return top;
	}

	public void setTop(Square top) {
		this.top = top;
	}

	public Occupated getOccupated() {
		return occupated;
	}

	public void setOccupated(Occupated occupated) {
		this.occupated = occupated;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	public void getInfo(Occupated occupated) {
		while((occupated!=null)){
			//System.out.println( occupated.getValue());
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
	
	public String getBoard(String aux) {
		String msg;
		getTop(firstSquare);
		msg= boardRow(top,aux);
		return msg;
	}
	
	
	public String boardRow(Square topS,String aux) {
		String msg="";
		if(topS!=null) {
			msg= boardCol(topS, aux)+"\n";
			msg+= boardRow(topS.getDown(),aux);
			boardRow(topS.getDown(),aux);
		}
		return msg;
	}

	private String boardCol(Square current, String aux) {
		String msg="";
		if(!(current==null)) {
			if(aux.equals("")) {
					msg= current.getInfo2();
					msg+=boardCol(current.getNext(),aux);
				
			}else if(aux.equals("a")){
				msg = current.getInfo();
				msg+=boardCol(current.getNext(),aux);
			}
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
				//System.out.println("There was an error at the time of set the snakes");
			}
		}
		else {
			//System.out.println("The snakes are setted");
			
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
		
		if(findSquare(head,top).getSnake()==null) {
			findSquare(head,top).setSnake(findByCode(findSquare(head,top).getCode() ,top));	
		}
		return true;
	}
	//It큦 Finished
	//Create and put the ladders
	
	public Square findByCode( String code,Square sTop) {
		if(sTop.getDown()==null){
			return find3(sTop,code);
		}
		else {
			if(find3(sTop,code)!=null){
				return find3(sTop,code);
			}
			else {
				return findByCode(code,sTop.getDown());
			}
			
		}
	}
	
	public Square find3(Square rTop, String code) {
		if(rTop.getNext()==null) {
			if((rTop.getCode()==code)) {
				return rTop;
				
			}
			else {
				return null;
			}
					
		}
		else {
			if((rTop.getCode()==code)) {
				return rTop;
			}
			else {
				return find3(rTop.getNext(),code);
			}
		}
	}

	public void putLadders(int amount) {
		int ladders=0;
		if(amount>ladders) {
			if(setLadder(counter)) {
				counter++;
				putLadders(amount-1);
			}
			else {
				//System.out.println("There was an error at the time of create ladders");
				//System.out.println("Verify if the amount of ladders is correctly");
			}
		}
		else {
			//System.out.println("The ladders are setted");
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
			
			if(findSquare(number,top).getLadder()==null) {
				findSquare(number,top).setLadder(findByCode(findSquare(number2,top).getCode(),top));
			}
			
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
	
	//Method implemented by search a node with It큦 position
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

// IN this method you search a square by using It큦 position
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

	public String movePlayer() {
		String a="";
		
			int aux=playerPlay(auxP);
			//System.out.println(aux+"hola");
			//System.out.println(findPlayer(aux).getPosition()+"chao");
			
			findSquare(findPlayer(aux).getPosition(), top).deleteCharacter(findPlayer(aux).getSymbol());
			int temp=findPlayer(aux).launchDice();
			a+="\n The " + findPlayer(aux).getSymbol() + " player got: " + temp;
		if(findPlayer(aux).getPosition()<=rows*columns) {
				if(findSquare(findPlayer(aux).getPosition(), top).isOccupatedLadder()) {
							
							if(findSquare(findPlayer(aux).getPosition(), top).getLower()) {
									findPlayer(aux).setPosition(findSquare(findPlayer(aux).getPosition(), top).getLadder().getPosition());
											a+="\n "+findPlayer(aux).getSymbol()+" Found a ladder, It큦 new position is: "+findPlayer(aux).getPosition();
											
							}
							else if(findSquare(findPlayer(aux).getPosition(), top).getUpper()) {
										//System.out.println("ENTRO ESCALERA upper");
							}
				}
				else if(findSquare(findPlayer(aux).getPosition(), top).isOccupatedSnakes()) {
									if(findSquare(findPlayer(aux).getPosition(), top).getHead()) {
										//System.out.println("ENTRO SNAKE HEADD"+findSquare(firstPlayer.getPosition(), top).getPosition()+" ");
										
										findPlayer(aux).setPosition(findSquare(findPlayer(aux).getPosition(), top).getSnake().getPosition());
										a+="\n"+findPlayer(aux).getSymbol()+" Found a Snake, It큦 new position is: "+findPlayer(aux).getPosition();
										//setPlayerPosition(current);
					}
					if(findSquare(findPlayer(aux).getPosition(), top).getTail()) {
									//System.out.println("ENTRO SNAKE tail");
									
					}
			}
			a+="\nThe player "+findPlayer(aux).getSymbol()+" position is: "+findPlayer(aux).getPosition();
			a+="\n"+setPlayerPosition(findPlayer(aux));
			}
			else {
				findPlayer(aux).setPosition(rows*columns);
				a+="\nThe player "+findPlayer(aux).getSymbol()+" position is: "+findPlayer(aux).getPosition();
				a+="\n\n\n"+setPlayerPosition(findPlayer(aux));
			}
			
		auxP++;	
		return a;
	}
	
	/*private void movePlayer(Player current) {
		if(current!=null) {
		if(current.getTurn()) {
			int temp=current.launchDice();
			System.out.println("The " + current.getSymbol() + " player got: " + temp);
			
			if(findSquare(current.getPosition(), top).isOccupatedLadder()) {
						
						if(findSquare(current.getPosition(), top).getLower()) {
										System.out.println("ENTRO ESCALERA low");
						
										current.setPosition(findSquare(current.getPosition(), top).getLadder().getPosition());
										System.out.println(current.getSymbol()+" Found a ladder, It큦 new position is: "+current.getPosition());
										//setPlayerPosition(current);
						}
						else if(findSquare(current.getPosition(), top).getUpper()) {
									System.out.println("ENTRO ESCALERA upper");
						}
			}
			else if(findSquare(current.getPosition(), top).isOccupatedSnakes()) {
								if(findSquare(current.getPosition(), top).getHead()) {
									System.out.println("ENTRO SNAKE HEADD\n");
									System.out.println(findSquare(current.getPosition(), top).getSnake().getPosition());
									current.setPosition(findSquare(current.getPosition(), top).getSnake().getPosition());
									System.out.println(current.getSymbol()+" Found a Snake, It큦 new position is: "+current.getPosition());
									
				}
				if(findSquare(current.getPosition(), top).getTail()) {
								System.out.println("ENTRO SNAKE tail");
								
				}
			}
			
			System.out.println("The player"+current.getSymbol()+"position is"+current.getPosition());
			
			setPlayerPosition(current);
			current.setTurn(false);
			if(current.getNext()!=null) {
				current.getNext().setTurn(true);
			}
			else {
				firstPlayer.setTurn(true);
			}
		}
		
		
	}	
	firstPlayer.setTurn(true);
}*/
			
			
			
	public Square findSquareYulu(int plPosition,Square currentSq) {		
		if(currentSq.getPosition() == plPosition) {
			return currentSq;
		}else {
			currentSq = currentSq.getNext();
			return findSquareYulu(plPosition, currentSq);
		}
	}
	
	
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	public void setPnumber(String playersSymbols) {
		this.amountP=playersSymbols.length();
	}
	public void setPlayers(String playersSymbols) {//Recibe los symbols que el usuario digito en el menu
		
		if(playersSymbols.length() == 1) {//Si solo escribio un simbolo, crea un solo player
			if(firstPlayer == null) {
				firstPlayer = new Player(playersSymbols,tempP,columns, rows, snakes, ladders);
				tempP++;
				setPlayerPosition(firstPlayer);
			}else {
				Player newP = new Player(playersSymbols, tempP, columns, rows, snakes, ladders);
				tempP++;
				firstPlayer.add(newP);
				setPlayerPosition(newP);
			}
		}else {//Si el usuario escribio mas de un symbol, se va pasando uno por uno a si mismo, y quita el que ya paso.
			String nextSymbol = String.valueOf(playersSymbols.charAt(0));
			setPlayers(nextSymbol);
			setPlayers(playersSymbols.substring(1,playersSymbols.length()));
		}
	}
	
	private void linkPlayers(Player p) {
		if(p.getNext()==null) {
			p.setNext(this.firstPlayer);
			
		}
		else {
			linkPlayers(p.getNext());
		}
	}
	
	public void linkPlayers() {
		linkPlayers(firstPlayer);
	}
	
	public String setPlayerPosition(Player p) {
		String a="";
		if(!(p.getPosition()>=rows*columns)) {
			int pPosition = p.getPosition();
			//Aqui va el metodo de los akdnasjdnasjfnaljsdfnajskdfnajevnaljdnvlajdvnljav
			findSquare(pPosition, top).addPlayer(p);
			findSquare(pPosition, top).addCharacter(p.getSymbol());
			
		}else {
			a="Congratulations the player: "+p.getSymbol()+" won the game.";
			//somebodyWon(searchP(p.getPosition()));
			setWinner(p);
		}
		return a;
	}
	
	
	/*private boolean gameWon(Player current) {
		boolean won = false;
		
		if(current.getPosition() >= rows*columns) {
			System.out.println("Entrooooooooooooooooooooooooooooo");
			won =true;
			return won;
		}else {
			if(current.getNext() != null) {
				return gameWon(current.getNext());
			}else {
				won = false;
			}
		}
		System.out.println(won+"2");
		return won;
	}*/
	
	
	public Square searchSquare(int position) {
		if(firstSquare.getPosition() == position) {
			return firstSquare;
		}else {
			return searchSquare(firstSquare.getNext(),position);
		}
	}
	
	private Square searchSquare(Square sq, int position) {
		if(sq.getPosition() == position) {
			return sq;
		}
		else {
			if(sq.getNext() != null) {
				return searchSquare(sq.getNext(),position);
			}
			/*else {
				somebodyWon(searchP(position));
				return null;
			}*/
			else {
				return null;
			}
		}		
}

public Player searchP(int pos) {
	if(firstPlayer.getPosition() == pos) {
		return firstPlayer;
	}else {
		return searchP(pos,firstPlayer.getNext());
	}
}

private Player searchP(int pos, Player p) {
	if(p.getPosition() == pos) {
		return p;
	}else {
		return searchP(pos,p.getNext());
	}
}
	public String getPlayersSq() {
		String playersSq = "";
		
		playersSq = "Players in first square: " + firstSquare.getPlayersS();
		
		return playersSq;
	}
	
	public String getPlayersB() {//Borrar antes de entregar
		String playersB = "";
		
		Player current = firstPlayer;
		
		if(firstPlayer == null) {
			playersB = "No players in existance";
		}else {			
			while(current != null) {
				playersB += current.getSymbol();
				
				current = current.getNext();
			}
		}
		
		return playersB;
	}
	
	/*public void somebodyWon(Player winnerP) {
		System.out.println("The " + winnerP.getSymbol() + " player has won the game.\nCongrats!!!");
		System.exit(0);
	}*/
	
	private int playerPlay(int auxP){
		if(auxP > amountP){
			return playerPlay(auxP-amountP); 
		}
		else{
			return auxP;
		}
	}
	
	public Player findPlayer(int numP){
		return findPlayer(numP,firstPlayer);
	}
	
	public int getPositionTop() {
		return top.getPosition();
	}
	
	

	private Player findPlayer(int num2, Player firstPlayer) {
		if (firstPlayer == null) {
			return null;
		}
		else{
			if(firstPlayer.getpNumber()==num2){
				return firstPlayer;
			}
			else{
				return findPlayer(num2,firstPlayer.getNext());
			}
		}
		
	}
	public int getAmountP() {
		return amountP;
	}

	public void setAmountP(int amountP) {
		this.amountP = amountP;
	}

	public int getAuxP() {
		return auxP;
	}

	public void setAuxP(int auxP) {
		this.auxP = auxP;
	}

	public int getTempP() {
		return tempP;
	}

	public void setTempP(int tempP) {
		this.tempP = tempP;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	
	
}

