package view;

import model.TreeNode;

/**
 * ViewModel do Root.
 * Utilizando MVVM para facilitar possíveis implementações de testes
 * <p>
 * Toda a regra de negócio sera implementada nos ViewModels
 */
class RootViewModel {

    private final TreeNode<String> root;

    RootViewModel() {
        this.root = new TreeNode<>(null, "ROOT");

        /* Valores iniciais, conforme aplicativo de exemplo */
        this.root.addNode("Massa").addNode("Lasanha");
        this.root.addNode("Bolo").addNode("Bolo de chocolate");
    }

    // TODO: Cria eventos para manipular e encontrar comidas na lista


    public TreeNode<String> getRoot() {
        return root;
    }
}
