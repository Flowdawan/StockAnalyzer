package stockanalyzer.ctrl.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//callable is like runabble but with return value

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> tickers) {

        //create the pool
        ExecutorService executor = Executors.newFixedThreadPool(15);
        //submit the tasks for execution
        List<Future> allFutures = new ArrayList<>();

        for(String str : tickers) {
            allFutures.add(executor.submit(() -> saveJson2File(str)));
            allFutures.add(executor.submit(() -> saveJson2File(str)));
        }

        for (int i = 0; i < tickers.size(); i++) {
            Future<String> future = allFutures.get(i);
            try {
                System.out.println("Aktie mit dem Namen : " + future.get().substring(DIRECTORY_DOWNLOAD.length()) + " wurde gedownloadet!"); //blocking
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return allFutures.size();
    }
}

/*

class Task implements Callable<String> {
    private String ticker;

    public Task(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String call() throws Exception {
        SequentialDownloader sq = new SequentialDownloader();
        return sq.saveJson2File(ticker);
    }

}*/


