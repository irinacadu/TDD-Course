package JUnitMockitoProject.Controllers;

import JUnitMockitoProject.Entities.TransactionProjectDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.hamcrest.Matchers.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

/**
 * Test de integración para controller en entorno real
 * Se selecciona el entorno en un puerto aleatorio para que no se utilice el 8080.
 * RANDOM_PORT levanta un puerto real
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AccountProjectControllerWebClientTest {
    @Autowired
    private WebTestClient webTestClient;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }
    @Test
    void accounts_test() {
    }

    @Test
    void accountDetails_test() {
    }

    @Test
    void saveAccount_test() {
    }

    @Test
    void transfer_test() throws JsonProcessingException {
        //GIVEN
        TransactionProjectDTO transactionProjectDTO = new TransactionProjectDTO();
        transactionProjectDTO.setOriginAccountId(1L);
        transactionProjectDTO.setDestinyAccountId(2L);
        transactionProjectDTO.setBankId(1L);
        transactionProjectDTO.setAmount(new BigDecimal("200"));

        Map<String,Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("status", "OK");
        response.put("mensaje", "Transferencia realizada con éxito");
        response.put("transacción",transactionProjectDTO);

        //WHEN
        webTestClient.post().uri("http://localhost:8080/bank/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transactionProjectDTO)
                .exchange() //envío solicitud y espero respuesta
                .expectStatus().isOk()
                //THEN
                .expectBody()
                .jsonPath("$.mensaje").value(is("Transferencia realizada con éxito"))
                .jsonPath("$.mensaje").value(val ->{
                    assertEquals("Transferencia realizada con éxito",val);
                })
                .jsonPath("$.mensaje").isEqualTo("Transferencia realizada con éxito")
                .jsonPath("$.transacción.originAccountId").isEqualTo(transactionProjectDTO.getOriginAccountId())
                .json(objectMapper.writeValueAsString(response));
    }
}