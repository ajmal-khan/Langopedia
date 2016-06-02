// https://dkpro.github.io/dkpro-jwktl/documentation/architecture/
package com.codopedia.dictionary1;

import de.tudarmstadt.ukp.jwktl.api.WiktionaryFormatter;
import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalGender;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		/* Query by word form. - Returns the page with the given title. The method only returns the page if its title
		 *  matches exactly. Use getPagesForWord(String, boolean) for case insensitive and string-normalized matching.
		 */
		//case insensitive
		//List<IWiktionaryPage> pages = wkt.getPagesForWord(wordToSearch, true)

		//System.out.println(WiktionaryFormatter.instance().formatHeader(page));
		System.out.println("*******************************************************");
		IWiktionaryPage page = wkt.getPageForWord(wordToSearch);
		// Access by page.
		List<IWiktionaryEntry> entriesOnThisPage = null;
		
		if(page != null){
			entriesOnThisPage = page.getEntries();//never null
			printSyntacticFuntion(wkt, wordToSearch, entriesOnThisPage);
		}else{
			System.out.println("\n=====>No page contains (" + wordToSearch + ") : ");
			wkt.close();
			return;
		}
		System.out.println("*******************************************************");
		///////////////////////////////////////////////////////////////////////////////////////////
	
		IWiktionaryEntry entry = null;
		if(entriesOnThisPage != null && entriesOnThisPage.size() > 0){
			entry = entriesOnThisPage.get(0);
		}
		
		// Enumerate senses i.e, meaning of the wordToSearch.
		if(entry != null){
			
			printPronunciation(entry, wordToSearch);
			System.out.println("*******************************************************");
			printGender(entry, wordToSearch);
			System.out.println("*******************************************************");
			
			printMeanings(entry, wordToSearch);
			System.out.println("*******************************************************");
			 List<IWikiString> usagesOfTheWordToSearch = entry.getExamples();
			printUsages(wordToSearch, usagesOfTheWordToSearch);




		}//if entry != null ends here.
			
//		// Access first sense.
//		IWiktionarySense sense = entry.getSense(1);
//		System.out.println(WiktionaryFormatter.instance().formatHeader(sense));
//
//		// Access second sense.
//		sense = entry.getSense(2);
//		System.out.println(WiktionaryFormatter.instance().formatHeader(sense));
		System.out.println("************************************************************************");
		wkt.close();
		//iwc.close();
		
		/****************************************************************************************************/
		// IWiktionaryEntryFilter filter;
		//////////////////////////////////////////////////////////////////////////////////////////////

		
