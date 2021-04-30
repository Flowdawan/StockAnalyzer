//https://github.com/Flowdawan/StockAnalyzer.git

import stockanalyzer.ui.UserInterface;
import stockanalyzer.ui.YahooIOException;

public class MCP {

	public static void main(String args[]) throws YahooIOException {
		UserInterface ui = new UserInterface();
		ui.start();
	}
}
