package com.salveaterra.salveaterra.model;

/**
 * Created by Andrey on 05/05/2016.
 */
public enum GeneroEnum {
    MASCULINO(1), FEMININO(2);

    public int genero;

    GeneroEnum(int genero) {
        this.genero = genero;
    }
}
