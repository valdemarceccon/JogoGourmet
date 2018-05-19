package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Tree genérica.
 * Contém o valor T, lista de Filhos que serão TreeNode<T> e o Pai do node.
 * Node sem pai é o root, Node sem filhos é uma folha da árvore.
 * <p>
 * Implementa Iterable como atalho para iterar os filhos diretos do Node
 *
 * @param <T> Tipo do valor do Node.
 */
public class TreeNode<T> implements Iterable<TreeNode<T>> {
    //TODO: Verificar a possibilidade de criar uma Tree específica para Caracteristicas (Nodes) e Pratos (Folhas)
    private final T valor;
    private final TreeNode<T> pai;
    private final List<TreeNode<T>> filhos;

    public TreeNode(TreeNode<T> pai, T valor) {
        this.valor = valor;
        this.filhos = new LinkedList<>();
        this.pai = pai;
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return this.filhos.iterator();
    }

    @Override
    public void forEach(Consumer<? super TreeNode<T>> action) {
        this.filhos.forEach(action);
    }

    public TreeNode<T> addNode(T valor) {
        final TreeNode<T> node = new TreeNode<>(this, valor);
        this.filhos.add(node);
        return node;
    }

    public boolean isFolha() {
        return this.filhos.isEmpty();
    }

    public T getValor() {
        return valor;
    }

    public TreeNode<T> getPai() {
        return pai;
    }

    public List<TreeNode<T>> getFilhos() {
        return filhos;
    }
}
