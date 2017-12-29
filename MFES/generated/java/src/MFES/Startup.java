package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Startup {
  private String name = SeqUtil.toStr(SeqUtil.seq());
  private String description = SeqUtil.toStr(SeqUtil.seq());
  private String website = SeqUtil.toStr(SeqUtil.seq());
  private String country = SeqUtil.toStr(SeqUtil.seq());

  public void cg_init_Startup_1(
      final String startupName,
      final String startupDescription,
      final String startupWebsite,
      final String startupCountry) {

    name = startupName;
    description = startupDescription;
    website = startupWebsite;
    country = startupCountry;
    return;
  }

  public Startup(
      final String startupName,
      final String startupDescription,
      final String startupWebsite,
      final String startupCountry) {

    cg_init_Startup_1(startupName, startupDescription, startupWebsite, startupCountry);
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

  public String GetWebsite() {

    return website;
  }

  public void SetWebsite(final String w) {

    website = w;
  }

  public String GetCountry() {

    return country;
  }

  public void SetCountry(final String c) {

    country = c;
  }

  public Startup() {}

  public String toString() {

    return "Startup{"
        + "name := "
        + Utils.toString(name)
        + ", description := "
        + Utils.toString(description)
        + ", website := "
        + Utils.toString(website)
        + ", country := "
        + Utils.toString(country)
        + "}";
  }
}
