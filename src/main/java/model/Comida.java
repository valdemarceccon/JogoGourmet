package model;

import java.util.Objects;

/**
 * Representação da comida e sua caracteristicas.
 * Esta classe é imutável.
 */
public class Comida {
    private final String nome;

    // TODO: Uma só caracteristica não será o suficiente (pode ter varios doces, salgados, massas...)
    // TODO: Ou criar um Set de caracteristicas aqui, ou remover a caracteristica daqui.

    // TODO: Outra possibilidade é criar uma árvore de comidas onde cada node é um caracteristica, e finaliza em uma
    // TODO: comida. Adicionar novas comidas cria uma nova ramificação combinando as caracteristicas anteriores.
    private final String caracteristica;

    /**
     * Construtor da classe.
     * Depois de criada os campos não poderão mais ser alterados.
     *
     * @param nome           Nome da comida. Define o valor da classe.
     * @param caracteristica Descrição da comida, algo que à diferiencie das demais comidas
     */
    public Comida(final String nome, final String caracteristica) {
        this.nome = nome;
        this.caracteristica = caracteristica;
    }

    public String getNome() {
        return nome;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    /**
     * Comidas serão consideradas iguais se tiverem o mesmo nome.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comida comida = (Comida) o;
        return Objects.equals(nome, comida.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
