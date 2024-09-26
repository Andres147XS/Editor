import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditorDeDibujo editor = new EditorDeDibujo();
            editor.setVisible(true);
        });
    }
}
