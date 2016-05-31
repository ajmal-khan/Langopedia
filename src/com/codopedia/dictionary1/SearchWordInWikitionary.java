package com.codopedia.dictionary1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IPronunciation;
import de.tudarmstadt.ukp.jwktl.api.IQuotation;
import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionarySense;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryWordForm;
import de.tudarmstadt.ukp.jwktl.api.util.GrammaticalGender;

public class SearchWordInWikitionary {

	private final static File parsedDump = new File(
			"/Users/macbookproretina/Dropbox/code/MacMachine/javaCore/JohnSonmezMine/Langopedia/db/");
	private List<IQuotation> quotationsForEachDefintionEntry;
	private List<IWikiString> entryExamples;
	private Iterable<IWiktionarySense> entrySenses;
	private List<IWikiString> entryReferences;
	private String partsOfSpeech;
	private String wordAspect = null, wordMood = null, wordTense = null, wordForm = null;
	private String pageAuthor;
	private String pageLanuage;
	private String languageISO639_3Code;
	private String pageLastEditedDate;

	public void searchInWiktionary(String wordToSearch) {
		IWiktionaryEdition wkt = JWKTL.openEdition(parsedDump);
		// IWiktionaryEntryFilter filter;
		List<IWiktionaryEntry> definitionEntries = wkt.getEntriesForWord(wordToSearch, true);

		definitionEntries.stream().forEach(entry -> {
			printEntry(wordToSearch, entry);
		});

		wkt.close();

	}// method searchInWiktionary ends here.

	private void printEntry(String wordToSearch, IWiktionaryEntry entry) {

		System.out.println("YOU SEARCHED FOR : (" + entry.getWord().trim() + ")");

		System.out.println("");
		

		IWikiString wordEtymology = entry.getWordEtymology();
		if (wordEtymology != null) {
			System.out.println("Etymology for (" + wordToSearch.trim() + ") : \n");
			System.out.println(wordEtymology.getPlainText().trim());
		}

		System.out.println("");
		/* entry.getGender() Returns the grammatical gender of this lexical entry, which can be one of masculine, feminine, neuter.
		If no gender is specified, null is returned.*/
		
		if(entry.getGender() != null){
			System.out.println("Gender of (" + wordToSearch.trim() + ") : \n");
			String grammaticalGender = entry.getGender().toString();
			System.out.println(grammaticalGender);
		}


		System.out.println("");

		List<IPronunciation> pronunciation = entry.getPronunciations();
		if (pronunciation != null && pronunciation.size() > 0) {
			System.out.println("Pronuciations for  (" + wordToSearch.trim() + ") : ");
			pronunciation.stream().forEach(pronun -> System.out.println(pronun.getText().trim().toString()));
		}

		System.out.println("");
		
		//The resulted list is never null and includes at least one element.
			entry.getPartsOfSpeech().stream().forEach(pos -> {
				partsOfSpeech = pos.toString() + "\t";
			});
			System.out.println(partsOfSpeech);

		
		System.out.println("");
		
		pageAuthor = entry.getPage().getAuthor().toString();
		System.out.println("Author of the page containing (" + wordToSearch.trim() + ") : " + pageAuthor);
		
		System.out.println("");
		
		languageISO639_3Code = entry.getPage().getEntryLanguage().getISO639_3().toString();
		pageLanuage = entry.getPage().getEntryLanguage().toString();
		System.out.println("ISO639_3Code for the language of the page with (" + wordToSearch.trim() + ") : " + languageISO639_3Code);
		System.out.println("Language of the page with (" + wordToSearch.trim() + ") : " + pageLanuage);
		
		System.out.println("");
		
		pageLastEditedDate = entry.getPage().getTimestamp().toString();
		System.out.println("Page containing (" + wordToSearch.trim() + ") was last time edited on : "+ pageLastEditedDate);
		
		System.out.println("");

		List<IWiktionaryWordForm> wordForms = entry.getWordForms();
		
		if (wordForms != null && wordForms.size() > 0) {
			
			System.out.println("Word forms for (" + wordToSearch.trim() + ") : ");
			
			wordForms.stream().forEach(form -> {
				
				if (form.getAspect() != null){
					wordAspect = form.getAspect().toString() + "\t";
				}
				if (form.getWordForm() != null){
					wordForm = form.getWordForm().toString() + "\t";
				}
				if (form.getTense() != null){
					wordTense = form.getTense().toString() + "\t";
				}
				if (form.getMood() != null){
					wordMood = form.getMood().toString() + "\n";
				}	
			});
			System.out.println("Aspect of (" + wordToSearch.trim() + ") : " +  wordAspect);
			System.out.println("Word form of (" + wordToSearch.trim() + ") : " +  wordForm);
			System.out.println("Tense of (" + wordToSearch.trim() + ") : " +  wordTense);
			System.out.println("Mood of (" + wordToSearch.trim() + ") : " +  wordMood);
		}//if ends here.

		System.out.println("");

		List<IQuotation> quotations = entry.getQuotations();
		// The list is never null but might be empty.
		if (quotations.size() > 0) {
			System.out.println("Quoations for (" + wordToSearch.trim() + ") are: ");
			quotations.stream().forEach(quotation -> {
				quotation.getLines().stream().forEach(quotationText -> {
					quotationText.getPlainText();
				});
			});
		}

		System.out.println("");

		List<IWikiString> entryExamples = entry.getExamples();
		// The list is never null but might be empty.
		if (entryExamples.size() > 0) {
			System.out.println("All sense definitions of the entry's senses (including the unassigned sense) are :\n");
			entryExamples.stream().forEach(senseDef -> {
				senseDef.getPlainText();
			});
		}

		System.out.println("");
		entrySenses = entry.getSenses();
		entrySenses.forEach(sense -> {
			if (sense.getExamples() != null) {
				sense.getExamples().stream().forEach(s -> {
					s.getPlainText();
				});
			}
		});
		System.out.println("");

		List<IWikiString> entryReferences = entry.getReferences();
		// The list is never null but might be empty.
		if (entryReferences.size() > 0) {
			System.out.println("Refrences for (" + wordToSearch.trim() + ") : \n");
			entryReferences.stream().forEach(ref -> {
				ref.getPlainText();
			});
		}

		System.out.println("");

	}// method printEntry ends here.

}// class SearchWordInWikitionary ends here.
