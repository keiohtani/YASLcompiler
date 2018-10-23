package csc426;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ast.Program;

/**
 * Main class for Project 1 -- Scanner for a Subset of YASL (Fall 2015). Scans
 * tokens from standard input and prints the token stream to standard output.
 * 
 * @author bhoward
 * @author keiohtani
 * 
 */
public class Project1 {
	public static void main(String[] args) throws IOException {
		try
		{
//			Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
			Scanner scanner = new Scanner(new FileReader("TestCases/test01.in"));
			Parser parser = new Parser(scanner);
			Program program = parser.parseProgram();
//			Token token;
//			do {
//				token = scanner.next();
//				//System.out.println(token);
//				parser.parseProgram();
//			} while (token.type != TokenType.EOF);
			program.display("");
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File is not found.");
		}
	}
}
