package ast;

import interp.SymbolTable;
import interp.Value;

public class Program extends ASTNode {
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Block getBlock() {
		return block;
	}


	public void setBlock(Block block) {
		this.block = block;
	}


	private String name;
	private Block block;
	
	
	public Program(String name, Block block) {
		this.name = name;
		this.block = block;
	}


	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Program " + name);
		block.display(indent + "  ");
	}
	public Value interpret(SymbolTable table) {	//program
		table.enter(name);
		Value value = block.interpret(table);
		table.exit();
		return value;
	}
}
