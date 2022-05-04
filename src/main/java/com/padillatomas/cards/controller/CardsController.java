package com.padillatomas.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.padillatomas.cards.config.CardsServiceConfig;
import com.padillatomas.cards.model.Properties;
import com.padillatomas.cards.model.entity.Cards;
import com.padillatomas.cards.model.entity.Customer;
import com.padillatomas.cards.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    @Autowired
    CardsServiceConfig cardsServiceConfig;

    //    === GET Properties ===
    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        System.out.println(cardsServiceConfig);
        Properties properties = new Properties(
                cardsServiceConfig.getMsg(),
                cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(),
                cardsServiceConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

    //    === My Cards ===
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
