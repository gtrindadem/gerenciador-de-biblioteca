package test;

import controller.Controller;
import model.DaoMySql;
import view.JanelaHome;

public class testController {
	
	public static void main(String[] args) {
		new Controller(new DaoMySql(), new JanelaHome());
	}
	
}
