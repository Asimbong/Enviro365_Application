package com.enviro.assessment.grad001.asimbongembende.controller;

import com.enviro.assessment.grad001.asimbongembende.domain.RecyclingTip;
import com.enviro.assessment.grad001.asimbongembende.service.RecyclingTipService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RecyclingTips.
 */
@RestController
@RequestMapping("/api/recycling-tips/")
@CrossOrigin(origins = "*")
public class RecyclingTipController {
    private final RecyclingTipService service;

    /**
     * Constructor for RecyclingTipController.
     *
     * @param service the RecyclingTipService to be used
     */
    public RecyclingTipController(RecyclingTipService service) {
        this.service = service;
    }

    /**
     * GET /get-all : Get all recycling tips.
     *
     * @return a list of all RecyclingTips
     */
    @GetMapping("get-all")
    public List<RecyclingTip> getAllRecyclingTips() {
        return service.findAll();
    }

    /**
     * GET /get/{id} : Get a recycling tip by ID.
     *
     * @param id the ID of the RecyclingTip to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the RecyclingTip, or with status 404 (Not Found)
     */
    @GetMapping("get/{id}")
    public ResponseEntity<RecyclingTip> getRecyclingTipById(@PathVariable Long id) {
        Optional<RecyclingTip> recyclingTip = service.findById(id);
        return recyclingTip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /create : Create a new recycling tip.
     *
     * @param recyclingTip the RecyclingTip to create
     * @return the created RecyclingTip
     */
    @PostMapping("create")
    public RecyclingTip createRecyclingTip(@Validated @RequestBody RecyclingTip recyclingTip) {
        return service.createRecyclingTip(recyclingTip.getTip());
    }

    /**
     * PUT /update/{id} : Update an existing recycling tip.
     *
     * @param id the ID of the RecyclingTip to update
     * @param recyclingTip the updated RecyclingTip
     * @return the ResponseEntity with status 200 (OK) and with body the updated RecyclingTip, or with status 404 (Not Found)
     */
    @PutMapping("update/{id}")
    public ResponseEntity<RecyclingTip> updateRecyclingTip(@PathVariable Long id, @Validated @RequestBody RecyclingTip recyclingTip) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updateRecyclingTip(id, recyclingTip.getTip()));
    }

    /**
     * DELETE /delete/{id} : Delete a recycling tip by ID.
     * @param id the ID of the RecyclingTip to delete
     * @return the ResponseEntity with status 204 (No Content), or with status 404 (Not Found)
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteRecyclingTip(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}