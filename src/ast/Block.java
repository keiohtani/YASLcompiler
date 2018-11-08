package ast;

import java.util.ArrayList;

import interp.SymbolTable;
import interp.Value;

public class Block extends ASTNode{
	private ArrayList<ValDecl> vals;
	private ArrayList<VarDecl> vars;
	private ArrayList<FunDecl> funs;
	private Stmt body;
	
	public Block(ArrayList<ValDecl> vals, ArrayList<VarDecl> vars, ArrayList<FunDecl> funs, Stmt body) {
		this.vals = vals;
		this.vars = vars;
		this.funs = funs;
		this.body = body;
	}

	public ArrayList<ValDecl> getVals() {
		return vals;
	}

	public void setVals(ArrayList<ValDecl> vals) {
		this.vals = vals;
	}

	public ArrayList<VarDecl> getVars() {
		return vars;
	}

	public void setVars(ArrayList<VarDecl> vars) {
		this.vars = vars;
	}

	public ArrayList<FunDecl> getFuns() {
		return funs;
	}

	public void setFuns(ArrayList<FunDecl> funs) {
		this.funs = funs;
	}

	public Stmt getBody() {
		return body;
	}

	public void setBody(Stmt body) {
		this.body = body;
	}

	@Override
	public void display(String indent) {
		System.out.println(indent + "Block");
		for(ValDecl val:vals) {
			val.display(indent + "  ");
		}
		for(VarDecl var:vars) {
			var.display(indent + "  ");
		}
		for(FunDecl fun:funs) {
			fun.display(indent + "  ");
		}
		body.display(indent + "  ");
	}
	
	public Value interpret(SymbolTable table) {	//Block
		for (ValDecl d: vals) {
			d.interpret(table);
		}
		for (VarDecl d: vars) {
			d.interpret(table);
		}
		for (FunDecl d: funs) {
			d.interpret(table);
		}
		return body.interpret(table);
	}
}
