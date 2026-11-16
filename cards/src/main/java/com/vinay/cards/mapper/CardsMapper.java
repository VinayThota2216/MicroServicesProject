package com.vinay.cards.mapper;

import com.vinay.cards.constants.CardsConstants;
import com.vinay.cards.dto.CardsDto;
import com.vinay.cards.entity.Cards;

import java.util.Random;

public class CardsMapper {
    public static Cards createCard(String mobileNumber) {
        Cards cards = new Cards();

        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        cards.setCardNumber(Long.toString(randomCardNumber));
        cards.setMobileNumber(mobileNumber);
        cards.setAmountUsed(0L);
        cards.setCardType(CardsConstants.CARD_TYPE);
        cards.setAvailableAmount(CardsConstants.CREDIT_LIMIT);
        cards.setTotalLimit(CardsConstants.CREDIT_LIMIT);
        return cards;
    }

    public static Cards cardsDtoToCardsEntity(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setTotalLimit(cardsDto.getTotalLimit());

        return cards;
    }

    public static CardsDto cardsEntityToCardsDto(Cards cards, CardsDto cardsDto) {

        cardsDto.setCardId(cards.getCardId());
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        return cardsDto;
    }
}
