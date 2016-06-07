// https://dkpro.github.io/dkpro-jwktl/documentation/architecture/
//https://dkpro.github.io/dkpro-jwktl/documentation/use-cases/
package com.codopedia.dictionary1;

import java.io.File;
import java.util.List;

import com.codopedia.dictionary.Relationships.Relationship;
import com.codopedia.dictionary.pronunciation.ClausePronunciation;
import com.codopedia.dictionary.pronunciation.Pronunciation;
import com.codopedia.dictionary.pronunciation.SyntacticFunction;
import com.codopedia.dictionary.quotations.Quotation;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import de.tudarmstadt.ukp.jwktl.api.RelationType;
import de.tudarmstadt.ukp.jwktl.api.util.Language;

public class SearchWordInWikitionary {

	private final static File parsedDump = new File(
			"/Users/macbookproretina/Dropbox/code/MacMachine/javaCore/JohnSonmezMine/Langopedia/db/");
	String formatedEntry = "";
	private PresentedText output;
	
	public PresentedText searchInWiktionary(String wordToSearch) {
		output = new PresentedText();
		output.setInputText(wordToSearch);
		
		IWiktionaryEdition wkt = JWKTL.openEdition(parsedDump);
		// case insensitive
		// List<IWiktionaryPage> pages = wkt.getPagesForWord(wordToSearch, true)
		IWiktionaryPage page = wkt.getPageForWord(wordToSearch);
		// Access by page.
		List<IWiktionaryEntry> entriesOnThisPage = null;

		if (page != null) {
			entriesOnThisPage = page.getEntries();// never null
			IWiktionaryEntry entry = page.getEntries().get(0);
			printSyntacticFuntions(wkt, entry);
			
		} else {
			output.setWordNotFound(true);
			wkt.close();
			return output;
		}

		IWiktionaryEntry entry = null;
		if (entriesOnThisPage != null && !entriesOnThisPage.isEmpty()) {
			entry = entriesOnThisPage.get(0);
		}

		// Enumerate senses i.e, meaning of the wordToSearch.
		if (entry != null) {
			printLaguageOfWordToSearch(entry);
			printPronunciation(entry, wkt);
			printGender(entry);
			printMeanings(entry);
			printUsages(entry);
			printQuotations(entry);
			printRelatedWords(entriesOnThisPage);
		}

		wkt.close();
		// pageLastEditedDate = entry.getPage().getTimestamp().toString();
		// System.out.println("Page containing (" + wordToSearch.trim() + ") was
		// last time edited on : "+ pageLastEditedDate);
		output.wordNotFound = false;
		return output;
	}// method searchInWiktionary ends here.

	private void printLaguageOfWordToSearch(IWiktionaryEntry entry) {
		// TODO Auto-generated method stub
		LanguageFromCode lang = new LanguageFromCode();
		String threeLetteredCode = entry.getWordLanguage().getCode();
		String twoLetteredCode = lang.getMe2LetteredFrom3Lettered(threeLetteredCode);
		output.setLanguageCode(threeLetteredCode);
		String  language = lang.getMeLanguageFromCode(twoLetteredCode);
		output.setLanguage(language);
	}

	private void printRelatedWords(List<IWiktionaryEntry> entriesOnThisPage) {
		printSynonyms(entriesOnThisPage);
		printAntonyms(entriesOnThisPage);
		printHolonyms(entriesOnThisPage);
		printHypernyms(entriesOnThisPage);
		printHyponyms(entriesOnThisPage);
		printMeronyms(entriesOnThisPage);
	}

	private void printMeronyms(List<IWiktionaryEntry> entriesOnThisPage) {
		Relationship meronyms = new Relationship(entriesOnThisPage);
		meronyms.searchRelatedRelationship(RelationType.MERONYM);
		output.setMeronyms(meronyms.toString());
		//meronyms.print();
	}

