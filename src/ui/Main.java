package ui;
import model.*;

public class Main {
	public static void main(String[] args) {
		/*Menu a = new Menu();
		a.menu();*/

		Board a=new Board(6,6,2,2);
		//Board a = new Board(20,5,1,1);
		System.out.println(a.getBoard());
		
		
	}
}
