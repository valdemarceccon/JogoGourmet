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
//        this.root = new TreeNode<>(null, null);

        this.root = new TreeNode<>(null, new Prato("Lasanha", "Massa"));
        final TreeNode<Prato> nao = new TreeNode<>(root, new Prato("Bolo de chocolate", "Sobremesa"));

        root.setNaoNode(nao);

    }

    void iniciarProcura() {
        procurar(this.root);
    }

    private void procurar(final TreeNode<Prato> pratoNode) {
        if (pratoNode.isFolha()) {
            palpite(pratoNode);
        }
        else {
            final boolean clicouSim = onProxPrato.apply(pratoNode);
            final TreeNode<Prato> proximoPrato = pratoNode.getProx(clicouSim);
            if (proximoPrato == null)
                palpite(pratoNode);
            else
                procurar(proximoPrato);
        }


    }

    private void palpite(final TreeNode<Prato> pratoNode) {
        final boolean result = onPalpite.apply(pratoNode);
        if (result) {
            onVitoria.accept(pratoNode.getValor());
        } else {
            final Prato novoPrato = onDerrota.apply(pratoNode.getValor());
            if (novoPrato != null) {
                final TreeNode<Prato> novoPratoNode = new TreeNode<>(null, novoPrato);
                novoPratoNode.setNaoNode(pratoNode);
                pratoNode.setNaoNode(novoPratoNode);
            }
//                    .ifPresent(novoPrato -> pratoNode.setNaoNode(new TreeNode<>(pratoNode, novoPrato)));
        }

    }
}
