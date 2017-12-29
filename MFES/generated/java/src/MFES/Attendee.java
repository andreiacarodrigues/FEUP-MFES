package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Attendee {
  private String name = SeqUtil.toStr(SeqUtil.seq());
  private Number id;

  public void cg_init_Attendee_1(final String attendeeName) {

    name = attendeeName;
    id = Utilities.id;
    Utilities.id = Utilities.id.longValue() + 1L;
    return;
  }

  public Attendee(final String attendeeName) {

    cg_init_Attendee_1(attendeeName);
  }

  public String GetName() {

    return name;
  }

  public Number GetID() {

    return id;
  }

  public void SetName(final String n) {

    name = n;
  }

  public Attendee() {}

  public String toString() {

    return "Attendee{" + "name := " + Utils.toString(name) + ", id := " + Utils.toString(id) + "}";
  }
}
