package csc426;

import java.util.ArrayList;

import ast.*;

public class Parser {
	public Parser(Scanner scanner) {
		this.scanner = scanner;
		this.current = scanner.next();
	}

	/**
	 * If the current token matches the given type, return it and advance to the
	 * next token; otherwise print an error message and exit. Harsh.
	 * 
	 * @param type
	 */
	private Token match(TokenType type) {
		if (current.type == type) {
			Token result = current;
			current = scanner.next();
			return result;
		} else {
			System.err.println("Expected " + type + " but found " + current);
			System.exit(1);
			return null; // never reaches this
		}
	}
	
	/**
	 * Check whether the current token's type is one of a given ArrayList of type.
	 * 
	 * @param types a variable number of arguments ArrayListing the expected token types
	 * @return true if the current token type is in the given ArrayList
	 */
	private boolean check(TokenType... types) {
		for (TokenType type : types) {
			if (current.type == type) {
				return true;
			}
		}
		return false;
	}

	public Program parseProgram() {
		match(TokenType.PROGRAM);
		String name = match(TokenType.ID).lexeme;
		match(TokenType.SEMI);
		Block block = parseBlock();
		match(TokenType.PERIOD);
		return new Program(name, block);
	}
	private Block parseBlock() {
		ArrayList<ValDecl> vals = parseValDecls();
		ArrayList<VarDecl> vars = parseVarDecls();
		ArrayList<FunDecl> funs = parseFunDecls();
		Stmt stmt = parseStmt();
		return new Block(vals, vars, funs, stmt);
	}

	private ArrayList<ValDecl> parseValDecls() {
		if (check(TokenType.VAL)) {
			ValDecl val = parseValDecl();
			ArrayList<ValDecl> vals = parseValDecls();
			vals.add(0, val);
			return vals; 
		} else {
			return new ArrayList<ValDecl>(); // epsilon
		}
	}

	private ValDecl parseValDecl() {
		match(TokenType.VAL);
		String id = match(TokenType.ID).lexeme;
		match(TokenType.ASSIGN);
		int sign = parseSign();
		int value = Integer.parseInt(match(TokenType.NUM).lexeme);
		match(TokenType.SEMI);
		return new ValDecl(id, value * sign);
	}
	private int parseSign() {
		if (check(TokenType.MINUS)) {
			match(TokenType.MINUS);
			return -1;
		} else {
			return 1; // epsilon
		}
	}
	private ArrayList<VarDecl> parseVarDecls() {
		if (check(TokenType.VAR)) {
			VarDecl var = parseVarDecl();
			ArrayList<VarDecl> vars = parseVarDecls();
			vars.add(0, var);
			return vars;			
		} else {
			return new ArrayList<VarDecl>(); // epsilon
		}
	}
	private VarDecl parseVarDecl() {
		match(TokenType.VAR);
		String id = match(TokenType.ID).lexeme;
		match(TokenType.COLON);
		Type typ = parseType();
		match(TokenType.SEMI);
		return new VarDecl(id, typ);
	}
	private Type parseType() {
		if (check(TokenType.INT)) {
			match(TokenType.INT);
			return new IntType();
		} else if (check(TokenType.BOOL)){
			match(TokenType.BOOL);
			return new BoolType();
		} else {
			match(TokenType.VOID);
			return new VoidType();
		}	
	}
	private ArrayList<FunDecl> parseFunDecls() {
		if (check(TokenType.FUN)){
			FunDecl fun = parseFunDecl();
			ArrayList<FunDecl> funs = parseFunDecls();
			funs.add(0, fun);
			return funs;
		} else {
			return new ArrayList<FunDecl>(); // epsilon
		}
	}
	private FunDecl parseFunDecl() {
		match(TokenType.FUN);
		String id = match(TokenType.ID).lexeme;
		match(TokenType.LPAREN);
		ArrayList<Param> params = parseParamList();
		match(TokenType.RPAREN);
		match(TokenType.COLON);
		Type typ = parseType();
		match(TokenType.SEMI);
		Block block = parseBlock();
		match(TokenType.SEMI);
		return new FunDecl(id, typ, params, block);
	}

	private ArrayList<Param> parseParamList() {
		if (check(TokenType.ID)) {
			ArrayList<Param> params = parseParams();
			return params;
		} else {
			return new ArrayList<Param>(); // epsilon
		}
	}

