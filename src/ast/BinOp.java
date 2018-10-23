package ast;

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

}
