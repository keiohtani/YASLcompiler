package interp;

import java.util.ArrayList;

import ast.Block;
import ast.Param;
import ast.Type;

public class FunValue extends Value {
	
	public ArrayList<Param> params;
	public Type typ; 
	public Block block;
	
	public FunValue(ArrayList<Param> params, Type typ, Block block) {
		this.params = params;
		this.typ = typ;
		this.block = block;
	}

}
