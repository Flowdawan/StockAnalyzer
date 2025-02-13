package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import stockanalyzer.ctrl.Controller;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		try{
			ctrl.process("CSCO");
		}		catch (YahooIOException ye){
			System.out.println(ye.getMessage());
		}
	}

	public void getDataFromCtrl2()  {
		try{
			ctrl.process("MSFT");
		}		catch (YahooIOException ye){
			System.out.println(ye.getMessage());
		}
	}

	public void getDataFromCtrl3(){
		try{
			ctrl.process("AMZN");
		}		catch (YahooIOException ye){
			System.out.println(ye.getMessage());
		}
	}

	public void getDataForCustomInput() {
		try{
			ctrl.process(readLine());
		}		catch (YahooIOException ye){
			System.out.println(ye.getMessage());
		}

	}

	public void getAnalysis() {
		try{
			ctrl.process("analysis");
		}		catch (YahooIOException ye){
			System.out.println(ye.getMessage());
		}

	}
	public void downloadDataSq() {
		try{
			ctrl.process("downSq");
		}		catch (YahooIOException ye){
			System.out.println(ye.getMessage());
		}
	}

	public void downloadDataPa() {
		try{
			ctrl.process("downPa");
		}		catch (YahooIOException ye){
			System.out.println(ye.getMessage());
		}
	}

	public void downloadDataSpeed() {
		try{
			ctrl.process("speedTest");
		}		catch (YahooIOException ye){
			System.out.println(ye.getMessage());
		}
	}

	public void start() throws YahooIOException {
			Menu<Runnable> menu = new Menu<>("User Interface");
			menu.setTitel("Wählen Sie aus:");
			menu.insert("a", "Choice Cisco Systems, Inc.", this::getDataFromCtrl1);
			menu.insert("b", "Choice Amazon.com, Inc", this::getDataFromCtrl2);
			menu.insert("c", "Choice Microsoft Corporation", this::getDataFromCtrl3);
			menu.insert("d", "Choice User Input:", this::getDataForCustomInput);
			menu.insert("e", "Get analysis to your tickers:", this::getAnalysis);
			menu.insert("f", "Download Data as JSon (sequentiell)", this::downloadDataSq);
			menu.insert("g", "Download Data as JSon (parallel)", this::downloadDataPa);
			menu.insert("h", "Download Data as SpeedTest (sequentiell & parallel)", this::downloadDataSpeed);


			menu.insert("q", "Quit", null);
			Runnable choice;
			while ((choice = menu.exec()) != null) {
				choice.run();
			}
			System.out.println("Program finished");
	}

	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
			System.out.println("Die Eingabe war Fehlerhaft!");
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
