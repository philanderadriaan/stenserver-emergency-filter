
package gui.frame;

import gui.panel.FieldPanel;
import gui.panel.LabelPanel;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

import action.SaveAction;

import com.toedter.calendar.JDateChooser;

/**
 * Main window of the program.
 * 
 * @author padriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class NKFrame extends JFrame
{
  /**
   * Chooses the date from.
   */
  private JDateChooser my_date_from_chooser = setDateChooser();
  /**
   * Chooses the date to.
   */
  private JDateChooser my_date_to_chooser = setDateChooser();
  /**
   * Selected log file.
   */
  private File my_log_file;

  /**
   * Creates the window.
   * 
   * @param the_log_file Selected log file.
   */
  public NKFrame(final File the_log_file)
  {
    super("STENServer Emergency Filter");
    my_log_file = the_log_file;
    setLayout(new BorderLayout());
    final LabelPanel label_panel = new LabelPanel();
    final FieldPanel field_panel = new FieldPanel(this);
    final Action save_action = new SaveAction(this);
    final JButton save_button = new JButton(save_action);
    add(label_panel, BorderLayout.WEST);
    add(field_panel);
    add(save_button, BorderLayout.SOUTH);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
    pack();
    final int width = getWidth() * 2;
    final int height = getHeight();
    setSize(width, height);
    setLocationRelativeTo(null);
  }

  /**
   * Sets the date picker to the format of MM/dd/yyyy to standardize with the
   * log file.
   * 
   * @return Formatted date picker.
   */
  private JDateChooser setDateChooser()
  {
    final JDateChooser date_chooser = new JDateChooser();

    date_chooser.setDateFormatString("MM/dd/yyyy");

    final Calendar today_calendar = Calendar.getInstance();
    final Date today_date = today_calendar.getTime();
    date_chooser.setDate(today_date);

    return date_chooser;
  }

  /**
   * Fetches the log file.
   * 
   * @return Selected log file.
   */
  public File getLogFile()
  {
    return my_log_file;
  }

  /**
   * Fetches the date from chooser.
   * 
   * @return Date from picker.
   */
  public final JDateChooser getDateFromChooser()
  {
    return my_date_from_chooser;
  }

  /**
   * Fetches the date to picker.
   * 
   * @return Date to picker.
   */
  public final JDateChooser getDateToChooser()
  {
    return my_date_to_chooser;
  }
}
