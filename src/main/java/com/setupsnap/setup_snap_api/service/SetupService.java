package com.setupsnap.setup_snap_api.service;


import com.setupsnap.setup_snap_api.Dto.SetupDto;

import java.util.List;

public interface SetupService {
    SetupDto createSetup(SetupDto dto);
    List<SetupDto> getSetup();
    SetupDto updateSetup(Long id,SetupDto dto);
    void deleteSetup(Long id);
    List<SetupDto> searchSetup(String keyword);
}
