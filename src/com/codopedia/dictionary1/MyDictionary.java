package com.codopedia.dictionary1;

import java.util.HashMap;
import java.util.Scanner;

public class MyDictionary {

	public static void main(String[] args) {
		HashMap<String, String> dictionary = new HashMap<String, String>(10);
		populateDictionary(dictionary);
		Scanner keyboard = new Scanner(System.in);

		String input = "q";
		do {
			input = getInput(keyboard);
			checkInputInDictionary(input, dictionary);
		} while (!input.equalsIgnoreCase("q"));
		keyboard.close();
	}// method main ends here.

	private static void checkInputInDictionary(String input, HashMap<String, String> dictionary) {
		input = input.toLowerCase().trim();

		if("q".equals(input)){
			System.out.println("Bye bye");
		}
//			else if(dictionary.containsKey(input)){
//			Word WordObject = new Word(input);
//			WordObject.setDefinition(dictionary.get(input));
//			
//			System.out.println(WordObject.toString());
//			
//			System.out.println("\n\n\n");
//			System.out.println("Ready again!!!!!!!!");
//			System.out.println();
//		}
			else if(!dictionary.containsKey(input)){
				Word WordObject = new Word(input);
				SearchWordInWikitionary swiw = new SearchWordInWikitionary();
				swiw.searchInWiktionary(input);
				//String definition = swiw.searchInWiktionary(input);
				//WordObject.setDefinition(definition);
				//System.out.println(WordObject.toString());
				
				System.out.println("\n\n\n\n");
				System.out.println("Ready again!!!!!!!!");
				System.out.println();

		}else{
			System.err.println("Word " + input + " was not found.");
		}
		
	}

	private static String getInput(Scanner keyboard) {
		System.out.println(
				"To quit press q on you keyboard.\n\n Please enter the word you are looking for in the dictionary.");
		String input = keyboard.nextLine().toLowerCase().trim().replace(" ", "");// removing
																					// the
																					// white
																					// spaces.
		return input;
	}

	private static void populateDictionary(HashMap<String, String> dictionary) {
		dictionary.put("newbie", "A person in the begining stages of something");
		dictionary.put("fat", "having too much flabby tissue");
		dictionary.put("browsing",
				"To read something superficially by selecting passages at random. To look for something on the Internet.");
		dictionary.put("surfing",
				"The activity of casually looking at something that offers numerous options, such as the Internet or television. Surfboarding");
		dictionary.put("cloud-nine",
				"A state of elation or great happiness: She was on cloud nine after winning the marathon.");
		dictionary.put("stötta", "Support. To inspire with hope, courage, or confidence.");
		dictionary.put("حوصلہ",
				"Courage. The state or quality of mind or spirit that enables one to face danger, fear, or vicissitudes with self-possession, confidence, and resolution; bravery.");
		dictionary.put("incoraggiare", "Encourage some one to do something.");

	}
}// class MyDictionary ends here.
