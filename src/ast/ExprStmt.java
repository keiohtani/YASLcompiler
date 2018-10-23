package ast;

public class ExprStmt extends Stmt {
	Expr expr;
	public ExprStmt(Expr expr) {
		super();
		this.expr = expr;
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
		System.out.println(indent + "ExperStmt");
		expr.display(indent + "  ");
	}

}
