package JUnit.ConditionalTestsExamples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Properties;

@SpringBootTest
public class ConditionalTestsExamples {
    @Nested
    @DisplayName("Probando SO")
    static class SystemConfigTest {
        /**
         * Ejemplos de anotaciones para tests condicionales
         */
        @Test
        @EnabledOnOs(OS.WINDOWS)
        @DisplayName("Test que se ejecuta solo en Windows")
        void only_windows_test() {

        }

        @Test
        @EnabledOnOs({OS.LINUX, OS.MAC})
        @DisplayName("Test que se ejecuta solo en Mac y Linux")
        void only_linux_mac_test() {

        }

        @Test
        @DisabledOnOs(OS.WINDOWS)
        @DisplayName("Test que no se va a ejecutar en Windows")
        void no_windows_test() {

        }

        @Test
        @EnabledOnJre(JRE.JAVA_8)
        @DisplayName("Test que solo se va a ejecutar en Java8")
        void jdk8_only_test() {

        }

        @Test
        @DisabledOnJre(JRE.JAVA_15)
        @DisplayName("Test que solo se va a ejecutar en Java8")
        void no_jdk15_test() {

        }

    }

    @Nested
    @Tag("param")
    @DisplayName("Probando las propiedades del sistema")
    static class SystemPropertiesTest {
        @Test
        @DisplayName("Test que nos muestra las propiedades del sistema")
        void print_system_properties() {
            Properties properties = System.getProperties();
            properties.forEach((key, value) -> System.out.println(key + ":" + value));
        }

        @Test
        @EnabledIfSystemProperty(named = "java.version", matches = ".*17.*")
        @DisplayName("Test que se va a ejecutar solo si coincide la versión de java")
        void java_version_test() {
            System.out.println("versión java 17");
        }

        @Test
        @DisabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
        @DisplayName("Test que se va a ejecutar solo si coincide la arquitectura")
        void architecture_64_only_test() {
            System.out.println("arquitectura 64");
        }

        @Test
        @DisabledIfSystemProperty(named = "user.name", matches = "irina")
        @DisplayName("Test que se va a ejecutar solo si coincide el nombre de usuario")
        void user_name_test() {
            System.out.println("nombre de usuario");
        }

    }

    @Nested
    @DisplayName("Probando las variables de entorno")
    static class EnvironmentVariablesTest {
        @Test
        @Tag("param")
        @DisplayName("Test que nos muestra las variables del sistema")
        void print_environment_variables() {
            Map<String, String> getEnv = System.getenv();
            getEnv.forEach((key, value) -> {
                System.out.println(key + "=" + value);
            });
        }


        @Test
        @Tag("java")
        @Tag("param")
        @DisplayName("Test que comprueba que tenemos correctamente el JAVA_HOME ")
        @EnabledIfEnvironmentVariable(named = "JAVA_HOME", matches = ".*jdk-11.*")
        void java_home_test() {

        }

        @Test
        @DisplayName("Test que comprueba que tenemos los números de procesadores seleccionados")
        @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "8")
        void number_processors_test() {
        }

        @Test
        @DisplayName("Test que se ejecuta en el entorno DEV")
        @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "dev")
        void environment_dev_test() {
        }

        @Test
        @DisplayName("Test que se ejecuta en el entorno PROD")
        @EnabledIfEnvironmentVariable(named = "ENVIRONMENT", matches = "prod")
        void environment_prod_test() {
        }
    }


}
