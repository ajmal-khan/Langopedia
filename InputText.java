package com.codopedia.dictionary1;

import java.util.List;

public class InputText {
	int length;
	private String inputText, language;
	private List<String> usageList, meaningsList, synonymsList, antonymsList, quotationsList, pronunciationsList;
	
	public int getLength() {
		return length;
	}
	private void setLength() {
		length = inputText.length();
	}

	public String getInputText() {
		return inputText;
	}
	public void setInputText(String inputText) {
		this.inputText = inputText;
		setLength();
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getUsageList() {
		return usageList;
	}
	public void setUsageList(List<String> usageList) {
		this.usageList = usageList;
	}

	public List<String> getMeaningsList() {
		return meaningsList;
	}
	public void setMeaningsList(List<String> meaningsList) {
		this.meaningsList = meaningsList;
	}

	public List<String> getSynonymsList() {
		return synonymsList;
	}
	public void setSynonymsList(List<String> synonymsList) {
		this.synonymsList = synonymsList;
	}

	public List<String> getAntonymsList() {
		return antonymsList;
	}
	public void setAntonymsList(List<String> antonymsList) {
		this.antonymsList = antonymsList;
	}

	public List<String> getQuotationsList() {
		return quotationsList;
	}
	public void setQuotationsList(List<String> quotationsList) {
		this.quotationsList = quotationsList;
	}

	public List<String> getPronunciationsList() {
		return pronunciationsList;
	}
	public void setPronunciationsList(List<String> pronunciationsList) {
		this.pronunciationsList = pronunciationsList;
	}

	public String toString() {
		// TODO Auto-generated catch block
		String myToString = "You searched for: " + this.getInputText() + "\n" +
							"Number of characters: " + this.getLength() + "\n" +
							"Language: " + this.getLanguage() + "\n" + 
							"Meaning: " + makeStringFromList(this.getMeaningsList()) + "\n" + 
							"Usage/Sentences: " + makeStringFromList(this.getUsageList()) + "\n" +
							"Synonyms: " + makeStringFromList(this.getSynonymsList()) + "\n" +
							"Antonyms: " + makeStringFromList(this.getAntonymsList()) + "\n" +
							"Quotations: " + makeStringFromList(this.getQuotationsList()) + "\n\n";
		return myToString;
	}
	private String makeStringFromList(List<String> someList) {
		// TODO Auto-generated method stub
		String str = "";
		for (String listItem : someList) {
			str += (listItem + "\n");
		}
		return str.trim();
	}
}// class Word ends here.
