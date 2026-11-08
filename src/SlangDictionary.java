import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.TreeMap;

class SlangDictionary {
    private static SlangDictionary SD;
    private TreeMap<String, ArrayList<String>> dictionary;

    private SlangDictionary() {
        dictionary = new TreeMap<>();
    }
    
    public static SlangDictionary getInstance() {
        if (SD == null) {
            SD = new SlangDictionary();
        }
        return SD;
    }

    public void setDict(TreeMap<String, ArrayList<String>> dict) {
        dictionary.clear();
        for (String key : dict.keySet()) {
            dictionary.put(key, dict.get(key));
        }
    }

    public TreeMap<String, ArrayList<String>> getDict() {
        return dictionary;
    }

    public void load(String pathToFile) {
        try {
            BufferedReader BR = new BufferedReader(new FileReader(pathToFile));
            
            if (dictionary != null)
                dictionary.clear();

            String line;
            line = BR.readLine(); // Get rid of the first line which is just the format

            while ((line = BR.readLine()) != null) {
                int backTickPos = line.indexOf('`');

                String slang, meaning;
                slang = line.substring(0, backTickPos);
                meaning = line.substring(backTickPos + 1, line.length());

                ArrayList<Integer> separatorPos = new ArrayList<>();
                ArrayList<String> meaningArr = new ArrayList<>();
                for (int i = 0; i < meaning.length(); i++) {
                    if (meaning.charAt(i) == '|') {
                        separatorPos.add(i);
                    }
                }
                if (separatorPos.isEmpty()) {
                    meaningArr.add(meaning);
                }
                else if (separatorPos.size() == 1) {
                    meaningArr.add(meaning.substring(0, separatorPos.get(0)).trim());
                    meaningArr.add(meaning.substring(separatorPos.get(0) + 1, meaning.length()).trim());
                }
                else {
                    for (int i = 0; i <= separatorPos.size(); i++) {
                        if (i == 0) {
                            meaningArr.add(meaning.substring(0, separatorPos.get(0)).trim());
                        }
                        else if (i == separatorPos.size()) {
                            meaningArr.add(meaning.substring(separatorPos.get(i - 1) + 1, meaning.length()).trim());
                        }
                        else {
                            meaningArr.add(meaning.substring(separatorPos.get(i - 1) + 1, separatorPos.get(i)).trim());
                        }
                    }
                }

                dictionary.put(slang, meaningArr);
            }

            BR.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addSlang(String slang, String meanings) {
        ArrayList<Integer> separatorPos = new ArrayList<>();
        ArrayList<String> meaningArr = new ArrayList<>();
        for (int i = 0; i < meanings.length(); i++) {
            if (meanings.charAt(i) == '|') {
                separatorPos.add(i);           
            }
        }
        if (separatorPos.size() == 0) {
            meaningArr.add(meanings);
        }
        else if (separatorPos.size() == 1) {
            meaningArr.add(meanings.substring(0, separatorPos.get(0)).trim());
            meaningArr.add(meanings.substring(separatorPos.get(0) + 1, meanings.length()).trim());
        }
        else {
            for (int i = 0; i <= separatorPos.size(); i++) {
                if (i == 0) {
                    meaningArr.add(meanings.substring(0, separatorPos.get(i)).trim());
                }
                else if (i == separatorPos.size()) {
                    meaningArr.add(meanings.substring(separatorPos.get(i - 1) + 1, meanings.length()).trim());
                }
                else {
                    meaningArr.add(meanings.substring(separatorPos.get(i - 1) + 1, separatorPos.get(i)).trim());
                }
            }
        }
        dictionary.put(slang, meaningArr);
    }

    public void deleteSlang(String slang) {
        dictionary.remove(slang);
    }

    public void setSlangFile(String pathToFile) {
        try {
            BufferedWriter BW = new BufferedWriter(new FileWriter(pathToFile));

            BW.write("Slag`Meaning");
            BW.newLine();

            for (String key : dictionary.keySet()) {
                String meaning = "";
                ArrayList<String> meanings = dictionary.get(key);
                if (meanings.size() == 1) {
                    meaning += meanings.get(0);
                }
                else {
                    for (int i = 0; i < meanings.size(); i++) {
                        if (i == meanings.size() - 1){
                            meaning += meanings.get(i);
                        }
                        else {
                            meaning += meanings.get(i);
                            meaning += "| ";
                        }
                    }
                }
                BW.write(key);
                BW.write('`');
                BW.write(meaning);
                BW.newLine();
            }

            BW.flush();
            BW.close();
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
