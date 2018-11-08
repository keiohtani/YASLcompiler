package ast;

import java.util.ArrayList;

import interp.BoolValue;
import interp.IntCell;
import interp.IntValue;
import interp.SymbolTable;
import interp.Value;
import interp.VoidValue;

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
	public Value interpret(SymbolTable table) {
		for (Item item : items) {
			switch (item.getClass().getSimpleName()) {
			case "ExprItem":
				Value value = ((ExprItem)item).interpret(table);
				if (value.getClass() == IntValue.class) {
					System.out.print(((IntValue)value).get());					
				} else if (value.getClass() == BoolValue.class) {
					System.out.print(((BoolValue)value).get());
				} else if (value.getClass() == IntCell.class) {
					System.out.print(((IntCell)value).get());
				}
				
				break;
			case "StringItem":
				System.out.print(((StringItem)item).getMessage());
				break;
			}
		}
		System.out.println();
		return new VoidValue();
	}
}
