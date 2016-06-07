package com.codopedia.dictionary.pronunciation;

import java.util.ArrayList;
import java.util.List;

import com.codopedia.dictionary1.LanguageCodeDetection;
import com.codopedia.dictionary1.LanguageFromCode;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

public class DetectLanguage {
	private List<Language> possibleLanguagesAL;
	private String language;
	private String languageCode;
	
	public DetectLanguage (){
		possibleLanguagesAL = new ArrayList<>();
		language = "";
		languageCode = "";
	}

	private String getLanguageCode() {
		return languageCode;
	}

	private void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	private List<Language> getPossibleLanguagesAL() {
		return possibleLanguagesAL;
	}

	private void setPossibleLanguagesAL(List<Language> possibleLanguagesAL) {
		this.possibleLanguagesAL = possibleLanguagesAL;
	}

	public String getLanguage() {
		return language;
	}

	private void setLanguage(String language) {
		this.language = language;
	}

	public void detectLanguage(String inputText) {
		LanguageCodeDetection langCodeDetecter = new LanguageCodeDetection(inputText);
		try {
			langCodeDetecter.detectPossibleLanguagesList();
			List<Language> possibleLangList = langCodeDetecter.detectPossibleLanguagesList();
			setPossibleLanguagesAL(possibleLangList);
			String languageCode = langCodeDetecter.detectLanguageOfHighestProbability();
			this.setLanguageCode(languageCode);
			LanguageFromCode lfc = new LanguageFromCode();
			setLanguage(lfc.getMeLanguageFromCode(languageCode));
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//method detectLanguage ends here.
	public String toString(){
		String str = "";
		str += "Language: " + this.getLanguage() + " " + 
				"(" + this.getLanguageCode() + ").";
		return str;
	}//method toString ends here.
}
