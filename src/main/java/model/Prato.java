package model;

import java.util.Objects;

public class Prato {
    private final String nome;
    private final String caracteristica;

    public Prato(String nome, String caracteristica) {
        this.nome = nome;
        this.caracteristica = caracteristica;
    }

    public String getNome() {
        return nome;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prato prato = (Prato) o;
        return Objects.equals(nome, prato.nome);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome);
    }
}
