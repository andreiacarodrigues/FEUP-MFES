package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class New {
  private String title = SeqUtil.toStr(SeqUtil.seq());
  private String body = SeqUtil.toStr(SeqUtil.seq());

  public void cg_init_New_1(final String newTitle, final String newBody) {

    title = newTitle;
    body = newBody;
    return;
  }

  public New(final String newTitle, final String newBody) {

    cg_init_New_1(newTitle, newBody);
  }

  public String GetTitle() {

    return title;
  }

  public void SetTitle(final String newTitle) {

    title = newTitle;
  }

  public String GetBody() {

    return body;
  }

  public void SetBody(final String newBody) {

    body = newBody;
  }

  public New() {}

  public String toString() {

    return "New{" + "title := " + Utils.toString(title) + ", body := " + Utils.toString(body) + "}";
  }
}
