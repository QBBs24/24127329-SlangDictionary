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

    public SlangDictionaryGUI(TreeMap<String, ArrayList<String>> dictionary) {
        super("Slang Dictionary");

        // Create panels
        resultPanel = new ResultPanel();
        searchPanel = new SearchPanel();
        crudPanel = new CRUDPanel();
        funPanel = new FunPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        add(resultPanel);
        add(searchPanel);
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
        // addSlangBtn.addActionListener(e -> {
        //     JFrame addSlangFrame = new JFrame("Add a new slang");
        //     addSlangFrame.pack();
        //     addSlangFrame.setPreferredSize(new Dimension(600, 400));
        //     addSlangFrame.setMinimumSize(new Dimension(600, 400));
        //     addSlangFrame.setLocationRelativeTo(null);
        //     addSlangFrame.setVisible(true);
        // });

        // 5. Edit a slang

        // 6. Delete slang word

        // 7. Reset slang dictionary (Fix slang.txt and defaultSlang.txt first)
        // resetDictionaryBtn.addActionListener(e -> {
        //     SlangDictionary SD = SlangDictionary.getInstance();
        //     SD.load("defaultSlang.txt");
        //     SD.setSlangFile("slang.txt");
        //     definitionArea.setText("Dictionary successfully resetted!");
        // });

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
