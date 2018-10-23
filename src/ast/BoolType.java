package ast;

public class BoolType extends Type {

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + this.getClass().getSimpleName());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Bool";
	}
}
