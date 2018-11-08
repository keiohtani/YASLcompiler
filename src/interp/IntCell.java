package interp;

public class IntCell extends IntValue {
	public IntCell(int n) {
		super(n);
	}	
	public void set(int x) {
		super.intValue = x;
	}
	public int get() {
		return super.intValue;
	}
}
