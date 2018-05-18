package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.TreeNode;
import view.widget.DialogPerguntaPrato;


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

    private void nao(TreeNode<String> tTreeNode) {
        if (tTreeNode.isFolha()) {
            // fim - derrota
        } else if (tTreeNode.getProxImao() != null) {
            dialogPerguntaPrato.show(tTreeNode.getProxImao());
        }

    }

    private void sim(TreeNode<String> tTreeNode) {
        if (tTreeNode.isFolha()) {
            // fim - vitória
        } else {
            dialogPerguntaPrato.show(tTreeNode.getFilhos().get(0));
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
