package com.codopedia.dictionary1;

import java.util.ArrayList;
import java.util.List;

import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;

public class Usage {
	String word;
	List<String>usages;
	IWiktionaryEntry entry;
	
	public Usage(IWiktionaryEntry entry){
		this.entry = entry;
		this.setWord(entry.getWord().toString().trim());
		usages = new ArrayList<>();
	}
	private void setWord(String word){
		this.word = word;
	}
	public List<String> getUsages() {
		return usages;
	}
	public IWiktionaryEntry getEntry() {
		return entry;
	}
	public void setEntry(IWiktionaryEntry entry) {
		this.entry = entry;
	}
	public void setUsages(List<String> usages) {
		this.usages = usages;
	}
	
	public void searchUsages(){
		List<String> usagesList = new ArrayList<>();
		List<IWikiString> usagesOfWord = this.getEntry().getExamples();
		if (usagesOfWord != null && !usagesOfWord.isEmpty()) {
			for (IWikiString entryExample : usagesOfWord) {
				if (entryExample != null && !"".equals(entryExample)) {
					String temp = entryExample.getPlainText().trim();
					usagesList.add(temp);
				}
			}
		}
		this.setUsages(usagesList);
	}//method searchUsage ends here.
	
	public String toString(){
		String str = "";
		if(this.getUsages() != null && !this.getUsages().isEmpty()){
			for (String s : this.getUsages()) {
				if(s != null && !s.isEmpty()){
					str += s + "\n";
				}
			}
		}else{
			str += "????";
		}
		return str;
	}
	
	public void printUsages(){
		if(this.toString().contains("????")){
			System.out.println("No usage(s) found!");
		}else{
			System.out.println(this.toString());
		}
	}
	
}//class Usage ends here.
