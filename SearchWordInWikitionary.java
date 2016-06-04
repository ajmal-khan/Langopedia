// https://dkpro.github.io/dkpro-jwktl/documentation/architecture/
package com.codopedia.dictionary1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.codopedia.dictionary.pronunciation.ClausePronunciation;
import com.codopedia.dictionary.pronunciation.Pronunciation;
import com.codopedia.dictionary.pronunciation.SyntacticFunction;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import de.tudarmstadt.ukp.jwktl.api.IWiktionarySense;
import de.tudarmstadt.ukp.jwktl.api.WiktionaryFormatter;

public class SearchWordInWikitionary {

	private final static File parsedDump = new File(
			"/Users/macbookproretina/Dropbox/code/MacMachine/javaCore/JohnSonmezMine/Langopedia/db/");
	String formatedEntry = "";

	public void searchInWiktionary(String wordToSearch) {
		IWiktionaryEdition wkt = JWKTL.openEdition(parsedDump);
		// case insensitive
		// List<IWiktionaryPage> pages = wkt.getPagesForWord(wordToSearch, true)
		IWiktionaryPage page = wkt.getPageForWord(wordToSearch);
		// Access by page.
		List<IWiktionaryEntry> entriesOnThisPage = null;

		if (page != null) {
			entriesOnThisPage = page.getEntries();// never null
			printSyntacticFuntions(wkt, wordToSearch);
			System.out.println("*******************************************************");
		} else {
			System.out.println("\n=====>No page contains (" + wordToSearch + ") : ");
			wkt.close();
			return;
		}

		IWiktionaryEntry entry = null;
		if (entriesOnThisPage != null && !entriesOnThisPage.isEmpty()) {
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

	private void printSyntacticFuntions(IWiktionaryEdition wkt, String wordToSearch) {
		// TODO Auto-generated method stub
		SyntacticFunction sf = new SyntacticFunction(wordToSearch);
		//sf.setLanguage("deu");//deu for German
		sf.findSyntacticFuntions(wkt);
		System.out.println(sf.toString());
	}//method printSyntacticFuntions ends here.

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
			pronounceClause(entry, wkt);
		} else {
			pronounceSingleWord(entry);
		}
	}// method printPronunciation ends here.

	private void pronounceClause(IWiktionaryEntry entry, IWiktionaryEdition wkt) {
		ClausePronunciation cp = new ClausePronunciation();
		cp.createPronunciationForClause(entry.getWord(), wkt);
	}// method pronounceMultipleWords ends here.

	private void pronounceSingleWord(IWiktionaryEntry entry) {
		Pronunciation p = new Pronunciation();
		p.createPronunciationSingleWord(entry);
		p.printPronunciationSingleWord();
	}// method pronounceSingleWord ends here.

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
			if(str.contains("]")){
				str = str.substring(str.indexOf("]") + 2).trim();
			}
			if (str != null && !str.isEmpty()){
				meanings.add(str);
			}
		}
		if(meanings != null && !meanings.isEmpty()){
			System.out.println("Meaning(s)/Sense(s) of " + wordToSearch + " are: ");
			meanings.forEach(m -> System.out.println(m));
		}else{
			System.out.println("No Meaning(s)/Sense(s) found for " + wordToSearch + ".");
		}
		
	}// method printMeaning ends here.

}// class SearchWordInWikitionary ends here.
