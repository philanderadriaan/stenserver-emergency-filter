
package main;

import gui.frame.NKFrame;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The main program.
 * 
 * @author padriaan
 * @version 1
 */
public class Main
{

  /**
   * Prevents instantiation.
   */
  private Main()
  {
  }

  /**
   * Starts the program by creating 
   * 
   * @param the_args Command line arguments. There is no parameters to this
   *          program so it does nothing.
   */
  public static void main(final String[] the_args)
  {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
    {
      if ("Windows Classic".equals(info.getName()))
      {
        try
        {
          UIManager.setLookAndFeel(info.getClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
            | UnsupportedLookAndFeelException e)
        {
          JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
        break;
      }
    }
    String current_directory = System.getProperty("user.dir");
    JFileChooser open_file_chooser = new JFileChooser(current_directory);
    int approval = open_file_chooser.showOpenDialog(null);
    if (approval == JFileChooser.APPROVE_OPTION)
    {
      File log_file = open_file_chooser.getSelectedFile();
      new NKFrame(log_file);
    }
  }
}