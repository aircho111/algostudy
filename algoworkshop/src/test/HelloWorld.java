package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloWorld
{
	public static void main(String[] args) throws IOException {
		System.out.println("Hello World!");
		
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
		String userInput = br.readLine();
		
		System.out.println("user input :"+userInput);
	}
}