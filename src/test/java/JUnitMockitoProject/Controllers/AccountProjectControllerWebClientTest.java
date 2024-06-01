package JUnitMockitoProject.Controllers;

import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.TransactionProjectDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Test de integración para controller en entorno real
 * Se selecciona el entorno en un puerto aleatorio para que no se utilice el 8080.
 * RANDOM_PORT levanta un puerto real
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountProjectControllerWebClientTest {
    ObjectMapper objectMapper;
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: transferencias entre cuentas")
    void transfer_test() throws JsonProcessingException {
        //GIVEN
        TransactionProjectDTO transactionProjectDTO = new TransactionProjectDTO();
        transactionProjectDTO.setOriginAccountId(1L);
        transactionProjectDTO.setDestinyAccountId(2L);
        transactionProjectDTO.setBankId(1L);
        transactionProjectDTO.setAmount(new BigDecimal("100"));

        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("status", "OK");
        response.put("mensaje", "Transferencia realizada con éxito");
        response.put("transacción", transactionProjectDTO);

        //WHEN
        webTestClient.post().uri("/bank/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transactionProjectDTO)
                .exchange() //envío solicitud y espero respuesta
                //THEN
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(responseBody -> { // El consumeWith es otra forma de comprobar los datos a parte de jsonPath.
                    try {
                        JsonNode json = objectMapper.readTree(responseBody.getResponseBody());
                        assertEquals("Transferencia realizada con éxito", json.path("mensaje").asText());
                        assertEquals(LocalDate.now().toString(), json.path("date").asText());
                        assertEquals("OK", json.path("status").asText());
                        assertEquals(1L, json.path("transacción").path("originAccountId").asLong());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .jsonPath("$.mensaje").value(is("Transferencia realizada con éxito"))
                .jsonPath("$.mensaje").value(val -> {
                    assertEquals("Transferencia realizada con éxito", val);
                })
                .jsonPath("$.mensaje").isEqualTo("Transferencia realizada con éxito")
                .jsonPath("$.transacción.originAccountId").isEqualTo(transactionProjectDTO.getOriginAccountId())
                .jsonPath("$.date").isEqualTo(LocalDate.now().toString())
                .jsonPath("$.status").isEqualTo("OK")
                .json(objectMapper.writeValueAsString(response));
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Detalle cuenta001 con expectBody() y jsonPath()")
    void account001Details_test() {
        webTestClient.get().uri("/bank/account-details-1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.person").isEqualTo("Irina")
                .jsonPath("$.balance").isEqualTo(900);
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Detalle cuenta002 con consumeWith()")
    void account002Details_test() {
        webTestClient.get().uri("/bank/account-details-2")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(AccountProject.class)//Mapeamos con la clase esperada
                .consumeWith(response -> {
                    AccountProject accountProject = response.getResponseBody();
                    BigDecimal expectedBalance = new BigDecimal("2100.00");
                    assertEquals("Candela", accountProject.getPerson());
                    assertEquals(expectedBalance, accountProject.getBalance());
                });
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: guardar cuenta con expectBody() y jsonPath()")
    void saveAccount_test() {
        //GIVEN
        AccountProject accountMaria = new AccountProject(null, "Maria", new BigDecimal("3000"));

        //WHEN
        webTestClient.post().uri("/bank/create-account")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(accountMaria)
                .exchange()
                //THEN
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.person").isEqualTo("Maria")
                .jsonPath("$.balance").isEqualTo(3000)
                .jsonPath("$.id").isEqualTo(3);
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: guardar cuenta con consumeWith()")
    void saveAccount_consumeWith_test() {
        //GIVEN
        AccountProject accountMario = new AccountProject(null, "Mario", new BigDecimal("3500"));

        //WHEN
        webTestClient.post().uri("/bank/create-account")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(accountMario)
                .exchange()
                //THEN
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(AccountProject.class)
                .consumeWith(response -> {
                    AccountProject accountProject = response.getResponseBody();
                    assertEquals(4L, accountProject.getId());
                    assertEquals("Mario", accountProject.getPerson());
                    assertEquals("3500", accountProject.getBalance().toPlainString());
                });
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: listar cuentas con expectBody() y jsonPath()")
    void accounts_test() {
        webTestClient.get().uri("/bank")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].person").isEqualTo("Irina")
                .jsonPath("$[0].id").isEqualTo(1L)
                .jsonPath("$[0].balance").isEqualTo(900)
                .jsonPath("$[1].person").isEqualTo("Candela")
                .jsonPath("$[1].id").isEqualTo(2L)
                .jsonPath("$[1].balance").isEqualTo(2100)
                .jsonPath("$").isArray()
                .jsonPath("$").value(hasSize(4));
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: listar cuentas con consumeWith()")
    void accounts_consumeWith_test() {
        webTestClient.get().uri("/bank")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(AccountProject.class)
                .consumeWith(response -> {
                    List<AccountProject> accounts = response.getResponseBody();
                    assertEquals(4, accounts.size());
                    assertEquals(1L, accounts.get(0).getId());
                    assertEquals("Irina", accounts.get(0).getPerson());
                    assertEquals(900, accounts.get(0).getBalance().intValue());
                    assertEquals(2L, accounts.get(1).getId());
                    assertEquals("Candela", accounts.get(1).getPerson());
                    assertEquals("2100.00", accounts.get(1).getBalance().toPlainString());
                }).hasSize(4);
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: eliminar cuenta")
    void delete_test() {
        webTestClient.get().uri("/bank")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AccountProject.class)
                .hasSize(4);
        webTestClient.delete().uri("/bank/delete-account-3")
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        webTestClient.get().uri("/bank")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(AccountProject.class)
                .hasSize(3);

        webTestClient.get().uri("/bank/account-details-3")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }
}