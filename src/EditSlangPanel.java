import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditSlangPanel extends JPanel{
    private JButton cancelButton;
    private JButton editButton;
    private JTextField slangInput;
    private JTextArea edittedMeaning;
    private JLabel slangLabel;
    private JLabel meaningLabel;

    public EditSlangPanel() {
        setPreferredSize(new Dimension(400, 150));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 20));
        cancelButton.setMaximumSize(new Dimension(100, 20));

        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(100, 20));
        editButton.setMaximumSize(new Dimension(100, 20));

        slangInput = new JTextField();
        slangInput.setPreferredSize(new Dimension(300, 25));
        slangLabel = new JLabel("The slang you want to edit:");

        JPanel slangPanel = new JPanel();
        slangPanel.add(slangLabel);
        slangPanel.add(slangInput);

        meaningLabel = new JLabel("Edit meaning (use '|' for multiple meanings, will replace old meanings):");
        edittedMeaning = new JTextArea();
        edittedMeaning.setPreferredSize(new Dimension(300, 70));
        edittedMeaning.setLineWrap(true);
        edittedMeaning.setWrapStyleWord(true);
        edittedMeaning.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JPanel meaningPanel = new JPanel();
        meaningPanel.add(meaningLabel);
        meaningPanel.add(edittedMeaning);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);

        JPanel mainLayout = new JPanel();
        mainLayout.add(Box.createRigidArea(new Dimension(0, 5)));
        mainLayout.add(slangPanel);
        mainLayout.add(meaningPanel);
        mainLayout.add(buttonPanel);
        mainLayout.add(Box.createRigidArea(new Dimension(0, 5)));

        mainLayout.setLayout(new BoxLayout(mainLayout ,BoxLayout.PAGE_AXIS));

        add(mainLayout);
    }
    
    public JButton getCancelButton() {
        return this.cancelButton;
    }

    public JButton getEditButton() {
        return this.editButton;
    }

    public String getSlangInput() {
        return this.slangInput.getText();
    }

    public String getNewMeaning() {
        return this.edittedMeaning.getText();
    }

    public void setSlangInput(String str) {
        this.slangInput.setText(str);
    }

    public void setMeaning(String str) {
        this.edittedMeaning.setText(str);
    }

    public void erase() {
        this.edittedMeaning.setText("");
        this.slangInput.setText("");
    }
}
