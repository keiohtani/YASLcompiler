package ast;

import java.util.ArrayList;

public class Print extends Stmt {
	ArrayList<Item> items;
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Print");
		for (Item item : items) {
			item.display(indent + "  ");
		}
	}
	public Print(ArrayList<Item> items) {
		super();
		this.items = items;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
