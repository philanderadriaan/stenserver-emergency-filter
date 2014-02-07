
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
public class FileAnalyzer
{

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
  public static List<String> analyze(List<String> the_data, Date the_date_from,
                                     Date the_date_to)
  {

    int i = 0;

    while (i < the_data.size())
    {
      String line = the_data.get(i);

      String emergency_keyword = "EMERGENCY";
      String new_call_keyword = "*** New Call ***";

      boolean emergency_keyword_found = line.contains(emergency_keyword);
      boolean new_call_keyword_found = line.contains(new_call_keyword);

      if (emergency_keyword_found && new_call_keyword_found)
      {
        String[] line_split = line.split(" ");
        String date_string = line_split[0];
        String[] date_string_split = date_string.split("/");

        List<Integer> date_int_list = new ArrayList<Integer>();

        for (String j : date_string_split)
        {
          int date_int = Integer.valueOf(j);
          date_int_list.add(date_int);
        }

        int month = date_int_list.get(0) - 1;
        int date = date_int_list.get(1);
        int year = date_int_list.get(2) - 1900;

        Date current_date = new Date();
        current_date.setMonth(month);
        current_date.setDate(date);
        current_date.setYear(year);
        current_date.setHours(12);
        current_date.setMinutes(0);
        current_date.setSeconds(0);

        boolean after_date_from = current_date.after(the_date_from);
        boolean before_date_to = current_date.before(the_date_to);

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
