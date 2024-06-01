package JUnitMockitoProject.Controllers;

import JUnitMockitoProject.Entities.TransactionProjectDTO;
import JUnitMockitoProject.Services.AccountProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static JUnitMockitoProject.Data.DataAccountBank.createAccount_001;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @WebMvcTest anotación que indica que esta clase va a testear el entorno web
 */
@WebMvcTest(AccountProjectController.class)
class AccountProjectControllerTest {
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc; //creación de objetos simulados del entorno web
    @MockBean
    private AccountProjectService accountProjectService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Test que comprueba el detalle de la cuenta.
     * <p>
     * MockMvcBuilders: construye la simulación del endpoint
     * contentType: indica el formato de los datos que vamos a obtener
     * andExpect: todas las comprobaciones esperadas.
     *
     * @throws Exception
     */
    @Test
    void account_details_test() throws Exception {
        //GIVEN
        when(accountProjectService.findById(1L)).thenReturn(createAccount_001().orElseThrow());

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/bank/account-details-1").contentType(MediaType.APPLICATION_JSON))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.person").value("Irina"))
                .andExpect(jsonPath("$.balance").value("1000"));

        verify(accountProjectService).findById(1L);
    }


    @Test
    void transfer_test() throws Exception {

        //GIVEN
        TransactionProjectDTO transactionProjectDTO = new TransactionProjectDTO();
        transactionProjectDTO.setOriginAccountId(2L);
        transactionProjectDTO.setDestinyAccountId(1L);
        transactionProjectDTO.setAmount(new BigDecimal("100"));
        transactionProjectDTO.setBankId(1L);
        System.out.println(objectMapper.writeValueAsString(transactionProjectDTO));

        //Json con el valor simulado
        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("status", "OK");
        response.put("mensaje", "Transferencia realizada con éxito");
        response.put("transacción", transactionProjectDTO);

        System.out.println(objectMapper.writeValueAsString(response));

        //WHEN
        mvc.perform(post("/bank/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionProjectDTO)))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.mensaje").value("Transferencia realizada con éxito"))
                .andExpect(jsonPath("$.transacción.originAccountId").value(transactionProjectDTO.getOriginAccountId()))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

    }

}