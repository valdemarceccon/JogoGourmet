package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import model.Prato;
import model.TreeNode;
import view.widget.DialogPerguntaPrato;

import java.text.MessageFormat;
import java.util.Optional;


public class RootView {

    private final RootViewModel viewModel;

    public RootView() {
        viewModel = new RootViewModel(this::perguntaCaracteristica, this::confirmarPrato, this::criaPratoDerrota, this::exibeAlertaVitoria);
    }

    private Boolean perguntaCaracteristica(TreeNode<Prato> nodePrato) {
        final DialogPerguntaPrato dialog = new DialogPerguntaPrato(
                "Tem esta caracteristica?", MessageFormat.format("Seu prato é {0}?", nodePrato.getValor().getCaracteristica().toLowerCase()));
        final Optional<ButtonType> buttonType = dialog.showAndWait();
        if (buttonType.isPresent()) {
            if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
                return true;
            } else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.NO) {
                return false;
            }
        }
        return null;
    }

    private Boolean confirmarPrato(TreeNode<Prato> nodePrato) {
        final DialogPerguntaPrato dialog = new DialogPerguntaPrato(
                "Este é o seu prato?", MessageFormat.format("Seu prato é {0}?", nodePrato.getValor().getNome().toLowerCase()));
        final Optional<ButtonType> buttonType = dialog.showAndWait();
        if (buttonType.isPresent()) {
            if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
                return true;
            } else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.NO) {
                return false;
            }
        }
        return null;
    }

    private Prato criaPratoDerrota(final Prato prato) {

        final TextInputDialog nomeDialog = new TextInputDialog();
        nomeDialog.setTitle("Não sei no que você pensou");
        nomeDialog.setHeaderText(null);
        nomeDialog.setContentText("Qual o prato que você pensou?");
        Optional<String> nomeOptional = nomeDialog.showAndWait();

        if (nomeOptional.isPresent()) {
            final String nome = nomeOptional.get();

            final TextInputDialog caracteristicaDialog = new TextInputDialog();
            caracteristicaDialog.setTitle("Não sei no que você pensou");
            caracteristicaDialog.setHeaderText(null);
            if (prato != null)
                caracteristicaDialog.setContentText(MessageFormat.format("{0} é ________, mas {1} não.", nome, prato.getNome()));
            else
                caracteristicaDialog.setContentText(MessageFormat.format("{0} é ________.", nome));

            final Optional<String> caractOptional = caracteristicaDialog.showAndWait();

            if (caractOptional.isPresent()) {
                final String caracteristica = caractOptional.get();

                return new Prato(nome, caracteristica);
            }
        }

        return null;
    }

    private void exibeAlertaVitoria(Prato prato) {
        final Alert msgVitoria = new Alert(Alert.AlertType.INFORMATION);
        msgVitoria.setHeaderText(null);
        msgVitoria.setTitle("Vitória");
        msgVitoria.setContentText(MessageFormat.format("Acertei, sabia que você gostava de {0}", prato.getNome()));
        msgVitoria.showAndWait();
    }

    @FXML
    public void iniciarClickHandle() {
        this.viewModel.iniciarProcura();
    }

}
