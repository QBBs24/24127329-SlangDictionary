import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

class SlangDictionaryGUI extends JFrame{
    private ResultPanel resultPanel;
    private SearchPanel searchPanel;
    private CRUDPanel crudPanel;
    private FunPanel funPanel;
    private AddSlangPanel addSlangPanel;
    private EditSlangPanel editSlangPanel;
    private DeleteSlangPanel delSlangPanel;
    private HistoryPanel historyPanel;
    private QuizPanel quizPanel;
    private DefSlangQuizPanel defQuizPanel;

    public SlangDictionaryGUI(TreeMap<String, ArrayList<String>> dictionary) {
        super("Slang Dictionary");

        CardLayout cards = new CardLayout();
        
        JPanel mainPanel = new JPanel(cards);
        JPanel mainCard = new JPanel();

        // Create panels (for main card)
        resultPanel = new ResultPanel();
        searchPanel = new SearchPanel();
        crudPanel = new CRUDPanel();
        funPanel = new FunPanel();
        addSlangPanel = new AddSlangPanel();
        editSlangPanel = new EditSlangPanel();
        delSlangPanel = new DeleteSlangPanel();

        // Separate cards
        historyPanel = new HistoryPanel();
        quizPanel = new QuizPanel();
        defQuizPanel = new DefSlangQuizPanel();
        
        addSlangPanel.setVisible(false);
        editSlangPanel.setVisible(false);
        delSlangPanel.setVisible(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        mainCard.setLayout(new BoxLayout(mainCard, BoxLayout.PAGE_AXIS));

        mainCard.add(resultPanel);
        mainCard.add(searchPanel);
        mainCard.add(addSlangPanel);
        mainCard.add(editSlangPanel);
        mainCard.add(delSlangPanel);
        mainCard.add(crudPanel);
        mainCard.add(funPanel);

        // Adding cards
        mainPanel.add(mainCard, "main");
        mainPanel.add(historyPanel, "history");
        mainPanel.add(quizPanel, "quiz");
        mainPanel.add(defQuizPanel, "defQuiz");

        add(mainPanel);

        // 1. Search slang
        searchPanel.getSearchButton().addActionListener(e -> { // Tham khảo từ Internet, Lambda expression
            // Phần implement của hàm do sinh viên tự viết
            String inp = searchPanel.getTextInput();
            inp = inp.toUpperCase();
            inp = inp.trim();
            if (!inp.isEmpty()) {
                ArrayList<String> meanings = dictionary.get(inp);
                if (meanings != null) {
                    String definitions = "";
                    for (String str : meanings) {
                        definitions += str;
                        definitions += '\n';
                    }
                    resultPanel.setDefinitionArea(definitions);
                }
                else {
                    resultPanel.setDefinitionArea("There's no definition for this word");
                }
                resultPanel.setLabel(inp);
                // Update history (to remain with the industry standard, even invalid input will be added to history)
                historyPanel.addHistory(inp);
            }
        });

        // 2. Search by definition
        searchPanel.getSearchByDefButton().addActionListener(e -> { // Tham khảo từ Internet, Lambda expression
            // Phần implement của hàm do sinh viên tự viết
                String inp = searchPanel.getTextInput();
                inp = inp.trim();
                inp = inp.toLowerCase();
                if (!inp.isEmpty()) {
                    String slangs = "";
                    for (String slang : dictionary.keySet()) {
                        for (String meaning : dictionary.get(slang)) {
                            if (meaning.toLowerCase().contains(inp)) {
                                slangs += slang;
                                slangs += '\n';
                            }
                        }
                    }
                    if (slangs.isEmpty()) {
                        resultPanel.setDefinitionArea("No words found.");
                    }
                    else {
                        resultPanel.setDefinitionArea(slangs);
                    }
                }
                else {
                    resultPanel.setDefinitionArea("You didn't type anything in");
                }
                resultPanel.setLabel("Searching by meaning");
        });

        // 3. History
        /// a. Switch to history page
        searchPanel.getHistoryButton().addActionListener(e -> {
            cards.show(mainPanel, "history");
        });
        /// b. Switch back to main page
        historyPanel.getCloseButton().addActionListener(e -> {
            cards.show(mainPanel, "main");
        });
        /// c. Clear history
        historyPanel.getClearButton().addActionListener(e -> {
            historyPanel.clearHistory();
        });

        // 4. Add new slang
        /// a. Make the add slang panel appear
        crudPanel.getAddButton().addActionListener(e -> {
            addSlangPanel.setVisible(true);

            editSlangPanel.erase();
            editSlangPanel.setVisible(false);

            delSlangPanel.erase(); 
            delSlangPanel.setVisible(false);
            delSlangPanel.cancelConfirm("Delete", false);
        });
        /// b. Close the panel when cancel
        addSlangPanel.getCancelButton().addActionListener(e -> {
            addSlangPanel.setVisible(false);
            addSlangPanel.erase();
            addSlangPanel.reset();
        });
        /// c. Clicking the add slang
        addSlangPanel.getAddButton().addActionListener(e -> {
            String newSlang = addSlangPanel.getSlangInput().trim().toUpperCase();
            String newMeaning = addSlangPanel.getMeaningInput().trim();
            if (newSlang.isEmpty() || newMeaning.isEmpty()) {
                addSlangPanel.setMeaningError("Slang input and meaning input can't be empty, please fill out both fields");
            }
            // If duplicate
            else if (dictionary.get(newSlang) != null) {
                addSlangPanel.getWarning().setVisible(true);
                addSlangPanel.getDuplicateButton().setVisible(true);

                // Overwrite already confirmed so do the operation
                if (addSlangPanel.getConfirmState()) {
                    SlangDictionary SD = SlangDictionary.getInstance();
                    SD.addSlang(newSlang, newMeaning);
                    SD.setSlangFile("src/files/slang.txt");
                    resultPanel.setLabel("Slang editted");
                    resultPanel.setDefinitionArea(newSlang + " has been overwritten");
                    addSlangPanel.setVisible(false);
                    addSlangPanel.erase();
                    addSlangPanel.reset();
                }
                // Button says "Overwrite", change to "Confirm"
                else if (addSlangPanel.getAddButton().getText().equals("Overwrite")) {
                    addSlangPanel.getAddButton().setText("Confirm?");
                    addSlangPanel.setConfirmState(true);

                    // Duplicate state and text reset
                    addSlangPanel.getDuplicateButton().setText("Duplicate");
                    addSlangPanel.setDuplicateState(false);
                }
                // First click on the add button, show the warning options
                else {
                    addSlangPanel.getAddButton().setText("Overwrite");
                    addSlangPanel.getDuplicateButton().setText("Duplicate");
                    addSlangPanel.setDuplicateState(false);
                }
            }
            // No duplicate
            else {
                if (!addSlangPanel.getConfirmState()) {
                    addSlangPanel.getAddButton().setText("Confirm?");
                    addSlangPanel.setConfirmState(true);
                }
                else {
                    SlangDictionary SD = SlangDictionary.getInstance();
                    SD.addSlang(newSlang, newMeaning);
                    SD.setSlangFile("src/files/slang.txt");
                    resultPanel.setLabel("New slang successfully added");
                    resultPanel.setDefinitionArea(newSlang +" has been added to the dictionary");
                    addSlangPanel.setVisible(false);
                    addSlangPanel.erase();
                    addSlangPanel.reset();
                }
            }
        });

        // If this appears then it means it is guaranteed a duplicate slang
        addSlangPanel.getDuplicateButton().addActionListener(e -> {
            String newSlang = addSlangPanel.getSlangInput().trim().toUpperCase();
            String newMeaning = addSlangPanel.getMeaningInput().trim();
            if (newSlang.isEmpty() || newMeaning.isEmpty()) {
                addSlangPanel.setMeaningError("Slang input and meaning input can't be empty, please fill out both fields");
            }
            else if (!addSlangPanel.getDuplicateState()) {
                addSlangPanel.getDuplicateButton().setText("Confirm?");
                addSlangPanel.setDuplicateState(true);
                
                addSlangPanel.setConfirmState(false);
                addSlangPanel.getAddButton().setText("Overwrite");
            }
            else {
                SlangDictionary SD = SlangDictionary.getInstance();
                SD.addMeaning(newSlang, newMeaning);
                SD.setSlangFile("src/files/slang.txt");
                resultPanel.setLabel("New slang duplicated");
                resultPanel.setDefinitionArea(newSlang +" has been duplicated to the dictionary");
                addSlangPanel.setVisible(false);
                addSlangPanel.erase();
                addSlangPanel.reset();
            }
        });

        // 5. Edit a slang
        /// a. Make the edit panel appear
        crudPanel.getEditButton().addActionListener(e -> {
            addSlangPanel.erase();
            addSlangPanel.reset();
            addSlangPanel.setVisible(false);

            editSlangPanel.setVisible(true);

            delSlangPanel.erase();
            delSlangPanel.setVisible(false);
            delSlangPanel.cancelConfirm("Delete", false);
        });

        /// b. Hide the panel when clicking the Cancel button
        editSlangPanel.getCancelButton().addActionListener(e -> {
            editSlangPanel.erase();
            editSlangPanel.setVisible(false);
        });
        
        /// c. Clicking the edit button
        editSlangPanel.getEditButton().addActionListener(e -> {
            String editSlang = editSlangPanel.getSlangInput();
            editSlang = editSlang.trim().toUpperCase();
            String newMeaning = editSlangPanel.getNewMeaning();
            SlangDictionary SD = SlangDictionary.getInstance();
            if (SD.getDict().get(editSlang) == null) {
                editSlangPanel.setSlangInput("This slang doesn't exist");
            }
            else {
                SD.addSlang(editSlang, newMeaning);
                SD.setSlangFile("src/files/slang.txt");
                resultPanel.setLabel("Slang editted");
                resultPanel.setDefinitionArea(editSlang +" has been editted");
                editSlangPanel.setVisible(false);
                editSlangPanel.erase();
            }
        });

        // 6. Delete slang word
        /// a. Make the delete panel appear
        crudPanel.getDeleteButton().addActionListener(e -> {
            addSlangPanel.erase();
            addSlangPanel.reset();
            addSlangPanel.setVisible(false);

            editSlangPanel.erase();
            editSlangPanel.setVisible(false);

            delSlangPanel.setVisible(true);
        });

        /// b. Hide the panel when clicking the Cancel button
        delSlangPanel.getCancelButton().addActionListener(e -> {
            delSlangPanel.erase();
            delSlangPanel.setVisible(false);
            delSlangPanel.cancelConfirm("Delete", false);
        });

        /// c. Clicking the delete button
        delSlangPanel.getDeleteButton().addActionListener(e -> {
            String slangToBeDeleted = delSlangPanel.getSlangInput();
            slangToBeDeleted = slangToBeDeleted.trim().toUpperCase();
            SlangDictionary SD = SlangDictionary.getInstance();
            if (SD.getDict().get(slangToBeDeleted) == null) {
                delSlangPanel.setSlangInput("That slang doesn't exist.");
            }

            else if (!delSlangPanel.getConfirmState()) {
                delSlangPanel.setDeleteButtonText("Confirm?");
                delSlangPanel.setConfirmState(true);
            }
            else if (delSlangPanel.getConfirmState()){
                SD.deleteSlang(slangToBeDeleted);
                SD.setSlangFile("src/files/slang.txt");
                resultPanel.setLabel("Deletion succeeded");
                resultPanel.setDefinitionArea(slangToBeDeleted + " was deleted");
                delSlangPanel.setVisible(false);
                delSlangPanel.erase();
                delSlangPanel.cancelConfirm("Delete", false);
            }
        });

        // 7. Reset slang dictionary (Fix slang.txt and defaultSlang.txt first)
        crudPanel.getResetButton().addActionListener(e -> {
            SlangDictionary SD = SlangDictionary.getInstance();
            SD.load("src/files/defaultSlang.txt");
            SD.setSlangFile("src/files/slang.txt");
            resultPanel.setDefinitionArea("Dictionary successfully resetted!");
            resultPanel.setLabel("Resetted");
        });

        // 8. Random slang word
        funPanel.getRandomButton().addActionListener(e -> {
            Set<String> keys = dictionary.keySet();
            ArrayList<String> arrKeys = new ArrayList<>();

            for (String key : keys) {
                arrKeys.add(key);
            }
            
            Random rand = new Random();
            Integer random = rand.nextInt(keys.size());
            String key = arrKeys.get(random);

            ArrayList<String> meanings = dictionary.get(key);
            String definitions = "";
            for (String str : meanings) {
                definitions += str;
                definitions += '\n';
            }
            resultPanel.setDefinitionArea(definitions);
            resultPanel.setLabel(key);
        });

        // 9. Slang quiz (Given slang, guess the meaning)
        /// a. Open the quiz page and start a round
        funPanel.getSlangQuizButton().addActionListener(e -> {
            cards.show(mainPanel, "quiz");
            startSlangQuiz(dictionary);
        });

        /// b. Retry: new round, same page
        quizPanel.getRestartButton().addActionListener(e -> {
            startSlangQuiz(dictionary);
        });

        /// c. Close: back to the main page
        quizPanel.getCloseButton().addActionListener(e -> {
            cards.show(mainPanel, "main");
        });

        // 10. Slang definition quiz (Given the meaning, guess the slang)
        /// a. Show the definition quiz panel
        funPanel.getDefinitionQuizButton().addActionListener(e -> {
            cards.show(mainPanel, "defQuiz");
            startDefQuiz(dictionary);
        });
        // b. Go back to the main panel
        defQuizPanel.getCloseButton().addActionListener(e -> {
            cards.show(mainPanel, "main");
        });
        // c. Restart the definition quiz
        defQuizPanel.getRestartButton().addActionListener(e -> {
            startDefQuiz(dictionary);
        });

        pack();
        setVisible(true);
    }

    // CITATION: The position of the function was suggested by Claude, before it was put in addActionListener
    // Implementation was done 95% by the student with 5% correction + suggestion from Claude
    private void startSlangQuiz(TreeMap<String, ArrayList<String>> dictionary) {
        // gameEnded = false, resets label and button colors
        quizPanel.resetQuiz();

        ArrayList<String> keys = new ArrayList<>(dictionary.keySet());
        if (keys.isEmpty()) {
            return;
        }

        Random random = new Random();
        ArrayList<String> allAnswers = new ArrayList<>();

        /// a. Get the question
        String question = keys.get(random.nextInt(keys.size()));

        /// b. Get the correct answer (random meaning of that slang)
        ArrayList<String> correctMeanings = new ArrayList<>(dictionary.get(question));
        String correct = correctMeanings.get(random.nextInt(correctMeanings.size()));
        allAnswers.add(correct);
        quizPanel.setCorrectAnswer(correct);

        /// c. Get 3 wrong answers from other slangs
        if (dictionary.size() >= 4)
        {
            while (allAnswers.size() < 4) {
                String wrongKey = keys.get(random.nextInt(keys.size()));
                if (wrongKey.equals(question)) {
                    continue;
                }

                ArrayList<String> wrongMeanings = new ArrayList<>(dictionary.get(wrongKey));
                String wrong = wrongMeanings.get(random.nextInt(wrongMeanings.size()));
                if (!allAnswers.contains(wrong)) {
                    allAnswers.add(wrong);
                }
            }
        }
        else {
            for (int i = 0; i < 3; i++) {
                allAnswers.add("Placeholder, not enough answers");
            }
        }

        /// d. Set the question label
        quizPanel.setLabel(question);

        /// e. Set the options in random order
        for (int i = 0; i < 4; i++) {
            int answerIndex = random.nextInt(allAnswers.size());
            quizPanel.setAnswerButtons(allAnswers.get(answerIndex), i);
            allAnswers.remove(answerIndex);
        }
    }

    private void startDefQuiz(TreeMap<String, ArrayList<String>> dictionary) {
        defQuizPanel.resetQuiz();
        // a. Get the question (the definition) and the correct answer
        Random random = new Random();
        ArrayList<String> questions = new ArrayList<>(dictionary.keySet());
        String correctAnswer = questions.get(random.nextInt(questions.size()));

        ArrayList<String> availableQuestions = new ArrayList<>(dictionary.get(correctAnswer));

        // The definition the player has to guess
        String question = availableQuestions.get(random.nextInt(availableQuestions.size()));

        ArrayList<String> allAnswers = new ArrayList<>();
        allAnswers.add(correctAnswer);
        defQuizPanel.setCorrectAnswer(correctAnswer);

        /// c. Get 3 wrong answers from other slangs
        if (dictionary.size() >= 4)
        {
            while (allAnswers.size() < 4) {
                allAnswers.add(questions.get(random.nextInt(questions.size())));
            }
        }
        else {
            for (int i = 0; i < 3; i++) {
                allAnswers.add("Placeholder, not enough answers");
            }
        }

        /// d. Set the label question
        defQuizPanel.setLabel(question);

        /// e. Set the options in random order
        for (int i = 0; i < 4; i++) {
            int answerIndex = random.nextInt(allAnswers.size());
            defQuizPanel.setAnswerButtons(allAnswers.get(answerIndex), i);
            allAnswers.remove(answerIndex);
        }
    }
}
