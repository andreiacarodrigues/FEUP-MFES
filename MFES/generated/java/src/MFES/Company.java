package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Company {
  private String name = SeqUtil.toStr(SeqUtil.seq());

  public void cg_init_Company_1(final String companyName) {

    name = companyName;
    return;
  }

  public Company(final String companyName) {

    cg_init_Company_1(companyName);
  }

  public String GetName() {

    return name;
  }

  public void SetName(final String n) {

    name = n;
  }

  public Company() {}

  public String toString() {

    return "Company{" + "name := " + Utils.toString(name) + "}";
  }
}
