package stockanalyzer.ctrl;

//Cisco Systems, Inc. (CSCO)
//Amazon.com, Inc. (AMZN)
//Microsoft Corporation (MSFT)

import stockanalyzer.ui.YahooIOException;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;
import yahoofinance.Stock;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockDividend;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    List<String> myTickers = new ArrayList<>();
    Stock stock = null;
    Calendar calender = null;

    public void process(String ticker) throws YahooIOException {
        //to get the data from the last 10 days
        calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_WEEK, -11);
        System.out.println("Start process");

        myTickers.add(ticker);

        System.out.println("--------------Der höchste Kurs der letzten 10 Tage von deinen Aktien: --------------");

        for (String str : myTickers) {
            System.out.println("Aktie: " + str + ", Kurs: " + Math.round(getHighestHistorical(str)*100.0)/100.0);
        }

        System.out.println("--------------Der durchschnittliche Kurs von deinen Aktien in den 10 letzten Tagen:--------------");

        for (String str : myTickers) {
            System.out.println("Aktie: " + str + ", Kurs: " + Math.round(getAverageHistorical(str)*100.0)/100.0);
        }

        System.out.println("--------------Die Anzahl der Datensätze in deinen aktien:--------------");

        for (String str : myTickers) {
            System.out.println("Datensätze von: "+ str +" sind: " + getDataQuantityHistorical(str));
        }


    }

    public double getHighestHistorical(String myTicker) {
        double result = 0;
        try {
            stock = yahoofinance.YahooFinance.get(myTicker);
            result = stock.getHistory(calender, Interval.DAILY).stream()
                    .mapToDouble(value -> value.getClose().doubleValue())
                    .max()
                    .orElse(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getAverageHistorical(String myTicker) {
        double result = 0;
        try {
            stock = yahoofinance.YahooFinance.get(myTicker);
            result = stock.getHistory(calender, Interval.DAILY).stream()
                    .mapToDouble(value -> value.getClose().doubleValue())
                    .average()
                    .orElse(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public double getDataQuantityHistorical(String myTicker) {
        double count = 0;
        try {
            stock = yahoofinance.YahooFinance.get(myTicker);
            count = stock.getHistory().stream()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;

    }
}



/*

        This was the old version of the exercise

        myTickers.add(ticker);

        YahooResponse response = loadData();
        QuoteResponse myQuotes = response.getQuoteResponse();


        System.out.println("--------------Der höchste Kurs von deinen Aktien in den letzten 10 Tagen war:--------------");
        getHighest(myQuotes);

        System.out.println("--------------Der durchschnittliche Kurs von deinen Aktien in den letzten 10 Tagen war:--------------");
        getAverage(myQuotes);

        System.out.println("--------------Die Anzahl der derzeitigen Datensätze:--------------");
        System.out.println(getDataQuantity(myQuotes));

        This was for the old version of the exercise too

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