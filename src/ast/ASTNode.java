
package ast;

import interp.SymbolTable;
import interp.Value;

public abstract class ASTNode {
	public abstract void display(String indent);
	public abstract Value interpret(SymbolTable table);
	
}