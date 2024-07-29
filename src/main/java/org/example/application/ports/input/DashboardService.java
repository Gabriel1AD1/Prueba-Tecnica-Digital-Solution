package org.example.application.ports.input;

import org.example.domain.models.dto.PostDTO;
import java.util.List;

/**
 * Servicio para mostrar los posts de los usuarios seguidos.
 */
public interface DashboardService {

    /**
     * Obtiene el dashboard de posts para un usuario específico.
     *
     * @param username el nombre del usuario cuyo dashboard se va a obtener
     * @return una lista de {@link PostDTO} que contiene los posts de los usuarios seguidos
     */
    List<PostDTO> getDashboard(String username);
}
