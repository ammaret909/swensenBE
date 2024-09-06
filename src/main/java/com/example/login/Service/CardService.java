package com.example.login.Service;

import com.example.login.Model.CardModel;
import com.example.login.Model.request.CardDTO;
import com.example.login.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<CardDTO> getAllCards() {
        return cardRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<CardDTO> getCardById(Long cardId) {
        return cardRepository.findById(cardId).map(this::convertToDTO);
    }

    public CardDTO createCard(CardDTO cardDTO) {
        CardModel card = convertToEntity(cardDTO);
        return convertToDTO(cardRepository.save(card));
    }

    public CardDTO updateCard(Long cardId, CardDTO cardDetails) {
        CardModel card = cardRepository.findById(cardId).orElseThrow();
        card.setName(cardDetails.getName());
        card.setPrice(cardDetails.getPrice());
        return convertToDTO(cardRepository.save(card));
    }

    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    private CardDTO convertToDTO(CardModel cardModel) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setName(cardModel.getName());
        cardDTO.setPrice(cardModel.getPrice());
        return cardDTO;
    }

    private CardModel convertToEntity(CardDTO cardDTO) {
        CardModel cardModel = new CardModel();
        cardModel.setName(cardDTO.getName());
        cardModel.setPrice(cardDTO.getPrice());
        return cardModel;
    }
}