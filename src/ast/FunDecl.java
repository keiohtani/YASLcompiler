package ast;

import java.util.ArrayList;

public class FunDecl extends ASTNode {
	String id;
	Type typ;
	ArrayList<Param> params;
	Block block;
	
	public FunDecl(String id, Type typ, ArrayList<Param> params, Block block) {
		this.id = id;
		this.typ = typ;
		this.params = params;
		this.block = block;
	}
	

	@Override
	public void display(String indent) {
		System.out.println(indent + "Fun " + id + " : " + typ);
		for(Param param : params) {
			param.display(indent + "  ");
		}
		block.display(indent + "  ");
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


	public ArrayList<Param> getParams() {
		return params;
	}


	public void setParams(ArrayList<Param> params) {
		this.params = params;
	}


	public Block getBlock() {
		return block;
	}


	public void setBlock(Block block) {
		this.block = block;
	}

}
