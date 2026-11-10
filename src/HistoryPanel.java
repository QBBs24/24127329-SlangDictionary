import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class HistoryPanel extends JPanel{
    private DefaultListModel<String> historyModel;
    private JList<String> historyList;
    private JButton closeButton;
    private JButton clearHistoryButton;
    private JLabel label;
    private JScrollPane scrollPane;

    public HistoryPanel() {
        setPreferredSize(new Dimension(400, 600));
        
        JPanel buttonPanel = new JPanel();
        
        historyModel = new DefaultListModel<>();
        loadHistory("src/files/history.txt");

        historyList = new JList<>(historyModel);
        
        scrollPane = new JScrollPane(historyList);

        closeButton = new JButton("Close X");
        clearHistoryButton = new JButton("Clear history");
        label = new JLabel("History");

        buttonPanel.add(clearHistoryButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(closeButton);
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(label);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(buttonPanel);
    }

    public JButton getCloseButton() {
        return this.closeButton;
    }

    public JButton getClearButton() {
        return this.clearHistoryButton;
    }

    public void addHistory(String slang) {
        // Add new searches to the top of the list, so add at index 0
        this.historyModel.add(0, slang);
    }

    public void clearHistory() {
        this.historyModel.clear();
    }
    
    // Write history to file (so when the app closes, the history is still there)
    public void writeHistory(String pathToFile) {
        try {
            BufferedWriter BW = new BufferedWriter(new FileWriter(pathToFile));
            
            for (int i = 0; i < historyModel.size(); i++) {
                if (i == historyModel.size() - 1)
                    BW.write(historyModel.get(i));
                else {
                    BW.write(historyModel.get(i));
                    BW.newLine();
                }
            }

            BW.flush();
            BW.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Load history from file
    public void loadHistory(String pathToFile) {
        try {
            BufferedReader BR = new BufferedReader(new FileReader(pathToFile));

            String line;
            while ((line = BR.readLine()) != null) {
                historyModel.add(historyModel.size(), line);
            }
            BR.close();
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearHistoryFile(String pathToFile) {
        try {
            // Write 0 characters with append mode off to clear the file
            new FileWriter(pathToFile, false).close();;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
