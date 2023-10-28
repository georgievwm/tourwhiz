package org.georgiev.tourwhiz.presentation.controllers;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.georgiev.tourwhiz.mappers.SocialMediaMapper;
import org.georgiev.tourwhiz.presentation.dtos.SocialMediaDto;
import org.georgiev.tourwhiz.presentation.dtos.UpdateSocialMediaDto;
import org.georgiev.tourwhiz.services.SocialMediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialMediaController {

    private final SocialMediaService socialMediaService;
    private final SocialMediaMapper socialMediaMapper;

    public SocialMediaController(SocialMediaService socialMediaService, SocialMediaMapper socialMediaMapper) {
        this.socialMediaService = socialMediaService;
        this.socialMediaMapper = socialMediaMapper;
    }

    @GetMapping("/venues/{venueId}/social-medias")
    public ResponseEntity<List<SocialMediaDto>> findAll(@PathVariable Long venueId) {
        final List<SocialMediaDto> socialMedias = socialMediaService.findAllByVenueId(venueId).stream().map(socialMediaMapper::toDto).toList();

        return ResponseEntity.ok(socialMedias);
    }

    @PostMapping("/venues/{venueId}/social-medias")
    public ResponseEntity<SocialMediaDto> create(@RequestBody SocialMediaDto socialMediaDto, @PathVariable Long venueId) {
        final SocialMedia created = socialMediaService.create(venueId, socialMediaMapper.dtoToEntity(socialMediaDto));
        final SocialMediaDto createdDto = socialMediaMapper.toDto(created);

        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/venues/{venueId}/social-medias/{socialMediaId}")
    public ResponseEntity<SocialMediaDto> update(@RequestBody UpdateSocialMediaDto updateSocialMediaDto,
                                                 @PathVariable Long venueId,
                                                 @PathVariable Long socialMediaId
    ) {
        final SocialMedia toUpdate = socialMediaMapper.updateDtoToEntity(updateSocialMediaDto);
        final SocialMedia updated = socialMediaService.update(venueId, socialMediaId, toUpdate);
        final SocialMediaDto socialMediaDto = socialMediaMapper.toDto(updated);

        return ResponseEntity.ok(socialMediaDto);
    }

    @DeleteMapping("/venues/{venueId}/social-medias/{socialMediaId}")
    public ResponseEntity<Void> delete(@PathVariable Long venueId, @PathVariable Long socialMediaId) {
        socialMediaService.delete(venueId, socialMediaId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
