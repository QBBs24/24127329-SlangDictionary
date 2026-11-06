import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CRUDPanel extends JPanel{
    private JButton addSlangBtn;
    private JButton editSlangBtn;
    private JButton delSlangBtn;
    private JButton resetDictionaryBtn;

    public CRUDPanel() {
        // Add slang button
        addSlangBtn = new JButton("Add slang");
        addSlangBtn.setPreferredSize(new Dimension(100, 20));

        // Edit slang button
        editSlangBtn = new JButton("Edit slang");
        editSlangBtn.setPreferredSize(new Dimension(100, 20));

        // Delete slang button
        delSlangBtn = new JButton("Delete slang");
        delSlangBtn.setPreferredSize(new Dimension(150, 20));

        // Reset dictionary button
        resetDictionaryBtn = new JButton("Reset dictionary");
        resetDictionaryBtn.setPreferredSize(new Dimension(150, 20));

        add(addSlangBtn);
        add(editSlangBtn);
        add(delSlangBtn);
        add(resetDictionaryBtn);
    }

    public JButton getAddButton() {
        return this.addSlangBtn;
    }

    public JButton getEditButton() {
        return this.editSlangBtn;
    }

    public JButton getDeleteButton() {
        return this.delSlangBtn;
    }

    public JButton getResetButton() {
        return this.resetDictionaryBtn;
    }
}
