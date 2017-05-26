package com.techelevator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

	public LocalDateTime ldt = LocalDateTime.now();

	public void printActionLog(String message, BigDecimal previousBalance, BigDecimal currentBalance) { 
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
		try(FileWriter fw = new FileWriter("log.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter writer = new PrintWriter(bw))
			{
			String format = "%-21s";
				writer.print(timeFormat.format(ldt) + "\t    ");
			    writer.format(format, message);
			    writer.println("\t$" + previousBalance + "\t$" + currentBalance);
			   
			} catch (IOException e) {
			   e.printStackTrace();
			}
	}
}
