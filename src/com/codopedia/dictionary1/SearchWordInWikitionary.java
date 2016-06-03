// https://dkpro.github.io/dkpro-jwktl/documentation/architecture/
package com.codopedia.dictionary1;

import de.tudarmstadt.ukp.jwktl.api.WiktionaryFormatter;
import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalGender;
import de.tudarmstadt.ukp.jwktl.api.util.IWiktionaryIterator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IPronunciation;
import de.tudarmstadt.ukp.jwktl.api.IQuotation;
import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryCollection;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import de.tudarmstadt.ukp.jwktl.api.IWiktionarySense;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryWordForm;

public class SearchWordInWikitionary {

	private final static File parsedDump = new File(
			"/Users/macbookproretina/Dropbox/code/MacMachine/javaCore/JohnSonmezMine/Langopedia/db/");
	String formatedEntry = "";

	public void searchInWiktionary(String wordToSearch) {

		/****************************************************************************************************/

		IWiktionaryEdition wkt = JWKTL.openEdition(parsedDump);
		/*
		 * Query by word form. - Returns the page with the given title. The
		 * method only returns the page if its title matches exactly. Use
		 * getPagesForWord(String, boolean) for case insensitive and
		 * string-normalized matching.
		 */
		// case insensitive
		// List<IWiktionaryPage> pages = wkt.getPagesForWord(wordToSearch, true)

		// System.out.println(WiktionaryFormatter.instance().formatHeader(page));
		System.out.println("*******************************************************");
		IWiktionaryPage page = wkt.getPageForWord(wordToSearch);
		// Access by page.
		List<IWiktionaryEntry> entriesOnThisPage = null;

		if (page != null) {
			entriesOnThisPage = page.getEntries();// never null
			printSyntacticFuntion(wkt, wordToSearch, entriesOnThisPage);
		} else {
			System.out.println("\n=====>No page contains (" + wordToSearch + ") : ");
			wkt.close();
			return;
		}
		System.out.println("*******************************************************");
		///////////////////////////////////////////////////////////////////////////////////////////

		IWiktionaryEntry entry = null;
		if (entriesOnThisPage != null && entriesOnThisPage.size() > 0) {
			entry = entriesOnThisPage.get(0);
		}

		// Enumerate senses i.e, meaning of the wordToSearch.
		if (entry != null) {

			printPronunciation(entry, wkt);
			System.out.println("*******************************************************");
			printGender(entry);
			System.out.println("*******************************************************");

			printMeanings(entry, wordToSearch);
			System.out.println("*******************************************************");
			List<IWikiString> usagesOfTheWordToSearch = entry.getExamples();
			printUsages(wordToSearch, usagesOfTheWordToSearch);
		} // if entry != null ends here.

		System.out.println("************************************************************************");
		wkt.close();
		// pageLastEditedDate = entry.getPage().getTimestamp().toString();
		// System.out.println("Page containing (" + wordToSearch.trim() + ") was
		// last time edited on : "+ pageLastEditedDate);
	}// method printEntry ends here.

	private void printGender(IWiktionaryEntry entry) {

		String genderOfWordToSearch = entry.getGender() != null ? entry.getGender().toString() : null;
		if (genderOfWordToSearch != null) {
			System.out.println("Gender of the word (" + entry.getWord() + ") : " + entry.getGender().toString());
		} else {
			System.out.println("No gender found for (" + entry.getWord() + ").");
		}

	}// method printGender ends here.

	private void printPronunciation(IWiktionaryEntry entry, IWiktionaryEdition wkt) {
		// A word might have more than one pronunciations.
		System.out.println("Pronuciation(s) for  (" + entry.getWord() + ") : ");
		if (entry.getWord().trim().contains(" ")) {
			// pronounceMultipleWords(entry, wkt);
			pForNwordsOnNlines(entry, wkt);
		} else {
			pronounceSingleWord(entry);
		}
	}// method printPronunciation ends here.

