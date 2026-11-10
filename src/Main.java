
import javax.swing.SwingUtilities;

class Main {
    public static void main(String args[]) {
        SlangDictionary SD;

        SD = SlangDictionary.getInstance();

        SD.load("src/files/slang.txt");

        // Suggested by Claude, Invoke later, so this will only run in a thread (Entity Dispatch Thread, EDT)
        SwingUtilities.invokeLater(() -> {
            SlangDictionaryGUI SDGUI = new SlangDictionaryGUI(SD.getDict());
        });
        
    }
}