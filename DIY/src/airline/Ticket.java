package airline;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Ticket {
	String from;
	String to;
	Date departure_date;
	Date arrival_date;
	int no_of_passengers;
	String status;
	int ticket_no;
	public String toString()
	{
		String s="\nTicket Number:"+ticket_no+"\nFrom:"+from+"\n"+"To:"+to+"\n"+"Departure Date:"+departure_date+"\n"+"Arrival Date:"+arrival_date+"\nNumber of passengers:"+no_of_passengers+"\nStatus:"+status;
		return s;
	}
	public static int ticketno()
	{
		String path="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\ticketno.txt";
		String path2="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\temp.csv";
		BufferedReader reader=null;
		FileWriter filewriter=null;
		File old=new File(path);
		File newf=new File(path2);
		int t=0;
		try
		{
			reader=new BufferedReader(new FileReader(path));
			filewriter=new FileWriter(path2);
			String[] fields=reader.readLine().split(",");
			t=Integer.parseInt(fields[0])+1;
			filewriter.append(Integer.toString(t));
			filewriter.append(",");
			filewriter.append(fields[1]);
			filewriter.append(",");
			filewriter.append(fields[2]);
			filewriter.append(",");
			filewriter.append(fields[3]);
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
		
		return t;
		
	}
	public Date str_to_date(String input)
	{
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy"); 
		Date t = null;
//		if (input == null || !input.matches(""))
//			{System.out.println("Enter a valid date");
//	        return t;}
	      try {
	         t = ft.parse(input); 
	         //System.out.println(t); 
	      } catch (ParseException e) { 
	         System.out.println("Unparseable using " + ft); 
	      }
		return t;
	}
	
	public Ticket(String from,String to,Date departure_date,Date arrival_date,int no_of_passengers) {
		// TODO Auto-generated constructor stub
		this.from=from;
		this.to=to;
		this.departure_date=departure_date;
		this.arrival_date=arrival_date;
		this.no_of_passengers=no_of_passengers;
		this.status="Booked";
		this.ticket_no=ticketno();
		//System.out.println(this);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
