package airline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
public class PassengerData {
	private static Scanner sc;
	private static String filepath="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\accounts.csv";
	static String path2="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\temp.csv";
	private static void check_account(String id,String ch,char na) {
		BufferedReader reader=null;
		String line="";
		int flag=0;
		try {
			reader=new BufferedReader(new FileReader(filepath));
			while((line=reader.readLine())!=null)
			{
				String[] fields=line.split(",");
				if(fields[2].compareTo(id)==0)
				{
					flag=1;
					reader.close();
					if(na=='n')
						modifyCSV(fields[0],ch,fields[2],fields[3],'n');
					else if(na=='a')
						modifyCSV(fields[0],fields[1],fields[2],ch,'a');
					System.out.println("Changes done!");
					break;
				}
			}
			if(flag==0)
				{
				System.out.println("Invalid id");
				return;
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public static void modifyCSV(String name,String number,String id,String address,char na)
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
				if(na=='n' && fields[2].compareTo(id)==0)
					filewriter.append(number);
				else
					filewriter.append(fields[1]);
				filewriter.append(",");
				filewriter.append(fields[2]);
				filewriter.append(",");
				if(na=='a' && fields[2].compareTo(id)==0)
					filewriter.append(address);
				else
					filewriter.append(fields[3]);
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
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void writeCSV(Account a)
	{
		FileWriter filewriter=null;
		try
		{
			filewriter=new FileWriter(filepath,true);
			filewriter.append(a.name);
			filewriter.append(",");
			filewriter.append(a.number);
			filewriter.append(",");
			filewriter.append(a.mail_id);
			filewriter.append(",");
			filewriter.append(a.address);
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
	
	public static void readCSV()
	{
		BufferedReader reader=null;
		String line="";
		String path="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\Diy_ticket.csv";
		try {
			reader=new BufferedReader(new FileReader(path));
			while((line=reader.readLine())!=null)
			{
				String[] fields=line.split(",");
				System.out.println("Ticket Number:"+fields[0]+"\nFrom:"+fields[1]+"\n"+"To:"+fields[2]+"\n"+"Departure Date:"+fields[3]+"\n"+"Arrival Date:"+fields[4]+"\nNumber of passengers:"+fields[5]+"\nStatus:"+fields[6]+"\n\n\n");
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
    public static void menu()
    {
    	sc = new Scanner(System.in);
    	System.out.println("Choose an option(1,2,3 or 4):");
    	System.out.println("1.Check all tickets data");
    	System.out.println("2.Check a ticket");
    	System.out.println("3.Create a passenger account");
    	System.out.println("4.Change phone number");
    	System.out.println("5.Change address");
    	System.out.println("6.Check passenger aaccount");
    	int ch=sc.nextInt();
    	switch(ch)
    	{
    	case 1:
    		data_display();
			break;
		case 4:
			change_no();
			break;
		case 5:
			change_address();
			break;
		case 2:
			System.out.println("\nEnter ticket number:");
			int t=sc.nextInt();
			check_ticket(t);
			break;
		case 3:
			make_account();
			break;
		case 6:
			sc.nextLine();
			System.out.println("\nEnter passenger id:");
			String id=sc.nextLine();
			check_passenger_account(id);
			break;
		default:
			System.out.println("Enter either 1,2,3 or 4");
    	}
    }
	private static void check_passenger_account(String id) {
	
		BufferedReader reader=null;
		String line="";
		int flag=0;
		String path="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\accounts.csv";
		try {
			reader=new BufferedReader(new FileReader(path));
			while((line=reader.readLine())!=null)
			{
				String[] fields=line.split(",");
				if(fields[2].compareTo(id)==0)
				{
					flag=1;
					System.out.println("Name:"+fields[0]+"\nPhone number:"+fields[1]+"\n"+"Mail id:"+fields[2]+"\n"+"Address:"+fields[3]+"\n\n\n");
					break;
				}
			}
			if(flag==0)
			{
				System.out.println("Enter valid id");
				return;
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
	private static void make_account() {
		sc.nextLine();
		System.out.println("Enter you name:");
		String name=sc.nextLine();
		sc.nextLine();
		System.out.println("Enter your number:");
		String number=sc.nextLine();
		if(!number.matches("\\d{10}"))
		{
			System.out.println("Enter a valid phone number");
			return;
		}
		sc.nextLine();
		System.out.println("Enter your mail-id:");
		String mail=sc.nextLine();
		if(!mail.matches("^(.+)@(.+)$"))
		{
			System.out.println("Enter valid email id");
			return;
		}
		sc.nextLine();
		System.out.println("Enter your address");
		String address=sc.nextLine();
		Account ac=new Account(name,number,mail,address);
		writeCSV(ac);
		System.out.println("Account Created!");
		
		
	}
	private static void check_ticket(int t) {
		BufferedReader reader=null;
		String line="";
		int flag=0;
		String path="C:\\Users\\AMALUJSU\\eclipse-workspace\\DIY\\Diy_ticket.csv";
		try {
			reader=new BufferedReader(new FileReader(path));
			while((line=reader.readLine())!=null)
			{
				String[] fields=line.split(",");
				if(Integer.parseInt(fields[0])==t)
				{
					flag=1;
					System.out.println("Ticket Number:"+fields[0]+"\nFrom:"+fields[1]+"\n"+"To:"+fields[2]+"\n"+"Departure Date:"+fields[3]+"\n"+"Arrival Date:"+fields[4]+"\nNumber of passengers:"+fields[5]+"\nStatus:"+fields[6]+"\n\n\n");
				}
			}
			if(flag==0)
				System.out.println("Invalid id");
			return;
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
	public static void data_display()
	{
		readCSV();
		System.out.println("Data Displayed");
	}
	public static void change_no()
	{	
		sc.nextLine();
		System.out.println("Enter your mail-id:");
		String mail=sc.nextLine();
		if(!mail.matches("^(.+)@(.+)$"))
		{
			System.out.println("Enter valid email id");
			return;
		}
		sc.nextLine();
		System.out.println("Enter your number:");
		String number=sc.nextLine();
		if(!number.matches("\\d{10}"))
		{
			System.out.println("Enter a valid phone number");
			return;
		}
		check_account(mail,String.valueOf(number),'n');
//			System.out.println("Number changed");
	}
	public static void change_address()
	{
		sc.nextLine();
		System.out.println("Enter your mail-id:");
		String mail=sc.nextLine();
		if(!mail.matches("^(.+)@(.+)$"))
		{
			System.out.println("Enter valid email id");
			return;
		}
		sc.nextLine();
		System.out.println("Enter you address:");
		String address=sc.nextLine();
		check_account(mail,address,'a');
//		System.out.println("Address changed");
	}
	

}