	private void printHyponyms(List<IWiktionaryEntry> entriesOnThisPage) {
		Relationship hyponyms = new Relationship(entriesOnThisPage);
		hyponyms.searchRelatedRelationship(RelationType.HYPONYM);
		output.setHyponyms(hyponyms.toString());
		//hyponyms.print();
	}

	private void printHypernyms(List<IWiktionaryEntry> entriesOnThisPage) {
		Relationship hypernyms = new Relationship(entriesOnThisPage);
		hypernyms.searchRelatedRelationship(RelationType.HYPERNYM);
		output.setHypernyms(hypernyms.toString());
		//hypernyms.print();
	}

	private void printHolonyms(List<IWiktionaryEntry> entriesOnThisPage) {
		Relationship holonyms = new Relationship(entriesOnThisPage);
		holonyms.searchRelatedRelationship(RelationType.HOLONYM);
		output.setHolonyms(holonyms.toString());
		//holonyms.print();
	}

	private void printSynonyms(List<IWiktionaryEntry> entriesOnThisPage) {
		Relationship s = new Relationship(entriesOnThisPage);
		s.searchRelatedRelationship(RelationType.SYNONYM);
		output.setSynonyms(s.toString());
		//s.print();
	}

	private void printAntonyms(List<IWiktionaryEntry> entriesOnThisPage) {
		Relationship a = new Relationship(entriesOnThisPage);
		a.searchRelatedRelationship(RelationType.ANTONYM);
		output.setAntonyms(a.toString());
		//a.print();
	}

	private void printQuotations(IWiktionaryEntry entry) {
		Quotation q = new Quotation();
		q.searchQuotations(entry);
		output.setQuotations(q.toString());
		//q.printQuotations();
	}

	// private void printSyntacticFuntions(IWiktionaryEdition wkt, String
	// wordToSearch) {
	private void printSyntacticFuntions(IWiktionaryEdition wkt, IWiktionaryEntry entry) {
		SyntacticFunction sf = new SyntacticFunction(entry);
		// sf.setLanguage("deu");//deu for German
		sf.findSyntacticFuntions(wkt);
		output.setSyntacticFunctions(sf.toString());
		//sf.printSF();
	}// method printSyntacticFuntions ends here.

	private void printGender(IWiktionaryEntry entry) {

		String genderOfWordToSearch = entry.getGender() != null ? entry.getGender().toString() : null;
		if (genderOfWordToSearch != null) {
			output.setGender(entry.getGender().toString());
		} else {
			output.setGender("Gender Not found!");
		}

	}// method printGender ends here.

	private void printPronunciation(IWiktionaryEntry entry, IWiktionaryEdition wkt) {
		// A word might have more than one pronunciations.
		if (entry.getWord().trim().contains(" ")) {
			pronounceClause(entry, wkt);
		} else {
			pronounceSingleWord(entry);
		}
	}// method printPronunciation ends here.

	private void pronounceClause(IWiktionaryEntry entry, IWiktionaryEdition wkt) {
		ClausePronunciation cp = new ClausePronunciation();
		output.setPronunciations(cp.toString());
		//cp.printClausePronunciation();
	}

	private void pronounceSingleWord(IWiktionaryEntry entry) {
		Pronunciation p = new Pronunciation();
		p.createPronunciationSingleWord(entry);
		output.setPronunciations(p.toString());
		//System.out.println(p.toString());
	}// method pronounceSingleWord ends here.

	private void printUsages(IWiktionaryEntry entry) {
		Usage usage = new Usage(entry);
		usage.searchUsages();
		output.setUsages(usage.toString());
		//usage.printUsages();
	}

	private void printMeanings(IWiktionaryEntry entry) {
		Meaning meanings = new Meaning(entry);
		meanings.searchMeaning();
		output.setMeanings(meanings.toString());
		//meanings.printMeanings();
	}// method printMeaning ends here.

}// class SearchWordInWikitionary ends here.
