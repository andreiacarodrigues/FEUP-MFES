package MFES;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.overture.codegen.runtime.VDMSeq;

public class Interface {

	private WebSummit websummit = WebSummit.GetInstance(); 
	private Scanner scanner = new Scanner(System.in);

	private Utilities.Date startDate = new Utilities.Date(2017L, 9L, 1L);
	private Utilities.Date endDate = new Utilities.Date(2017L, 9L, 3L);

	public Interface()
	{
		Conference c1 = new Conference("ROBOTICS","This conference is just about robots");
		Conference c2 = new Conference("Cyber Security","This conference is just about Cyber Security");
		Talk t1 = new Talk("Sophia Robot","Talk about Sophia Robot",new Utilities.Date(2017L, 9L, 1L),new Utilities.Time(15L, 30L),30L);
		Talk t2 = new Talk("IOT","Internet Of Things",new Utilities.Date(2017L, 9L, 1L),new Utilities.Time(16L, 30L),30L);
		Talk t3 = new Talk("Is Our Home safe?","Complex discussion about the dangers of internet related with our homes.",new Utilities.Date(2017L, 9L, 2L),new Utilities.Time(16L, 30L),30L);
		Influential s1 = new Influential("Mark Zuckemberg","CEO",new Company("Facebook"),"Social Networks lover","USA");
		Influential s2 = new Influential("Bill Gates","CEO",new Company("Microsoft"),"My description","USA");

		//some data added
		websummit.SetDates(startDate, endDate);
		websummit.AddConference(c1);
		websummit.AddConference(c2);
		websummit.AddTalk(c1, t1);
		websummit.AddTalk(c1, t2);
		websummit.AddTalk(c2, t3);
		websummit.AddSpeaker(t1, s1);
		websummit.AddSpeaker(t2, s1);
		websummit.AddSpeaker(t3, s1);
		websummit.AddSpeaker(t3, s2);
		websummit.AddAttendee(t1, new Common("Ines"));
		websummit.AddAttendee(t1, new Common("Andreia"));

		printMainMenu();		
	}

	public void printMainMenu()
	{
		//create options
		ArrayList<String> options = new ArrayList<String>();
		options.add("Administration");
		options.add("Register Common Attendee");
		options.add("Register Influential Attendee");
		options.add("Attend Conferences");
		options.add("Startups");
		options.add("EXIT");

		//display menu
		System.out.println(" ====================== ");
		System.out.println(" === WebSummit Menu === ");
		System.out.println(" ====================== \n");
		for(int i = 1; i<= options.size(); i++){
			System.out.println(" "+i+". "+options.get(i-1));
		}

		//user input
		int number = getUserInput(options.size());

		switch (number) {
		case 1:
			printAdministrationMenu();
			break;
		case 2:
			printCommonRegister();
			break;
		case 3:
			printInfluentialRegister();
			break;
		case 4:
			isParticipant();
			break;
		case 5:
			printStartupMenu();
			break;
		case 6:
			return;
		default:
			break;
		}
	}

	// --- ADMINISTRATION ---

	public void printAdministrationMenu()
	{
		//create options
		ArrayList<String> options = new ArrayList<String>();
		options.add("News");
		options.add("Conferences");
		options.add("Total Attendees");
		options.add("BACK");

		//display menu
		System.out.println(" === ADMINISTRATION === \n");
		for(int i = 1; i<= options.size(); i++){
			System.out.println(" "+i+". "+options.get(i-1));
		}

		//user input
		int number = getUserInput(options.size());

		switch (number) {
		case 1:
			printNewsMenu();
			break;
		case 2:
			printConferencesMenu();
			break;
		case 3:
			printTotalAttendees();
			break;
		case 4:
			printMainMenu();
			break;
		default:
			break;
		}		
	}

	// --- NEWS ---

