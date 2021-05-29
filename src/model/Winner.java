package model;

import java.io.Serializable;

public class Winner implements Serializable {


	private Score winnerPlayers;
	private static final long serialVersionUID = 1;
	
	public Winner() {
		winnerPlayers=null;
	}

	

	



	public void receiveWinningPlayer(Player winner,String name) {
		int score = winner.getMovements()*winner.getColumns()*winner.getRows();
		Score scoreOfBestPlayer = new Score(name,score, winner.getRows(), winner.getColumns(), winner.getLadders(), winner.getSnakes(),winner.getPlayers().length(),winner.getPlayers(),winner.getSymbol().charAt(0));
		winnerPlayers = addToWinners(winnerPlayers,scoreOfBestPlayer);
	}

	public Score addToWinners(Score a,Score b){
		if (a == null) {
			a = b;
			return a;
		}
		if (b.getScore() <= a.getScore())
			a.setLeft(addToWinners(a.getLeft(),b));
		else if (b.getScore() > a.getScore())
			a.setRight(addToWinners(a.getRight(),b));
		return a;
	}
	
	public Score getWinnerPlayers() {
		return winnerPlayers;
	}



	public void setWinnerPlayers(Score winnerPlayers) {
		this.winnerPlayers = winnerPlayers;
	}
	
}