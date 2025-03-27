package mk.ukim.finki.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlab.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlab.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints to managing accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @Operation(summary = "Get all products", description = "Retrieves a list of all available accommodations.")
    @GetMapping
    public List<DisplayAccommodationDto> findAll() {
        return this.accommodationApplicationService.findAll();
    }

    @Operation(summary = "Get accommodation by ID", description = "Finds a accommodation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return this.accommodationApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new product", description = "Creates a new accommodation based on the given AccommodationDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto createAccommodationDto) {
        return this.accommodationApplicationService.save(createAccommodationDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Update an existing accommodation", description = "Updates a accommodation by ID using AccommodationDto.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto createAccommodationDto) {
        return this.accommodationApplicationService.update(id, createAccommodationDto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a accommodation", description = "Deletes a accommodation by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.accommodationApplicationService.findById(id).isPresent()) {
            this.accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Rent a accommodation", description = "Rents a accommodation by its ID.")
    @PutMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDto> rent(@PathVariable Long id){
        return this.accommodationApplicationService.rent(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
