package ast;

public class IfThenElse extends Stmt {
	Expr test;
	Stmt trueClause;
	Stmt falseClause;
	
	public IfThenElse(Expr test, Stmt trueClause, Stmt falseClause) {
		this.test = test;
		this.trueClause = trueClause;
		this.falseClause = falseClause;
	}

	public Expr getTest() {
		return test;
	}

	public void setTest(Expr test) {
		this.test = test;
	}

	public Stmt getTrueClause() {
		return trueClause;
	}

	public void setTrueClause(Stmt trueClause) {
		this.trueClause = trueClause;
	}

	public Stmt getFalseClause() {
		return falseClause;
	}

	public void setFalseClause(Stmt falseClause) {
		this.falseClause = falseClause;
	}

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "IfThenElse");
		test.display(indent + "  ");
		trueClause.display(indent + "  ");
		falseClause.display(indent + "  ");
	}

}
