package ast;

public class Input2 extends Stmt {
	String message;
	String id;
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Input2 \"" + message + "\", " + id);
	}
	public Input2(String message, String id) {
		super();
		this.message = message;
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
