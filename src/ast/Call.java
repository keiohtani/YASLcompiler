package ast;

import java.util.ArrayList;

import interp.*;

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

	@Override
	public Value interpret(SymbolTable table) {
		FunValue funValue = (FunValue) table.lookup(id);
		ArrayList<Value> as = new ArrayList<Value>();
		for (Expr expr : args) {
			as.add(expr.interpret(table));
		}
		if (as.size() == args.size()) {
			table.enter(id);
			Value result = call(funValue.params, funValue.block, as, table);
			table.exit();
			return result;
		} else {
			System.err.println("The number of argments do not match");
			System.exit(1);
			return null;
		}
	}
	
	private Value call(ArrayList<Param> params, Block block, ArrayList<Value> as, SymbolTable table) {
		Value value = null;
		Param param = null;
//		ArrayList<VarDecl> vars = new ArrayList<VarDecl>();
//		for (Param param : params) {
//			vars.add(new VarDecl(param.id, param.typ));
//		}
//		block.setVars(vars);
//		block.interpret(table);
		for (int i = 0; i < params.size(); i++) {
			param = params.get(i);
			value = as.get(i);
			table.bind(param.id, value);
		}
		value = block.interpret(table);
		return value;
	}
}
