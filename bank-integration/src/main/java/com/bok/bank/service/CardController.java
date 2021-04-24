package com.bok.bank.service;

import com.bok.bank.dto.CardInfoDTO;
import com.bok.bank.dto.NewCardDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public interface CardController {

    @GetMapping("/allCards")
    public List<CardInfoDTO> allCards(@RequestParam Long accountId);

    @GetMapping("/{cardId}")
    public CardInfoDTO findCard(@RequestParam Long accountId, @PathVariable Long cardId);

    @PostMapping("/")
    public CardInfoDTO newCard(@RequestParam Long accountId, @RequestBody NewCardDTO newCardDTO);
}
