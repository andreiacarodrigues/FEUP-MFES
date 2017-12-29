package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class WebSummit {
  private VDMSet conferences = SetUtil.set();
  private Exhibit exhibit = new Exhibit();
  private VDMSet attendees = SetUtil.set();
  private VDMSet news = SetUtil.set();
  private Utilities.Date initialDate = new Utilities.Date(2001L, 1L, 1L);
  private Utilities.Date finalDate = new Utilities.Date(2001L, 1L, 2L);
  private static WebSummit websummit = new WebSummit();

  public void cg_init_WebSummit_1() {

    return;
  }

  public WebSummit() {

    cg_init_WebSummit_1();
  }

  public static WebSummit GetInstance() {

    return WebSummit.websummit;
  }

  public static WebSummit ClearInstance() {

    websummit = new WebSummit();
    return GetInstance();
  }

  public void SetDates(final Utilities.Date d1, final Utilities.Date d2) {

    initialDate = Utils.copy(d1);
    finalDate = Utils.copy(d2);
  }

  public Utilities.Date GetInitialDate() {

    return Utils.copy(initialDate);
  }

  public Utilities.Date GetFinalDate() {

    return Utils.copy(finalDate);
  }

  public void AddConference(final Conference conference) {

    conferences = SetUtil.union(Utils.copy(conferences), SetUtil.set(conference));
  }

  public void AddTalk(final Conference conference, final Talk talk) {

    conference.AddTalk(talk);
  }

  public void RemoveTalk(final Conference conference, final Talk talk) {

    conference.RemoveTalk(talk);
  }

  public void AddCompany(final Conference conference, final Company company) {

    conference.AddCompany(company);
  }

  public void RemoveCompany(final Conference conference, final Company company) {

    conference.RemoveCompany(company);
  }

  public void AddSpeaker(final Talk talk, final Influential speaker) {

    talk.AddSpeaker(speaker);
    if (!(SetUtil.inSet(speaker, attendees))) {
      attendees = SetUtil.union(Utils.copy(attendees), SetUtil.set(speaker));
    }
  }

  public void RemoveSpeaker(final Talk talk, final Influential speaker) {

    talk.RemoveSpeaker(speaker);
  }

  public VDMSet GetConferences() {

    return Utils.copy(conferences);
  }

  public Conference GetConference(final String conferenceName) {

    for (Iterator iterator_8 = conferences.iterator(); iterator_8.hasNext(); ) {
      Conference conference = (Conference) iterator_8.next();
      if (Utils.equals(conference.GetName(), conferenceName)) {
        return conference;
      }
    }
    return null;
  }

  public VDMMap GetSchedule(final Conference c) {

    return c.GetSchedule();
  }

  public VDMMap GetSchedule() {

    VDMMap temp = MapUtil.map();
    Utilities.Date currentDate = Utils.copy(initialDate);
    for (Iterator iterator_9 = conferences.iterator(); iterator_9.hasNext(); ) {
      Conference conference = (Conference) iterator_9.next();
      if (Utils.empty(temp)) {
        temp = conference.GetSchedule();
      } else {
        Boolean whileCond_2 = true;
        while (whileCond_2) {
          whileCond_2 = !(Utils.equals(currentDate, Utilities.nextDay(Utils.copy(finalDate))));
          if (!(whileCond_2)) {
            break;
          }

          {
            Utils.mapSeqUpdate(
                temp,
                Utils.copy(currentDate),
                SeqUtil.conc(
                    Utils.copy(((VDMSeq) Utils.get(temp, currentDate))),
                    conference.GetSchedule(Utils.copy(currentDate))));
            currentDate = Utilities.nextDay(Utils.copy(currentDate));
          }
        }
      }
    }
    currentDate = WebSummit.GetInstance().GetInitialDate();
    Boolean whileCond_3 = true;
    while (whileCond_3) {
      whileCond_3 = !(Utils.equals(currentDate, Utilities.nextDay(Utils.copy(finalDate))));
      if (!(whileCond_3)) {
        break;
      }

      {
        Utils.mapSeqUpdate(
            temp,
            Utils.copy(currentDate),
            Utilities.mergeSortTalks(Utils.copy(((VDMSeq) Utils.get(temp, currentDate)))));
        currentDate = Utilities.nextDay(Utils.copy(currentDate));
      }
    }

    return Utils.copy(temp);
  }

  public VDMSeq GetSchedule(final Utilities.Date date, final Utilities.Time time) {

    VDMSeq temp = SeqUtil.seq();
    for (Iterator iterator_10 = conferences.iterator(); iterator_10.hasNext(); ) {
      Conference conference = (Conference) iterator_10.next();
      for (Iterator iterator_11 =
              SeqUtil.elems(Utils.copy(((VDMSeq) Utils.get(conference.GetSchedule(), date))))
                  .iterator();
          iterator_11.hasNext();
          ) {
        Talk talk = (Talk) iterator_11.next();
        if (Utils.equals(talk.GetTime().hour, time.hour)) {
          temp = SeqUtil.conc(Utils.copy(temp), SeqUtil.seq(talk));
        } else {
          if (Utils.equals(talk.GetTime().hour.longValue() + 1L, time.hour)) {
            if (talk.GetDuration().longValue() >= 60L - talk.GetTime().minute.longValue()) {
              if (talk.GetTime().minute.longValue() + talk.GetDuration().longValue() - 60L <= 60L) {
                temp = SeqUtil.conc(Utils.copy(temp), SeqUtil.seq(talk));
              }
            }
          }
        }
      }
    }
    temp = Utilities.mergeSortTalks(Utils.copy(temp));
    return Utils.copy(temp);
  }

  public void AddAttendee(final Talk talk, final Attendee attendee) {

    talk.AddAttendee(attendee);
    if (!(SetUtil.inSet(attendee, attendees))) {
      attendees = SetUtil.union(Utils.copy(attendees), SetUtil.set(attendee));
    }
  }

  private Boolean notAlreadyExistent(final Conference newConference) {

    Boolean doesntExist = true;
    for (Iterator iterator_12 = conferences.iterator(); iterator_12.hasNext(); ) {
      Conference conference = (Conference) iterator_12.next();
      if (Utils.equals(conference.GetName(), newConference.GetName())) {
        doesntExist = false;
        return doesntExist;
      }
    }
    return doesntExist;
  }

  public VDMSet GetAttendees() {

    return Utils.copy(attendees);
  }

  public VDMSet GetStartups() {

    return exhibit.GetStartups();
  }

  public void AddStartup(final Startup s) {

    exhibit.AddStartup(s);
  }

  public void RemoveStartup(final Startup s) {

    exhibit.RemoveStartup(s);
  }

  public void AddInvestor(final Influential i) {

    exhibit.AddInvestor(i);
  }

  public void RemoveInvestor(final Influential i) {

    exhibit.RemoveInvestor(i);
  }

  public VDMSet GetInvestors() {

    return exhibit.GetInvestors();
  }

  public void AddNew(final New n) {

    news = SetUtil.union(Utils.copy(news), SetUtil.set(n));
  }

  public VDMSet GetNews() {

    return Utils.copy(news);
  }

  public Number GetTotalAttendeesByTalk(final Talk talk) {

    return talk.GetTotalAttendees();
  }

  public String toString() {

    return "WebSummit{"
        + "conferences := "
        + Utils.toString(conferences)
        + ", exhibit := "
        + Utils.toString(exhibit)
        + ", attendees := "
        + Utils.toString(attendees)
        + ", news := "
        + Utils.toString(news)
        + ", initialDate := "
        + Utils.toString(initialDate)
        + ", finalDate := "
        + Utils.toString(finalDate)
        + ", websummit := "
        + Utils.toString(websummit)
        + "}";
  }
}
