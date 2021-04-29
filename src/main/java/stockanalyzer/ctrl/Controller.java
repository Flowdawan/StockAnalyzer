package stockanalyzer.ctrl;

//Cisco Systems, Inc. (CSCO)
//Amazon.com, Inc. (AMZN)
//Microsoft Corporation (MSFT)

import stockanalyzer.ui.YahooIOException;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Controller {
    List<String> myTickers = new ArrayList<>();

    public void process(String ticker) throws YahooIOException {
        System.out.println("Start process");

        myTickers.add(ticker);

        YahooResponse response = loadData();
        QuoteResponse myQuotes = response.getQuoteResponse();

        System.out.println("--------------Der höchste Kurs von deinen Aktien in den letzten 10 Tagen war:--------------");
        getHighest(myQuotes);

        System.out.println("--------------Der durchschnittliche Kurs von deinen Aktien in den letzten 10 Tagen war:--------------");
        getAverage(myQuotes);

        System.out.println("--------------Die Anzahl der derzeitigen Datensätze:--------------");
        System.out.println(getDataQuantity(myQuotes));

    }

    private YahooResponse loadData() throws YahooIOException {
        YahooFinance yf = new YahooFinance();
        return yf.getCurrentData(myTickers);
    }

    public void getHighest(QuoteResponse quotes) {
        quotes.getResult().stream().forEach(quote -> System.out.println("Name der Aktie: " + quote.getShortName() + " Höchster Kurs der letzten 10 Tage: " + quote.getFiftyTwoWeekHigh()));
    }

    public void getAverage(QuoteResponse quotes) {
        quotes.getResult().stream().forEach(quote -> System.out.println("Name der Aktie: " + quote.getShortName() + " Durchschnittskurs der letzten 10 Tage: " + quote.getAverageDailyVolume10Day()));
    }

    public long getDataQuantity(QuoteResponse quotes) {
        quotes.getResult().stream().forEach(quote -> System.out.println());
        return 0;

    }


}
