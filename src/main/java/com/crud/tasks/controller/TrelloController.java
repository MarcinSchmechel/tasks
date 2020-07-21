package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards.stream()
//                .filter(trelloBoardDto -> Objects.nonNull(trelloBoardDto.getId()))
//                .filter(trelloBoardDto -> Objects.nonNull(trelloBoardDto.getName()))
////                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
//                .forEach(trelloBoardDto -> {System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
//                    System.out.println("This board contains lists: ");
//                    trelloBoardDto.getLists().forEach(trelloList ->
//                            System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//                });
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}