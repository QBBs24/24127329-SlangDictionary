import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ResultPanel extends JPanel {
    private JLabel wordLabel;
    private JPanel topWordPanel;
    private JTextArea definitionArea;
    private JScrollPane scrollPane;

    // Dimension resultPanelDim = new Dimension(600, 150);
    
    public ResultPanel() {
        setPreferredSize(new Dimension(600, 150));
        Dimension scrollPaneDim = new Dimension(600, 120);

        // Word label
        wordLabel = new JLabel("Word:");
        topWordPanel = new JPanel();
        topWordPanel.add(wordLabel);
        topWordPanel.setPreferredSize(new Dimension(200, 20));
        topWordPanel.setMaximumSize(new Dimension(300, 20));

        // Definition area
        definitionArea = new JTextArea("Definition goes here");
        definitionArea.setEditable(false);
        definitionArea.setLineWrap(true);
        definitionArea.setWrapStyleWord(true);

        // Scroll pane for the definition area
        scrollPane = new JScrollPane(definitionArea);
        scrollPane.setPreferredSize(scrollPaneDim);
        scrollPane.setMaximumSize(scrollPaneDim);
        scrollPane.setMinimumSize(scrollPaneDim);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        add(topWordPanel);
        add(scrollPane);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void setLabel(String label) {
        wordLabel.setText(label);
    }
    
    public void setDefinitionArea(String definition) {
        definitionArea.setText(definition);
    }
}
