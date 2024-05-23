package tddCourse.tdd.ConditionalTestsExamples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.util.Properties;

public class ConditionalTestsExamples {

    /**
     * Ejemplos de anotaciones para tests condicionales
     */
    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisplayName("Test que se ejecuta solo en Windows")
    void only_windows_test(){

    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    @DisplayName("Test que se ejecuta solo en Mac y Linux")
    void only_linux_mac_test(){

    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    @DisplayName("Test que no se va a ejecutar en Windows")
    void no_windows_test(){

    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    @DisplayName("Test que solo se va a ejecutar en Java8")
    void jdk8_only_test(){

    }

    @Test
    @DisabledOnJre(JRE.JAVA_15)
    @DisplayName("Test que solo se va a ejecutar en Java8")
    void no_jdk15_test(){

    }


    @Test
    @DisplayName("Test que nos muestra las propiedades del sistema")
    void print_system_properties(){
        Properties properties =System.getProperties();
        properties.forEach((key, value)-> System.out.println(key + ":" + value));
    }

    @Test
    @EnabledIfSystemProperty(named="java.version", matches = "17.0.8")
    @DisplayName("Test que se va a ejecutar solo si coincide la versión de java")
    void java_version_test(){
        System.out.println("versión java");
    }



}
