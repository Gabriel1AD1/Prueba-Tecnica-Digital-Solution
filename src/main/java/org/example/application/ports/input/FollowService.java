package org.example.application.ports.input;
/**
 * Servicio para manejar las acciones de seguir a otros usuarios.
 */
public interface FollowService {
    /**
     * Permite que un usuario siga a otro usuario.
     *
     * @param follower el nombre del usuario que sigue
     * @param followee el nombre del usuario a seguir
     */
    void follow(String follower, String followee);

}