	private void pForNwordsOnNlines(IWiktionaryEntry entry, IWiktionaryEdition wkt) {
		List<List<String>> listOfListsOfPronun = new ArrayList<>();
		// List<String> listOfPronunciations = new ArrayList<>();
		giveListOfListsOfPronunciations(entry, wkt, listOfListsOfPronun);
		List<Stack<String>> listOfStacks = giveListOfStacks(listOfListsOfPronun);
		int numSentences = getSizeOfListWithMaxPronunciations(listOfListsOfPronun);
		String allLines = "";
		for (int i = 0; i < numSentences; i++) {
			String ithLine = printOneLineFromStacks(listOfStacks);
			allLines += ithLine + "\n";
		}
		System.out.println(allLines);
	}// method pronounceMultipleWords ends here.

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

				// linesOfPronunciationsList.add(new String(nthPrronun.trim()));
			}
		}
		return ithLine.trim();
	}// method printOneLineFromStacks ends here.

	private List<Stack<String>> giveListOfStacks(List<List<String>> listOfListsOfPronun) {

		List<Stack<String>> listOfStacks = new ArrayList<>();
		for (List<String> listOfP : listOfListsOfPronun) {
			Stack<String> thisStack = new Stack<String>();
			listOfP.forEach(p -> thisStack.push(new String(p)));
			listOfStacks.add(thisStack);
		}
		return listOfStacks;
	}// method giveListOfStacks ends here.

	private int getSizeOfListWithMaxPronunciations(List<List<String>> listOfListsOfPronun) {
		int sizeOfLargestList = 0;
		for (List<String> list : listOfListsOfPronun) {
			sizeOfLargestList = list.size() > sizeOfLargestList ? list.size() : sizeOfLargestList;
		}
		return sizeOfLargestList;
	}// method getSizeOfListWithMaxPronunciations ends here.

	private void giveListOfListsOfPronunciations(IWiktionaryEntry entry, IWiktionaryEdition wkt,
			List<List<String>> listOfListsOfPronun) {
		String[] wordsToSearch = entry.getWord().trim().split(" ");
		IWiktionaryPage page2 = null;

		for (int i = 0; i < wordsToSearch.length; i++) {
			if (wordsToSearch[i] != null && !wordsToSearch[i].isEmpty()) {
				page2 = wkt.getPageForWord(wordsToSearch[i]);
				List<IWiktionaryEntry> entries2 = null;
				if (page2 != null) {
					entries2 = page2.getEntries();
				}
				IWiktionaryEntry entry2 = null;
				if (entries2 != null) {
					entry2 = entries2.get(0);
					List<String> listOfPronunciations2 = null;
					listOfPronunciations2 = returnPronunciationForAllCasesSingleWordOrMore(entry2.getPronunciations());
					if (listOfPronunciations2 != null && !listOfPronunciations2.isEmpty()) {
						/*
						 * The following line will not add the elments to
						 * listOfListsOfPronun. It will only add a reference to
						 * listOfListsOfPronun.
						 * listOfListsOfPronun.add(listOfPronunciations2);
						 */
						listOfListsOfPronun.add(new ArrayList<String>(listOfPronunciations2));
						// listOfPronunciations2.forEach(p ->
						// listOfPronunciations.add(p));
						// listOfPronunciations.addAll(listOfPronunciations2);//appends.
					}
				}
			}
		}
	}// method giveListOfListsOfPronunciations ends here.

	private void pronounceSingleWord(IWiktionaryEntry entry) {
		List<String> listOfPronunciations = null;
		listOfPronunciations = returnPronunciationForAllCasesSingleWordOrMore(entry.getPronunciations());
		if (listOfPronunciations.size() > 0) {
			listOfPronunciations.forEach(p -> System.out.println(p));
		} else {
			System.out.println("No pronuncation found for (" + entry.getWord() + ").");
		}
	}

	private List<String> returnPronunciationForAllCasesSingleWordOrMore(List<IPronunciation> pronunciation) {
		List<String> pronunciationsList = new ArrayList<>();
		if (pronunciation != null && pronunciation.size() > 0) {
			pronunciation.forEach(p -> {
				if(p != null && p.getText().trim().length() > 0){
					String temp1 = p.getText().trim();
					String temp2 = "";
					if(temp1.contains("/")){
						temp2 = temp1.substring(temp1.indexOf("/") + 1);
					}
					if(!temp2.isEmpty() && temp2.contains("/")){
						temp2 = temp2.substring(0, temp2.indexOf("/"));
						pronunciationsList.add(new String(temp2));
					}else{
						pronunciationsList.add(new String(temp1));
					}
				}
			});
		}else{
			/* Substituting the missing word in a clause with ???? in the position of that word. */
			pronunciationsList.add(new String("?????"));
		}
		return pronunciationsList;
	}// method returnPronunciationForAllCasesSingleWordOrMore ends here.

	private void printUsages(String wordToSearch, List<IWikiString> usagesOfTheWordToSearch) {
		List<String> usagesList = new ArrayList<>();
		if (usagesOfTheWordToSearch != null && usagesOfTheWordToSearch.size() > 0) {
			for (IWikiString entryExample : usagesOfTheWordToSearch) {
				if (entryExample != null && !"".equals(entryExample)) {
					String temp = entryExample.getPlainText().trim();
					usagesList.add(temp);
				}
			}
		}
		if (usagesList != null && usagesList.size() > 0) {
			// System.out.println("Found following usage(s) for " + wordToSearch
			// + ":");
			usagesList.forEach(usage -> System.out.println(usage));
		} else {
			System.out.println("Found no usage(s) for " + wordToSearch + ".");
		}
	}// method printUsages ends here.

	private void printMeanings(IWiktionaryEntry entry, String wordToSearch) {
		List<String> meanings = new ArrayList<String>();
		for (IWiktionarySense sense : entry.getSenses()) {
			String str = WiktionaryFormatter.instance().formatHeader(sense);
			str = str.substring(str.indexOf("]") + 2).trim();
			if (str != null && !str.isEmpty())
				meanings.add(str);
		}
		System.out.println("Meaning(s)/Sense(s) of " + wordToSearch + " are: ");
		meanings.forEach(m -> System.out.println(m));
	}// method printMeaning ends here.

	private void printSyntacticFuntion(IWiktionaryEdition wkt, String wordToSearch,
			List<IWiktionaryEntry> entriesOnThisPage) {

		List<String> syntacticFunctions = new ArrayList<String>();

		if (entriesOnThisPage.size() > 0) {
			for (IWiktionaryEntry entry : entriesOnThisPage) {
				String str = WiktionaryFormatter.instance().formatHeader(entry);
				String language = str.substring(str.indexOf("(") + 1, str.indexOf(","));
				if ("English".equals(language)) {
					str = str.substring(str.indexOf(",") + 2, str.lastIndexOf(")"));
				} else {
					str = "";
				}
				if (str != null && !str.isEmpty()) {
					// Changing from NOUN to Noun, for example.
					str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
					syntacticFunctions.add(str);
				}
			}
			// Making a hashshet from the list to remove repetitions.
			Set<String> hs = new HashSet<String>(syntacticFunctions);
			syntacticFunctions.clear();
			syntacticFunctions.addAll(hs);
			syntacticFunctions.forEach(sf -> System.out.println(wordToSearch + " is " + sf));
		}
	}// method printSyntacticFunction ends here.

}// class SearchWordInWikitionary ends here.

