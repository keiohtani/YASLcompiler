package csc426;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ast.Program;
import interp.SymbolTable;

/**
 * Main class for Project 1 -- Scanner for a Subset of YASL (Fall 2015). Scans
 * tokens from standard input and prints the token stream to standard output.
 * 
 * @author bhoward
 * @author keiohtani
 * 
 */
public class Project1 {
	public static void main(String fileName) throws IOException {
		try
		{
//			Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
			Scanner scanner = new Scanner(new FileReader(fileName));
			Parser parser = new Parser(scanner);
			Program program = parser.parseProgram();
			Token token;
			do {
				token = scanner.next();
				System.out.println(token);
			} while (token.type != TokenType.EOF);
//			program.display("");
			program.interpret(new SymbolTable());
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File is not found.");
		}
	}
}
