

package gui.panel;

import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

/**
 * Panel for storing fields.
 * 
 * @author padriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class FieldPanel extends JPanel
{

  /**
   * Creates the panel.
   * 
   * @param the_frame Container frame.
   */
  public FieldPanel(final NKFrame the_frame)
  {
    super(new GridLayout(2, 1));

    JDateChooser date_from_chooser = the_frame.getDateFromChooser();
    JDateChooser date_to_chooser = the_frame.getDateToChooser();

    add(date_from_chooser);
    add(date_to_chooser);
  }
}