//		List<IWiktionaryPage> pages = iwc.getPagesForWord(wordToSearch, true);
//
//		if(pages != null && pages.size() > 0){
//			pages.forEach(page -> {
//				pageText += page.getTitle().toString().trim() + "\n\n";
//			});
//		}
//		System.out.println("Text of the pages that contain (" + wordToSearch + ") :");
//		System.out.println(pageText);
//
//		
//		System.out.print("Sentences contianing (" + wordToSearch + ") are:\n");
//		entries.forEach(entry -> {
//			entry.getExamples().stream().forEach(example -> {
//				if(!"".equals(example))
//					System.out.print("\n" +example.getPlainText().trim());
//			});
//		});
//		
//		/* Returns the grammatical gender of this lexical entry, which can be one of masculine, feminine, neuter. If no gender is specified, null is returned.*/
//		if(entries.size() > 0 && entries.get(0).getGender() != null){
//			System.out.print("Gender for (" + wordToSearch + ") :\n");
//			System.out.println(entries.get(0).getGender().toString());
//		}
//		
//		
//		//iwc.close();
//
//		//////////////////////////////////////////////////////////////////////////////////////////////
//		IWiktionaryEdition wkt = JWKTL.openEdition(parsedDump);
//		List<IWiktionaryEntry> definitionEntries = wkt.getEntriesForWord(wordToSearch, true);
//
//		definitionEntries.stream().forEach(entry -> {
//			printEntry(wordToSearch, entry);
//		});
//
//		wkt.close();
//
//	}// method searchInWiktionary ends here.
//
//	private void printEntry(String wordToSearch, IWiktionaryEntry entry) {
//
//		System.out.println("\nYOU SEARCHED FOR : (" + entry.getWord().trim() + ")");
//
//		System.out.println("");
//		
//		/* entry.getGender() Returns the grammatical gender of this lexical entry, which can be one of masculine, feminine, neuter.
//		If no gender is specified, null is returned.*/
//		
//		if(entry.getGender() != null){
//			System.out.println("Gender of (" + wordToSearch.trim() + ") : \n");
//			String grammaticalGender = entry.getGender().toString();
//			System.out.println(grammaticalGender);
//		}
//
//
//		System.out.println("");
//
//		List<IPronunciation> pronunciation = entry.getPronunciations();
//		if (pronunciation != null && pronunciation.size() > 0) {
//			System.out.println("Pronuciations for  (" + wordToSearch.trim() + ") : ");
//			pronunciation.stream().forEach(pronun -> System.out.println(pronun.getText().trim().toString()));
//		}
//
//		System.out.println("");
//		
//		//The resulted list is never null and includes at least one element.
//			entry.getPartsOfSpeech().stream().forEach(pos -> {
//				partsOfSpeech = pos.toString() + "\t";
//			});
//			System.out.println(partsOfSpeech);
//
//		
//		System.out.println("");
//		
//		pageAuthor = (entry.getPage().getAuthor() != null ? entry.getPage().getAuthor().toString() : "Not available");
//		System.out.println("Author of the page containing (" + wordToSearch.trim() + ") : " + pageAuthor);
//		
//		System.out.println("");
//		
//		languageISO639_3Code = entry.getPage().getEntryLanguage().getISO639_3().toString();
//		pageLanuage = entry.getPage().getEntryLanguage().toString();
//		System.out.println("ISO639_3Code for the language of the page with (" + wordToSearch.trim() + ") : " + languageISO639_3Code);
//		System.out.println("Language of the page with (" + wordToSearch.trim() + ") : " + pageLanuage);
//		
//		System.out.println("");
//		
//		pageLastEditedDate = entry.getPage().getTimestamp().toString();
//		System.out.println("Page containing (" + wordToSearch.trim() + ") was last time edited on : "+ pageLastEditedDate);
//
//		System.out.println("");
//
//		List<IQuotation> quotations = entry.getQuotations();
//		// The list is never null but might be empty.
//		if (quotations.size() > 0) {
//			System.out.println("Quoations for (" + wordToSearch.trim() + ") are: ");
//			quotations.forEach(quotation -> {
//				quotation.getLines().stream().forEach(quotationText -> {
//					quotationText.getPlainText();
//				});
//			});
//		}
//
//		System.out.println("");
//
//		List<IWikiString> entryExamples = entry.getExamples();
//		// The list is never null but might be empty.
//		if (entryExamples.size() > 0) {
//			System.out.println("All sense definitions of the entry's senses (including the unassigned sense) are :\n");
//			entryExamples.stream().forEach(senseDef -> {
//				senseDef.getPlainText();
//			});
//		}else{
//			System.out.println("No sense definition found for this entry when using the entry.getExamples()");
//		}
//
//		System.out.println("");
//		
//		entrySenses = entry.getSenses();
//		//The list is never null nor empty.
//		System.out.println("Here are all the senses for (" + wordToSearch.trim() + ") : "); 
//		entrySenses.forEach(sense -> {
//			if (sense.getExamples() != null) {
//				sense.getExamples().stream().forEach(s -> {
//					s.getPlainText();
//				});
//			}
//		});
//		
//		System.out.println("");
//
//		List<IWikiString> entryReferences = entry.getReferences();
//		// The list is never null but might be empty.
//		if (entryReferences.size() > 0) {
//			System.out.println("Refrences for (" + wordToSearch.trim() + ") : \n");
//			entryReferences.forEach(ref -> {
//				ref.getPlainText();
//			});
//		}else{
//			System.out.println("No references found when using the entryReferences.stream.forEach...ref.getPlainText()");
//		}
//
//		System.out.println("");

	}// method printEntry ends here.

	private void printGender(IWiktionaryEntry entry, String wordToSearch) {

		String genderOfWordToSearch = entry.getGender() != null ? entry.getGender().toString() : null;
		if(genderOfWordToSearch != null){
			System.out.println("Gender of the word (" + wordToSearch + ") : " + entry.getGender().toString());
		}else{
			System.out.println("No gender found for (" + wordToSearch + ".)");
		}
	
	}//method printGender ends here.

	private void printPronunciation(IWiktionaryEntry entry, String wordToSearch) {
		List<String> pronunciationsList = new ArrayList<>();
		List<IPronunciation> pronunciation = entry.getPronunciations();
		if (pronunciation != null && pronunciation.size() > 0) {
			pronunciation.stream().forEach(pronun -> pronunciationsList.add(pronun.getText()));
		}
		if(pronunciationsList.size() > 0){
			System.out.println("Pronuciations for  (" + wordToSearch.trim() + ") : ");
			pronunciationsList.forEach(p -> System.out.println(p));

		}else{
			System.out.println("No pronuncation found for (" + wordToSearch + ").");
		}
		
	}//method printPronunciation ends here.

	private void printUsages(String wordToSearch, List<IWikiString> usagesOfTheWordToSearch) {
		List<String> usagesList= new ArrayList<>();
		 if( usagesOfTheWordToSearch != null && usagesOfTheWordToSearch.size() > 0){
				for (IWikiString entryExample : usagesOfTheWordToSearch) {
					if(entryExample != null && !"".equals(entryExample)){
						usagesList.add(entryExample.getPlainText().trim());
					}
				}
		 }
		 if(usagesList!= null && usagesList.size() > 0){
			 System.out.println("Found following usage(s) for " + wordToSearch + ":");
			 usagesList.forEach(usage -> System.out.println(usage));
		 }else{
			 System.out.println("Found no usage(s) for " + wordToSearch + ".");
			}
	}//method printUsages ends here.

	private void printMeanings(IWiktionaryEntry entry, String wordToSearch) {
		List<String> meanings = new ArrayList<String>();
		for (IWiktionarySense sense : entry.getSenses())
		{
			 String str = WiktionaryFormatter.instance().formatHeader(sense);
			 str = str.substring(str.indexOf("]") + 2).trim();
			 if(str != null && !str.isEmpty())
				 meanings.add(str);
		}
		System.out.println("Meaning(s)/Sense(s) of " + wordToSearch + " are: ");
		meanings.forEach(m -> System.out.println(m));
	}//method printMeaning ends here.

	private void printSyntacticFuntion(IWiktionaryEdition wkt, String wordToSearch,
			List<IWiktionaryEntry> entriesOnThisPage) {
		
		List<String> syntacticFunctions = new ArrayList<String>();
		
		if(entriesOnThisPage.size() > 0){
			for (IWiktionaryEntry entry : entriesOnThisPage){
				String str = WiktionaryFormatter.instance().formatHeader(entry);
				String language = str.substring(str.indexOf("(") + 1, str.indexOf(","));
				if("English".equals(language)){
					str = str.substring(str.indexOf(",") + 2, str.lastIndexOf(")"));
				}else{
					str = "";
				}
				if(str != null && !str.isEmpty()){
					//Changing from NOUN to Noun, for example.
					str = str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
					syntacticFunctions.add(str);
				}
			}
			//Making a hashshet from the list to remove repetitions.
			Set<String> hs = new HashSet<String>(syntacticFunctions);
			syntacticFunctions.clear();
			syntacticFunctions.addAll(hs);
			syntacticFunctions.forEach(sf -> System.out.println(wordToSearch + " is " + sf));
		}
	}//method printSyntacticFunction ends here.

}// class SearchWordInWikitionary ends here.

/*
 		//IWiktionaryCollection iwc = JWKTL.openCollection(parsedDump);
 //		// Query by word form (case insensitive). For case sensitive pass only the frist argument and not the boolean one.
//		List<IWiktionaryEntry> entries = wkt.getEntriesForWord(wordToSearch, true);
//		if(entries != null && entries.size() > 0){
//			for (IWiktionaryEntry entry : entries)
//			  System.out.println(WiktionaryFormatter.instance().formatHeader(entry));
//
//		} 
 */
