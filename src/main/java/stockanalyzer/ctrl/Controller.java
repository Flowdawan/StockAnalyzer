package stockanalyzer.ctrl;

//Cisco Systems, Inc. (CSCO)
//Amazon.com, Inc. (AMZN)
//Microsoft Corporation (MSFT)

import stockanalyzer.ctrl.downloader.Downloader;
import stockanalyzer.ctrl.downloader.ParallelDownloader;
import stockanalyzer.ctrl.downloader.SequentialDownloader;
import stockanalyzer.ui.YahooIOException;

import yahoofinance.Stock;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.*;

public class Controller {
    final SequentialDownloader sqDownloader = new SequentialDownloader();
    final ParallelDownloader parallelDownloader = new ParallelDownloader();

    List<String> myTickers = new ArrayList<>();
    Stock stock = null;
    Calendar calender = null;


    public void process(String ticker) throws YahooIOException {
        System.out.println("Start process");
        switch (ticker) {

            case "speedTest":
                List<String> speedList = Arrays.asList("OMV.VI",
                        "EBS.VI", "DOC.VI", "SBO.VI", "RBI.VI", "VIG.VI", "TKA.VI", "VOE.VI", "FACC.VI", "ANDR.VI", "VER.VI",
                        "WIE.VI", "CAI.VI", "BG.VI", "POST.VI", "LNZ.VI", "UQA.VI", "SPI.VI", "ATS.VI", "IIA.VI", "AAPL", "UBER", "CSCO", "MSFT");
                final long timeStart = new Date().getTime();
                System.out.println("So viele futures gab es: " + downloadTickers(speedList, parallelDownloader));
                final long timeEnd = new Date().getTime() - timeStart;
                System.out.println("Laufzeit mit dem Parallel Downloader: " + (timeEnd) + " sek");

                final long timeStart2 = new Date().getTime();
                downloadTickers(speedList, sqDownloader);
                final long timeEnd2 = new Date().getTime() - timeStart2;
                System.out.println("Laufzeit mit dem Sequentiellen Downloader: " + (timeEnd2) + " sek");
                break;

            case "downPa":
                downloadTickers(myTickers, parallelDownloader);
                break;

            case "downSq":
                downloadTickers(myTickers, sqDownloader);
                break;

            case "analysis":
                //to get the data from the last 10 days
                calender = Calendar.getInstance();
                calender.add(Calendar.DAY_OF_WEEK, -11);

                System.out.println("--------------Der höchste Kurs der letzten 10 Tage von deinen Aktien: --------------");

                for (String str : myTickers) {
                    System.out.println("Aktie: " + str + ", Kurs: " + Math.round(getHighestHistorical(str) * 100.0) / 100.0);
                }

                System.out.println("--------------Der durchschnittliche Kurs von deinen Aktien in den 10 letzten Tagen:--------------");

                for (String str : myTickers) {
                    System.out.println("Aktie: " + str + ", Kurs: " + Math.round(getAverageHistorical(str) * 100.0) / 100.0);
                }

                System.out.println("--------------Die Anzahl der Datensätze in deinen aktien:--------------");

                for (String str : myTickers) {
                    System.out.println("Datensätze von: " + str + " sind: " + getDataQuantityHistorical(str));
                }

                break;

            default:
                if (!myTickers.contains(ticker)) {
                    myTickers.add(ticker);
                } else {
                    System.out.println("Der ticket ist schon enthalten!");
                }
                System.out.println("Hier eine liste von deinen Tickern: ");
                myTickers.forEach(System.out::println);
        }


    }


    public int downloadTickers(List<String> myTickers, Downloader myDownloader) {
        return myDownloader.process(myTickers);
        //myTickers.forEach(sqDownloader::saveJson2File);
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