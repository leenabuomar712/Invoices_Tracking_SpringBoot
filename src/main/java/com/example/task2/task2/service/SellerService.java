package com.example.task2.task2.service;

import com.example.task2.task2.data.Repositories.SellerRepository;
import com.example.task2.task2.data.entities.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long id) {
        // todo: check if the id exists in the DB

        // todo: return the attachemnts and items
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (!sellerOptional.isPresent()) {
            // Todo: research how to apply exception handling using spring boot
            throw new RuntimeException("The Seller was not found");
        }
        return sellerOptional.get();
    }

    public Seller createInvoice(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Optional<Seller> updateSeller(Long id, Seller updatedSeller) {
        Optional<Seller> existingSeller = sellerRepository.findById(id);

        // TODO: Keep a blank line before and after code blocks
        if (existingSeller.isPresent()) {
            updatedSeller.setSellerId(existingSeller.get().getSellerId());
            return Optional.of(sellerRepository.save(updatedSeller));
        }

        // TODO: 404
        return Optional.empty();
    }

    public boolean deleteSeller(Long id) {
        // todo: handle non existing ids
        try {
            sellerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
