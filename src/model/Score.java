package model;

import java.io.Serializable;

public class Score implements Serializable {

	private String name;
	private Score right;
	private Score left;
	private int players;
	private String symbols;
	private char symbolW;
	private double score;
	private int rows;
	private int columns;
	private int ladders;
	private int snakes;

	private static final long serialVersionUID = 1;
	
	 public Score(String name, double score,int rows,int columns,int ladders,int snakes,int players,String symbols,char symbolW) {
	        this.name = name;
	        this.rows = rows;
	        this.columns = columns;
	        this.ladders = ladders;
	        this.snakes = snakes;
	        this.players = players;
	        this.symbols = symbols;
	        this.symbolW = symbolW;
	        this.score = score;
	        this.right = null;
	        this.left = null;
	    }



	public String getName() {
		return name;
	}
	
	public Score getRight() {
		return right;
	}


	public Score getLeft() {
		return left;
	}
	

	public int getPlayers() {
		return players;
	}
	
	public String getSymbols() {
		return symbols;
	}
	
	public char getSymbolW() {
		return symbolW;
	}

	public void setSymbolW(char symbolW) {
		this.symbolW = symbolW;
	}

	public double getScore() {
		return score;
	}

	
	public int getRows() {
		return rows;
	}
	
		public int getColumns() {
		return columns;
	}
		
		public void setScore(double score) {
			this.score = score;
		}

		public void setRows(int rows) {
			this.rows = rows;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setRight(Score right) {
			this.right = right;
		}

		public void setLeft(Score left) {
			this.left = left;
		}

		public void setPlayers(int players) {
			this.players = players;
		}


		public void setSymbols(String symbols) {
			this.symbols = symbols;
		}



	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getLadders() {
		return ladders;
	}

	public void setLadders(int ladders) {
		this.ladders = ladders;
	}

	public int getSnakes() {
		return snakes;
	}

	public void setSnakes(int snakes) {
		this.snakes = snakes;
	}
}