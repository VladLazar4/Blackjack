import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        BjModel model = new BjModel();
        BjView view = new BjView(model);
        BjController controller = new BjController(model, view);

        view.setVisible(true);
    }
}