package com.codopedia.dictionary1;

import java.util.ArrayList;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class LanguageCodeDetection {
	
	//String profileDirectory = "/profiles/"; relative directory ?
	String profileDirectory = "/Users/macbookproretina/Dropbox/code/MacMachine/javaCore/JohnSonmezMine/Langopedia/profiles/";
	String textToDetect = "";
	
	public LanguageCodeDetection() {
		this("Jag älskar dig");
	}
	
	public LanguageCodeDetection(String textToDetect) {
		super();
		setTextToDetect(textToDetect);
		try {
			init(profileDirectory);
		} catch (LangDetectException e) {
			e.printStackTrace();
		}
	}//constructor ends here.
	
	public ArrayList<Language> detectPossibleLanguagesList() throws LangDetectException{
		Detector detector = DetectorFactory.create();
		detector.append(this.getTextToDetect());
		return detector.getProbabilities();
	}
	
	public String detectLanguageOfHighestProbability() throws LangDetectException{
		Detector detector = DetectorFactory.create();
		detector.append(this.getTextToDetect());
		return detector.detect();
	}
	
	private void init(String profileDriectory) throws LangDetectException{
		DetectorFactory.loadProfile(profileDirectory);
	}

	private String getTextToDetect() {
		return textToDetect;
	}

	private void setTextToDetect(String textToDetect) {
		this.textToDetect = textToDetect;
	}
}//class LanguagecodeDetection ends here.
