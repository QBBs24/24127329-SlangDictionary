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

public class AddSlangPanel extends JPanel{
    private JButton cancelButton;
    private JButton addButton;
    private JTextField slangInput;
    private JTextArea meaningInput;
    private JLabel slangLabel;
    private JLabel meaningLabel;
    private boolean confirmState = false;

    public AddSlangPanel() {
        setPreferredSize(new Dimension(400, 150));

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 20));
        cancelButton.setMaximumSize(new Dimension(100, 20));

        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 20));
        addButton.setMaximumSize(new Dimension(100, 20));

        slangInput = new JTextField();
        slangInput.setPreferredSize(new Dimension(300, 25));
        slangLabel = new JLabel("Add new slang:");
        
        JPanel slangPanel = new JPanel();
        slangPanel.add(slangLabel);
        slangPanel.add(slangInput);
        
        meaningLabel = new JLabel("Add slang meaning (use '|' for multiple meanings):");
        meaningInput = new JTextArea();
        meaningInput.setPreferredSize(new Dimension(300, 70));
        meaningInput.setLineWrap(true);
        meaningInput.setWrapStyleWord(true);
        meaningInput.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        JPanel meaningPanel = new JPanel();
        meaningPanel.add(meaningLabel);
        meaningPanel.add(meaningInput);
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
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

    public JButton getAddButton() {
        return this.addButton;
    }

    public void setAddState() {
        if (confirmState)
            this.addButton.setText("Confirm");
    }

    public String getSlangInput() {
        return slangInput.getText();
    }

    public String getMeaningInput() {
        return meaningInput.getText();
    }

    public void setMeaningError(String error) {
        this.meaningInput.setText(error);
        this.slangInput.setText("");
    }

    public void erase() {
        this.meaningInput.setText("");
        this.slangInput.setText("");
    }

    public void setConfirmState(boolean state) {
        this.confirmState = state;
    }
}
