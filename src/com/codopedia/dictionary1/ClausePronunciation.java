package com.codopedia.dictionary.pronunciation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;

public class ClausePronunciation {
	String pronunciationClause;

	public ClausePronunciation() {
		pronunciationClause = "";
	}// constructor ends here.

	private String getPronunciationClause() {
		return pronunciationClause;
	}

	public void setPronunciationClause(String pronunciationClause) {
		this.pronunciationClause = pronunciationClause;
	}

	public void createPronunciationForClause(String clause, IWiktionaryEdition wkt) {
		List<Pronunciation> clausePronunciationList = new ArrayList<>();
		clausePronunciationList = makeClausePronunciationList(clause, wkt);
		List<Stack<String>> listOfStacks = giveListOfStacks(clausePronunciationList);
		int numSentences = getSizeOfStackWithMaxPronunciations(listOfStacks);
		String allLines = "";
		for (int i = 0; i < numSentences; i++) {
			String ithLine = printOneLineFromStacks(listOfStacks);
			allLines += ithLine + "\n";
		}
		this.setPronunciationClause(allLines);
	}// method createPronunciationForClause ends here.

	private List<Pronunciation> makeClausePronunciationList(String clause, IWiktionaryEdition wkt) {
		List<Pronunciation> clausePronunciationList = new ArrayList<>();
		if (clause != null && !clause.isEmpty() && clause.contains(" ")) {
			String[] wordsToSearch = clause.trim().split(" ");
			for (String word : wordsToSearch) {
				if (word != null && !word.isEmpty()) {
					IWiktionaryPage page = wkt.getPageForWord(word);
					List<IWiktionaryEntry> entries = null;
					if (page != null) {
						entries = page.getEntries();// never null
					}
					IWiktionaryEntry entry = null;
					if (entries != null && !entries.isEmpty()) {
						entry = entries.get(0);
					}
					if (entry != null) {
						// Calling constructor of class Pronunciation to
						// create object p.
						Pronunciation p = new Pronunciation();
						p.createPronunciationSingleWord(entry);
						clausePronunciationList.add(p);
					}
				}
			}
		}
		return clausePronunciationList;
	}// method makeClausePronunciationList ends here.

	private String printOneLineFromStacks(List<Stack<String>> listOfStacks) {
		String ithLine = "";
		for (Stack<String> stack : listOfStacks) {
			if (!stack.isEmpty()) {
				String nthPronun = "";
				if (stack.size() >= 2) {
					String temp = stack.pop();
					nthPronun += temp;
				} else if (stack.size() == 1) {
					String temp = stack.peek();
					nthPronun += temp;
				}
				ithLine += " " + nthPronun.trim();
			}
		}
		return ithLine.trim();
	}// method printOneLineFromStacks ends here.

	private int getSizeOfStackWithMaxPronunciations(List<Stack<String>> listOfStacks) {
		int size = 0;
		for (Stack<String> stack : listOfStacks) {
			size = stack.size() > size ? stack.size() : size;
		}
		return size;
	}// method getSizeOfStackWithMaxPronunciations ends here.

	private List<Stack<String>> giveListOfStacks(List<Pronunciation> pronunciationObjList) {
		List<Stack<String>> listOfStacks = new ArrayList<>();
		for (Pronunciation pronunciationObj : pronunciationObjList) {
			// Make a new stack object.
			Stack<String> thisStack = new Stack<String>();
			/*
			 * getPhoneticTranscription will return a list of pronunciations for
			 * pronunciationObj
			 */
			List<String> pForThisObjList = pronunciationObj.getPhoneticTranscription();
			pForThisObjList.forEach(p -> thisStack.push(new String(p)));
			listOfStacks.add(thisStack);
		}
		return listOfStacks;
	}// method giveListOfStacks ends here.

	public String toString(){
		if(this.getPronunciationClause() != null && !this.getPronunciationClause().isEmpty()){
			return this.getPronunciationClause();
		}
		else{
			return "????";
		}
	}
	
	public void printClausePronunciation(){
		if(this.toString().contains("????")){
			System.out.println("No pronunciation found!");
		}else{
			System.out.println(this.toString());
		}
	}
	
}// class ClausePronunciation ends here.
