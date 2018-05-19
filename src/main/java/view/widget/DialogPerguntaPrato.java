package view.widget;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class DialogPerguntaPrato extends Alert {

    public DialogPerguntaPrato(final String title, final String message) {
        super(Alert.AlertType.CONFIRMATION);
        this.setHeaderText(null);
        this.setTitle(title);
        this.setContentText(message);

        this.getButtonTypes().setAll(
                new ButtonType("Sim", ButtonBar.ButtonData.YES),
                new ButtonType("Não", ButtonBar.ButtonData.NO),
                new ButtonType("Parar", ButtonBar.ButtonData.CANCEL_CLOSE)
        );
    }
}
