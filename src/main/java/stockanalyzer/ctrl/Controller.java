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
		Quote myQuote = null;
		YahooFinance yf = null;
	public void process(String ticker) {
		System.out.println("Start process");

		List<String> myList = new ArrayList<>();
		myList.add(ticker);
		yf = new YahooFinance();
		yf.requestData(myList);
		yf.getCurrentData(myList);
		Asset as = new Asset();
		myQuote = new Quote( as.getDate() , 1.0);
		as.addQuote(myQuote);
		System.out.println(as.getName());
		yf.fetchAssetName(as);
		System.out.println(as.getName());
		System.out.println(myQuote);
		System.out.println("Du bekommst nun die Aktien von: " + ticker);

		//TODO implement Error handling

		//TODO implement methods for
		//1) Daten laden
		//2) Daten Analyse

	}
	

	public Object getData(String searchString) {

		
		return null;
	}


	public void closeConnection() {
		
	}
}