	public void printNewsMenu()
	{
		//create options
		ArrayList<String> options = new ArrayList<String>();
		options.add("Add New");
		options.add("Get All News");
		options.add("BACK");

		//display menu
		System.out.println(" === ADMINISTRATION / NEWS === \n");
		for(int i = 1; i<= options.size(); i++){
			System.out.println(" "+i+". "+options.get(i-1));
		}

		//user input
		int number = getUserInput(options.size());

		switch (number) {
		case 1:
			printAddNew();
			break;
		case 2:
			printAllNews();
			break;
		case 3:
			printAdministrationMenu();
			break;
		default:
			break;
		}
	}

	public void printAddNew()
	{
		System.out.println(" === ADMINISTRATION / NEWS / ADD NEW === \n");
		System.out.println("Title : ");
		String title = scanner.nextLine();
		System.out.println("Body : ");
		String body = scanner.nextLine();

		New myNew = new New(title,body);
		websummit.AddNew(myNew);

		//back
		waitOk();
		printNewsMenu();
	}

	public void printAllNews()
	{
		System.out.println(" === ADMINISTRATION / NEWS / GET ALL NEWS === \n");
		Set<New> news = websummit.GetNews();

		for(New myNew: news)
		{
			System.out.println(myNew.toString());
			System.out.println("\n ~~~ \n");
		}

		waitOk();
		printNewsMenu();
	}

	// --- CONFERENCES ---

	public void printConferencesMenu()
	{
		//create options
		ArrayList<String> options = new ArrayList<String>();
		options.add("Add New Conference");
		options.add("Organize Conferences");
		options.add("BACK");

		//display menu
		System.out.println(" === ADMINISTRATION / CONFERENCES === \n");
		for(int i = 1; i<= options.size(); i++){
			System.out.println(" "+i+". "+options.get(i-1));
		}

		//user input
		int number = getUserInput(options.size());

		switch (number) {
		case 1:
			printAddConference();
			break;
		case 2:
			printAllConferences();
			break;
		case 3:
			printAdministrationMenu();
			break;
		default:
			break;
		}
	}

	public void printAddConference()
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ADD CONFERENCE  === \n");

		System.out.println("Name : ");
		String name = scanner.nextLine();

		System.out.println("Description : ");
		String des = scanner.nextLine();

		Conference c = new Conference(name,des);
		websummit.AddConference(c);

