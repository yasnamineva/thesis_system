package com.uni.thesissystem.service;

import com.uni.thesissystem.dto.RecensionDTO;
import java.util.List;

public interface RecensionService {
    RecensionDTO saveRecension(RecensionDTO recensionDTO);
    RecensionDTO getRecensionById(Long id);
    List<RecensionDTO> getAllRecensions();
    RecensionDTO updateRecension(Long id, RecensionDTO recensionDTO);
    void deleteRecension(Long id);
}
