import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteSlangPanel extends JPanel{
    private JButton cancelButton;
    private JButton deleteButton;
    private JTextField slangInput;
    private JLabel slangLabel;

    public DeleteSlangPanel() {
        setPreferredSize(new Dimension(400, 100));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 20));
        cancelButton.setMaximumSize(new Dimension(100, 20));

        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100, 20));
        deleteButton.setMaximumSize(new Dimension(100, 20));

        slangInput = new JTextField();
        slangInput.setPreferredSize(new Dimension(300, 25));
        slangLabel = new JLabel("The slang you want to delete:");

        JPanel slangPanel = new JPanel();
        slangPanel.add(slangLabel);
        slangPanel.add(slangInput);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);

        JPanel mainLayout = new JPanel();
        mainLayout.add(Box.createRigidArea(new Dimension(0, 5)));
        mainLayout.add(slangPanel);
        mainLayout.add(buttonPanel);
        mainLayout.add(Box.createRigidArea(new Dimension(0, 5)));

        mainLayout.setLayout(new BoxLayout(mainLayout ,BoxLayout.PAGE_AXIS));

        add(mainLayout);
    }
    
    public JButton getCancelButton() {
        return this.cancelButton;
    }

    public JButton getDeleteButton() {
        return this.deleteButton;
    }

    public String getSlangInput() {
        return this.slangInput.getText();
    }

    public void setSlangInput(String str) {
        this.slangInput.setText(str);
    }

    public void erase() {
        this.slangInput.setText("");
    }
}
