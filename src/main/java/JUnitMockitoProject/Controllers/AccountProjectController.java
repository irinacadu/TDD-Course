package JUnitMockitoProject.Controllers;

import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.TransactionProjectDTO;
import JUnitMockitoProject.Services.AccountProjectService;
import JUnitMockitoProject.Services.AccountProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/bank")
public class AccountProjectController {

    @Autowired
    private AccountProjectService accountProjectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountProject> accounts(){
        return accountProjectService.findAll();
    }


    @GetMapping("/account-details-{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountProject accountDetails(@PathVariable(name = "id") Long id){
        return accountProjectService.findById(id);
    }

    @PostMapping("/create-account")
    @ResponseStatus(CREATED)
    public AccountProject saveAccount(@RequestBody AccountProject accountProject){
        return accountProjectService.save(accountProject);
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
        response.put("transacción",transactionProjectDTO);

        return ResponseEntity.ok(response);
    }

}
