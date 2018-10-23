package ast;

import java.util.ArrayList;

public class Call extends Expr {
	String id;
	ArrayList<Expr> args;
	
	public Call(String id, ArrayList<Expr> args) {
		this.id = id;
		this.args = args;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Expr> getArgs() {
		return args;
	}

	public void setArgs(ArrayList<Expr> args) {
		this.args = args;
	}

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Call " + id);
		for (Expr expr : args) {
			expr.display(indent + "  ");
		}
	}

}
