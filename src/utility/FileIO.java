
package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the input and output of files for this program.
 * 
 * @author padriaan
 * @version 1
 */
public final class FileIO
{

  /**
   * Prevents instantiation.
   */
  private FileIO()
  {
  }

  /**
   * Inputs the file into a list of strings, each representing a line of text in
   * the file.
   * 
   * @param the_file Input file.
   * @return Data representation of the contents of the file per line.
   * @throws FileNotFoundException Throws exception when STENServer.log file is
   *           not found.
   */
  public static List<String> input(final File the_file) throws FileNotFoundException
  {
    final List<String> data = new ArrayList<String>();
    Scanner input = null;
    input = new Scanner(the_file);
    while (input.hasNextLine())
    {
      final String line = input.nextLine();
      data.add(line);
    }
    input.close();
    return data;
  }

  /**
   * Converts a list of data into a file specified by the path.
   * 
   * @param the_path Path of the output file.
   * @param the_data Data to bit parsed into the file.
   * @throws UnsupportedEncodingException
   * @throws FileNotFoundException Throws exception when STENServer file is not
   *           found.
   * @throws UnsupportedEncodingException Throws exception when file contains
   *           unsupported encoding.
   */
  public static void output(final String the_path, final List<String> the_data)
      throws FileNotFoundException, UnsupportedEncodingException
  {
    PrintWriter print_writer = null;
    print_writer = new PrintWriter(the_path, "UTF-8");
    for (String i : the_data)
    {
      print_writer.println(i);
    }
    print_writer.close();
  }
}
