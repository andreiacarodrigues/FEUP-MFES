package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Conference {
  private String name = SeqUtil.toStr(SeqUtil.seq());
  private String description = SeqUtil.toStr(SeqUtil.seq());
  private VDMSet talks = SetUtil.set();
  private VDMSet companies = SetUtil.set();

  public void cg_init_Conference_1(
      final String conferenceName, final String conferenceDescription) {

    name = conferenceName;
    description = conferenceDescription;
    return;
  }

  public Conference(final String conferenceName, final String conferenceDescription) {

    cg_init_Conference_1(conferenceName, conferenceDescription);
  }

  public String GetName() {

    return name;
  }

  public void SetName(final String n) {

    name = n;
  }

  public String GetDescription() {

    return description;
  }

  public void SetDescription(final String d) {

    description = d;
  }

  public VDMSet GetTalks() {

    return Utils.copy(talks);
  }

  public Talk GetTalk(final String talkName) {

    for (Iterator iterator_2 = talks.iterator(); iterator_2.hasNext(); ) {
      Talk talk = (Talk) iterator_2.next();
      if (Utils.equals(talk.GetName(), talkName)) {
        return talk;
      }
    }
    return null;
  }

  public VDMSet GetCompanies() {

    return Utils.copy(companies);
  }

  public void AddTalk(final Talk talk) {

    talk.SetConference(name);
    talks = SetUtil.union(Utils.copy(talks), SetUtil.set(talk));
  }

  public void RemoveTalk(final Talk talk) {

    talks = SetUtil.diff(Utils.copy(talks), SetUtil.set(talk));
  }

  public void AddCompany(final Company company) {

    companies = SetUtil.union(Utils.copy(companies), SetUtil.set(company));
  }

  public void RemoveCompany(final Company company) {

    companies = SetUtil.diff(Utils.copy(companies), SetUtil.set(company));
  }

  public VDMSeq GetSchedule(final Utilities.Date date) {

    VDMSeq talkSet = SeqUtil.seq();
    for (Iterator iterator_3 = talks.iterator(); iterator_3.hasNext(); ) {
      Talk talk = (Talk) iterator_3.next();
      if (Utils.equals(talk.GetDate(), date)) {
        talkSet = SeqUtil.conc(Utils.copy(talkSet), SeqUtil.seq(talk));
      }
    }
    return Utilities.mergeSortTalks(Utils.copy(talkSet));
  }

  public VDMMap GetSchedule() {

    VDMMap result = MapUtil.map();
    Utilities.Date currentDate = WebSummit.GetInstance().GetInitialDate();
    Utilities.Date finalDate = WebSummit.GetInstance().GetFinalDate();
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = !(Utils.equals(currentDate, Utilities.nextDay(Utils.copy(finalDate))));
      if (!(whileCond_1)) {
        break;
      }

      {
        result =
            MapUtil.munion(
                Utils.copy(result),
                MapUtil.map(
                    new Maplet(Utils.copy(currentDate), GetSchedule(Utils.copy(currentDate)))));
        currentDate = Utilities.nextDay(Utils.copy(currentDate));
      }
    }

    return Utils.copy(result);
  }

  private Boolean DoesntOverlap(final Talk newTalk) {

    Boolean doesntOverlap = true;
    for (Iterator iterator_4 = talks.iterator(); iterator_4.hasNext(); ) {
      Talk talk = (Talk) iterator_4.next();
      if (Utils.equals(talk.GetDate(), newTalk.GetDate())) {
        if (Utils.equals(
            Utilities.DoesntOverlap(
                newTalk.GetTime(), newTalk.GetDuration(), talk.GetTime(), talk.GetDuration()),
            false)) {
          doesntOverlap = false;
          return doesntOverlap;
        }
      }
    }
    return doesntOverlap;
  }

  private Boolean notAlreadyExistent(final Talk newTalk) {

    Boolean doesntExist = true;
    for (Iterator iterator_5 = talks.iterator(); iterator_5.hasNext(); ) {
      Talk talk = (Talk) iterator_5.next();
      if (Utils.equals(talk.GetName(), newTalk.GetName())) {
        doesntExist = false;
        return doesntExist;
      }
    }
    return doesntExist;
  }

  private Boolean notAlreadyExistent(final Company newCompany) {

    Boolean doesntExist = true;
    for (Iterator iterator_6 = companies.iterator(); iterator_6.hasNext(); ) {
      Company company = (Company) iterator_6.next();
      if (Utils.equals(company.GetName(), newCompany.GetName())) {
        doesntExist = false;
        return doesntExist;
      }
    }
    return doesntExist;
  }

  public Conference() {}

  public String toString() {

    return "Conference{"
        + "name := "
        + Utils.toString(name)
        + ", description := "
        + Utils.toString(description)
        + ", talks := "
        + Utils.toString(talks)
        + ", companies := "
        + Utils.toString(companies)
        + "}";
  }
}
