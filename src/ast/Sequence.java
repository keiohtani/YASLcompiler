package ast;

import java.util.ArrayList;

import interp.SymbolTable;
import interp.Value;
import interp.VoidValue;

public class Sequence extends Stmt {
	ArrayList<Stmt> body;
	
	public Sequence(ArrayList<Stmt> body) {
		super();
		this.body = body;
	}

	public ArrayList<Stmt> getBody() {
		return body;
	}

	public void setBody(ArrayList<Stmt> body) {
		this.body = body;
	}

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Sequence");
		for	(Stmt stmt : body) {
			stmt.display(indent + "  ");
		}
	}
	public Value interpret(SymbolTable table) {	//Sequence
		Value lastValue = null;
		for (Stmt s : body){
			lastValue = s.interpret(table);
		}
		if (lastValue != null) {
			return lastValue;
		} else {
			return new VoidValue();
		}
	}
}
