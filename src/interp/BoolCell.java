package interp;

public class BoolCell extends BoolValue {
	public BoolCell(boolean b) {
		super(b);
	}	
	public void set(boolean x) {
		super.boolValue = x;
	}
	public boolean get() {
		return super.boolValue;
	}
}
