package org.example.application.ports.input;

/**
 * Servicio para manejar la publicación de mensajes.
 */
public interface PostService {
    /**
     * Publica un nuevo mensaje para un usuario.
     *
     * @param username  el nombre del usuario
     * @param message   el mensaje a publicar
     * @param timestamp la marca de tiempo de la publicación
     */
    void post(String username, String message, String timestamp);

}
