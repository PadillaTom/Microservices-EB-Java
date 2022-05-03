package com.padillatomas.cards.controller;

import com.padillatomas.cards.model.entity.Cards;
import com.padillatomas.cards.model.entity.Customer;
import com.padillatomas.cards.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer){
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
            if(cards !=null){
                return cards;
            } else {
                return null;
            }
    }

}
