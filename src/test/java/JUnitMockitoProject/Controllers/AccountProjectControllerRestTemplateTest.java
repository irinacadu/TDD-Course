package JUnitMockitoProject.Controllers;

import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.TransactionProjectDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountProjectControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate testTemplateClient;
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    private String createURI(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    @Order(1)
    @DisplayName("TEST 1: transferencias")
    void transfer_test() throws JsonProcessingException {
        //GIVEN
        TransactionProjectDTO transactionProjectDTO = new TransactionProjectDTO();
        transactionProjectDTO.setOriginAccountId(1L);
        transactionProjectDTO.setDestinyAccountId(2L);
        transactionProjectDTO.setBankId(1L);
        transactionProjectDTO.setAmount(new BigDecimal("100"));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("date", LocalDate.now().toString());
        responseMap.put("status", "OK");
        responseMap.put("mensaje", "Transferencia realizada con éxito");
        responseMap.put("transacción", transactionProjectDTO);

        ResponseEntity<String> stringResponseEntity = testTemplateClient
                .postForEntity(createURI("/bank/transfer"), transactionProjectDTO, String.class);
        String json = stringResponseEntity.getBody();
        assertNotNull(json);
        assertEquals(HttpStatus.OK, stringResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, stringResponseEntity.getHeaders().getContentType());
        assertTrue(json.contains("Transferencia realizada con éxito"));

        JsonNode jsonNode = objectMapper.readTree(json);

        assertEquals("Transferencia realizada con éxito", jsonNode.path("mensaje").asText());
        assertEquals(LocalDate.now().toString(), jsonNode.path("date").asText());
        assertEquals("OK", jsonNode.path("status").asText());
        assertEquals(1L, jsonNode.path("transacción").path("originAccountId").asLong());
        assertEquals("100", jsonNode.path("transacción").path("amount").asText());
        assertEquals(objectMapper.writeValueAsString(responseMap), json);

    }

    @Test
    @Order(2)
    @DisplayName("TEST 2: Detalles de la cuenta")
    void account_details_test() {
        ResponseEntity<AccountProject> accountResponseEntity = testTemplateClient.getForEntity(createURI("/bank/account-details-1"), AccountProject.class);
        AccountProject accountProject = accountResponseEntity.getBody();

        assertEquals(HttpStatus.OK, accountResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, accountResponseEntity.getHeaders().getContentType());
        assertNotNull(accountProject);
        assertEquals("Irina", accountProject.getPerson());
        assertEquals(1L, accountProject.getId());
        assertEquals("900.00", accountProject.getBalance().toPlainString());
        assertEquals(new AccountProject(1L, "Irina", new BigDecimal("900.00")), accountProject);

    }

    @Test
    @Order(3)
    @DisplayName("TEST 3: Listar")
    void accounts_test() throws JsonProcessingException {
        ResponseEntity<AccountProject[]> accountsResponseEntity = testTemplateClient.getForEntity(createURI("/bank"), AccountProject[].class);
        List<AccountProject> accountsProject = Arrays.asList(accountsResponseEntity.getBody());

        assertEquals(HttpStatus.OK, accountsResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, accountsResponseEntity.getHeaders().getContentType());
        assertEquals(2, accountsProject.size());
        assertNotNull(accountsProject);
        assertEquals("Irina", accountsProject.get(0).getPerson());
        assertEquals(1L, accountsProject.get(0).getId());
        assertEquals("900.00", accountsProject.get(0).getBalance().toPlainString());
        assertEquals("Candela", accountsProject.get(1).getPerson());
        assertEquals(2L, accountsProject.get(1).getId());
        assertEquals("2100.00", accountsProject.get(1).getBalance().toPlainString());

        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(accountsProject));
        assertEquals(1L, jsonNode.get(0).path("id").asLong());
        assertEquals("900.0", jsonNode.get(0).path("balance").asText());
        assertEquals("Irina", jsonNode.get(0).path("person").asText());
        assertEquals(2L, jsonNode.get(1).path("id").asLong());
        assertEquals("2100.0", jsonNode.get(1).path("balance").asText());
        assertEquals("Candela", jsonNode.get(1).path("person").asText());

    }

    @Test
    @Order(4)
    @DisplayName("TEST 4: Guardar")
    void save_test() throws JsonProcessingException {
        AccountProject accountMaria = new AccountProject(null, "Maria", new BigDecimal("3000"));
        ResponseEntity<AccountProject> accountResponseEntity = testTemplateClient.postForEntity(createURI("/bank/create-account"),accountMaria, AccountProject.class);


        assertEquals(HttpStatus.CREATED,accountResponseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,accountResponseEntity.getHeaders().getContentType());
        AccountProject accountProject = accountResponseEntity.getBody();
        assertEquals("Maria",accountProject.getPerson());
        assertEquals(3L,accountProject.getId());
        assertEquals("3000",accountProject.getBalance().toPlainString());

        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(accountProject));
        assertEquals(3L, jsonNode.path("id").asLong());
        assertEquals("3000", jsonNode.path("balance").asText());
        assertEquals("Maria", jsonNode.path("person").asText());

    }

    @Test
    @Order(5)
    @DisplayName("TEST 5: Eliminar cuenta")
    void delete_test() throws JsonProcessingException {
        ResponseEntity<AccountProject[]> accountsResponseEntity = testTemplateClient.getForEntity(createURI("/bank"), AccountProject[].class);
        List<AccountProject> accountsProject = Arrays.asList(accountsResponseEntity.getBody());
        assertEquals(3, accountsProject.size());

//      Se puede hacer con testTemplateClient... o cob exchange()
//      testTemplateClient.delete(createURI("/bank/delete-account-3"), AccountProject.class);
        Map<String,Long>pathVariables = new HashMap<>();
        pathVariables.put("id",3L);
        ResponseEntity<Void> deleteResponse = testTemplateClient.exchange(createURI("/bank/delete-account-{id}"), HttpMethod.DELETE,null,void.class,pathVariables);


        assertEquals(HttpStatus.NO_CONTENT,deleteResponse.getStatusCode());
        assertFalse(deleteResponse.hasBody());




        accountsResponseEntity = testTemplateClient.getForEntity(createURI("/bank"), AccountProject[].class);
        accountsProject = Arrays.asList(accountsResponseEntity.getBody());
        assertEquals(2,accountsProject.size());

        ResponseEntity<AccountProject> accountResponseEntity = testTemplateClient.getForEntity(createURI("/bank/account-details-3"), AccountProject.class);
        assertEquals(HttpStatus.NOT_FOUND, accountResponseEntity.getStatusCode());
        assertFalse(accountResponseEntity.hasBody());

    }

}