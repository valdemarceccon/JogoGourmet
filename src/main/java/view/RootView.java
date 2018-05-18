package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.TreeNode;
import view.widget.DialogFimDerrota;
import view.widget.DialogPerguntaPrato;

import java.text.MessageFormat;


/**
 * View Root
 * <p>
 * Camada View do Design Pattern MVVM
 * Somente código manipulação da UI será implementada aqui.
 * Tudo que depender de regra de negócio será repassada para o ViewModel
 */
public class RootView {

    private final RootViewModel viewModel = new RootViewModel();
    private final DialogPerguntaPrato dialogPerguntaPrato;

    public RootView() {
        dialogPerguntaPrato = new DialogPerguntaPrato(this::sim, this::nao);
    }

    private void nao(final TreeNode<String> node) {
        if (node.isFolha() || node.getProxImao() == null) {
            new DialogFimDerrota("Qual prato você pensou?").show(comida -> afterComida(node, comida));
        } else {
            dialogPerguntaPrato.show(node.getProxImao());
        }
    }

    private void afterComida(final TreeNode<String> node, final String comida) {
        final String message = MessageFormat.format("{0} é __________ e {1} não:", comida, node.getValor());
        new DialogFimDerrota(message).show(caract -> afterCaracteristica(node, caract, comida));
    }

    private void afterCaracteristica(final TreeNode<String> node, final String caracteristica, final String comida) {
        node.getPai().addNode(caracteristica).addNode(comida);
    }

    private void sim(TreeNode<String> node) {
        if (node.isFolha()) {
            final Alert messageSucesso = new Alert(Alert.AlertType.INFORMATION);
            messageSucesso.setHeaderText(null);
            messageSucesso.setTitle("Sucesso!");
            messageSucesso.setContentText(MessageFormat.format("Acertei {0}. Cada um com seu gosto!", node.getValor()));
            messageSucesso.showAndWait();
        } else {
            dialogPerguntaPrato.show(node.getFilhos().get(0));
        }
    }

    @FXML
    public void iniciarClickHandle(ActionEvent event) {
        dialogPerguntaPrato.show(this.viewModel.getRoot().getFilhos().get(0));
    }

    // TODO: Implementar evento do botão inciar

    // TODO: Implementar o fluxo dos dialogos de pergunta. Para simplicidade será utilizada dialogos default do
    // TODO: JavaFX

}
