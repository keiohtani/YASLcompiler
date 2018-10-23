package ast;

public class UnOp extends Expr {
	Op1 op;
	Expr expr;
	
	public UnOp(Op1 op, Expr expr) {
		this.op = op;
		this.expr = expr;
	}

	public Op1 getOp() {
		return op;
	}

	public void setOp(Op1 op) {
		this.op = op;
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "UnOp " + op);
		expr.display(indent + "  ");
	}

}
