package com.book.Library.service;

import com.book.Library.model.Rental;
import com.book.Library.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental updateRental(Long id, Rental rental) {
        Optional<Rental> existingRental = rentalRepository.findById(id);
        if (existingRental.isPresent()) {
            Rental updatedRental = existingRental.get();
            updatedRental.setUser(rental.getUser());
            updatedRental.setBook(rental.getBook());
            updatedRental.setRentalDate(rental.getRentalDate());
            updatedRental.setReturnDate(rental.getReturnDate());
            updatedRental.setRentalFee(rental.getRentalFee());
            return rentalRepository.save(updatedRental);
        } else {
            throw new RuntimeException("Rental not found with id " + id);
        }
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
