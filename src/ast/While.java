package ast;

public class While extends Stmt {
	Expr test;
	Stmt body;
	
	public While(Expr test, Stmt body) {
		this.test = test;
		this.body = body;
	}

	public Expr getTest() {
		return test;
	}

	public void setTest(Expr test) {
		this.test = test;
	}

	public Stmt getBody() {
		return body;
	}

	public void setBody(Stmt body) {
		this.body = body;
	}

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "While");
		test.display(indent + "  ");
		body.display(indent + "  ");
	}

}