	private ArrayList<Param> parseParams() {
		Param param = parseParam();
		ArrayList<Param> params = parseParamRest();
		params.add(0, param);
		return params;
	}
	private Param parseParam() {
		String id = match(TokenType.ID).lexeme;
		match(TokenType.COLON);
		Type typ = parseType();
		return new Param(id, typ);
	}
	private ArrayList<Param> parseParamRest() {
		if (check(TokenType.COMMA)) {
			match(TokenType.COMMA);
			return parseParams();
		} else {
			return new ArrayList<Param>(); // epsilon
		}
	}
	private ArrayList<Stmt> parseStmts() {
		Stmt stmt = parseStmt();
		ArrayList<Stmt> stmts = parseStmtRest();
		if (stmts == null) {
			stmts = new ArrayList<Stmt>();
			stmts.add(0, stmt);
		} else {
			stmts.add(0, stmt);
		}
		return stmts;
	}
	private ArrayList<Stmt> parseStmtRest() {
		if (check(TokenType.SEMI)) {
			match(TokenType.SEMI);
			return parseStmts(); 
		} else {
			return null; // epsilon
		}
	}
	private Stmt parseStmt() {
		if (check(TokenType.LET)) {
			match(TokenType.LET);
			String id = match(TokenType.ID).lexeme;
			match(TokenType.ASSIGN);
			Expr expr = parseExpr();
			return new Assign(id, expr);
		} else if (check(TokenType.BEGIN)) {
			match(TokenType.BEGIN);
			ArrayList<Stmt> stmtList = parseStmtList();
			match(TokenType.END);
			return new Sequence(stmtList);
		} else if (check(TokenType.IF)) {
			match(TokenType.IF);
			Expr test = parseExpr();
			match(TokenType.THEN);
			Stmt trueClause = parseStmt();
			Stmt falseClause = parseIfRest();
			if (falseClause == null) {
				return new IfThen(test, trueClause);
			} else {
				return new IfThenElse(test, trueClause, falseClause);
			}
		} else if (check(TokenType.WHILE)) {
			match(TokenType.WHILE);
			Expr test = parseExpr();
			match(TokenType.DO);
			Stmt body = parseStmt();
			return new While(test, body);
		} else if (check(TokenType.INPUT)) {
			match(TokenType.INPUT);
			String message = match(TokenType.STRING).lexeme; 
			String id = parseInputRest();
			if (id == null) {
				return new Input(message);
			} else {
				return new Input2(message, id);
			}
		} else if (check(TokenType.PRINT)) {
			match(TokenType.PRINT);
			ArrayList<Item> items = parseItems();
			return new Print(items);
		} else {
			Expr expr = parseExpr();
			return new ExprStmt(expr);
		}
	}
	private Stmt parseIfRest() {
		if (check(TokenType.ELSE)) {
			match(TokenType.ELSE);
			Stmt stmt = parseStmt();
			return stmt;
		} else {
			return null; // epsilon
		}
	}

	private String parseInputRest() {
		if (check(TokenType.COMMA)) {
			match(TokenType.COMMA);
			return match(TokenType.ID).lexeme;
		} else {
			return null; // epsilon
		}
	}

	private ArrayList<Item> parseItems() {
		Item item = parseItem();
		ArrayList<Item> items = parseItemsRest();
		items.add(0, item);
		return items;
	}
	private Item parseItem() {
		if (check(TokenType.NUM, TokenType.ID, TokenType.TRUE, TokenType.FALSE, TokenType.MINUS, TokenType.NOT, TokenType.LPAREN)) {
			Expr expr = parseExpr();
			return new ExprItem(expr);
		} else {
			Token token = match(TokenType.STRING);
			return new StringItem(token.lexeme);
		}
	}
	private ArrayList<Item> parseItemsRest() {
		if (check(TokenType.COMMA)) {
			match(TokenType.COMMA);
			return parseItems();
			
		} else {
			return new ArrayList<Item>(); // epsilon
		}
	}

	private ArrayList<Stmt> parseStmtList() {
		if (check(TokenType.LET, TokenType.BEGIN, TokenType.IF, TokenType.WHILE, TokenType.INPUT, TokenType.PRINT, TokenType.NUM, TokenType.STAR, TokenType.ID, TokenType.TRUE, TokenType.FALSE, TokenType.MINUS, TokenType.NOT, TokenType.LPAREN)) {
			return parseStmts();
		} else {
			return new ArrayList<Stmt>(); // epsilon
		}
	}
	private Expr parseExpr() {
		Expr left = parseSimpleExpr();
		Expr expr = parseExprRest(left);
		return expr;
	}

	private Expr parseExprRest(Expr left) {
		if (check(TokenType.EQUAL, TokenType.NOTEQUAL, TokenType.LESSEQUAL, TokenType.LESS, TokenType.GREATER, TokenType.GREATEREQUAL)) {
			Op2 op = parseRelOp();
			Expr right = parseSimpleExpr();
			return new BinOp(left, op, right);
		} else {
			return left;
		}
	}

