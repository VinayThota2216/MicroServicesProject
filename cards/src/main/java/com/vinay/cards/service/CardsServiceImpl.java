package com.vinay.cards.service;

import com.vinay.cards.dto.CardsDto;
import com.vinay.cards.entity.Cards;
import com.vinay.cards.exception.CardAlreadyExistsException;
import com.vinay.cards.exception.ResourceNotFoundException;
import com.vinay.cards.mapper.CardsMapper;
import com.vinay.cards.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements ICardsService{

    private final CardsRepository cardsRepository;

    @Override
    public void createCards(String mobileNumber) {

        Optional<Cards> optionalCards=cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already exists with this mobile number is :"+mobileNumber);
        }

        cardsRepository.save(CardsMapper.createCard(mobileNumber));
    }

    @Override
    public CardsDto getCardsByMobileNumber(String mobileNumber) {

        Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () ->new ResourceNotFoundException("Cards not found with mobile number :"+mobileNumber));

        return CardsMapper.cardsEntityToCardsDto(cards,new CardsDto());
    }


    @Override
    public boolean updateCards(CardsDto cardsDto) {

        Cards cards=cardsRepository.findByMobileNumber(cardsDto.getMobileNumber()).orElseThrow(
                () ->new ResourceNotFoundException("Cards not found with mobile number :"+cardsDto.getMobileNumber()));

        CardsMapper.cardsDtoToCardsEntity(cardsDto,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCards(String mobileNumber) {

         Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                 () ->new ResourceNotFoundException("Cards not found with mobile number :"+mobileNumber));
         cardsRepository.deleteById(cards.getCardId());
         return true;
    }
}
