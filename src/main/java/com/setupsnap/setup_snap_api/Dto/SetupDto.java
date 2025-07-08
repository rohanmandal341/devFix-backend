package com.setupsnap.setup_snap_api.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetupDto {

    private String video;
    private String title;
    private String description;
    private String solution;
}
