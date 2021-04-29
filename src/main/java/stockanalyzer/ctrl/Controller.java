package stockanalyzer.ctrl;

//Cisco Systems, Inc. (CSCO)
//Amazon.com, Inc. (AMZN)
//Microsoft Corporation (MSFT)

import stockanalyzer.ui.YahooIOException;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;
import yahoofinance.Stock;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

public class Controller {

    List<String> myTickers = new ArrayList<>();
    Stock stock = null;

    public void process(String ticker) throws YahooIOException {
        System.out.println("Start process");

        System.out.println(ticker);

        System.out.println("--------------Die History der derzeitigen Datensätze:--------------");

        for (String str : myTickers) {
            getHighestHistory(str);
        }
        System.out.println("--------------Der durchschnittliche Kurs von deinen Aktien in den letzten 10 Tagen war:--------------");
        for (String str : myTickers) {
            getHighestHistory(str);
        }


       /*
        myTickers.add(ticker);

        YahooResponse response = loadData();
        QuoteResponse myQuotes = response.getQuoteResponse();


        System.out.println("--------------Der höchste Kurs von deinen Aktien in den letzten 10 Tagen war:--------------");
        getHighest(myQuotes);

        System.out.println("--------------Der durchschnittliche Kurs von deinen Aktien in den letzten 10 Tagen war:--------------");
        getAverage(myQuotes);

        System.out.println("--------------Die Anzahl der derzeitigen Datensätze:--------------");
        System.out.println(getDataQuantity(myQuotes));
*/
    }


    public void getHighestHistory(String myTicker) {
        try {
            stock = yahoofinance.YahooFinance.get(myTicker);
            stock.getHistory().forEach(quote -> quote.getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAverageHistorical(String myTicker) {
        try {
            stock = yahoofinance.YahooFinance.get(myTicker);
            stock.getHistory().forEach(quote -> System.out.println(quote.getDate().toInstant()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDataQuantityHistory(String myTicker) {
        try {
            stock = yahoofinance.YahooFinance.get(myTicker);
            stock.getHistory().forEach(quote -> System.out.println(quote.getDate().toInstant()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
        private YahooResponse loadData () throws YahooIOException {
            YahooFinance yf = new YahooFinance();
            return yf.getCurrentData(myTickers);
        }

        public void getHighest (QuoteResponse quotes){
            quotes.getResult().stream().forEach(quote -> System.out.println("Name der Aktie: " + quote.getShortName() + " Höchster Kurs der letzten 10 Tage: " + quote.getFiftyTwoWeekHigh()));
        }

        public void getAverage (QuoteResponse quotes){
            quotes.getResult().stream().forEach(quote -> System.out.println("Name der Aktie: " + quote.getShortName() + " Durchschnittskurs der letzten 10 Tage: " + quote.getAverageDailyVolume10Day()));
        }

        public long getDataQuantity (QuoteResponse quotes){
            quotes.getResult().stream().forEach(quote -> System.out.println());
            return 0;
        }

*/