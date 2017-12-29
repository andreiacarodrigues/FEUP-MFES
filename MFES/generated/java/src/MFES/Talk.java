package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Talk {
  private String name = SeqUtil.toStr(SeqUtil.seq());
  private String description = SeqUtil.toStr(SeqUtil.seq());
  private Utilities.Date date;
  private Utilities.Time time;
  private Number duration;
  private String conference = SeqUtil.toStr(SeqUtil.seq());
  private VDMSet speakers = SetUtil.set();
  private VDMSet attendees = SetUtil.set();

  public void cg_init_Talk_1(
      final String talkName,
      final String talkDescription,
      final Utilities.Date talkDate,
      final Utilities.Time talkTime,
      final Number talkDuration) {

    name = talkName;
    description = talkDescription;
    date = Utils.copy(talkDate);
    time = Utils.copy(talkTime);
    duration = talkDuration;
    return;
  }

  public Talk(
      final String talkName,
      final String talkDescription,
      final Utilities.Date talkDate,
      final Utilities.Time talkTime,
      final Number talkDuration) {

    cg_init_Talk_1(
        talkName, talkDescription, Utils.copy(talkDate), Utils.copy(talkTime), talkDuration);
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

  public Utilities.Date GetDate() {

    return Utils.copy(date);
  }

  public void SetDate(final Utilities.Date d) {

    date = Utils.copy(d);
  }

  public Utilities.Time GetTime() {

    return Utils.copy(time);
  }

  public void SetTime(final Utilities.Time t) {

    time = Utils.copy(t);
  }

  public Number GetDuration() {

    return duration;
  }

  public void SetDuration(final Number d) {

    duration = d;
  }

  public String GetConference() {

    return conference;
  }

  public void SetConference(final String c) {

    conference = c;
  }

  public VDMSet GetSpeakers() {

    return Utils.copy(speakers);
  }

  public VDMSet GetAttendees() {

    return Utils.copy(attendees);
  }

  public void AddSpeaker(final Influential speaker) {

    speakers = SetUtil.union(Utils.copy(speakers), SetUtil.set(speaker));
  }

  public void RemoveSpeaker(final Influential speaker) {

    speakers = SetUtil.diff(Utils.copy(speakers), SetUtil.set(speaker));
  }

  public void AddAttendee(final Attendee attendee) {

    attendees = SetUtil.union(Utils.copy(attendees), SetUtil.set(attendee));
  }

  public Number GetTotalAttendees() {

    return attendees.size();
  }

  public Talk() {}

  public String toString() {

    return "Talk{"
        + "name := "
        + Utils.toString(name)
        + ", description := "
        + Utils.toString(description)
        + ", date := "
        + Utils.toString(date)
        + ", time := "
        + Utils.toString(time)
        + ", duration := "
        + Utils.toString(duration)
        + ", conference := "
        + Utils.toString(conference)
        + ", speakers := "
        + Utils.toString(speakers)
        + ", attendees := "
        + Utils.toString(attendees)
        + "}";
  }
}
