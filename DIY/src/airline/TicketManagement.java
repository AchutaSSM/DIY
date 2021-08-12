
package airline;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


 
public class TicketManagement {

	private static Scanner sc;
	private static String filepath="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\Diy_ticket.csv";
	static String path2="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\temp.csv";
	public static void modifyCSV(String ticket_number,String from,String to,String departure_date,String arrival_date,String no_of_passengers,String status)
	{
		BufferedReader reader=null;
		FileWriter filewriter=null;
		File old=new File(filepath);
		File newf=new File(path2);
		String line;
		try {
			reader=new BufferedReader(new FileReader(filepath));
			filewriter=new FileWriter(path2);
			while((line=reader.readLine())!=null)
			{
				String[] fields=line.split(",");
				filewriter.append(fields[0]);
				filewriter.append(",");
				filewriter.append(fields[1]);
				filewriter.append(",");
				filewriter.append(fields[2]);
				filewriter.append(",");
				filewriter.append(fields[3]);
				filewriter.append(",");
				filewriter.append(fields[4]);
				filewriter.append(",");
				filewriter.append(fields[5]);
				filewriter.append(",");
				if(fields[0].compareTo(ticket_number)==0)
				filewriter.append("Cancelled");
				else
				filewriter.append(fields[6]);
				filewriter.append("\n");
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
				filewriter.flush();
				filewriter.close();
				filewriter=null;
				reader.close();
				reader=null;
				old.delete();
				File dump=new File(filepath);
				newf.renameTo(dump);
				Report.change_report(Integer.parseInt(no_of_passengers)*1500,'c');
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	private static void check_ticket(int t) {
		// TODO Auto-generated method stub
		BufferedReader reader=null;
		String line="";
		int flag=0;
		try {
			reader=new BufferedReader(new FileReader(filepath));
			while((line=reader.readLine())!=null)
			{
				String[] fields=line.split(",");
				if(Integer.parseInt(fields[0])==t)
				{
					flag=1;
					reader.close();
					modifyCSV(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],"Cancelled");
					System.out.println("Ticket Number:"+fields[0]+"\nFrom:"+fields[1]+"\n"+"To:"+fields[2]+"\n"+"Departure Date:"+fields[3]+"\n"+"Arrival Date:"+fields[4]+"\nNumber of passengers:"+fields[5]+"\nStatus:Cancelled"+"\n\n\n");
					System.out.println("Cancellation done");
					break;
				}
			}
			if(flag==0)
			{
				System.out.println("Invalid ticket number");
				return;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	public static void writeCSV(Ticket t)
	{
		FileWriter filewriter=null;
		DateFormat df=new SimpleDateFormat("dd-MM-yyyy");
		try
		{
			filewriter=new FileWriter(filepath,true);
			filewriter.append(Integer.toString(t.ticket_no));
			filewriter.append(",");
			filewriter.append(t.from);
			filewriter.append(",");
			filewriter.append(t.to);
			filewriter.append(",");
			filewriter.append(df.format(t.departure_date));
			filewriter.append(",");
			filewriter.append(df.format(t.arrival_date));
			filewriter.append(",");
			filewriter.append(Integer.toString(t.no_of_passengers));
			filewriter.append(",");
			filewriter.append(t.status);
			filewriter.append("\n");
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
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static String ticket()
	{
		String s = null;
		System.out.println("Choose");
		System.out.println("1.Ticket Reservation");
		System.out.println("2.Ticket Cancellation");
		sc = new Scanner(System.in);
		int c=sc.nextInt();
		if(c==1)
			s = reservation();
		else if(c==2)
			s=cancellation();
		return s;
		
	}
	public static Date str_to_date(String input)
	{
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy"); 
		Date t = null;
		if (input == null || !input.matches("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-(20\\d\\d)"))
			{System.out.println("Enter a valid date");
	        return t;}
	      try {
	         t = ft.parse(input); 
	      } catch (ParseException e) { 
	         System.out.println("Unparseable using " + ft); 
	      }
		return t;
	}
	
	
	public static String reservation()
	{
		sc.nextLine();
		System.out.println("Enter from which airport you are departing:");
		String from=sc.nextLine();
		sc.nextLine();
		System.out.println("Enter the name of airport you wish to reach:");
		String to=sc.nextLine();
		sc.nextLine();
		System.out.println("Enter departure date(dd-mm-yyyy):");
		String dep_date=sc.nextLine();
		Date dd=str_to_date(dep_date);
		if(dd==null || dd.compareTo(new Date())<0)
		{
			System.out.println("Invalid date");
			return "";
		}
		sc.nextLine();
		System.out.println("Enter arrival date(dd-mm-yyyy)");
		String arr_date=sc.nextLine();
		Date ad=str_to_date(arr_date);
		if(ad==null || ad.compareTo(dd)<0)
			{
			System.out.println("Invalid date");
			return "";
			}
		sc.nextLine();
		System.out.println("Enter number of passengers:");
		int no_of_passengers=sc.nextInt();
		Ticket t=new Ticket(from,to,str_to_date(dep_date),str_to_date(arr_date),no_of_passengers);
		writeCSV(t);
		Report.change_report(t.no_of_passengers*1500,'r');
		System.out.println(t);
		System.out.println("Fare is:"+Integer.toString(no_of_passengers*1500));
		return "Reservation done";
	}	
	public static String cancellation()
	{
		System.out.println("Enter ticket number:");
		int ticket_no=sc.nextInt();
		check_ticket(ticket_no);
		return "";
	}

}


