package csc426;

/**
 * Enumeration of the different kinds of tokens in the YASL subset.
 * 
 * @author bhoward
 * @author keiohtani
 */
public enum TokenType {
	NUM, // numeric literal
	SEMI, // semicolon (;)
	PLUS, // plus operator (+)
	MINUS, // minus operator (-)
	STAR, // times operator (*)
	EOF, // end-of-file
	
	// TODO add more token types here
	ID, // Identifier
	PROGRAM, //Program
	VAL, // Variable
	// New
	STRING,
	VAR,
	INT,
	BOOL,
	VOID,
	FUN,
	LET,
	IF,
	THEN,
	ELSE,
	WHILE,
	DO,
	INPUT,
	AND,
	OR,
	NOT,
	TRUE,
	FALSE,
	// New end
	PRINT,
	BEGIN,
	END,
	DIV, // division operator (\)
	MOD, // modelater (%)
	ASSIGN,	// assignment (=)
	PERIOD,
	// New
	EQUAL, 
	NOTEQUAL, 
	LESSEQUAL, 
	GREATEREQUAL, 
	LESS,
	GREATER,
	COLON, LPAREN, RPAREN, COMMA,
	// New end
	
}
