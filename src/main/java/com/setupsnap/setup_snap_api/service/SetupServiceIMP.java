package com.setupsnap.setup_snap_api.service;

import com.setupsnap.setup_snap_api.Dto.SetupDto;
import com.setupsnap.setup_snap_api.exception.ResourceNotFoundException;
import com.setupsnap.setup_snap_api.model.SetupEntity;
import com.setupsnap.setup_snap_api.repo.SetupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetupServiceIMP implements SetupService{

    private final SetupRepo repo;

    public SetupDto mapResponse(SetupEntity entity){
        return SetupDto.builder()
                .id(entity.getId())
                .video(entity.getVideo())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .solution(entity.getSolution())
                .build();
    }

    @Override
   public SetupDto createSetup(SetupDto dto){
        SetupEntity data = SetupEntity.builder()
                .video(dto.getVideo())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .solution(dto.getSolution())
                .build();

        return mapResponse(repo.save(data));
    }

    @Override
    public List<SetupDto> getSetup(){
        return repo.findAll()
                .stream()
                .map(this::mapResponse)
                .toList();
    }

    @Override
    public SetupDto updateSetup(Long id,SetupDto dto){

        SetupEntity entity = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("id not found"));
       entity.setVideo(dto.getVideo());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setSolution(dto.getSolution());
        return mapResponse(repo.save(entity));
    }

    @Override
    public void deleteSetup(Long id){
        repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("id not found"));
        repo.deleteById(id);

    }

    @Override
    public List<SetupDto> searchSetup(String keyword){
        return repo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,keyword)
                .stream()
                .map(this::mapResponse)
                .toList();
    }

}
