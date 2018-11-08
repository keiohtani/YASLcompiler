package ast;

import interp.BoolValue;
import interp.IntCell;
import interp.IntValue;
import interp.SymbolTable;
import interp.Value;

public class BinOp extends Expr {
	Expr left;
	Op2 op;
	Expr right;
	public BinOp(Expr left, Op2 op, Expr right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}
	public Expr getLeft() {
		return left;
	}
	public void setLeft(Expr left) {
		this.left = left;
	}
	public Op2 getOp() {
		return op;
	}
	public void setOp(Op2 op) {
		this.op = op;
	}
	public Expr getRight() {
		return right;
	}
	public void setRight(Expr right) {
		this.right = right;
	}
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "BinOp " + op);
		left.display(indent + "  ");
		right.display(indent + "  ");
	}
	public Value interpret(SymbolTable table) {
		Value lhs = left.interpret(table);
		switch (op.name()) {
		case "And":
			if (((BoolValue)lhs).get()) {
				return right.interpret(table);
			} else {
				return lhs;
			}
		case "Or":
			if (((BoolValue)lhs).get()) {
				return lhs;
			} else {
				return right.interpret(table);
			}
		case "EQ": case "NE": case "LE": case "LT" : case "GE": case "GT":
			Value rhs = right.interpret(table);
			return new BoolValue(((IntValue)lhs).get(), op, (((IntValue)rhs).get()));
		case "Plus": case "Minus": case "Times": case "Div": case "Mod":
			Value rhs2 = right.interpret(table);
			return new IntValue(((IntValue)lhs).get(), op, (((IntValue)rhs2).get()));
		default:
			System.err.println("Error in BinOp");
			return null;
		}
	}
}
