package org.example.application.ports.input;

import org.example.infrastructure.adapters.in.dto.PostDTO;

import java.util.List;

public interface DashboardService {
    List<PostDTO> getDashboard(String username);
}
