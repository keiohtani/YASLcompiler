package ast;

public class ExprItem extends Item {
	Expr expr;
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "ExprItem");
		expr.display(indent + "  ");
	}
	
	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}

	public ExprItem(Expr expr) {
		super();
		this.expr = expr;
	}

}
