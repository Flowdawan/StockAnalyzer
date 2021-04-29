package stockanalyzer.ctrl;

//Cisco Systems, Inc. (CSCO)
//Amazon.com, Inc. (AMZN)
//Microsoft Corporation (MSFT)

import stockanalyzer.ui.YahooIOException;
import yahooApi.YahooFinance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller{
	List<String> myList = new ArrayList<>();

	public void process(String ticker) throws YahooIOException {
		/*
		System.out.println("Start process");
		YahooFinance yf = (YahooFinance) getData(ticker);

		long count_daten = yf.getCurrentData(myList).getQuoteResponse().getResult().get(0).getAskSize();

		System.out.println("Du bekommst nun die Aktien von: " + yf.getCurrentData(myList).getQuoteResponse().getResult().get(0).getLongName());

		System.out.println("Es gibt " + count_daten + " Datensätze");
		//TODO implement Error handling

		//TODO implement methods for
		//1) Daten laden
		//2) Daten Analyse
*/
	}

	public Object getData(String searchString) {

		return null;
	}

}
