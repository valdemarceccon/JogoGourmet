package view;

import model.Comida;

import java.util.LinkedList;
import java.util.List;

/**
 * ViewModel do Root.
 * Utilizando MVVM para facilitar possíveis implementações de testes
 * <p>
 * Toda a regra de negócio sera implementada nos ViewModels
 */
class RootViewModel {

    private final List<Comida> comidas;

    RootViewModel() {
        // TODO: Criar lista incial de comidas
        comidas = new LinkedList<>();
    }

    // TODO: Cria eventos para manipular e encontrar comidas na lista

}
