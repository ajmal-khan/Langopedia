package com.codopedia.dictionary1;

import java.util.HashMap;

public class LanguageFromCode {

	private HashMap<String, String> languageWithCode; 
	private String[] codesArraySeparatedByComma;
	private String languageCode,language;

	public LanguageFromCode() {
		super();
		languageWithCode = new HashMap<String, String>();
		this.languageCode = "";
		language = "";
		makeHashMap();
	}

	private String threeLetteredCode = "ab,Abkhaz:aa,Afar:af,Afrikaans:ak,Akan:sq,Albanian:am,Amharic:ar,Arabic:an,Aragonese:hy,Armenian:as,Assamese:av,Avaric:ae,Avestan"
			+ ":ay,Aymara:az,Azerbaijani:bm,Bambara:ba,Bashkir:eu,Basque:be,Belarusian:bn,\"Bengali, Bangla\":bh,Bihari:bi,Bislama:bs,Bosnian:br,Breton"
			+ ":bg,Bulgarian:my,Burmese:ca,Catalan:ch,Chamorro:ce,Chechen:ny,\"Chichewa, Chewa, Nyanja\":zh,Chinese:cv,Chuvash:kw,Cornish:co,Corsican:cr,Cree"
			+ ":hr,Croatian:cs,Czech:da,Danish:dv,\"Divehi, Dhivehi, Maldivian\":nl,Dutch:dz,Dzongkha:en,English:eo,Esperanto:et,Estonian:ee,Ewe:fo,Faroese"
			+ ":fj,Fijian:fi,Finnish:fr,French:ff,\"Fula, Fulah, Pulaar, Pular\":gl,Galician:ka,Georgian:de,German:el,Greek (modern):gn,Guaraní:gu,Gujarati"
			+ ":ht,\"Haitian, Haitian Creole\":ha,Hausa:he,Hebrew (modern):hz,Herero:hi,Hindi:ho,Hiri Motu:hu,Hungarian:ia,Interlingua:id,Indonesian"
			+ ":ie,Interlingue:ga,Irish:ig,Igbo:ik,Inupiaq:io,Ido:is,Icelandic:it,Italian:iu,Inuktitut:ja,Japanese:jv,Javanese:kl,\"Kalaallisut, Greenlandic\""
			+ ": kn,Kannada:kr,Kanuri:ks,Kashmiri:kk,Kazakh:km,Khmer:ki,\"Kikuyu, Gikuyu\":rw,Kinyarwanda:ky,Kyrgyz:kv,Komi:kg,Kongo:ko,Korean:ku,Kurdish"
			+ ": kj,\"Kwanyama, Kuanyama\":la,Latin:lb,\"Luxembourgish, Letzeburgesch\":lg,Ganda:li,\"Limburgish, Limburgan, Limburger\":ln,Lingala:lo,Lao"
			+ ":lt,Lithuanian:lu,Luba-Katanga:lv,Latvian:gv,Manx:mk,Macedonian:mg,Malagasy:ms,Malay:ml,Malayalam:mt,Maltese:mi,Māori:mr,Marathi (Marāṭhī)"
			+ ":mh,Marshallese:mn,Mongolian:na,Nauruan:nv,\"Navajo, Navaho\":nd,Northern Ndebele:ne,Nepali:ng,Ndonga:nb,Norwegian Bokmål:nn,Norwegian Nynorsk"
			+ ":no,Norwegian:ii,Nuosu:nr,Southern Ndebele:oc,Occitan:oj,\"Ojibwe, Ojibwa\":cu,\"Old Church Slavonic, Church Slavonic, Old Bulgarian\":om,Oromo"
			+ ":or,Oriya:os,\"Ossetian, Ossetic\":pa,\"Panjabi, Punjabi\":pi,Pāli:fa,Persian (Farsi):pl,Polish:ps,\"Pashto, Pushto\":pt,Portuguese:qu,Quechua"
			+ ":rm,Romansh:rn,Kirundi:rc,\"Reunionese, Reunion Creole\":ro,Romanian:ru,Russian:sa,Sanskrit (Saṁskṛta):sc,Sardinian:sd,Sindhi:se,Northern Sami"
			+ ":sm,Samoan:sg,Sango:sr,Serbian:gd,\"Scottish Gaelic, Gaelic\":sn,Shona:si,\"Sinhalese, Sinhala\":sk,Slovak:sl,Slovene:so,Somali:st,Southern Sotho"
			+ ":es,Spanish:su,Sundanese:sw,Swahili:ss,Swati:sv,Swedish:ta,Tamil:te,Telugu:tg,Tajik:th,Thai:ti,Tigrinya:bo,\"Tibetan Standard, Tibetan, Central\""
			+ ":tk,Turkmen:tl,Tagalog:tn,Tswana:to,Tonga (Tonga Islands):tr,Turkish:ts,Tsonga:tt,Tatar:tw,Twi:ty,Tahitian:ug,Uyghur:uk,Ukrainian:ur,Urdu:uz,Uzbek"
			+ ":ve,Venda:vi,Vietnamese:vo,Volapük:wa,Walloon:cy,Welsh:wo,Wolof:fy,Western Frisian:xh,Xhosa:yi,Yiddish:yo,Yoruba:za,\"Zhuang, Chuang\":zu,Zulu";
	
