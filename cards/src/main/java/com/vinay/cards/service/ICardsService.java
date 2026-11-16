package com.vinay.cards.service;

import com.vinay.cards.dto.CardsDto;

public interface ICardsService {

    void createCards(String  mobileNumber);
    CardsDto getCardsByMobileNumber(String  mobileNumber);
    boolean updateCards(CardsDto cardsDto);
    boolean deleteCards(String  mobileNumber);
}
