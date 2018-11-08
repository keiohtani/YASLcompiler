package interp;

import ast.*;

public class Interpreter {
	public Interpreter() {
	}
//	public void interpret(Program program) {	//program
//		String name = program.getName();
//		Block block = program.getBlock();
//		SymbolTable table = new SymbolTable();
//		table.enter(name);
//		interpret(block, table);
//		table.exit();
//	}
//	public Value interpret(Block block, SymbolTable table) {	//Block
//		for (ValDecl d: block.getVals()) {
//			interpret(d, table);
//		}
//		for (VarDecl d: block.getVars()) {
//			interpret(d, table);
//		}
//		for (FunDecl d: block.getFuns()) {
//			interpret(d, table);
//		}
//		return interpret(block.getBody(), table);
//	}
//	public Value interpret(Stmt stmt, SymbolTable table) {	//Statement
//		if (stmt instanceof Assign) {
//			interpret(((Assign) stmt), table);
//		} else if (stmt instanceof Sequence) {
//			interpret(((Sequence) stmt), table);
//		} else if (stmt instanceof IfThen) {
//			interpret(((IfThen) stmt), table);
//		} else if (stmt instanceof IfThenElse) {
//			interpret(((IfThenElse) stmt), table);
//		} else if (stmt instanceof While) {
//			interpret(((While) stmt), table);
//		}  else if (stmt instanceof Input) {
//			interpret(((Input) stmt), table);
//		} else if (stmt instanceof Input2) {
//			interpret(((Input2) stmt), table);
//		} else if (stmt instanceof Print) {
//			interpret(((Print) stmt), table);
//		}  else if (stmt instanceof ExprStmt) {
//			interpret(((ExprStmt) stmt), table);
//		}
//		System.err.println("Errro");
//		return null;
//	}
//	public Value interpret(ValDecl d, SymbolTable table) {	//ValDecls
//		table.bind(d.getId(), new IntValue(d.getValue()));
//	}
//	public Value interpret(VarDecl d, SymbolTable table) {	//VarDecls
//		table.bind(d.getId(), new IntCell(0));
//	}
//	public Value interpret(FunDecl d, SymbolTable table) {	//FunDecls
//		table.bind(d.getId(), new FunValue(d.getParams(), d.getTyp(), d.getBlock()));
//		return new VoidValue();
//	}
//	public Value interpret(Assign assign, SymbolTable table) {	//Assign
//		Value lhs = table.lookup(assign.getId());
//		Value rhs = interpret(assign.getExp(), table);
//		if (lhs instanceof IntCell) {
//			((IntCell)lhs).set(((IntValue)rhs).intValue);
//		} else {
//			((BoolValue)lhs).set(((BoolValue)rhs).boolValue);
//		}
//		return rhs;
//	}
	public Value interpret(Expr exp, SymbolTable table) {	// Expr
		if (exp instanceof Num) {
			return new IntValue(((Num) exp).getValue());
		} else if (exp instanceof Id) {
			return table.lookup(((Id)exp).getId());
		} else if (exp instanceof Call) {
			return table.lookup(((Call)exp).getId());
		} else if (exp instanceof True) {
			return new BoolValue(true);
		} else if (exp instanceof False) {
			return new BoolValue(false);
		} else {
			System.err.println("ERROR");
			return null;
		}
		
	}
//	public Value interpret(Sequence sequence, SymbolTable table) {	//Sequence
//		Value lastValue = null;
//		for (Stmt s : sequence.getBody()){
//			lastValue = interpret(s, table);
//		}
//		if (lastValue != null) {
//			return lastValue;
//		} else {
//			return new VoidValue();
//		}
//	}
//	public Value interpret(IfThen ifThen, SymbolTable table) {	//IfThen
//		BoolValue test = (BoolValue) interpret(ifThen.gettest(), table);
//		if (test.boolValue){
//			return interpret(ifThen.getTrueClause(), table);
//		} else {
//			return new VoidValue();
//		}
//	} 
//	public Value interpret(IfThenElse ifThenElse, SymbolTable table) {	//IfThenElse
//		BoolValue test = (BoolValue) interpret(ifThenElse.getTest(), table);
//		if (test.boolValue){
//			return interpret(ifThenElse.getTrueClause(), table);
//		} else {
//			return interpret(ifThenElse.getFalseClause(), table);
//		}
//	}
	
//	public Value interpret(While whileS, SymbolTable table) {	//while
//		BoolValue test = (BoolValue) interpret(whileS.getTest(), table);
//		while (test.boolValue) {
//			interpret(whileS.getBody(), table);
//			test = (BoolValue)interpret(whileS.getTest(), table);
//		}
//		return new VoidValue();
//	}
	
//	public Value interpret(Input input, SymbolTable table) {	//input
//		System.out.println(input.getMessage());
//		java.util.Scanner scanner = new java.util.Scanner(System.in);
//		scanner.nextLine();
//		scanner.close();
//		return new VoidValue();
//	}
	
//	public Value interpret(Input2 input2, SymbolTable table) {
//		Value lhs = table.lookup(input2.getId());
//		System.out.println(input2.getMessage() + " ");
//		java.util.Scanner scanner = new java.util.Scanner(System.in);
//		int input = Integer.parseInt(scanner.nextLine());
//		((IntValue)lhs).intValue = input;
//		scanner.close();
//		return new VoidValue();
//	}
	
//	public Value interpret(Print print, SymbolTable table) {
//		for (Item item : print.getItems()) {
//			switch (item.getClass().toString()) {
//			case "ExprItem":
//				Value value = interpret(((ExprItem)item).getExpr(), table);
//				System.out.println(((IntValue)value).intValue);
//				break;
//			case "StringItem":
//				System.out.println(((StringItem)item).getMessage());
//				break;
//			}
//		}
//		System.out.println();
//		return new VoidValue();
//	}
	
//	public Value interpret(ExprStmt exprStmt, SymbolTable table) {	
//		return interpret(exprStmt.getExpr(), table);
//	}
	
//	public Value interpret(BinOp binOp, SymbolTable table) {
//		Value lhs = interpret(binOp.getLeft(), table);
//		switch (binOp.getOp().getClass().getName()) {
//		case "op":
//			if (((BoolValue)lhs).boolValue) {
//				return interpret(binOp.getRight(), table);
//			} else {
//				return lhs;
//			}
//		case "or":
//			if (((BoolValue)lhs).boolValue) {
//				return lhs;
//			} else {
//				return interpret(binOp.getRight(), table);
//			}
//		case "EQ": case "NE": case "LE": case "LT" : case "GE": case "GT":
//			Value rhs = interpret(binOp.getRight(), table);
//			return new BoolValue(((IntValue)lhs).intValue, binOp.getOp(), (((IntValue)rhs).intValue));
//		case "Plus": case "Minus": case "Star": case "Div": case "Mod":
//			Value rhs2 = interpret(binOp.getRight(), table);
//			return new IntValue(((IntValue)lhs).intValue, binOp.getOp(), (((IntValue)rhs2).intValue));
//		default:
//			System.err.println("Error");
//			return null;
//		}
//	}
//	public Value interpret(UnOp unOp, SymbolTable table) {	
//		Value value = interpret(unOp.getExpr(), table);
//		switch(unOp.getOp().getClass().getName()) {
//		case "Neg":
//			return new IntValue(-(((IntValue)value).intValue));
//		case "Not":
//			return new BoolValue(!(((BoolValue)value).boolValue));
//		default:
//			System.err.println("Erro");
//			return null;	
//		}
//	}
	
//	public Value interpret(Num num, SymbolTable table) {
//		return new IntValue(num.getValue());
//	}
	
//	public Value interpret(True true1, SymbolTable table) {
//		return new BoolValue(true);
//	}
//	public Value interpret(False false1, SymbolTable table) {
//		return new BoolValue(false);
//	}
	
	
	
}
