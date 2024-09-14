package org.juliomesquita.commom.interfaces;

public interface BaseUsecase<I, O> {
    O execute(I input);
}
