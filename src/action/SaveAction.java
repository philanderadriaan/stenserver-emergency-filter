
package action;

import gui.frame.NKFrame;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import utility.FileAnalyzer;
import utility.FileIO;

import com.toedter.calendar.JDateChooser;

/**
 * Triggers to save the output file.
 * 
 * @author padriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class SaveAction extends AbstractAction
{

  /**
   * File dialog box for saving.
   */
  JFileChooser my_save_file_chooser = new JFileChooser(System.getProperty("user.dir"));
  /**
   * Current instance of the window. Holds key data to be used when saving.
   */
  private NKFrame my_frame;

  /**
   * Creates the trigger.
   * 
   * @param the_frame Current instance of the window.
   */
  public SaveAction(final NKFrame the_frame)
  {
    super("Save As...");
    my_frame = the_frame;
    File default_file = new File("Output.txt");
    my_save_file_chooser.setSelectedFile(default_file);
  }

  @SuppressWarnings("deprecation")
  @Override
  public final void actionPerformed(final ActionEvent arg0)
  {
    JDateChooser date_from_chooser = my_frame.getDateFromChooser();
    JDateChooser date_to_chooser = my_frame.getDateToChooser();
    Date date_from = date_from_chooser.getDate();
    Date date_to = date_to_chooser.getDate();
    date_from.setHours(0);
    date_from.setMinutes(0);
    date_from.setSeconds(0);
    date_to.setHours(23);
    date_to.setMinutes(59);
    date_to.setSeconds(59);
    if (date_from.after(date_to))
    {
      JOptionPane.showMessageDialog(null, "Invalid date range.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
    else
    {
      int approval = my_save_file_chooser.showSaveDialog(null);
      if (approval == JFileChooser.APPROVE_OPTION)
      {
        File log_file = my_frame.getLogFile();
        List<String> data = null;
        try
        {
          data = FileIO.input(log_file);
        }
        catch (FileNotFoundException e)
        {
          JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
        data = FileAnalyzer.analyze(data, date_from, date_to);
        Format date_formatter = new SimpleDateFormat("MM/dd/yyyy");
        String formatted_date_from = date_formatter.format(date_from);
        String formatted_date_to = date_formatter.format(date_to);
        StringBuilder title_builder = new StringBuilder();
        title_builder.append("NKSD 911 Calls ");
        title_builder.append(formatted_date_from);
        title_builder.append(" to ");
        title_builder.append(formatted_date_to);
        String title = title_builder.toString();
        data.add(0, title);
        File selected_file = my_save_file_chooser.getSelectedFile();
        String path = selected_file.getPath();
        boolean write = true;
        if (selected_file.exists())
        {
          String file_name = selected_file.getName();
          String header = "Duplicate Detected";
          String message = file_name + " exists. Overwrite?";
          int selection =
              JOptionPane.showConfirmDialog(null, message, header, JOptionPane.YES_NO_OPTION);

          if (selection == JOptionPane.NO_OPTION)
          {
            write = false;
          }
        }
        if (write)
        {
          try
          {
            FileIO.output(path, data);
          }
          catch (FileNotFoundException | UnsupportedEncodingException e)
          {
            JOptionPane.showMessageDialog(null, e.getMessage(), null,
                                          JOptionPane.ERROR_MESSAGE);
          }
          my_frame.dispose();
          JOptionPane.showMessageDialog(null, "Operation successful.");
        }
        else
        {
          my_save_file_chooser.cancelSelection();
        }
      }
    }
  }
}
