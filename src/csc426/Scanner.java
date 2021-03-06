package csc426;

import java.io.IOException;
import java.io.Reader;

/**
 * A Lexical Analyzer for a subset of YASL. Uses a (Mealy) state machine to
 * extract the next available token from the input each time next() is called.
 * Input comes from a Reader, which will generally be a BufferedReader wrapped
 * around a FileReader or InputStreamReader (though for testing it may also be
 * simply a StringReader).
 * 
 * @author bhoward
 * @author keiohtani
 */
public class Scanner {
	/**
	 * Construct the Scanner ready to read tokens from the given Reader.
	 * 
	 * @param in
	 */
	public Scanner(Reader in) {
		source = new Source(in);
	}

	/**
	 * Extract the next available token. When the input is exhausted, it will
	 * return an EOF token on all future calls.
	 * 
	 * @return the next Token object
	 */
	public Token next() {
		int state = 0;
		StringBuilder lexeme = new StringBuilder();
		int startLine = source.line;
		int startColumn = source.column;
		while(true){
			//System.err.println("Loop");
			switch(state) {
			case 0: 
				if (source.atEOF)
					return new Token(source.line, source.column, TokenType.EOF, null);
				else if (source.current == '0' || source.current == ';' || 
						source.current == '+' ||
						source.current == '-' || source.current == '*' ||
						source.current == '.' || 
						source.current == ':' || source.current == ')' ||
						source.current == '(' || 
						source.current == ','){
					startLine = source.line;
					startColumn = source.column;
					state = 1;
				} else if (Character.isDigit(source.current)) {
					startLine = source.line;
					startColumn = source.column;
					lexeme.append(source.current);
					source.advance();
					state = 2;
				} else if (Character.isAlphabetic(source.current)){
					startLine = source.line;
					startColumn = source.column;
					lexeme.append(source.current);
					source.advance();
					state = 3;
				} else if (source.current == '=') {
					state = 10;
					source.advance();
				} else if (source.current == '/'){
					startLine = source.line;
					startColumn = source.column;
					source.advance();
					state = 4;
				} else if (Character.isWhitespace(source.current)){
					source.advance();
				} else if (source.current == '"') {
					startLine = source.line;
					startColumn = source.column;
					source.advance();
					state = 8;
				} else if (source.current == '<') {
					startLine = source.line;
					startColumn = source.column;
					source.advance();
					state = 11;
				} else if (source.current == '>') {
					startLine = source.line;
					startColumn = source.column;
					source.advance();
					state = 12;
				} else {
					startLine = source.line;
					startColumn = source.column;
					System.err.println("Illegal character at line " + startLine + ", column " + startColumn + ": " + source.current);
					source.advance();
				}
				break;
			case 1:
				switch(source.current){
				case '0':
					source.advance();
					return new Token(startLine, startColumn, TokenType.NUM, "0");
				case ';':
					source.advance();
					return new Token(startLine, startColumn, TokenType.SEMI, null);
				case '+':
					source.advance();
					return new Token(startLine, startColumn, TokenType.PLUS, null);
				case '-':
					source.advance();
					return new Token(startLine, startColumn, TokenType.MINUS, null);
				case '*':
					source.advance();
					return new Token(startLine, startColumn, TokenType.STAR, null);
				case '.':
					source.advance();
					return new Token(startLine, startColumn, TokenType.PERIOD, null);
				case ':':
					source.advance();
					return new Token(startLine, startColumn, TokenType.COLON, null);
				case ')':
					source.advance();
					return new Token(startLine, startColumn, TokenType.RPAREN, null);
				case '(':
					source.advance();
					return new Token(startLine, startColumn, TokenType.LPAREN, null);
				case ',':
					source.advance();
					return new Token(startLine, startColumn, TokenType.COMMA, null);
				}
				break;
			case 2:
				if (Character.isDigit(source.current)) {
					lexeme.append(source.current);
					source.advance();
				} else
					return new Token(startLine, startColumn, TokenType.NUM, lexeme.toString());
				break;
			case 3:
				if (Character.isDigit(source.current)||Character.isAlphabetic(source.current)) {
					lexeme.append(source.current);
					source.advance();
				} else {
					switch(lexeme.toString()){
					case "mod":
						return new Token(startLine, startColumn, TokenType.MOD, null);
					case "div":
						return new Token(startLine, startColumn, TokenType.DIV, null);
					case "program":
						return new Token(startLine, startColumn, TokenType.PROGRAM, null);
					case "val":
						return new Token(startLine, startColumn, TokenType.VAL, null);
					case "print":
						return new Token(startLine, startColumn, TokenType.PRINT, null);
					case "end":
						return new Token(startLine, startColumn, TokenType.END, null);
					case "begin":
						return new Token(startLine, startColumn, TokenType.BEGIN, null);
					case "var":
						return new Token(startLine, startColumn, TokenType.VAR, null);
					case "int":
						return new Token(startLine, startColumn, TokenType.INT, null);
					case "bool":
						return new Token(startLine, startColumn, TokenType.BOOL, null);
					case "void":
						return new Token(startLine, startColumn, TokenType.VOID, null);
					case "fun":
						return new Token(startLine, startColumn, TokenType.FUN, null);
					case "let":
						return new Token(startLine, startColumn, TokenType.LET, null);
					case "if":
						return new Token(startLine, startColumn, TokenType.IF, null);
					case "then":
						return new Token(startLine, startColumn, TokenType.THEN, null);
					case "else":
						return new Token(startLine, startColumn, TokenType.ELSE, null);
					case "while":
						return new Token(startLine, startColumn, TokenType.WHILE, null);
					case "do":
						return new Token(startLine, startColumn, TokenType.DO, null);
					case "input":
						return new Token(startLine, startColumn, TokenType.INPUT, null);
					case "and":
						return new Token(startLine, startColumn, TokenType.AND, null);
					case "or":
						return new Token(startLine, startColumn, TokenType.OR, null);
					case "not":
						return new Token(startLine, startColumn, TokenType.NOT, null);
					case "true":
						return new Token(startLine, startColumn, TokenType.TRUE, null);
					case "false":
						return new Token(startLine, startColumn, TokenType.FALSE, null);
					default:
						return new Token(startLine, startColumn, TokenType.ID, lexeme.toString());
					}
				}
				break;
			case 4:
				if(source.current == '/'){
					source.advance();
					state = 5;
				} else if(source.current == '*'){
					source.advance();
					state = 6;
				} else{
					System.err.println("'/' needs to be followed by either '*' or '/'. at line " + startLine + ", column" + startColumn);
					state = 0;
				}
				break;
			case 5:
				if (source.current != '\n'){
					source.advance();
				} else {
					source.advance();
					state = 0;
				}
				break;
			case 6:
				if (source.current == '*') {
					source.advance();
					state = 7;
				} else if (source.atEOF) {
					System.err.println("A comment at line " + startLine + ", column " + startColumn +  " needs to be closed before End of File.");
					return new Token(source.line, source.column, TokenType.EOF, null);
				} else {
					source.advance();
				}
				break;
			case 7:
				if (source.current == '/') {
					source.advance();
					state = 0;
				} else if (source.current == '*'){
					source.advance();
				} else if (source.atEOF){
					System.err.println("A comment at line " + startLine + ", column " + startColumn +  " needs to be closed before End of File.");
					return new Token(source.line, source.column, TokenType.EOF, null);
				} else {
					source.advance();
					state = 6;
				}
				break;
			case 8: // string literal state
				if (source.current == '"') {	// the second double quoat that can be the beginning of double quoat or the end of string literal is found.  
					source.advance();
					state = 9;
				} else if (source.current == '\n'){
					System.err.println("A string literal at line " + startLine + ", column " + startColumn +  " needs to be closed before a new line.");
					state = 0;
					lexeme = new StringBuilder();
					source.advance();
				} else if (source.atEOF){
					System.err.println("A string literal at line " + startLine + ", column " + startColumn +  " needs to be closed before End of File.");
					source.advance();
					return new Token(source.line, source.column, TokenType.EOF, null);
				} else {
					lexeme.append(source.current);
					source.advance();
				}
				break;
			case 9: // the state where the second double quoat that can be the beginning of double quoat or the end of string literal is found.  
				if (source.current == '"') {
					lexeme.append(source.current);
					state = 8;
					source.advance();
				} else {
					return new Token(startLine, startColumn, TokenType.STRING, lexeme.toString());
				}
				break;
			case 10: // =
				if (source.current == '=') {
					source.advance();
					return new Token(startLine, startColumn, TokenType.EQUAL, null);
				} else {
					return new Token(startLine, startColumn, TokenType.ASSIGN, null);
				}
			case 11: // <
				if (source.current == '=') {
					source.advance();
					return new Token(startLine, startColumn, TokenType.LESSEQUAL, null);
				} else if (source.current == '>') {
					source.advance();
					return new Token(startLine, startColumn, TokenType.NOTEQUAL, null);
				} else {
					return new Token(startLine, startColumn, TokenType.LESS, null);
				}
			case 12: // >
				if (source.current == '='){
					source.advance();
					return new Token(startLine, startColumn, TokenType.GREATEREQUAL, null);
				} else {
					return new Token(startLine, startColumn, TokenType.GREATER, null);
				}
			}
		}
		
			
		// TODO implement the state machine here:
		// - have a "state" variable start in the initial state
		// - repeatedly look at source.current (the current character),
		//   perform an appropriate action based on them, and assign
		//   a new state until the end of a token is seen
		// - call source.advance() on each state transition, until you
		//   see the first character after the token
		// - if source.atEOF is true, then return an EOF token:
		//     new Token(source.line, source.column, TokenType.EOF, null)
		// TODO replace this with an actual Token object
	}

	/**
	 * Close the underlying Reader.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		source.close();
	}

	private Source source;
}
