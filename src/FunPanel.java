import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FunPanel extends JPanel{
    private JButton randomSlangBtn;
    private JButton slangQuizBtn;
    private JButton definitionQuizBtn;

    public FunPanel() {
        // Random slang button
        randomSlangBtn = new JButton("Random slang");
        randomSlangBtn.setPreferredSize(new Dimension(150, 20));

        // Slang word quiz
        slangQuizBtn = new JButton("Slang quiz");
        slangQuizBtn.setPreferredSize(new Dimension(100, 20));

        // Definition quiz
        definitionQuizBtn = new JButton("Definition quiz");
        definitionQuizBtn.setPreferredSize(new Dimension(150, 20));

        add(randomSlangBtn);
        add(slangQuizBtn);
        add(definitionQuizBtn);
    }

    public JButton getRandomButton() {
        return this.randomSlangBtn;
    }

    public JButton getSlangQuizButton() {
        return this.slangQuizBtn;
    }

    public JButton getDefinitionQuizButton() {
        return this.definitionQuizBtn;
    }
}
