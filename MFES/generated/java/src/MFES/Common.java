package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Common extends Attendee {
  public void cg_init_Common_1(final String attendeeName) {

    cg_init_Attendee_1(attendeeName);
  }

  public Common(final String attendeeName) {

    cg_init_Common_1(attendeeName);
  }

  public Common() {}

  public String toString() {

    return "Common{}";
  }
}
