package com.codopedia.dictionary.pronunciation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import de.tudarmstadt.ukp.jwktl.api.WiktionaryFormatter;

public class SyntacticFunction {
	protected String wordToSearch;
	protected String language;
	/*
	 * A word can have many grammatical functions. For example, a word can be a
	 * word as well as a noun.
	 */
	protected List<String> syntacticFunctions;

	// constructor with no argument.
	public SyntacticFunction() {
		this("");
	}
	public SyntacticFunction(String wordToSearch) {
		syntacticFunctions = new ArrayList<>();
		this.setWordToSearch(wordToSearch);
		setLanguage("English");
	}
	private String giveOne(Stack<String> stack) {
		if (!stack.isEmpty()) {
			if (stack.size() >= 2) {
				return (stack.pop() + ", ");
			} else if (stack.size() == 1) {
				return ("and " + stack.pop() + ".\n");
			}
		}
		return "????";
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getWordToSearch() {
		return wordToSearch;
	}

	public void setWordToSearch(String wordToSearch) {
		this.wordToSearch = wordToSearch;
	}

	public List<String> getSyntacticFunctions() {
		return syntacticFunctions;
	}

	private void setSyntacticFunctions(List<String> syntacticFunctions) {
		this.syntacticFunctions = syntacticFunctions;
	}

	public void findSyntacticFuntions(IWiktionaryEdition wkt) {

		IWiktionaryPage page = wkt.getPageForWord(this.getWordToSearch());
		List<IWiktionaryEntry> entriesOnThisPage = null;
		if (page != null) {
			entriesOnThisPage = page.getEntries();// never null
		}
		List<String> sfLocal = new ArrayList<String>();

		if (!entriesOnThisPage.isEmpty()) {
			for (IWiktionaryEntry entry : entriesOnThisPage) {
				String partOfSpeechForThisEntry = WiktionaryFormatter.instance().formatHeader(entry);
				String langToCheck = partOfSpeechForThisEntry.substring(partOfSpeechForThisEntry.indexOf("(") + 1,
						partOfSpeechForThisEntry.indexOf(","));
				// Checking syntactic functions only for the language passed as
				// parameter.
				syntacticFunctionsForThisLanguage(langToCheck, partOfSpeechForThisEntry, sfLocal);
			}
			removeRepititionsFromList(sfLocal);
		} 
		this.setSyntacticFunctions(sfLocal);
	}//method findSyntacticFuntions ends here.

	private void syntacticFunctionsForThisLanguage(String langToCheck, String partOfSpeechThisEntry, List<String> sfLocal) {
		// TODO Auto-generated method stub
		if (this.getLanguage().equals(langToCheck)) {
			partOfSpeechThisEntry = partOfSpeechThisEntry.substring(partOfSpeechThisEntry.indexOf(",") + 2,
					partOfSpeechThisEntry.lastIndexOf(")"));
		} else {
			partOfSpeechThisEntry = "";
		}
		if (partOfSpeechThisEntry != null && !partOfSpeechThisEntry.isEmpty()) {
			// Changing from NOUN to Noun, for example.
			partOfSpeechThisEntry = partOfSpeechThisEntry.substring(0, 1).toUpperCase()
					+ partOfSpeechThisEntry.substring(1).toLowerCase();
			sfLocal.add(new String(partOfSpeechThisEntry));
		}
	}// method new String(partOfSpeechThisEntry) ends here.

	private void removeRepititionsFromList(List<String> someList) {
		// TODO Auto-generated method stub
		/* Making a hashset from the list someList to remove repetitions from it. 
		 * The order of items is disturbed but here order is not important.
		 */
		Set<String> hs = new HashSet<String>(someList);
		someList.clear();// Emptying the list as it has repitions.
		someList.addAll(hs);// Making list from the hash set that has no repititions. */
	}

	public String toString() {
		String sf = "The syntactic function(s) of (" + this.getWordToSearch() + ") : ";
		if (getSyntacticFunctions() != null && !getSyntacticFunctions().isEmpty()) {
			Stack<String> stack = new Stack<String>();
			getSyntacticFunctions().forEach(listItem -> stack.push(new String(listItem)));
			if (stack.size() == 1) {
				sf += stack.pop();
			} else if (stack.size() >= 2) {
				for (int i = 0; i < getSyntacticFunctions().size(); i++) {
					sf += giveOne(stack);
				}
			} else {
				sf += "";// Just for the sake of completion. Never executed.
			}
		} else if (getSyntacticFunctions() == null || getSyntacticFunctions().isEmpty()) {
			sf += "????";// The string ???? means syntactical function was not
							// found.
		}
		return sf;
	}// method toString() ends here.

}// class SyntacticFunction ends here.
