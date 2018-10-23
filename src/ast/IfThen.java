package ast;

public class IfThen extends Stmt {
	Expr test;
	Stmt trueClause;
	
	public IfThen(Expr test, Stmt trueClause) {
		this.test = test;
		this.trueClause = trueClause;
	}

	public Expr gettest() {
		return test;
	}

	public void settest(Expr test) {
		this.test = test;
	}

	public Stmt getTrueClause() {
		return trueClause;
	}

	public void setTrueClause(Stmt trueClause) {
		this.trueClause = trueClause;
	}

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "IfThen");
		test.display(indent + "  ");
		trueClause.display(indent + "  ");
	}

}
