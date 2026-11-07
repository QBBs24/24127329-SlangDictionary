
import javax.swing.SwingUtilities;

class Main {
    public static void main(String args[]) {
        SlangDictionary SD;

        SD = SlangDictionary.getInstance();

        SD.load("src/files/slang.txt");

        // Tham khảo từ Internet, Invoke later, cho hàm chỉ chạy trong 1 thread (Entity Dispatch Thread, EDT)
        SwingUtilities.invokeLater(() -> {
            SlangDictionaryGUI SDGUI = new SlangDictionaryGUI(SD.getDict());
        });
        
    }
}