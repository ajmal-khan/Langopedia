package com.codopedia.dictionary1;

import java.util.Scanner;

public class MyDictionary {

	public static void main(String[] args) {
		//HashMap<String, String> dictionary = new HashMap<String, String>(10);
		//populateDictionary(dictionary);
		Scanner keyboard = new Scanner(System.in);
		String input = "q";
		do {
			input = getInput(keyboard);
			checkInputInWiktionary(input);
		} while (!input.equalsIgnoreCase("q"));
		keyboard.close();
	}// method main ends here.

	private static void checkInputInWiktionary(String input) {
		if ("q".equals(input)) {
			System.out.println("Bye bye");
		} else {
			SearchWordInWikitionary swiw = new SearchWordInWikitionary();
			PresentedText output = swiw.searchInWiktionary(input);
			System.out.println("\n\n\n\n");
			output.print();
			System.out.println("\n\nReady again!!!!!!!!\n\n");
		}
	}//method checkInputInWiktionary ends here.

	private static String getInput(Scanner keyboard) {
		System.out.println(
				"To quit press q on you keyboard.\n\n Please enter the word you are looking for in the dictionary.");
		String input = keyboard.nextLine().trim();
		return input;
	}

}// class MyDictionary ends here.
