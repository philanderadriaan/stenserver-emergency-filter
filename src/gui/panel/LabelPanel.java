
package gui.panel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel to display labels.
 * 
 * @author padriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class LabelPanel extends JPanel
{

  /**
   * Adds all the labels on the fields.
   */
  public LabelPanel()
  {
    super(new GridLayout(2, 1));

    JLabel from_label = new JLabel(" From ");
    JLabel to_label = new JLabel(" To ");

    add(from_label);
    add(to_label);
  }
}
