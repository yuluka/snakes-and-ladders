package model;

public class Occupated {
private Occupated next;
private Occupated last;
private int value;

	public Occupated(int a) {
		value=a;
	}
	
	public void setNext(Occupated a) {
		next=a;
	}
	
	public void setValue(int a) {
		value=a;
	}
	
	public void setLast(Occupated a) {
		last=a;
	}
	
	public Occupated getNext() {
		return next;
	}
	
	public Occupated getLast() {
		return last;
	}
	
	public int getValue() {
		return value;
	}
}
