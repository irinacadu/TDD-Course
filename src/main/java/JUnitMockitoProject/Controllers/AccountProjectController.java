package JUnitMockitoProject.Controllers;

import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.TransactionProjectDTO;
import JUnitMockitoProject.Services.AccountProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class AccountProjectController {

    @Autowired
    private AccountProjectService accountProjectService;

    @GetMapping("/account-details-{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountProject accountDetails(@PathVariable(name = "id") Long id){
        return accountProjectService.findById(id);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransactionProjectDTO transactionProjectDTO){
        accountProjectService.transferAmount(transactionProjectDTO.getOriginAccountId(),
                                                transactionProjectDTO.getDestinyAccountId(),
                                                transactionProjectDTO.getAmount(),
                                                transactionProjectDTO.getBankId());

        Map<String,Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());

        response.put("status", "OK");
        response.put("mensaje", "Transferencia realizada con éxito");

        return ResponseEntity.ok(response);
    }

}
