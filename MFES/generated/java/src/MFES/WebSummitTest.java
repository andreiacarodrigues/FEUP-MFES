package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class WebSummitTest extends MyTestCase {
  private Utilities.Date date1 = new Utilities.Date(2017L, 9L, 1L);
  private Utilities.Date date2 = new Utilities.Date(2017L, 9L, 3L);
  private Utilities.Time time1 = new Utilities.Time(15L, 20L);
  private Utilities.Time time2 = new Utilities.Time(15L, 40L);
  private Utilities.Time time3 = new Utilities.Time(16L, 40L);
  private Utilities.Time time4 = new Utilities.Time(15L, 30L);

  public static void main() {

    WebSummitTest webSummitTest = new WebSummitTest();
    IO.print("testCreateAndAddConference -> ");
    webSummitTest.testCreateAndAddConference();
    IO.println("Success");
    IO.print("testCreateAndAddTalk -> ");
    webSummitTest.testCreateAndAddTalk();
    IO.println("Success");
    IO.print("testRemoveTalk -> ");
    webSummitTest.testRemoveTalk();
    IO.println("Success");
    IO.print("testSchedules -> ");
    webSummitTest.testSchedules();
    IO.println("Success");
    IO.print("testCreateAndAddCompany -> ");
    webSummitTest.testCreateAndAddCompany();
    IO.println("Success");
    IO.print("testRemoveCompany -> ");
    webSummitTest.testRemoveCompany();
    IO.println("Success");
    IO.print("testCreateAndAddAttendee -> ");
    webSummitTest.testCreateAndAddAttendee();
    IO.println("Success");
    IO.print("testCreateAndAddStartup -> ");
    webSummitTest.testCreateAndAddStartup();
    IO.println("Success");
    IO.print("testRemoveStartup -> ");
    webSummitTest.testRemoveStartup();
    IO.println("Success");
    IO.print("testCreateAndAddInvestors -> ");
    webSummitTest.testCreateAndAddInvestors();
    IO.println("Success");
    IO.print("testRemoveInvestors -> ");
    webSummitTest.testRemoveInvestors();
    IO.println("Success");
    IO.print("testCreateAndAddNews -> ");
    webSummitTest.testCreateAndAddNews();
    IO.println("Success");
  }

  private void testCreateAndAddConference() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Conference conference1 = new Conference("C1", "D1");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    assertEqual(Utils.copy(date1), webSummit.GetInitialDate());
    assertEqual(Utils.copy(date2), webSummit.GetFinalDate());
    AddConference(conference1);
    assertEqual("C1", conference1.GetName());
    assertEqual("D1", conference1.GetDescription());
    assertEqual(SetUtil.set(), conference1.GetTalks());
    assertEqual(SetUtil.set(), conference1.GetCompanies());
    assertEqual(conference1, webSummit.GetConference(conference1.GetName()));
    conference1.SetName("Conference 1");
    assertEqual("Conference 1", conference1.GetName());
    conference1.SetDescription("Conference 1 details");
    assertEqual("Conference 1 details", conference1.GetDescription());
    assertEqual(1L, webSummit.GetConferences().size());
    assertEqual(SetUtil.set(conference1), webSummit.GetConferences());
  }

  private void testCreateAndAddTalk() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Conference conference = new Conference("Conference 1", "Conference 1 details");
    Talk talk1 = new Talk("T1", "D1", Utils.copy(date2), Utils.copy(time2), 30L);
    Company company = new Company("Facebook");
    Influential speaker1 =
        new Influential("Mark Zuckerberg", "CEO", company, "Speaker Description", "EN");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    AddConference(conference);
    AddTalk(conference, talk1);
    assertEqual("T1", talk1.GetName());
    assertEqual("D1", talk1.GetDescription());
    assertEqual(Utils.copy(date2), talk1.GetDate());
    assertEqual(Utils.copy(time2), talk1.GetTime());
    assertEqual(30L, talk1.GetDuration());
    assertEqual("Conference 1", talk1.GetConference());
    assertEqual(SetUtil.set(), talk1.GetSpeakers());
    assertEqual(SetUtil.set(), talk1.GetAttendees());
    talk1.SetName("Talk 1");
    assertEqual("Talk 1", talk1.GetName());
    talk1.SetDescription("Talk 1 description");
    assertEqual("Talk 1 description", talk1.GetDescription());
    talk1.SetDate(Utils.copy(date1));
    assertEqual(Utils.copy(date1), talk1.GetDate());
    talk1.SetTime(Utils.copy(time1));
    assertEqual(Utils.copy(time1), talk1.GetTime());
    talk1.SetDuration(40L);
    assertEqual(40L, talk1.GetDuration());
    assertEqual(talk1, conference.GetTalk(talk1.GetName()));
    assertEqual(conference.GetName(), talk1.GetConference());
    assertEqual(1L, conference.GetTalks().size());
    assertEqual(SetUtil.set(talk1), conference.GetTalks());
    AddSpeaker(talk1, speaker1);
    assertEqual(SetUtil.set(speaker1), talk1.GetSpeakers());
    RemoveSpeaker(talk1, speaker1);
    assertEqual(SetUtil.set(), talk1.GetSpeakers());
  }

  private void testSchedules() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Conference conference1 = new Conference("Conference 1", "Conference 1 details");
    Conference conference2 = new Conference("Conference 2", "Conference 2 details");
    Talk talk1 =
        new Talk("Talk 1", "Talk 1 description", Utils.copy(date1), Utils.copy(time2), 40L);
    Talk talk2 =
        new Talk("Talk 2", "Talk 2 description", Utils.copy(date1), Utils.copy(time1), 20L);
    Talk talk3 =
        new Talk("Talk 3", "Talk 3 description", Utils.copy(date1), Utils.copy(time3), 20L);
    Talk talk4 =
        new Talk("Talk 4", "Talk 4 description", Utils.copy(date2), Utils.copy(time3), 20L);
    Talk talk5 =
        new Talk("Talk 5", "Talk 5 description", Utils.copy(date1), Utils.copy(time4), 20L);
    Talk talk6 =
        new Talk("Talk 6", "Talk 6 description", Utils.copy(date2), Utils.copy(time2), 60L);
    Talk talk7 =
        new Talk("Talk 7", "Talk 7 description", Utils.copy(date2), Utils.copy(time3), 20L);
    Talk talk8 =
        new Talk("Talk 8", "Talk 8 description", Utils.copy(date2), Utils.copy(time1), 20L);
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    AddConference(conference1);
    AddConference(conference2);
    AddTalk(conference1, talk1);
    AddTalk(conference1, talk2);
    AddTalk(conference1, talk3);
    AddTalk(conference1, talk4);
    AddTalk(conference2, talk5);
    AddTalk(conference2, talk6);
    AddTalk(conference2, talk7);
    AddTalk(conference2, talk8);
    assertEqual(2L, webSummit.GetConferences().size());
    assertEqual(SetUtil.set(conference1, conference2), webSummit.GetConferences());
    assertEqual(4L, conference1.GetTalks().size());
    assertEqual(SetUtil.set(talk1, talk2, talk3, talk4), conference1.GetTalks());
    assertEqual(4L, conference2.GetTalks().size());
    assertEqual(SetUtil.set(talk5, talk6, talk7, talk8), conference2.GetTalks());
    assertEqual(2L, webSummit.GetSchedule(Utils.copy(date1), new Utilities.Time(16L, 0L)).size());
    assertEqual(
        SeqUtil.seq(talk1, talk3),
        webSummit.GetSchedule(Utils.copy(date1), new Utilities.Time(16L, 0L)));
    assertEqual(3L, webSummit.GetSchedule(Utils.copy(date1), new Utilities.Time(15L, 0L)).size());
    assertEqual(
        SeqUtil.seq(talk2, talk5, talk1),
        webSummit.GetSchedule(Utils.copy(date1), new Utilities.Time(15L, 0L)));
    assertEqual(3L, MapUtil.dom(webSummit.GetSchedule(conference1)).size());
    assertEqual(
        MapUtil.map(
            new Maplet(Utils.copy(date1), SeqUtil.seq(talk2, talk1, talk3)),
            new Maplet(Utilities.nextDay(Utils.copy(date1)), SeqUtil.seq()),
            new Maplet(Utils.copy(date2), SeqUtil.seq(talk4))),
        webSummit.GetSchedule(conference1));
    assertEqual(3L, MapUtil.dom(webSummit.GetSchedule(conference2)).size());
    assertEqual(
        MapUtil.map(
            new Maplet(Utils.copy(date1), SeqUtil.seq(talk5)),
            new Maplet(Utilities.nextDay(Utils.copy(date1)), SeqUtil.seq()),
            new Maplet(Utils.copy(date2), SeqUtil.seq(talk8, talk6, talk7))),
        webSummit.GetSchedule(conference2));
    assertEqual(3L, MapUtil.dom(webSummit.GetSchedule()).size());
    assertEqual(
        MapUtil.map(
            new Maplet(Utils.copy(date1), SeqUtil.seq(talk2, talk5, talk1, talk3)),
            new Maplet(Utilities.nextDay(Utils.copy(date1)), SeqUtil.seq()),
            new Maplet(Utils.copy(date2), SeqUtil.seq(talk8, talk6, talk7, talk4))),
        webSummit.GetSchedule());
  }

  private void testCreateAndAddCompany() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Conference conference1 = new Conference("Conference 1", "Conference 1 details");
    Company company1 = new Company("Comp1");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    AddConference(conference1);
    AddCompany(conference1, company1);
    assertEqual("Comp1", company1.GetName());
    company1.SetName("Company 1");
    assertEqual("Company 1", company1.GetName());
    assertEqual(1L, conference1.GetCompanies().size());
    assertEqual(SetUtil.set(company1), conference1.GetCompanies());
  }

  private void testRemoveCompany() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Conference conference1 = new Conference("Conference 1", "Conference 1 details");
    Company company1 = new Company("Company 1");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    AddConference(conference1);
    AddCompany(conference1, company1);
    assertEqual(1L, conference1.GetCompanies().size());
    assertEqual(SetUtil.set(company1), conference1.GetCompanies());
    RemoveCompany(conference1, company1);
    assertEqual(0L, conference1.GetCompanies().size());
    assertEqual(SetUtil.set(), conference1.GetCompanies());
  }

  private void testRemoveTalk() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Conference conference1 = new Conference("Conference 1", "Conference 1 details");
    Talk talk1 =
        new Talk("Talk 1", "Talk 1 description", Utils.copy(date1), Utils.copy(time2), 40L);
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    AddConference(conference1);
    AddTalk(conference1, talk1);
    assertEqual(1L, conference1.GetTalks().size());
    assertEqual(SetUtil.set(talk1), conference1.GetTalks());
    RemoveTalk(conference1, talk1);
    assertEqual(0L, conference1.GetTalks().size());
    assertEqual(SetUtil.set(), conference1.GetTalks());
  }

  private void testCreateAndAddAttendee() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Conference conference = new Conference("Conference 1", "Conference 1 details");
    Talk talk = new Talk("Talk 1", "Talk 1 description", Utils.copy(date2), Utils.copy(time2), 30L);
    Common attendee1 = new Common("Ines");
    Influential attendee2 =
        new Influential(
            "Andreia Rodrigues", "STUDENT", new Company("feup"), "STUDENT AT FEUP", "PT");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    AddConference(conference);
    AddTalk(conference, talk);
    assertEqual("Ines", attendee1.GetName());
    attendee1.SetName("Ines Gomes");
    assertEqual("Ines Gomes", attendee1.GetName());
    AddAttendee(talk, attendee1);
    assertEqual(1L, GetTotalAttendeesByTalk(talk));
    assertEqual(SetUtil.set(attendee1), talk.GetAttendees());
    AddAttendee(talk, attendee2);
    assertEqual("STUDENT", attendee2.GetJobPosition());
    attendee2.SetJobPosition("Student");
    assertEqual("Student", attendee2.GetJobPosition());
    assertEqual("feup", attendee2.GetCompany().GetName());
    attendee2.SetCompany(new Company("FEUP"));
    assertEqual("FEUP", attendee2.GetCompany().GetName());
    assertEqual("STUDENT AT FEUP", attendee2.GetDescription());
    attendee2.SetDescription("Student At FEUP");
    assertEqual("Student At FEUP", attendee2.GetDescription());
    assertEqual("PT", attendee2.GetCountry());
    attendee2.SetCountry("Portugal");
    assertEqual("Portugal", attendee2.GetCountry());
    assertEqual(2L, GetTotalAttendeesByTalk(talk));
  }

  private void testCreateAndAddStartup() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Startup startup1 = new Startup("Startup Name", "Startup Description", "www.startup.pt", "PT");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    assertEqual("Startup Name", startup1.GetName());
    startup1.SetName("Emitu");
    assertEqual("Emitu", startup1.GetName());
    assertEqual("Startup Description", startup1.GetDescription());
    startup1.SetDescription("New Description");
    assertEqual("New Description", startup1.GetDescription());
    assertEqual("www.startup.pt", startup1.GetWebsite());
    startup1.SetWebsite("www.startup.en");
    assertEqual("www.startup.en", startup1.GetWebsite());
    assertEqual("PT", startup1.GetCountry());
    startup1.SetCountry("EN");
    assertEqual("EN", startup1.GetCountry());
    assertEqual(SetUtil.set(), GetStartups());
    AddStartup(startup1);
    assertEqual(SetUtil.set(startup1), GetStartups());
  }

  private void testRemoveStartup() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Startup startup1 = new Startup("Startup Name", "Startup Description", "www.startup.pt", "PT");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    AddStartup(startup1);
    RemoveStartup(startup1);
    assertEqual(SetUtil.set(), GetStartups());
  }

  private void testCreateAndAddInvestors() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Influential investor =
        new Influential(
            "Mark Zuckerberg", "CEO", new Company("facebook"), "Speaker Description", "EN");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    assertEqual(SetUtil.set(), GetInvestors());
    AddInvestor(investor);
    assertEqual(SetUtil.set(investor), GetInvestors());
  }

  private void testRemoveInvestors() {

    WebSummit webSummit = WebSummit.ClearInstance();
    Influential investor =
        new Influential(
            "Mark Zuckerberg", "CEO", new Company("facebook"), "Speaker Description", "EN");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    AddInvestor(investor);
    RemoveInvestor(investor);
    assertEqual(SetUtil.set(), GetInvestors());
  }

  private void testCreateAndAddNews() {

    WebSummit webSummit = WebSummit.ClearInstance();
    New new1 = new New("Title", "Body");
    webSummit.SetDates(Utils.copy(date1), Utils.copy(date2));
    assertEqual("Title", new1.GetTitle());
    new1.SetTitle("New Title");
    assertEqual("New Title", new1.GetTitle());
    assertEqual("Body", new1.GetBody());
    new1.SetBody("New Body");
    assertEqual("New Body", new1.GetBody());
    assertEqual(SetUtil.set(), GetNews());
    AddNew(new1);
    assertEqual(SetUtil.set(new1), GetNews());
  }

  private void AddConference(final Conference c) {

    WebSummit.GetInstance().AddConference(c);
  }

  private void AddTalk(final Conference c, final Talk t) {

    WebSummit.GetInstance().AddTalk(c, t);
  }

  private void RemoveTalk(final Conference c, final Talk t) {

    WebSummit.GetInstance().RemoveTalk(c, t);
  }

  private void AddCompany(final Conference conf, final Company c) {

    WebSummit.GetInstance().AddCompany(conf, c);
  }

  private void RemoveCompany(final Conference conf, final Company c) {

    WebSummit.GetInstance().RemoveCompany(conf, c);
  }

  private void AddAttendee(final Talk t, final Attendee a) {

    WebSummit.GetInstance().AddAttendee(t, a);
  }

  private void AddSpeaker(final Talk talk, final Influential s) {

    WebSummit.GetInstance().AddSpeaker(talk, s);
  }

  private void RemoveSpeaker(final Talk talk, final Influential s) {

    WebSummit.GetInstance().RemoveSpeaker(talk, s);
  }

  private void AddStartup(final Startup s) {

    WebSummit.GetInstance().AddStartup(s);
  }

  private void RemoveStartup(final Startup s) {

    WebSummit.GetInstance().RemoveStartup(s);
  }

  private VDMSet GetStartups() {

    return WebSummit.GetInstance().GetStartups();
  }

  private void AddInvestor(final Influential i) {

    WebSummit.GetInstance().AddInvestor(i);
  }

  private void RemoveInvestor(final Influential i) {

    WebSummit.GetInstance().RemoveInvestor(i);
  }

  private VDMSet GetInvestors() {

    return WebSummit.GetInstance().GetInvestors();
  }

  private void AddNew(final New n) {

    WebSummit.GetInstance().AddNew(n);
  }

  private VDMSet GetNews() {

    return WebSummit.GetInstance().GetNews();
  }

  private Number GetTotalAttendeesByTalk(final Talk talk) {

    return WebSummit.GetInstance().GetTotalAttendeesByTalk(talk);
  }

  public WebSummitTest() {}

  public String toString() {

    return "WebSummitTest{"
        + "date1 := "
        + Utils.toString(date1)
        + ", date2 := "
        + Utils.toString(date2)
        + ", time1 := "
        + Utils.toString(time1)
        + ", time2 := "
        + Utils.toString(time2)
        + ", time3 := "
        + Utils.toString(time3)
        + ", time4 := "
        + Utils.toString(time4)
        + "}";
  }
}
