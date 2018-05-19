package view;

import model.Prato;
import model.TreeNode;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;


class RootViewModel {

    private final TreeNode<Prato> root;
    private final Function<TreeNode<Prato>, Boolean> onProxPrato;
    private final Function<TreeNode<Prato>, Boolean> onPalpite;
    private final Function<Prato, Prato> onDerrota;
    private final Consumer<Prato> onVitoria;

    RootViewModel(final Function<TreeNode<Prato>, Boolean> onProxNode, Function<TreeNode<Prato>, Boolean> onPalpite, Function<Prato, Prato> onDerrota, Consumer<Prato> onVitoria) {
        this.onProxPrato = onProxNode;
        this.onPalpite = onPalpite;
        this.onDerrota = onDerrota;
        this.onVitoria = onVitoria;
        this.root = new TreeNode<>(null, null);

        this.root.addNode(new Prato("Lasanha", "Massa"));
        this.root.addNode(new Prato("Bolo de chocolate", "Sobremesa"));
    }

    void iniciarProcura() {
        procurar(this.root);
    }

    private void procurar(final TreeNode<Prato> pratoNode) {

        for (TreeNode<Prato> filho : pratoNode.getFilhos()) {
            final Boolean result = this.onProxPrato.apply(filho);
            if (result == null) return;
            if (result) {
                encontrou(filho);
                return;
            }
        }
        palpite(pratoNode);
    }

    private void encontrou(final TreeNode<Prato> pratoNode) {
        if (!pratoNode.isFolha()) {
            procurar(pratoNode);
        } else {
            palpite(pratoNode);
        }
    }

    private void palpite(final TreeNode<Prato> pratoNode) {

        final Boolean result = pratoNode == root ? null : onPalpite.apply(pratoNode);
        if (result != null && result) {
            onVitoria.accept(pratoNode.getValor());
        } else {
            Optional.ofNullable(onDerrota.apply(pratoNode.getValor())).ifPresent(pratoNode::addNode);
        }

    }
}
