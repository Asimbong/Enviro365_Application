package com.enviro.assessment.grad001.asimbongembende.service;

import com.enviro.assessment.grad001.asimbongembende.domain.RecyclingTip;
import com.enviro.assessment.grad001.asimbongembende.factory.RecyclingTipFactory;
import com.enviro.assessment.grad001.asimbongembende.repository.RecyclingTipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing RecyclingTips.
 */
@Service
public class RecyclingTipService {
    private final RecyclingTipRepository repository;

    /**
     * Constructor for RecyclingTipService.
     *
     * @param repository the RecyclingTipRepository to be used
     */
    public RecyclingTipService(RecyclingTipRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all recycling tips.
     *
     * @return a list of all RecyclingTips
     */
    public List<RecyclingTip> findAll() {
        return repository.findAll();
    }

    /**
     * Get a recycling tip by ID.
     *
     * @param id the ID of the RecyclingTip to retrieve
     * @return an Optional containing the RecyclingTip if found, or empty if not found
     */
    public Optional<RecyclingTip> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Create a new recycling tip.
     *
     * @param tip the tip of the RecyclingTip
     * @return the created RecyclingTip
     */
    public RecyclingTip createRecyclingTip(String tip) {
        RecyclingTip recyclingTip = RecyclingTipFactory.createRecyclingTip(tip);
        return repository.save(recyclingTip);
    }

    /**
     * Update an existing recycling tip.
     * @param id the ID of the RecyclingTip to update
     * @param tip the new tip of the RecyclingTip
     * @return the updated RecyclingTip
     */
    public RecyclingTip updateRecyclingTip(Long id, String tip) {
        RecyclingTip recyclingTip = RecyclingTipFactory.createRecyclingTip(id, tip);
        return repository.save(recyclingTip);
    }

    /**
     * Delete a recycling tip by ID.
     * @param id the ID of the RecyclingTip to delete
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}