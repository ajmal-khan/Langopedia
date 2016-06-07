package com.codopedia.dictionary.quotations;

import java.util.ArrayList;
import java.util.List;

import de.tudarmstadt.ukp.jwktl.api.IQuotation;
import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionarySense;

public class Quotation {
	// String quotations;
	List<String> quotationsList;

	public Quotation() {
		quotationsList = new ArrayList<>();
	}

	public void searchQuotations(IWiktionaryEntry entry) {
		List<String> quotationsList = new ArrayList<>();
		IWiktionarySense sense = entry.getSense(1);/* Begins with 1 and not 0 */
		if (sense.getQuotations() != null) {
			for (IQuotation quotation : sense.getQuotations()) {
				if (quotation != null) {
					String thisQuotation = "";
					for (IWikiString line : quotation.getLines()) {
						// Check this one also
						// thisQuotation += line.getPlainText().toString();
						if (line != null && !line.getText().isEmpty())
							thisQuotation += line.getPlainText() + "\n";
							//thisQuotation += line.getText() + "\n";
					}
					// Appending the source of the quotation to this quotation.
					if (quotation.getSource() != null)
						if (!quotation.getSource().getText().isEmpty())
							thisQuotation += quotation.getSource().getPlainText();
							//thisQuotation += quotation.getSource().getText();
					if (thisQuotation != null && !thisQuotation.isEmpty())
						quotationsList.add(new String(thisQuotation));
				}
			}

		}
		this.setQuotationsList(quotationsList);
	}// method searchQuotations ends here.

	public List<String> getQuotationsList() {
		return quotationsList;
	}

	public void setQuotationsList(List<String> quotationsList) {
		this.quotationsList = quotationsList;
	}

	@Override
	public String toString() {
		String str = "";
		if (this.getQuotationsList() != null && !this.getQuotationsList().isEmpty()) {
			for (String s : this.getQuotationsList()) {
				str += s + "\n\n";
			}
		} else {
			str = "????";
		}
		return str;
	}

	public void printQuotations() {
		if (this.toString().contains("????")) {
			System.out.println("No quotations found!");
		} else {
			System.out.println(this.toString());
		}
	}

}// class Quotation ends here.
