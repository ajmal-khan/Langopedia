package com.codopedia.dictionary1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

public class Word {
	int length;
	private String wordText, languageCode, language, definition, meaning;
	private Map<String, LinkedList<String>> synonyms;
	private Map<String, LinkedList<String>> antonyms;
	private ArrayList<Language> possibleLanguagesAL;
	LanguageFromCode getLangObj;

	public Map<String, LinkedList<String>> getAntonyms() {
		return antonyms;
	}

	public void setAntonyms(Map<String, LinkedList<String>> antonyms) {
		this.antonyms = antonyms;
	}

	public Word(String wordText) {
		this.wordText = wordText;
		setLength(wordText);
		getLangObj = new LanguageFromCode();
		setLanguage(wordText);
	}

	public int getLength() {
		return length;
	}

	public void setLength(String wordText) {
		this.length = wordText.length();
	}

	public String getLanguage() {
		return language;
	}

	public String getLanguageCode() {
		return languageCode;
	}
	public ArrayList<Language> getPossibleLanguagesAL() {
		return possibleLanguagesAL;
	}

	public String getWordText() {
		return wordText;
	}

	void setLanguage(String wordText) {
		LanguageCodeDetection langCodeDetecter = new LanguageCodeDetection(getWordText());
		try {
			langCodeDetecter.detectPossibleLanguagesList();
			possibleLanguagesAL = langCodeDetecter.detectPossibleLanguagesList();
			languageCode = langCodeDetecter.detectLanguageOfHighestProbability();
			language = getLangObj.getMeLanguageFromCode(languageCode);
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Word(String word, String language) {
		this.wordText = word;
		this.language = language;
	}

	public Map<String, LinkedList<String>> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Map<String, LinkedList<String>> synonyms) {
		this.synonyms = synonyms;
	}

	public Word(String word, String language, String meaning) {
		this.wordText = word;
		this.language = language;
		this.meaning = meaning;
	}

	public String toString() {
		String myToString = "Text: " + getWordText() + "\n" + "Number of characters: " + getLength() + "\n" +
							"Language: " + getLanguage() + "\n" + "Language Code: " + getLanguageCode() + "\n" +
							"Meaning: " + getMeaning() + "\n" + "Definition/Explanation: "+ getDefinition() + "\n" + 
							"Synonyms: " + getSynonyms() + "\n" + "Antonyms: " + getAntonyms() + "\n";
		return myToString;
	}
}// class Word ends here.
