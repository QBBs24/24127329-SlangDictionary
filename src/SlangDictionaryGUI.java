import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

class SlangDictionaryGUI extends JFrame{
    private ResultPanel resultPanel;
    private SearchPanel searchPanel;
    private CRUDPanel crudPanel;
    private FunPanel funPanel;
    private AddSlangPanel addSlangPanel;

    public SlangDictionaryGUI(TreeMap<String, ArrayList<String>> dictionary) {
        super("Slang Dictionary");

        // Create panels
        resultPanel = new ResultPanel();
        searchPanel = new SearchPanel();
        crudPanel = new CRUDPanel();
        funPanel = new FunPanel();
        addSlangPanel = new AddSlangPanel();

        addSlangPanel.setVisible(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        add(resultPanel);
        add(searchPanel);
        add(addSlangPanel);
        add(crudPanel);
        add(funPanel);

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

        // 4. Add new slang
        /// a. Make the add slang panel appear
        crudPanel.getAddButton().addActionListener(e -> {
            addSlangPanel.setVisible(true);
        });
        /// b. Close the panel when cancel
        addSlangPanel.getCancelButton().addActionListener(e -> {
            addSlangPanel.setVisible(false);
            addSlangPanel.setConfirmState(false);
        });
        /// c. Clicking the add slang
        addSlangPanel.getAddButton().addActionListener(e -> {
            String newSlang = addSlangPanel.getSlangInput().trim().toUpperCase();
            String newMeaning = addSlangPanel.getMeaningInput().trim();
            if (newSlang.isEmpty() || newMeaning.isEmpty()) {
                addSlangPanel.setMeaningError("Slang input and meaning input can't be empty, please fill out both fields");
            }
            // TODO: Add confirm + overwrite or duplicate feature
            else {
                SlangDictionary SD = SlangDictionary.getInstance();
                SD.addSlang(newSlang, newMeaning);
                SD.setSlangFile("src/files/slang.txt");
                resultPanel.setLabel("New slang successfully added");
                resultPanel.setDefinitionArea(newSlang +" has been added to the dictionary");
                addSlangPanel.setVisible(false);
            }
        });

        // 5. Edit a slang

        // 6. Delete slang word

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

        // 9. Slang quiz

        // 10. Slang definition quiz

        pack();
        setVisible(true);
    }
}
