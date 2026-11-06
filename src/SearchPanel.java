import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel{
    private JTextField searchField;
    private JButton searchBtn;
    private JButton searchByDefBtn;
    private JButton historyBtn;

    public SearchPanel() {
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 30));

        // Search by slang
        searchBtn = new JButton("Search");
        searchBtn.setPreferredSize(new Dimension(100, 20));

        // Search by definition
        searchByDefBtn = new JButton("Search by definition");
        searchByDefBtn.setPreferredSize(new Dimension(150, 20));

        // History button
        historyBtn = new JButton("History");
        historyBtn.setPreferredSize(new Dimension(100, 20));

        add(searchField);
        add(searchBtn);
        add(searchByDefBtn);
        add(historyBtn);
    }

    public String getTextInput() {
        return searchField.getText();
    }

    public JButton getSearchButton() {
        return this.searchBtn;
    }

    public JButton getSearchByDefButton() {
        return this.searchByDefBtn;
    }

    public JButton getHistoryButton() {
        return this.historyBtn;
    }
}
