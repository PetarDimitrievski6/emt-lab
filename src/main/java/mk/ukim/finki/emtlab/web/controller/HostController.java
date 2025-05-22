package mk.ukim.finki.emtlab.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostNameAndSurnameDto;
import mk.ukim.finki.emtlab.dto.DisplayHostPerCountryViewDto;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints to managing hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all available hosts.")
    @GetMapping
    public List<DisplayHostDto> findAll() {
        return this.hostApplicationService.findAll();
    }

    @Operation(summary = "Get host by ID", description = "Finds a host by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return this.hostApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new host", description = "Creates a new host based on the given HostDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto) {
        return this.hostApplicationService.save(createHostDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Update an existing host", description = "Updates a host by ID using HostDto.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto createHostDto) {
        return this.hostApplicationService.update(id, createHostDto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host", description = "Deletes a host by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.hostApplicationService.findById(id).isPresent()) {
            this.hostApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get hosts count per country", description = "Finds the count of hosts in country")
    @GetMapping("/by-country")
    public List<DisplayHostPerCountryViewDto> getHostsPerCountry() {
        return this.hostApplicationService.getHostsPerCountryView();
    }

    @Operation(summary = "Get name and surname of all hosts", description = "Gets all host's name and surname")
    @GetMapping("/names")
    public List<DisplayHostNameAndSurnameDto> takeNameAndSurnameByProjection() {
        return this.hostApplicationService.takeNameAndSurnameByProjection();
    }
}
