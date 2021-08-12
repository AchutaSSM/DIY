package airline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Report {

	static int total_sales=0;
	static int no_of_tickets=0;
	static float avg_ticket_price=0;
	public static void change_report(int fare,char c)
	{
			String path="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\ticketno.txt";
			String path2="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\temp.csv";
			BufferedReader reader=null;
			FileWriter filewriter=null;
			File old=new File(path);
			File newf=new File(path2);
			try
			{
				reader=new BufferedReader(new FileReader(path));
				filewriter=new FileWriter(path2);
				String[] fields=reader.readLine().split(",");
				filewriter.append(fields[0]);
				filewriter.append(",");
				if(c=='r')
					total_sales=Integer.parseInt(fields[1])+fare;
				else if(c=='c')
					total_sales=Integer.parseInt(fields[1])-fare;
				filewriter.append(Integer.toString(total_sales));
				filewriter.append(",");
				if(c=='r')
					no_of_tickets=Integer.parseInt(fields[2])+1;
				else if(c=='c')
					no_of_tickets=Integer.parseInt(fields[2])-1;
				filewriter.append(Integer.toString(no_of_tickets));
				filewriter.append(",");
				avg_ticket_price=total_sales/no_of_tickets;
				filewriter.append(Float.toString(avg_ticket_price));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					filewriter.flush();
					filewriter.close();
					filewriter=null;
					reader.close();
					reader=null;
					old.delete();
					File dump=new File(path);
					newf.renameTo(dump);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
	}
	public static void readCSV()
	{
		BufferedReader reader=null;
		String line="";
		String path="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\ticketno.txt";
		try {
			reader=new BufferedReader(new FileReader(path));
			//reader.readLine();
			while((line=reader.readLine())!=null)
			{
				String[] fields=line.split(",");
				total_sales=Integer.parseInt(fields[1]);
				no_of_tickets=Integer.parseInt(fields[2]);
				avg_ticket_price=Float.parseFloat(fields[3]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				reader.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	public static void report()
	{	
		readCSV();
		System.out.println("Total sales:"+String.valueOf(total_sales));
		System.out.println("No. of tickets:"+String.valueOf(no_of_tickets));
		System.out.println("Average ticket price:"+String.valueOf(avg_ticket_price));
	}

}
