
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DefSlangQuizPanel extends JPanel{
    private JLabel question;
    private JLabel defLabel;
    private JPanel answerPanel;
    private JButton[] answers;
    private JButton closeButton;
    private JButton restart;

    private String correctAnswer = "";
    private boolean gameEnded = false;

    public DefSlangQuizPanel() {
        question = new JLabel("Guess which slang has this meaning");
        defLabel = new JLabel("WORD");

        answerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        answers = new JButton[4];

        closeButton = new JButton("Close X");
        restart = new JButton("Retry");

        for (int i = 0; i < 4; i++) {
            answers[i] = new JButton();
            // Claude suggestion to make the index variable final
            final int index = i;
            answers[i].addActionListener(e -> gameStateManager(index));
            answerPanel.add(answers[i]);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        buttonPanel.add(restart);
        buttonPanel.setMaximumSize(new Dimension(200, 20));

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(question);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(defLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(answerPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonPanel);
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setLabel(String slang) {
        defLabel.setText(slang);
    }

    private void gameStateManager(int index) {
        if (this.gameEnded) {
            return;
        }
        if (checkCorrect(getAnswer(index), correctAnswer)) {
            setWin(index);
        }
        else {
            setLose(index);
        }
    }

    public void setAnswerButtons(String str, int num) {
        if (num >= 0 && num < 4) {
            answers[num].setText(str);
        }
    }

    public String getAnswer(int num) {
        if (num >= 0 && num < 4) {
            return answers[num].getText();
        }
        return "";
    }

    public boolean checkCorrect(String meaningGuess, String answer) {
        if (meaningGuess.equals(answer)) {
            return true;
        }
        return false;
    }
    
    public JButton getRestartButton() {
        return this.restart;
    }

    public JButton getCloseButton() {
        return this.closeButton;
    }

    // The correct answer will be green, the rest will be red
    public void setWin(int answerButtonIndex) {
        question.setText("YOU WIN!!!");
        for (int i = 0; i < 4; i++) {
            if (i == answerButtonIndex)
                answers[i].setBackground(Color.GREEN);
            else
                answers[i].setBackground(Color.RED);
        }
        gameEnded = true;
    }

    public void setLose(int answerButtonIndex) {
        question.setText("You lost... Maybe next time.");
        for (int i = 0; i < 4; i++) {
            // The right answer will be green
            if (checkCorrect(getAnswer(i), correctAnswer)) {
                answers[i].setBackground(Color.GREEN);
            }
            // User's answer will be red
            else if (i == answerButtonIndex) {
                answers[i].setBackground(Color.RED);
            }
        }
        gameEnded = true;
    }

    // Start a new round
    public void resetQuiz() {
        gameEnded = false;
        question.setText("Guess the meaning of this slang");
        for (JButton button : answers) {
            button.setBackground(null);
        }
    }    
}
