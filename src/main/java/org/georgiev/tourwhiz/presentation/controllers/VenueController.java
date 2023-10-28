package org.georgiev.tourwhiz.presentation.controllers;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.models.VenueType;
import org.georgiev.tourwhiz.mappers.VenueMapper;
import org.georgiev.tourwhiz.presentation.dtos.UpdateVenueDto;
import org.georgiev.tourwhiz.presentation.dtos.VenueDto;
import org.georgiev.tourwhiz.services.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;
    private final VenueMapper venueMapper;

    public VenueController(VenueService venueService, VenueMapper venueMapper) {
        this.venueService = venueService;
        this.venueMapper = venueMapper;
    }

    @GetMapping("{venueId}")
    public ResponseEntity<VenueDto> findById(@PathVariable Long venueId) {
        final Venue found = venueService.findById(venueId);
        final VenueDto foundDto = venueMapper.toDto(found);

        return ResponseEntity.ok(foundDto);
    }

    @PostMapping
    public ResponseEntity<VenueDto> create(@RequestBody VenueDto venueDto) {
        final Venue created = venueService.create(venueMapper.dtoToEntity(venueDto));
        final VenueDto createdDto = venueMapper.toDto(created);

        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VenueDto>> findFilteredBy(@RequestParam(name = "type", required = false) VenueType type,
                                                         @RequestParam(name = "city", required = false) String city,
                                                         @RequestParam(name = "rating", required = false) BigDecimal rating) {
        final List<VenueDto> filtered = venueService.findFilteredBy(type, city, rating)
                .stream().map(venueMapper::toDto).toList();

        return ResponseEntity.ok(filtered);
    }

    @PutMapping("/{venueId}")
    public ResponseEntity<VenueDto> update(@PathVariable Long venueId,
                                           @RequestBody UpdateVenueDto updateVenueDto) {
        final Venue toUpdate = venueMapper.updateDtoToEntity(updateVenueDto);
        final Venue updated = venueService.updateById(venueId, toUpdate);
        final VenueDto venueDto = venueMapper.toDto(updated);

        return ResponseEntity.ok(venueDto);
    }

    @DeleteMapping("{venueId}")
    public ResponseEntity<Void> delete(@PathVariable Long venueId) {
        venueService.deleteById(venueId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
