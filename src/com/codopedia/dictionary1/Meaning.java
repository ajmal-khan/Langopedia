package com.codopedia.dictionary1;

import java.util.ArrayList;
import java.util.List;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionarySense;
import de.tudarmstadt.ukp.jwktl.api.WiktionaryFormatter;

public class Meaning {
	String word;
	List<String> meanings;
	IWiktionaryEntry entry;
	
	public Meaning(IWiktionaryEntry entry){
		meanings = new ArrayList<>();
		this.entry = entry;
		this.setEntry(entry);
		setWord(entry.getWord().trim());
	}
	private IWiktionaryEntry getEntry(){
		return this.entry;
	}
	private void setEntry(IWiktionaryEntry entry2){
		this.entry = entry2;
	}
	public void searchMeaning(){
		List<String> meanings = new ArrayList<String>();
		for (IWiktionarySense sense : getEntry().getSenses()) {
			String str = WiktionaryFormatter.instance().formatHeader(sense);
			if(str.contains("]")){
				str = str.substring(str.indexOf("]") + 2).trim();
			}
			if (str != null && !str.isEmpty()){
				meanings.add(new String(str));
			}
		}
		this.setMeanings(meanings);
	}//method searchMeaning ends here.
	
	private void setWord(String input) {
		this.word = input;
	}
	public String getWord(){
		return word;
	}
	private List<String> getMeanings() {
		return meanings;
	}
	private void setMeanings(List<String> meanings) {
		this.meanings = meanings;
	}
	
	@Override
	public String toString(){
		// TODO Auto-generated method stub
		String str = "";
		for (String m : this.getMeanings()) {
			str += m + "\n";
		}
		str = str != null && !str.isEmpty() ? str : "????";
		return str;
	}
	public void printMeanings(){
		System.out.println("Meaning(s) of " + this.getWord() + " :");
		System.out.println(this.toString().contains("????") ? "Meaning not found!" : this.toString());
	}
}//class Meaning ends here.
