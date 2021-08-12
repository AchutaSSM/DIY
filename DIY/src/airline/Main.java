package airline;

import java.util.*;

public class Main {

	static int count=0;

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
			while(true)
			{
				System.out.println("\nWelcome to Imaginary airlines! Choose the service you would like to avail.");
				System.out.println("1.Tickets Management");
				System.out.println("2.Managing Passenger Data");
				System.out.println("3.Transactions report");
				System.out.println("4.Exit");
				int ch=sc.nextInt();
				switch(ch)
				{
				case 1:
					String s=TicketManagement.ticket();
					System.out.println(s);
					break;
				case 2:
					PassengerData.menu();
					break;
				case 3:
					Report.report();
					break;
				case 4:
					System.exit(1);
				default:
					System.out.println("Enter either 1,2,3 or 4");	
				}
			}
	}
}
