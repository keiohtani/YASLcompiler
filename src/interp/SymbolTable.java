package interp;

import java.util.HashMap;
import java.util.Stack;


public class SymbolTable {
	Stack<HashMap<String, Value>> tables;
	
	public SymbolTable() {
		tables = new Stack<HashMap<String, Value>>();
	}

	public void enter(String name) {
		tables.push(new HashMap<String, Value>());
	}
	
	public void exit() {
		tables.pop();
	}

	public void bind(String id, Value value) {
		tables.peek().put(id, value);
	}

	public Value lookup(String id) {
		HashMap<String, Value> currentTable;
		for (int i = tables.size()- 1; 0 <= i; i--) {
			currentTable = tables.get(i);
			if (currentTable.containsKey(id)) {
				return currentTable.get(id);
			} 		
		}
		System.err.println(id + " : does not exist. ");
		System.exit(1);
		return null;
	}
}