/*
 * //IWiktionaryCollection iwc = JWKTL.openCollection(parsedDump); // // Query
 * by word form (case insensitive). For case sensitive pass only the frist
 * argument and not the boolean one. // List<IWiktionaryEntry> entries =
 * wkt.getEntriesForWord(wordToSearch, true); // if(entries != null &&
 * entries.size() > 0){ // for (IWiktionaryEntry entry : entries) //
 * System.out.println(WiktionaryFormatter.instance().formatHeader(entry)); // //
 * }
 */

/*
 * // // Access first sense. // IWiktionarySense sense = entry.getSense(1); //
 * System.out.println(WiktionaryFormatter.instance().formatHeader(sense)); // //
 * // Access second sense. // sense = entry.getSense(2); //
 * System.out.println(WiktionaryFormatter.instance().formatHeader(sense));
 */

/*
 * // languageISO639_3Code =
 * entry.getPage().getEntryLanguage().getISO639_3().toString(); // pageLanuage =
 * entry.getPage().getEntryLanguage().toString(); // System.out.println(
 * "ISO639_3Code for the language of the page with (" + wordToSearch.trim() +
 * ") : " + languageISO639_3Code); // System.out.println(
 * "Language of the page with (" + wordToSearch.trim() + ") : " + pageLanuage);
 * // // System.out.println(""); //
 */
