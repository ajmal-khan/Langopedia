package com.codopedia.dictionary1;

import java.util.HashMap;

public class LanguageFromCode {

	HashMap<String, String> giveCodeGetLanguageHashMap = new HashMap<String, String>();
	String[] codesArraySeparatedByComma;

	String str = "ab,Abkhaz:aa,Afar:af,Afrikaans:ak,Akan:sq,Albanian:am,Amharic:ar,Arabic:an,Aragonese:hy,Armenian:as,Assamese:av,Avaric:ae,Avestan"
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

	private void makeHashMap() {
		codesArraySeparatedByComma = str.split(":");
		String key, value;
		for (String pair : codesArraySeparatedByComma) {
			key = pair.substring(0, str.indexOf(","));
			value = pair.substring(str.indexOf(",") + 1);
			giveCodeGetLanguageHashMap.put(key, value);
		}
	}

	public LanguageFromCode(){
		makeHashMap();
	}
	public String getMeLanguageFromCode(String twoLetterdCode) {
		return giveCodeGetLanguageHashMap.get(twoLetterdCode);
	}

}// class LanguageFromCode ends here.