	private Op2 parseRelOp() {
		if (check(TokenType.EQUAL)) {
			match(TokenType.EQUAL);
			return Op2.EQ;
		} else if (check(TokenType.NOTEQUAL)) {
			match(TokenType.NOTEQUAL);
			return Op2.NE;
		} else if (check(TokenType.LESSEQUAL)) {
			match(TokenType.LESSEQUAL);
			return Op2.LE;
		} else if (check(TokenType.GREATEREQUAL)) {
			match(TokenType.GREATEREQUAL);
			return Op2.GE;
		} else if (check(TokenType.GREATER)) {
			match(TokenType.GREATER);
			return Op2.GT;
		} else {
			match(TokenType.LESS);
			return Op2.LE;
		}
	}
	private Expr parseSimpleExpr() {
		Expr left = parseTerm();
		Expr expr = parseSimpleExprRest(left);
		return expr;
	}
	private Expr parseSimpleExprRest(Expr left) {
		if (check(TokenType.PLUS, TokenType.MINUS, TokenType.OR)) {
			Op2 op = parseAddOp();
			Expr right = parseTerm();
			return parseSimpleExprRest(new BinOp(left, op, right));
		} else {
			return left;// epsilon
		}
	}
	private Expr parseTerm() {
		Expr result = parseFactor();
		while (check(TokenType.STAR, TokenType.DIV, TokenType.MOD, TokenType.AND)) {
			Op2 op = parseMulOp();
			Expr t = parseFactor();
			result = new BinOp(result, op, t);
		}
		return result;
//		Expr left = parseFactor();
//		Expr expr = parseTermRest(left);
//		return expr;
	}
	private Expr parseTermRest(Expr left) {
		if (check(TokenType.STAR, TokenType.DIV, TokenType.MOD, TokenType.AND)) {
			Op2 op = parseMulOp();
			Expr right = parseTerm();
			return parseTermRest(new BinOp(left, op, right));
		} else {
			return left; // epsilon
		}
	}
	private Op2 parseMulOp() {
		if (check(TokenType.STAR)) {
			match(TokenType.STAR);
			return Op2.Times;
		} else if (check(TokenType.DIV)) {
			match(TokenType.DIV);
			return Op2.Div;
		} else if (check(TokenType.MOD)) {
			match(TokenType.MOD);
			return Op2.Mod;
		} else {
			match(TokenType.AND);
			return Op2.And;
		}
	}

	private Expr parseFactor() {
		if (check(TokenType.NUM)) {
			int value = Integer.parseInt(match(TokenType.NUM).lexeme);
			return new Num(value);
		} else if (check(TokenType.ID)) {
			String id = match(TokenType.ID).lexeme;
			ArrayList<Expr> args = parseFactorRest();
			if (args == null) {
				return new Id(id);
			} else {
				return new Call(id, args);
			}
		} else if (check(TokenType.TRUE)) {
			match(TokenType.TRUE);
			return new True();
		} else if (check(TokenType.FALSE)) {
			match(TokenType.FALSE);
			return new False();
		} else if (check(TokenType.MINUS, TokenType.NOT)) {
			Op1 op = parseUnOp();
			Expr factor = parseFactor();
			return new UnOp(op, factor);
		} else {
			match(TokenType.LPAREN);
			Expr expr = parseExpr();
			match(TokenType.RPAREN);
			return expr;
		}
	}
	private Op1 parseUnOp() {
		if (check(TokenType.MINUS)) {
			match(TokenType.MINUS);
			return Op1.Neg;
		} else {
			match(TokenType.NOT);
			return Op1.Not;
		}
	}

	private ArrayList<Expr> parseFactorRest() {
		if (check(TokenType.LPAREN)) {
			match(TokenType.LPAREN);
			ArrayList<Expr> args = parseArgList();
			match(TokenType.RPAREN);
			return args;
		} else {
			return null; // epsilon
		}
	}
	
	private ArrayList<Expr> parseArgList() {
		if (check(TokenType.NUM, TokenType.ID, TokenType.TRUE, TokenType.FALSE, TokenType.MINUS, TokenType.NOT)) {
			return parseArgs();
		} else {
			return new ArrayList<Expr>();
		}
	}

	private ArrayList<Expr> parseArgs() {
		Expr expr = parseExpr();
		ArrayList<Expr> args = parseArgsRest();
		args.add(0, expr);
		return args;
	}

	private ArrayList<Expr> parseArgsRest() {
		if (check(TokenType.COMMA)) {
			match(TokenType.COMMA);
			return parseArgs();
		} else {
			return new ArrayList<Expr>();
		}
	}

	private Op2 parseAddOp() {
		if (check(TokenType.PLUS)) {
			if (match(TokenType.PLUS) != null)
				return Op2.Plus;
		} else if (check(TokenType.MINUS)) {
			if (match(TokenType.MINUS) != null)
				return Op2.Minus;
		} else {
			if (match(TokenType.OR) != null)
				return Op2.Or;
		}
		return null;
	}

	private Scanner scanner;
	private Token current;
}