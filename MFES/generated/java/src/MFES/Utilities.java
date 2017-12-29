package MFES;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Utilities {
  public static Number id = 1L;

  public static Boolean isBefore(final Talk talk1, final Talk talk2) {

    if (talk1.GetTime().hour.longValue() < talk2.GetTime().hour.longValue()) {
      return true;

    } else if (Utils.equals(talk1.GetTime().hour, talk2.GetTime().hour)) {
      if (talk1.GetTime().minute.longValue() < talk2.GetTime().minute.longValue()) {
        return true;

      } else {
        return false;
      }

    } else {
      return false;
    }
  }

  private static VDMSeq lmergeSort(final VDMSeq s1, final VDMSeq s2) {

    if (Utils.empty(s1)) {
      return Utils.copy(s2);

    } else if (Utils.empty(s2)) {
      return Utils.copy(s1);

    } else if (isBefore(((Talk) s1.get(0)), ((Talk) s2.get(0)))) {
      return SeqUtil.conc(
          SeqUtil.seq(((Talk) s1.get(0))),
          lmergeSort(SeqUtil.tail(Utils.copy(s1)), Utils.copy(s2)));

    } else {
      return SeqUtil.conc(
          SeqUtil.seq(((Talk) s2.get(0))),
          lmergeSort(Utils.copy(s1), SeqUtil.tail(Utils.copy(s2))));
    }
  }

  public Utilities() {}

  public static Boolean IsLeapYear(final Number year) {

    Boolean orResult_1 = false;

    Boolean andResult_20 = false;

    if (Utils.equals(Utils.mod(year.longValue(), 4L), 0L)) {
      if (!(Utils.equals(Utils.mod(year.longValue(), 100L), 0L))) {
        andResult_20 = true;
      }
    }

    if (andResult_20) {
      orResult_1 = true;
    } else {
      orResult_1 = Utils.equals(Utils.mod(year.longValue(), 400L), 0L);
    }

    return orResult_1;
  }

  public static Number DaysOfMonth(final Number year, final Number month) {

    Number casesExpResult_1 = null;

    Number intPattern_1 = month;
    Boolean success_1 = Utils.equals(intPattern_1, 1L);

    if (!(success_1)) {
      Number intPattern_2 = month;
      success_1 = Utils.equals(intPattern_2, 3L);

      if (!(success_1)) {
        Number intPattern_3 = month;
        success_1 = Utils.equals(intPattern_3, 5L);

        if (!(success_1)) {
          Number intPattern_4 = month;
          success_1 = Utils.equals(intPattern_4, 7L);

          if (!(success_1)) {
            Number intPattern_5 = month;
            success_1 = Utils.equals(intPattern_5, 8L);

            if (!(success_1)) {
              Number intPattern_6 = month;
              success_1 = Utils.equals(intPattern_6, 10L);

              if (!(success_1)) {
                Number intPattern_7 = month;
                success_1 = Utils.equals(intPattern_7, 12L);

                if (!(success_1)) {
                  Number intPattern_8 = month;
                  success_1 = Utils.equals(intPattern_8, 4L);

                  if (!(success_1)) {
                    Number intPattern_9 = month;
                    success_1 = Utils.equals(intPattern_9, 6L);

                    if (!(success_1)) {
                      Number intPattern_10 = month;
                      success_1 = Utils.equals(intPattern_10, 9L);

                      if (!(success_1)) {
                        Number intPattern_11 = month;
                        success_1 = Utils.equals(intPattern_11, 11L);

                        if (!(success_1)) {
                          Number intPattern_12 = month;
                          success_1 = Utils.equals(intPattern_12, 2L);

                          if (success_1) {
                            Number ternaryIfExp_1 = null;

                            if (IsLeapYear(year)) {
                              ternaryIfExp_1 = 29L;
                            } else {
                              ternaryIfExp_1 = 28L;
                            }

                            casesExpResult_1 = ternaryIfExp_1;
                          }

                        } else {
                          casesExpResult_1 = 30L;
                        }

                      } else {
                        casesExpResult_1 = 30L;
                      }

                    } else {
                      casesExpResult_1 = 30L;
                    }

                  } else {
                    casesExpResult_1 = 30L;
                  }

                } else {
                  casesExpResult_1 = 31L;
                }

              } else {
                casesExpResult_1 = 31L;
              }

            } else {
              casesExpResult_1 = 31L;
            }

          } else {
            casesExpResult_1 = 31L;
          }

        } else {
          casesExpResult_1 = 31L;
        }

      } else {
        casesExpResult_1 = 31L;
      }

    } else {
      casesExpResult_1 = 31L;
    }

    return casesExpResult_1;
  }

  public static VDMSeq mergeSortTalks(final VDMSeq l) {

    Boolean orResult_2 = false;

    if (Utils.empty(l)) {
      orResult_2 = true;
    } else {
      orResult_2 = Utils.equals(l.size(), 1L);
    }

    if (orResult_2) {
      return Utils.copy(l);

    } else {
      return lmergeSort(
          mergeSortTalks(SeqUtil.seq(((Talk) l.get(0)))),
          mergeSortTalks(SeqUtil.tail(Utils.copy(l))));
    }
  }

  public static Date nextDay(final Date d) {

    if (Utils.equals(d.day, DaysOfMonth(d.year, d.month))) {
      if (d.month.longValue() < 12L) {
        return new Date(d.year, d.month.longValue() + 1L, 1L);

      } else {
        return new Date(d.year.longValue() + 1L, 1L, 1L);
      }

    } else {
      return new Date(d.year, d.month, d.day.longValue() + 1L);
    }
  }

  public static Boolean isSetBetweenDates(
      final Date dToCompare, final Date dinit, final Date dfinal) {

    if (Utils.equals(dinit, nextDay(Utils.copy(dfinal)))) {
      return false;

    } else {
      if (Utils.equals(dToCompare, dinit)) {
        return true;

      } else {
        return isSetBetweenDates(
            Utils.copy(dToCompare), nextDay(Utils.copy(dinit)), Utils.copy(dfinal));
      }
    }
  }

  public static Boolean DoesntOverlap(
      final Time time1, final Number dur1, final Time time2, final Number dur2) {

    if (time1.hour.longValue() < time2.hour.longValue()) {
      if (time1.minute.longValue() + dur1.longValue() <= 60L) {
        return true;

      } else {
        if (time1.minute.longValue() + dur1.longValue() - 60L <= time2.minute.longValue()) {
          return true;

        } else {
          return false;
        }
      }

    } else {
      if (Utils.equals(time1.hour, time2.hour)) {
        if (time1.minute.longValue() < time2.minute.longValue()) {
          if (time1.minute.longValue() + dur1.longValue() <= time2.minute.longValue()) {
            return true;

          } else {
            return false;
          }

        } else {
          if (time2.minute.longValue() < time1.minute.longValue()) {
            if (time2.minute.longValue() + dur2.longValue() <= time1.minute.longValue()) {
              return true;

            } else {
              return false;
            }

          } else {
            return false;
          }
        }

      } else {
        if (time2.hour.longValue() < time1.hour.longValue()) {
          if (time2.minute.longValue() + dur2.longValue() <= 60L) {
            return true;

          } else {
            if (time2.minute.longValue() + dur2.longValue() - 60L <= time1.minute.longValue()) {
              return true;

            } else {
              return false;
            }
          }

        } else {
          return false;
        }
      }
    }
  }

  public String toString() {

    return "Utilities{" + "id := " + Utils.toString(id) + "}";
  }

  public static class Date implements Record {
    public Number year;
    public Number month;
    public Number day;

    public Date(final Number _year, final Number _month, final Number _day) {

      year = _year;
      month = _month;
      day = _day;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(year, other.year))
          && (Utils.equals(month, other.month))
          && (Utils.equals(day, other.day));
    }

    public int hashCode() {

      return Utils.hashCode(year, month, day);
    }

    public Date copy() {

      return new Date(year, month, day);
    }

    public String toString() {

      return "mk_Utilities`Date" + Utils.formatFields(year, month, day);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_22 = false;

    if (d.month.longValue() >= 1L) {
      Boolean andResult_23 = false;

      if (d.month.longValue() <= 12L) {
        Boolean andResult_24 = false;

        if (d.day.longValue() >= 1L) {
          if (d.day.longValue() <= DaysOfMonth(d.year, d.month).longValue()) {
            andResult_24 = true;
          }
        }

        if (andResult_24) {
          andResult_23 = true;
        }
      }

      if (andResult_23) {
        andResult_22 = true;
      }
    }

    return andResult_22;
  }

  public static class Time implements Record {
    public Number hour;
    public Number minute;

    public Time(final Number _hour, final Number _minute) {

      hour = _hour;
      minute = _minute;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Time)) {
        return false;
      }

      Time other = ((Time) obj);

      return (Utils.equals(hour, other.hour)) && (Utils.equals(minute, other.minute));
    }

    public int hashCode() {

      return Utils.hashCode(hour, minute);
    }

    public Time copy() {

      return new Time(hour, minute);
    }

    public String toString() {

      return "mk_Utilities`Time" + Utils.formatFields(hour, minute);
    }
  }

  public static Boolean inv_Time(final Time t) {

    Boolean andResult_28 = false;

    if (t.hour.longValue() < 24L) {
      if (t.minute.longValue() < 60L) {
        andResult_28 = true;
      }
    }

    return andResult_28;
  }
}
