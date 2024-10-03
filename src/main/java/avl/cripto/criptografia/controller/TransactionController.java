package avl.cripto.criptografia.controller;

import avl.cripto.criptografia.controller.dto.CreateTransactionRequest;
import avl.cripto.criptografia.controller.dto.TransactionResponse;
import avl.cripto.criptografia.controller.dto.UpdateTransactionRequest;
import avl.cripto.criptografia.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest request) {
        service.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @RequestBody UpdateTransactionRequest request) {
        service.update(id, request);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var body = service.listAll(page,pageSize);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse>  findById(@PathVariable(value = "id") Long id) {

        var body = service.findById(id);

        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
