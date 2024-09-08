package example.cashcard;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardRepository cashCardRepository;

    private CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestId}")
    private ResponseEntity<CashCard> findById(@PathVariable Long requestId, Principal principal) {
        System.out.println("Nome de usuário do principal: " + principal.getName());
        CashCard cashCard = findCashCard(requestId, principal);
        if (cashCard != null) {
            return ResponseEntity.ok(cashCard);
        }
        return ResponseEntity.notFound().build();
    }

    

    @PostMapping
    public ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCard, UriComponentsBuilder ucb, Principal principal) {
        CashCard cashCardWithOwner = new CashCard(null, newCashCard.amount(), principal.getName());
        CashCard savedCashCard = cashCardRepository.save(cashCardWithOwner);
        URI locationOfNewCashCard = ucb.path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @GetMapping
    private ResponseEntity<Iterable<CashCard>> findAll(Pageable pageable, Principal principal) {
        Page<CashCard> page = cashCardRepository.findByOwner(principal.getName(),
                PageRequest.of(
                        pageable.getPageNumber(), pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))));
        return ResponseEntity.ok(page.getContent());
    }

    @PutMapping("/{requestedId}")
    public ResponseEntity<Void> putCashCard(@PathVariable Long requestedId, @RequestBody CashCard cashCardUpdate, Principal principal) {
        CashCard cashCard = findCashCard(requestedId, principal);
        if(cashCard != null){
            CashCard updatedCashCard = new CashCard(cashCard.id(), cashCardUpdate.amount(), principal.getName());
            cashCardRepository.save(updatedCashCard);
            
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }

    private CashCard findCashCard(Long requestId, Principal principal) {
        CashCard cashCard = cashCardRepository.findByIdAndOwner(requestId, principal.getName());
        return cashCard;
    }
}
