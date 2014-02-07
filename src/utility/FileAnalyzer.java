
package utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Utility for analyzing the file to filter out information that are needed.
 * 
 * @author padriaan
 * @version 1
 */
public final class FileAnalyzer
{
  /**
   * Hour during the noon.
   */
  private static final int NOON_HOUR = 12;
  
  /**
   * Start year of java date class.
   */
  private static final int START_YEAR = 1900;

  /**
   * Prevents instantiation.
   */
  private FileAnalyzer()
  {
  }

  /**
   * Analyzes a set of data, and remove any data that is out of the defined date
   * range.
   * 
   * @param the_data Set of all extracted data.
   * @param the_date_from Earliest date.
   * @param the_date_to Latest date.
   * @return A filtered data.
   */
  @SuppressWarnings("deprecation")
  public static List<String> analyze(final List<String> the_data, final Date the_date_from,
                                     final Date the_date_to)
  {

    int i = 0;

    while (i < the_data.size())
    {
      final String line = the_data.get(i);

      final String emergency_keyword = "EMERGENCY";
      final String new_call_keyword = "*** New Call ***";

      final boolean emergency_keyword_found = line.contains(emergency_keyword);
      final boolean new_call_keyword_found = line.contains(new_call_keyword);

      if (emergency_keyword_found && new_call_keyword_found)
      {
        final String[] line_split = line.split(" ");
        final String date_string = line_split[0];
        final String[] date_string_split = date_string.split("/");

        final List<Integer> date_int_list = new ArrayList<Integer>();

        for (String j : date_string_split)
        {
          final int date_int = Integer.valueOf(j);
          date_int_list.add(date_int);
        }

        final int month = date_int_list.get(0) - 1;
        final int date = date_int_list.get(1);
        final int year = date_int_list.get(2) - START_YEAR;

        final Date current_date = new Date();
        current_date.setMonth(month);
        current_date.setDate(date);
        current_date.setYear(year);
        current_date.setHours(NOON_HOUR);
        current_date.setMinutes(0);
        current_date.setSeconds(0);

        final boolean after_date_from = current_date.after(the_date_from);
        final boolean before_date_to = current_date.before(the_date_to);

        if (after_date_from && before_date_to)
        {
          i++;
        }
        else
        {
          the_data.remove(i);
        }
      }
      else
      {
        the_data.remove(i);
      }
    }

    return the_data;
  }
}
