package view.widget;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.TreeNode;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.function.Consumer;

public class DialogPerguntaPrato extends Alert {

    private final Consumer<TreeNode<String>> sim;
    private final Consumer<TreeNode<String>> nao;

    public DialogPerguntaPrato(Consumer<TreeNode<String>> sim, Consumer<TreeNode<String>> nao) {
        super(Alert.AlertType.CONFIRMATION);
        this.sim = sim;
        this.nao = nao;
        this.setHeaderText(null);

        this.getButtonTypes().setAll(
                new ButtonType("Sim", ButtonBar.ButtonData.YES),
                new ButtonType("Não", ButtonBar.ButtonData.NO),
                new ButtonType("Parar", ButtonBar.ButtonData.CANCEL_CLOSE)
        );
    }

    public void show(final TreeNode<String> node) {
        this.setTitle("É este?");
        if (node.isFolha()) {
            this.setContentText(MessageFormat.format("Você pensou em {0}?", node.getValor().toLowerCase()));
        } else {
            this.setContentText(MessageFormat.format("Por acaso a comida que você pensou é {0}?", node.getValor().toLowerCase()));
        }

        final Optional<ButtonType> buttonType = this.showAndWait();

        if (buttonType.isPresent()) {
            final ButtonBar.ButtonData buttonData = buttonType.get().getButtonData();

            if (buttonData == ButtonBar.ButtonData.YES) this.sim.accept(node);
            else if (buttonData == ButtonBar.ButtonData.NO) this.nao.accept(node);
        }

    }
}
