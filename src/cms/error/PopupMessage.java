package cms.error;

import javax.swing.JOptionPane;

public class PopupMessage {
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);

  }

  public void showErrorMessage(String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);

  }
}