		//back
		waitOk();
		printConferencesMenu();
	}

	public void printAllConferences()
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE === \n");

		Set<Conference> conferences = websummit.GetConferences();

		if(conferences.size() == 0){
			System.out.println("No conferences to show.");
			waitOk();
			printConferencesMenu();
		}
		else
		{
			//display menu
			int i = 0;
			for(Conference c : conferences){
				i++;
				System.out.println(" "+i+". "+c.toString());
			}

			//user input
			int number = getUserInput(conferences.size());

			//get conference
			i = 0;
			Conference conference = null;
			for(Conference c : conferences){
				i++;
				if(i == number){
					conference = c;
					break;
				}
			}

			//next menu
			printConferenceMenu(conference);	
		}
	}

	public void printConferenceMenu(Conference conference)
	{
		//create options
		ArrayList<String> options = new ArrayList<String>();
		options.add("Add New Talk");
		options.add("Organize Talks");
		options.add("Add Company");
		options.add("Remove Company");
		options.add("BACK");

		//display menu
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE === \n");
		for(int i = 1; i<= options.size(); i++){
			System.out.println(" "+i+". "+options.get(i-1));
		}

		//user input
		int number = getUserInput(options.size());

		switch (number) {
		case 1:
			printAddTalk(conference);
			break;
		case 2:
			printAllTalks(conference);
			break;
		case 3:
			printAddCompany(conference);
			break;
		case 4:
			printRemoveCompany(conference);
			break;
		case 5:
			printConferencesMenu();
			break;
		default:
			break;
		}
	}

	// --- TALKS ---

	public void printAddTalk(Conference conference)
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE / ADD TALK  === \n");

		System.out.println("Name : ");
		String name = scanner.nextLine();

		System.out.println("Description : ");
		String des = scanner.nextLine();

		System.out.println("Date (day): ");
		String day = scanner.nextLine();
		long dayn = Long.parseLong(day);

		System.out.println("Date (month): ");
		String mon = scanner.nextLine();
		long monn = Long.parseLong(mon);

		System.out.println("Date (year): ");
		String year = scanner.nextLine();
		long yearn = Long.parseLong(year);

		Utilities.Date date = new Utilities.Date(yearn, monn, dayn);

		System.out.println("Start Time (Hour): ");
		String hour = scanner.nextLine();
		long hourn = Long.parseLong(hour);

		System.out.println("Start Time (Minutes): ");
		String min = scanner.nextLine();
		long minn = Long.parseLong(min);

		Utilities.Time time = new Utilities.Time(hourn, minn);

		System.out.println("Duration");
		String dur = scanner.nextLine();
		long durn = Long.parseLong(dur);

		Talk t = new Talk(name,des,date,time,durn);
		websummit.AddTalk(conference, t);

		//back
		waitOk();
		printConferenceMenu(conference);
	}

	public void printAllTalks(Conference conference)
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE / ORGANIZE TALKS === \n");

		Set<Talk> talks = conference.GetTalks();

		if(talks.size() == 0){
			System.out.println("No talks to show.");
			waitOk();
			printConferencesMenu();
		}
		else{
			//display menu
			int i = 0;
			for(Talk t : talks){
				i++;
				System.out.println(" "+i+". "+t.toString());
			}

			//user input
			int number = getUserInput(talks.size());

			//get conference
			i = 0;
			Talk talk = null;
			for(Talk t : talks){
				i++;
				if(i == number){
					talk = t;
					break;
				}
			}

			//next menu
			printTalkMenu(talk);
		}
	}

	public void printTalkMenu(Talk talk)
	{
		//create options
		ArrayList<String> options = new ArrayList<String>();
		options.add("Add New Speaker");
		options.add("Remove Speaker");
		options.add("Get Total Attendees");
		options.add("BACK");

		//display menu
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE / ORGANIZE TALK === \n");
		for(int i = 1; i<= options.size(); i++){
			System.out.println(" "+i+". "+options.get(i-1));
		}

		//user input
		int number = getUserInput(options.size());

		switch (number) {
		case 1:
			printAddSpeaker(talk);
			break;
		case 2:
			printRemoveSpeaker(talk);
			break;
		case 3:
			printTotalAttendees(talk);
			break;
		case 4:
			Conference c = websummit.GetConference(talk.GetConference());
			printConferenceMenu(c);
			break;
		default:
			break;
		}
	}

	// --- SPEAKERS ---

	public void printAddSpeaker(Talk talk)
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE / ORGANIZE TALK / ADD SPEAKER  === \n");

		System.out.println("Name : ");
		String name = scanner.nextLine();

		System.out.println("Description : ");
		String des = scanner.nextLine();

		System.out.println("Company Name : ");
		String compName = scanner.nextLine();
		Company company = new Company(compName);

		System.out.println("Job Position : ");
		String jobPos = scanner.nextLine();

		System.out.println("Country : ");
		String country = scanner.nextLine();

		Influential speaker = new Influential(name,jobPos,company,des,country);
		websummit.AddSpeaker(talk, speaker);

		//back
		waitOk();
		printTalkMenu(talk);
	}

	public void printRemoveSpeaker(Talk talk)
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE / ORGANIZE TALKS / REMOVE SPEAKER === \n");

		Set<Influential> speakers = talk.GetSpeakers();

		if(speakers.size() == 0){
			System.out.println("No speakers to show.");
			waitOk();
			printTalkMenu(talk);
		}
		else{
			//display menu
			int i = 0;
			for(Influential s : speakers){
				i++;
				System.out.println(" "+i+". "+s.toString());
			}

			//user input
			int number = getUserInput(speakers.size());

			//get conference
			i = 0;
			Influential speaker = null;
			for(Influential s : speakers){
				i++;
				if(i == number){
					speaker = s;
					break;
				}
			}

			websummit.RemoveSpeaker(talk, speaker);

			//next menu
			printTalkMenu(talk);
		}
	}
	
	public void printTotalAttendees(Talk talk)
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE / ORGANIZE TALKS / GET TOTAL ATTENDEES === \n");
		
		System.out.println("\nTotal Attendees : "+websummit.GetTotalAttendeesByTalk(talk));
		
		//back
		waitOk();
		printTalkMenu(talk);
	}

	// --- COMPANY ---

	public void printAddCompany(Conference conference)
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE / ADD COMPANY  === \n");

		System.out.println("Name : ");
		String name = scanner.nextLine();

		Company company = new Company(name);
		websummit.AddCompany(conference, company);

		//back
		waitOk();
		printConferenceMenu(conference);
	}

	public void printRemoveCompany(Conference conference)
	{
		System.out.println(" === ADMINISTRATION / CONFERENCES / ORGANIZE CONFERENCE / REMOVE COMPANY === \n");

		Set<Company> companies = conference.GetCompanies();

		if(companies.size() == 0)
		{
			System.out.println("No companies to show.");
			waitOk();
			printConferenceMenu(conference);
		}
		else
		{
			//display menu
			int i = 0;
			for(Company c : companies){
				i++;
				System.out.println(" "+i+". "+c.toString());
			}

			//user input
			int number = getUserInput(companies.size());

			//get conference
			i = 0;
			Company company = null;
			for(Company c : companies){
				i++;
				if(i == number){
					company = c;
					break;
				}
			}

			websummit.RemoveCompany(conference, company);

			//next menu
			printConferenceMenu(conference);
		}
	}
	
	public void printTotalAttendees()
	{
		System.out.println(" === ADMINISTRATION / GET TOTAL ATTENDEES === \n");
		
		Set<Attendee> attendees = websummit.GetAttendees();
		for(Attendee a : attendees)
			System.out.println(a.toString());
		
		System.out.println("\n Total : "+attendees.size()+ " attendees");
		
		//back
		waitOk();
		printAdministrationMenu();
	}

	// --- COMMON ATTENDEE

	public void printCommonRegister()
	{
		System.out.println(" === COMMON ATTENDEE / REGISTER  === \n");

		System.out.println("Name : ");
		String name = scanner.nextLine();

		Common c = new Common(name);
		websummit.AddAttendee(c);

		//back
		waitOk();
		printMainMenu();
	}

	// --- INFLUENTIAL ATTENDEE ---

	public void printInfluentialRegister()
	{
		System.out.println(" === INFLUENTIAL ATTENDEE / REGISTER  === \n");

		System.out.println("Name : ");
		String name = scanner.nextLine();

		System.out.println("Description : ");
		String des = scanner.nextLine();

		System.out.println("Company Name : ");
		String compName = scanner.nextLine();
		Company company = new Company(compName);

		System.out.println("Job Position : ");
		String jobPos = scanner.nextLine();

		System.out.println("Country : ");
		String country = scanner.nextLine();

		Influential i = new Influential(name,jobPos,company,des,country);
		websummit.AddAttendee(i); 

		//back
		waitOk();
		printMainMenu();
	}

	// --- COMMON AND INFLUENTIAL ATTENDEE ---

	public void isParticipant()
	{
		System.out.println(" === ATTENDEE / ATTEND CONFERENCES  === \n");

		System.out.println("Participant name : ");
		String name = scanner.nextLine();

		Set<Attendee> attendees = websummit.GetAttendees();

		Attendee a = websummit.GetAttendee(name);
		
		if(a != null){
			System.out.println("Success!");
			waitOk();
			printParticipantMenu(a);
		}
		else{
			System.out.println("Invalid Participant Name!");
			waitOk();
			printMainMenu();
		}
	}

	public void printParticipantMenu(Attendee attendee)
	{
		//create options
		ArrayList<String> options = new ArrayList<String>();
		options.add("Register to talk");
		options.add("Get WebSummit Schedule");
		options.add("Get Conference Schedule");
		options.add("Get Talks By Hour");
		options.add("Get Exhibit");
		options.add("BACK");

		//display menu		
		System.out.println(" === ATTENDEE / ATTEND CONFERENCES === \n");
		for(int i = 1; i<= options.size(); i++){
			System.out.println(" "+i+". "+options.get(i-1));
		}

		//user input
		int number = getUserInput(options.size());

		switch (number) {
		case 1:
			printTalksToRegister(attendee);
			break;
		case 2:
			printWebSummitSchedule(attendee);
			break;
		case 3:
			printConferencesToSchedule(attendee);
			break;
		case 4:
			printScheduleByHour(attendee);
			break;
		case 5:
			printExhibit(attendee);
			break;
		case 6:
			printMainMenu();
		default:
			break;
		}	
	}

	public void printTalksToRegister(Attendee attendee)
	{
		System.out.println(" === ATTENDEE / ATTEND CONFERENCES / REGISTER TO TALK === \n");

		Set<Conference> conferences = websummit.GetConferences();

		if(conferences.size() == 0){
			System.out.println("No talks to show.");
			waitOk();
			printParticipantMenu(attendee);
		}
		else
		{
			Set<Talk> allTalks = new HashSet<Talk>();

			//display menu
			int i = 0;
			for(Conference c : conferences)
			{
				Set<Talk> talks = c.GetTalks();

				for(Talk t : talks)
				{
					i++;
					System.out.println(" "+i+". "+t.toString());
					allTalks.add(t);
				}
			}

			//user input
			int number = getUserInput(allTalks.size()+1);

			//get talk
			Talk talk = null;
			i = 0;
			for(Talk t : allTalks){
				i++;
				if(i == number){
					talk = t;
					break;
				}
			}

			websummit.AddAttendee(talk, attendee);

			System.out.println("Successfully registered to talk!");
			waitOk();

			//next menu
			printParticipantMenu(attendee);
		}

	}

	public void printWebSummitSchedule(Attendee attendee)
	{
		System.out.println(" === ATTENDEE / ATTEND CONFERENCES / WEBSUMMIT SCHEDULE === \n");

		printSchedule(websummit.GetSchedule());

		waitOk();
		printParticipantMenu(attendee);
	}

	public void printConferencesToSchedule(Attendee attendee)
	{
		System.out.println(" === ATTENDEE / ATTEND CONFERENCES / CONFERENCE SCHEDULE === \n");

		Set<Conference> conferences = websummit.GetConferences();

		if(conferences.size() == 0){
			System.out.println("No conferences to show.");
			waitOk();
			printParticipantMenu(attendee);
		}
		else
		{
			//display menu
			int i = 0;
			for(Conference c : conferences){
				i++;
				System.out.println(" "+i+". "+c.toString());
			}

			//user input
			int number = getUserInput(conferences.size());

			//get conference
			i = 0;
			Conference conference = null;
			for(Conference c : conferences){
				i++;
				if(i == number){
					conference = c;
					break;
				}
			}

			//print schedule
			printSchedule(websummit.GetSchedule(conference));

			//back to menu
			waitOk();
			printParticipantMenu(attendee);
		}
	}

	public void printScheduleByHour(Attendee attendee)
	{
		System.out.println(" === ATTENDEE / ATTEND CONFERENCES / TALKS BY HOUR === \n");

		System.out.println("Date (day): ");
		String day = scanner.nextLine();
		long dayn = Long.parseLong(day);

		System.out.println("Date (month): ");
		String mon = scanner.nextLine();
		long monn = Long.parseLong(mon);

		System.out.println("Date (year): ");
		String year = scanner.nextLine();
		long yearn = Long.parseLong(year);

		Utilities.Date date = new Utilities.Date(yearn, monn, dayn);

		System.out.println("Start Time (Hour): ");
		String hour = scanner.nextLine();
		long hourn = Long.parseLong(hour);

		System.out.println("Start Time (Minutes): ");
		String min = scanner.nextLine();
		long minn = Long.parseLong(min);

		Utilities.Time time = new Utilities.Time(hourn, minn);

		VDMSeq schedule = websummit.GetSchedule(date, time);

		System.out.println("Results : \n");
		for(int i = 0; i < schedule.size();i++){
			System.out.println(schedule.get(i));
		}

		//back to menu
		waitOk();
		printParticipantMenu(attendee);
	}

	public void printSchedule(Map<Utilities.Date,VDMSeq> schedule)
	{
		for (Map.Entry<Utilities.Date,VDMSeq> entry : schedule.entrySet())
		{
			System.out.println(entry.getKey().day+" / "+entry.getKey().month+" / "+entry.getKey().year);
			VDMSeq talks = entry.getValue();

			for(int i = 0; i < talks.size(); i++){
				System.out.println("  "+talks.get(i));
			}
			System.out.println();
		}
	}
	
	public void printExhibit(Attendee attendee)
	{
		System.out.println(" === ATTENDEE / EXHIBIT === \n");

		Set<Startup> startups = websummit.GetStartups();
		for(Startup s : startups)
			System.out.println(s.toString());
		
		//back to menu
		waitOk();
		printParticipantMenu(attendee);
		
	}

	// --- STARTUPS ---

	public void printStartupMenu()
	{
		//create options
		ArrayList<String> options = new ArrayList<String>();
		options.add("Register");
		options.add("Cancel Registration");
		options.add("BACK");

		//display menu
		System.out.println(" === STARTUPS === \n");
		for(int i = 1; i<= options.size(); i++){
			System.out.println(" "+i+". "+options.get(i-1));
		}

		//user input
		int number = getUserInput(options.size());

		switch (number) {
		case 1:
			printStartupRegister();
			break;
		case 2:
			printStartupRemove();
			break;
		case 3:
			printMainMenu();
			break;
		default:
			break;
		}		
	}

	public void printStartupRegister()
	{
		System.out.println(" === STARTUPS / REGISTER === \n");

		System.out.println("Name : ");
		String name = scanner.nextLine();

		System.out.println("Description : ");
		String des = scanner.nextLine();

		System.out.println("Website : ");
		String website = scanner.nextLine();

		System.out.println("Country : ");
		String country = scanner.nextLine();

		Startup s = new Startup(name,des,website,country);
		websummit.AddStartup(s);

		//back
		waitOk();
		printMainMenu();
	}

	public void printStartupRemove()
	{
		System.out.println(" === STARTUPS / REMOVE REGISTRATION === \n");

		System.out.println("Name : ");
		String name = scanner.nextLine();

		Startup s = websummit.GetStartup(name);
		websummit.RemoveStartup(s);
		
		//back
		waitOk();
		printMainMenu();
	}

	// --- OTHERS ---

	public int getUserInput(int max)
	{
		boolean valid = false;
		String input = "";
		int number = 0;
		while(!valid){
			System.out.println("\nSelect your option :");
			input = scanner.nextLine();
			number = Integer.parseInt(input);
			if(! (number < 1 || number > max) )
				valid = true;
		}
		return number;
	}

	public void waitOk()
	{
		boolean valid = false;
		String input = "";
		while(!valid){
			System.out.println("\nType 'ok' to continue :");
			input = scanner.nextLine();
			if(input.equals("ok"))
				valid = true;
		}
	}
}
