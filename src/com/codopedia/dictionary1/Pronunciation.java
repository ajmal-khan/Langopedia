package com.codopedia.dictionary.pronunciation;

import java.util.ArrayList;
import java.util.List;

import de.tudarmstadt.ukp.jwktl.api.IPronunciation;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;

public class Pronunciation {

	protected String soundFileAddress;
	protected String wordToSearch;
	protected List<String> phoneticTranscription;

	// constructor with no argument.
	public Pronunciation() {
		phoneticTranscription = new ArrayList<>();
		soundFileAddress = "";
		wordToSearch = "";
	}

	protected void setPhoneticTranscriptionList(List<String> phoneticTranscriptionList) {
		this.phoneticTranscription = phoneticTranscriptionList;
	}

	private void addItemToPronunciationList(String p) {
		if (p != null && !p.isEmpty())
			phoneticTranscription.add(new String(p));
		else {
			// ???? means pronunciation is missing for this word.
			phoneticTranscription.add(new String("????"));
		}
	}

	public String getSoundFileAddress() {
		return soundFileAddress;
	}

	private void setSoundFileAddress(String soundFileAddress) {
		this.soundFileAddress = soundFileAddress;
	}

	public List<String> getPhoneticTranscription() {
		return phoneticTranscription;
	}

	public String getWordToSearch() {
		return wordToSearch;
	}

	private void setWordToSearch(String wordToSearch) {
		this.wordToSearch = wordToSearch;
	}

	private void setPhoneticTranscription(IWiktionaryEntry entry) {
		List<IPronunciation> pronunciation = entry.getPronunciations();
		if (pronunciation != null && !pronunciation.isEmpty()) {
			pronunciation.forEach(p -> {
				if (p != null && !p.getText().trim().isEmpty()) {
					String temp1 = p.getText().trim();
					String temp2 = "";
					if (temp1.contains("/")) {
						temp2 = temp1.substring(temp1.indexOf("/") + 1);
					}
					if (!temp2.isEmpty() && temp2.contains("/")) {
						temp2 = temp2.substring(0, temp2.indexOf("/"));
						addItemToPronunciationList(temp2);
					} else {
						addItemToPronunciationList(temp1);
					}
				}
			});
		} else {
			addItemToPronunciationList("");
		}
	}// method setPhoneticTranscription ends here.

	public void createPronunciationSingleWord(IWiktionaryEntry entry) {
		setWordToSearch(entry.getWord());
		setPhoneticTranscription(entry);
		setSoundFileAddress(""); // To Do
	}

	public void printPronunciationSingleWord() {
		List<String> phoneticTranscription = getPhoneticTranscription();
		if (phoneticTranscription != null && !phoneticTranscription.isEmpty()) {
			phoneticTranscription.forEach(p -> System.out.println(p));
		} else {
			System.out.println("No pronuncation found for (" + getWordToSearch() + ").");
		}
	}// method printPronunciationSingleWord ends here.
}// class Pronunciation ends here.
