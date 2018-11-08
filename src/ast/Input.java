package ast;

import interp.SymbolTable;
import interp.Value;
import interp.VoidValue;

public class Input extends Stmt {
	String message;
	
	public Input(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Input \"" + message +"\"");
	}
	public Value interpret(SymbolTable table) {	//input
		System.out.println(message);
		return new VoidValue();
	}
}
