package JUnitMockitoProject.Controllers;

import JUnitMockitoProject.Data.DataAccountBank;
import JUnitMockitoProject.Services.AccountProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static JUnitMockitoProject.Data.DataAccountBank.createAccount_001;
import static JUnitMockitoProject.Data.DataAccountBank.createBank_001;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @WebMvcTest
 * anotación que indica que esta clase va a testear el entorno web
 */
@WebMvcTest (AccountProjectController.class)
class AccountProjectControllerTest {
    @Autowired
    private MockMvc mvc; //creación de objetos simulados del entorno web

    @MockBean
    private AccountProjectService accountProjectService;

    /**
     * Test que comprueba el detalle de la cuenta.
     *
     * MockMvcBuilders: construye la simulación del endpoint
     * contentType: indica el formato de los datos que vamos a obtener
     * andExpect: todas las comprobaciones esperadas.
     * @throws Exception
     */
    @Test
    void account_details_test() throws Exception {
        //GIVEN
        when(accountProjectService.findById(2L)).thenReturn(createAccount_001().orElseThrow());

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/bank/account-details-2").contentType(MediaType.APPLICATION_JSON))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.person").value("Irina"))
                .andExpect(jsonPath("$.balance").value("1000"));

        verify(accountProjectService).findById(2L);
    }


    @Test
    void transfer_test(){

    }

}