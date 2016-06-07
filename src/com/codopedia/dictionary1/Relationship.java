package com.codopedia.dictionary.Relationships;

import java.util.ArrayList;
import java.util.List;

import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryRelation;
import de.tudarmstadt.ukp.jwktl.api.PartOfSpeech;
import de.tudarmstadt.ukp.jwktl.api.RelationType;

public class Relationship {

	protected List<IWiktionaryEntry> entriesOnThisPage;
	protected final PartOfSpeech partOfSpeech = PartOfSpeech.NOUN;
	RelationType relationType;
	
	public Relationship(List<IWiktionaryEntry> entriesOnThisPage) {
		relatedWordsList = new ArrayList<>();
		setEntriesOnThisPage(entriesOnThisPage);
	}
	
	private RelationType getRelationType() {
		return relationType;
	}

	private void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

	private List<String> relatedWordsList;

	private PartOfSpeech getPartOfSpeech() {
		return partOfSpeech;
	}

	private List<String> getRelatedWordsList() {
		return relatedWordsList;
	}

	private void setRelatedWordsList(String str) {
		this.relatedWordsList.add(new String(str));
	}

	private List<IWiktionaryEntry> getEntriesOnThisPage() {
		return entriesOnThisPage;
	}

	private void setEntriesOnThisPage(List<IWiktionaryEntry> entriesOnThisPage) {
		this.entriesOnThisPage = entriesOnThisPage;
	}

	@Override
	public String toString(){
		String relatedWords = "";
		if(this.getRelatedWordsList() != null && !this.getRelatedWordsList().isEmpty())
			for (String s : this.getRelatedWordsList()) {
				if(s != null && !s.isEmpty())
					relatedWords += s + ", ";
			}
		if(!relatedWords.isEmpty()){
			relatedWords = relatedWords.trim().substring(0, relatedWords.lastIndexOf(","));
		}
		
		return ( !relatedWords.isEmpty() ) ? relatedWords : "????" ;
	}
	
	public void searchRelatedRelationship(RelationType relationType) {
		setRelationType(relationType);
		if(this.getEntriesOnThisPage() != null && !this.getEntriesOnThisPage().isEmpty()){
			for (IWiktionaryEntry entry : this.getEntriesOnThisPage()){
				if(entry.getPartOfSpeech() != null && entry.getPartOfSpeech() == this.getPartOfSpeech()){
					for (IWiktionaryRelation relation : entry.getRelations(this.getRelationType())){
						this.setRelatedWordsList(relation.getTarget().trim());
					}
				}
			}
		}
	}
	
	public void print() {
		if(this.toString().contains("????")){
			System.out.println("No " + this.getRelationType().name() + "S found!");
		}else{
			System.out.println(this.toString());
		}
	}
}//class Relationship ends here.
