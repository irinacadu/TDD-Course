## Configuraciones iniciales 
1. Dependencias y plugins:

   ### Dependencias:
        <!--Mockito-->        
                <dependency>
                    <groupId>org.Mockito</groupId>
                    <artifactId>Mockito-core</artifactId>
                    <version>4.5.1</version>
                </dependency>
        
        <!--JUnit-->
                <dependency>
                    <groupId>org.junit.jupiter</groupId>
                    <artifactId>junit-jupiter</artifactId>
                    <version>5.8.2</version>
                </dependency>

        <!--Mockito-JUnit-Jupiter-->
               <dependency>
                    <groupId>org.Mockito</groupId>
                    <artifactId>Mockito-junit-jupiter</artifactId>
                    <version>5.11.0</version>
                    <scope>test</scope>
               </dependency>

        <!--Lombock-->        
                <dependency>
                    <groupId>org.projectlombok</groupId>
                    <artifactId>lombok</artifactId>
                    <optional>true</optional>
                </dependency>

         <!-- Base de datos H2-->
               <dependency>
                  <groupId>com.h2database</groupId>
                  <artifactId>h2</artifactId>
                  <scope>test</scope>
               </dependency>
         <!-- JPA data starter-->
               <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-data-jpa</artifactId>
               </dependency>

   ### Plugins:
        <!--Pruebas en terminal-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
            </plugin>


2. Para cada uno de los tests tenemos que **configurar el entorno**, ya que la propiedad ENVIRONMENT no existe lo haremos de la siguiente manera:
          1. *Edit Configurations -> Environment variables -> ENVIRONMENT=DEV(o PROD)*
          2. "Build and run" donde aparece la versión de java, en el campo VM options(lo sabremos poniendo el ratón encima) escribimos lo siguiente *-ea -DENV=dev* o  *-ea -DENV=prod*




