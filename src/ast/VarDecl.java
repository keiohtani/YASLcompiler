package ast;

public class VarDecl extends ASTNode {
	
	private String id;
	private Type typ;
	
	public VarDecl(String id, Type typ) {
		this.id = id;
		this.typ = typ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Type getTyp() {
		return typ;
	}

	public void setTyp(Type typ) {
		this.typ = typ;
	}

	@Override
	public void display(String indent) {
		System.out.println(indent + "Var " + id + " : " + typ);
	}

}
