package com.codopedia.dictionary1;

public class PresentedText {
	boolean wordNotFound = true;
	private String inputText, languageCode, language, syntacticFunctions, gender;
	private String pronunciations, meanings, usages;
	private String synonyms, antonyms, quotations, hypernyms, hyponyms, meronyms;
	int length;

	protected String getLanguageCode() {
		return languageCode;
	}

	protected void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	protected String getSyntacticFunctions() {
		return syntacticFunctions;
	}

	protected String getGender() {
		return gender;
	}

	protected void setGender(String gender) {
		this.gender = gender;
	}

	protected void setSyntacticFunctions(String syntacticFunctions) {
		this.syntacticFunctions = syntacticFunctions;
	}

	protected String getHypernyms() {
		return hypernyms;
	}

	protected void setHypernyms(String hypernyms) {
		this.hypernyms = hypernyms;
	}

	protected String getHyponyms() {
		return hyponyms;
	}

	protected void setHyponyms(String hyponyms) {
		this.hyponyms = hyponyms;
	}

	protected String getMeronyms() {
		return meronyms;
	}

	protected void setMeronyms(String meronyms) {
		this.meronyms = meronyms;
	}

	private String holonyms;

	protected int getLength() {
		return length;
	}

	private void setLength() {
		length = inputText.length();
	}

	protected String getInputText() {
		return inputText;
	}

	protected void setInputText(String inputText) {
		this.inputText = inputText;
		setLength();
	}

	protected String getLanguage() {
		return language;
	}

	protected void setLanguage(String language) {
		this.language = language;
	}

	protected String getUsages() {
		return usages;
	}

	protected void setUsages(String usages) {
		this.usages = usages;
	}

	protected String getMeanings() {
		return this.meanings;
	}

	protected void setMeanings(String meanings) {
		this.meanings = meanings;
	}

	protected String getSynonyms() {
		return synonyms;
	}

	protected void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

	protected void setHolonyms(String holonyms) {
		this.holonyms = holonyms;
	}

	protected String getHolonyms() {
		return this.holonyms;
	}

	protected String getAntonyms() {
		return antonyms;
	}

	protected void setAntonyms(String antonyms) {
		this.antonyms = antonyms;
	}

	protected String getQuotations() {
		return this.quotations;
	}

	protected void setQuotations(String quotations) {
		this.quotations = quotations;
	}

	protected String getPronunciations() {
		return pronunciations;
	}

	protected void setPronunciations(String pronunciations) {
		this.pronunciations = pronunciations;
	}

	
	
	protected boolean isWordNotFound() {
		return wordNotFound;
	}

	protected void setWordNotFound(boolean wordNotFound) {
		this.wordNotFound = wordNotFound;
	}
	@Override
	public String toString() {
		// TODO Auto-generated catch block
		String myToString = "";
		if(wordNotFound == false){
			 myToString = "You searched for: " + this.getInputText() + "\n" +
					 "*****************************\n" +
					"Number of characters: " + this.getLength() + "\n" +
					"Language: " + this.getLanguage() + "\n" + 
					"Language Code: " + this.getLanguageCode() + "\n" +
					"*****************************\n" +
					"Pronunciations: \n" + this.getPronunciations() + "\n\n" +
					"*****************************\n" +
					"Meaning: \n" + this.getMeanings() + "\n" + 
					"*****************************\n" +
					"Usage/Sentences: \n" + this.getUsages() + "\n" +
					"*****************************\n" +
					"Quotations: \n" + this.getQuotations() + "\n\n" +
					"*****************************\n" +
					"Synonyms: \n" + this.getSynonyms() + "\n" +
					"*****************************\n" +
					"Antonyms: \n" + this.getAntonyms() + "\n" +
					"*****************************\n" +
					"Holonyms: \n" + this.getHolonyms() + "\n\n" +
					"*****************************\n" +
					"Hypernyms: \n" + this.getHypernyms() + "\n\n" +
					"*****************************\n" +
					"Hyponyms: \n" + this.getHyponyms() + "\n\n" +
					"*****************************\n" +
					"Meronyms: \n" + this.getMeronyms() + "\n\n";
		}else if(wordNotFound == true){
			myToString = "You searched for: " + this.getInputText() + "\n" +
					"Not found in Wikitionary!";
		}
		return myToString;
	}
	public void print(){
		if(this.toString() != null)
			System.out.println(this.toString());
	}
}// class Word ends here.