	private String[] twoLetteredCodes = {"ab", "aa", "af", "ak", "sq", "am", "ar", "an", "hy", "as", "av", "ae", "ay", "az", "bm", "ba", "eu", "be", "bn", "bh", "bi", "bs", "br", "bg", "my", "ca", "ch", "ce", "ny", "zh", "cv", "kw", "co", "cr", "hr", "cs", "da", "dv", "nl", "dz", "en", "eo", "et", "ee", "fo", "fj", "fi", "fr", "ff", "gl", "ka", "de", "el", "gn", "gu", "ht", "ha", "he", "hz", "hi", "ho", "hu", "ia", "id", "ie", "ga", "ig", "ik", "io", "is", "it", "iu", "ja", "jv", "kl", "kn", "kr", "ks", "kk", "km", "ki", "rw", "ky", "kv", "kg", "ko", "ku", "kj", "la", "lb", "lg", "li", "ln", "lo", "lt", "lu", "lv", "gv", "mk", "mg", "ms", "ml", "mt", "mi", "mr", "mh", "mn", "na", "nv", "nd", "ne", "ng", "nb", "nn", "no", "ii", "nr", "oc", "oj", "cu", "om", "or", "os", "pa", "pi", "fa", "pl", "ps", "pt", "qu", "rm", "rn", "rc", "ro", "ru", "sa", "sc", "sd", "se", "sm", "sg", "sr", "gd", "sn", "si", "sk", "sl", "so", "st", "es", "su", "sw", "ss", "sv", "ta", "te", "tg", "th", "ti", "bo", "tk", "tl", "tn", "to", "tr", "ts", "tt", "tw", "ty", "ug", "uk", "ur", "uz", "ve", "vi", "vo", "wa", "cy", "wo", "fy", "xh", "yi", "yo", "za", "zu"};
	private String[] threeLetteredCodes = {"abk", "aar", "afr", "aka", "sqi", "amh", "ara", "arg", "hye", "asm", "ava", "ave", "aym", "aze", "bam", "bak", "eus", "bel", "ben", "bih", "bis", "bos", "bre", "bul", "mya", "cat", "cha", "che", "nya", "zho", "chv", "cor", "cos", "cre", "hrv", "ces", "dan", "div", "nld", "dzo", "eng", "epo", "est", "ewe", "fao", "fij", "fin", "fra", "ful", "glg", "kat", "deu", "ell", "grn", "guj", "hat", "hau", "heb", "her", "hin", "hmo", "hun", "ina", "ind", "ile", "gle", "ibo", "ipk", "ido", "isl", "ita", "iku", "jpn", "jav", "kal", "kan", "kau", "kas", "kaz", "khm", "kik", "kin", "kir", "kom", "kon", "kor", "kur", "kua", "lat", "ltz", "lug", "lim", "lin", "lao", "lit", "lub", "lav", "glv", "mkd", "mlg", "msa", "mal", "mlt", "mri", "mar", "mah", "mon", "nau", "nav", "nde", "nep", "ndo", "nob", "nno", "nor", "iii", "nbl", "oci", "oji", "chu", "orm", "ori", "oss", "pan", "pli", "fas", "pol", "pus", "por", "que", "roh", "run", "rcf", "ron", "rus", "san", "srd", "snd", "sme", "smo", "sag", "srp", "gla", "sna", "sin", "slk", "slv", "som", "sot", "spa", "sun", "swa", "ssw", "swe", "tam", "tel", "tgk", "tha", "tir", "bod", "tuk", "tgl", "tsn", "ton", "tur", "tso", "tat", "twi", "tah", "uig", "ukr", "urd", "uzb", "ven", "vie", "vol", "wln", "cym", "wol", "fry", "xho", "yid", "yor", "zha", "zul"};
	
	public String getMe2LetteredFrom3Lettered(String threeLettered){
		int index = -1;
		for(int i = 0; i < threeLetteredCodes.length; i++){
			if(threeLetteredCodes[i].equals(threeLettered))
				index = i;
		}
		if(index > 0)
			return twoLetteredCodes[index];
		return "????";
	}
	
	private void makeHashMap() {
		codesArraySeparatedByComma = threeLetteredCode.split(":");
		String key, value;
		for (String pair : codesArraySeparatedByComma) {
			key = pair.substring(0, threeLetteredCode.indexOf(","));
			value = pair.substring(threeLetteredCode.indexOf(",") + 1);
			languageWithCode.put(key, value);
		}
	}

	public String getMeLanguageFromCode(String twoLetterdCode) {
		this.setLangaugeCode(twoLetterdCode);
		this.setLanguage(languageWithCode.get(this.getLangaugeCode()));
		return this.getLanguage();
	}

	private String getLangaugeCode() {
		return languageCode;
	}

	private String getLanguage() {
		return language;
	}
	
	public void setLangaugeCode(String languageCode){
		this.languageCode = languageCode;
	}
	
	private void setLanguage(String language){
		this.language = language;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "Langauge: " + this.getLanguage() + ", (" + getLangaugeCode() + ")";
		return str;
	}
}// class LanguageFromCode ends here.
