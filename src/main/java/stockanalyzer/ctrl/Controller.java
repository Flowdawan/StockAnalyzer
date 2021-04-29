package stockanalyzer.ctrl;

//Cisco Systems, Inc. (CSCO)
//Amazon.com, Inc. (AMZN)
//Microsoft Corporation (MSFT)

import yahooApi.YahooFinance;
import yahooApi.beans.Asset;
import yahooApi.beans.Quote;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller{
	List<String> myList = new ArrayList<>();

	public void process(String ticker) {
		System.out.println("Start process");
		YahooFinance yf = (YahooFinance) getData(ticker);

		long count_daten = yf.getCurrentData(myList).getQuoteResponse().getResult().get(0).getAskSize();

		System.out.println("Du bekommst nun die Aktien von: " + yf.getCurrentData(myList).getQuoteResponse().getResult().get(0).getLongName());

		System.out.println("Es gibt " + count_daten + " Datensätze");
		//TODO implement Error handling

		//TODO implement methods for
		//1) Daten laden
		//2) Daten Analyse

	}



	public Object getData(String searchString) {
		myList.add(searchString);
		Quote myQuote = null;
		YahooFinance yf = null;
		Asset asset = null;
		Date date = new Date();
		yf = new YahooFinance();
		yf.requestData(myList);
		asset = new Asset(searchString);
		myQuote = new Quote(date, 1.1);
		asset.addQuote(myQuote);

		yf.fetchAssetName(asset);

		System.out.println("Last quuote: " + asset.getLastQuote());
		return yf;
	}

}
