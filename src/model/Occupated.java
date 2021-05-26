package model;

public class Occupated {
private Occupated next;
private int value;

	public Occupated() {
		value=0;
	}
	
	public void setNext(Occupated a) {
		next=a;
	}
	
	public void setValue(int a) {
		value=a;
	}
	
	public Occupated getNext() {
		return next;
	}
	
	public int getValue() {
		return value;
	}
}
