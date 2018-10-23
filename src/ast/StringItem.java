package ast;

public class StringItem extends Item {
	String message;
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "StringItem \"" + message + "\"");
	}
	public StringItem(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
