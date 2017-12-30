package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Exhibit {
  private VDMSet startups = SetUtil.set();
  private VDMSet investors = SetUtil.set();

  public void cg_init_Exhibit_1() {

    return;
  }

  public Exhibit() {

    cg_init_Exhibit_1();
  }

  public VDMSet GetStartups() {

    return Utils.copy(startups);
  }

  public Startup GetStartup(final String name) {

    for (Iterator iterator_7 = startups.iterator(); iterator_7.hasNext(); ) {
      Startup startup = (Startup) iterator_7.next();
      if (Utils.equals(startup.GetName(), name)) {
        return startup;
      }
    }
    return null;
  }

  public void AddStartup(final Startup startup) {

    startups = SetUtil.union(Utils.copy(startups), SetUtil.set(startup));
  }

  public void RemoveStartup(final Startup startup) {

    startups = SetUtil.diff(Utils.copy(startups), SetUtil.set(startup));
  }

  public VDMSet GetInvestors() {

    return Utils.copy(investors);
  }

  public Influential GetInvestor(final String name) {

    for (Iterator iterator_8 = investors.iterator(); iterator_8.hasNext(); ) {
      Influential investor = (Influential) iterator_8.next();
      if (Utils.equals(investor.GetName(), name)) {
        return investor;
      }
    }
    return null;
  }

  public void AddInvestor(final Influential investor) {

    investors = SetUtil.union(Utils.copy(investors), SetUtil.set(investor));
  }

  public String toString() {

    return "Exhibit{"
        + "startups := "
        + Utils.toString(startups)
        + ", investors := "
        + Utils.toString(investors)
        + "}";
  }
}
