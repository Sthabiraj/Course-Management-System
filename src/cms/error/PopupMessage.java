package cms.error;

import javax.swing.JOptionPane;

/**
 * The PopupMessage class provides methods to display error messages in a popup
 * dialog.
 */
public class PopupMessage {

  /**
   * Displays an error message in a popup dialog with a default title.
   * 
   * @param message the error message to be displayed
   */
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Displays an error message in a popup dialog with a custom title.
   * 
   * @param message the error message to be displayed
   * @param title   the title of the popup dialog
   */
  public void showErrorMessage(String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  }
}
