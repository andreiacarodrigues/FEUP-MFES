package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Influential extends Attendee {
  private String jobPosition = SeqUtil.toStr(SeqUtil.seq());
  private Company company;
  private String description = SeqUtil.toStr(SeqUtil.seq());
  private String country = SeqUtil.toStr(SeqUtil.seq());

  public void cg_init_Influential_1(
      final String attendeeName,
      final String attendeeJobPosition,
      final Company attendeeCompany,
      final String attendeeDescription,
      final String attendeeCountry) {

    jobPosition = attendeeJobPosition;
    company = attendeeCompany;
    description = attendeeDescription;
    country = attendeeCountry;
    cg_init_Attendee_1(attendeeName);
  }

  public Influential(
      final String attendeeName,
      final String attendeeJobPosition,
      final Company attendeeCompany,
      final String attendeeDescription,
      final String attendeeCountry) {

    cg_init_Influential_1(
        attendeeName, attendeeJobPosition, attendeeCompany, attendeeDescription, attendeeCountry);
  }

  public String GetJobPosition() {

    return jobPosition;
  }

  public void SetJobPosition(final String j) {

    jobPosition = j;
  }

  public Company GetCompany() {

    return company;
  }

  public void SetCompany(final Company c) {

    company = c;
  }

  public String GetDescription() {

    return description;
  }

  public void SetDescription(final String d) {

    description = d;
  }

  public String GetCountry() {

    return country;
  }

  public void SetCountry(final String c) {

    country = c;
  }

  public Influential() {}

  public String toString() {

    return "Influential{"
        + "jobPosition := "
        + Utils.toString(jobPosition)
        + ", company := "
        + Utils.toString(company)
        + ", description := "
        + Utils.toString(description)
        + ", country := "
        + Utils.toString(country)
        + "}";
  }
}
