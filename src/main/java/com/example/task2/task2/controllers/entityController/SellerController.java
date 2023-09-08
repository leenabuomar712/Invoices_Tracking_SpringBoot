package com.example.task2.task2.controllers.entityController;

import com.example.task2.task2.NotFoundException;
import com.example.task2.task2.controllers.DTOs.SellerDTO;
import com.example.task2.task2.data.Repositories.SellerRepository;
import com.example.task2.task2.data.entities.Seller;
import com.example.task2.task2.service.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final SellerService sellerService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    // todo: return response entity and determine the response code
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        List<SellerDTO> sellers = sellerService.getAllSellers().stream()
                .map(seller -> modelMapper.map(seller, SellerDTO.class))
                .collect(Collectors.toList());

        if (!sellers.isEmpty()) {
            return ResponseEntity.ok(sellers); // response = 200 = OK
        } else {
            return ResponseEntity.noContent().build(); // response = 204 No Content
        }

    }

    @GetMapping("/{seller_id}")
    public ResponseEntity<SellerDTO> getSellerById(@PathVariable(name = "seller_id") Long id) {
        Seller seller = sellerService.getSellerById(id);

        // convert entity to DTO
        if (seller != null) {
            SellerDTO sellerResponse = modelMapper.map(seller, SellerDTO.class);

            // error: it gives bad request even we are searching for a valid id
            return ResponseEntity.ok(sellerResponse); // 200 OK with invoice
        } else {
            throw new NotFoundException("Seller not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<SellerDTO> createSeller(@RequestBody SellerDTO sellerDTO) {
        // Convert DTO to entity
        Seller sellerRequest = modelMapper.map(sellerDTO, Seller.class);

        // Use the repository to save the entity
        Seller savedSeller = sellerRepository.save(sellerRequest);

        // Convert entity to DTO
        SellerDTO sellerResponse = modelMapper.map(savedSeller, SellerDTO.class);

        return new ResponseEntity<>(sellerResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{seller_id}")
    public ResponseEntity<SellerDTO> updateSeller(@PathVariable(name = "seller_id") long id, @RequestBody SellerDTO sellerDto) {

        // convert DTO to Entity
        Seller sellerRequest = modelMapper.map(sellerDto, Seller.class);

        Optional<Seller> seller = sellerService.updateSeller(id, sellerRequest);

        // entity to DTO
        SellerDTO sellerResponse = modelMapper.map(seller.orElse(null), SellerDTO.class);

        if (seller.isPresent()) {
            return ResponseEntity.ok(sellerResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{seller_id}")
    public ResponseEntity<String> deleteSeller(@PathVariable(name = "seller_id") Long id) {
        var isRemoved = sellerService.deleteSeller(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204 successful
    }


}
