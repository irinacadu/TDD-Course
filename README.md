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

         <!-- Swagger -->
         - Añadiendo esta dependencia y en la url : http://localhost:8080/swagger-ui/index.html accedemos a la documentación de la app
               <dependency>
                  <groupId>org.springdoc</groupId>
                  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
                  <version>2.5.0</version>
               </dependency>
         <!-- Webflux para pruebas de integración en entorno real -->
               <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-starter-webflux</artifactId>
                  <scope>test</scope>
               </dependency>


   ### Plugins:
        <!--Pruebas en terminal-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
            </plugin>


2. Para cada uno de los tests tenemos que **configurar el entorno**, ya que la propiedad ENVIRONMENT no existe lo haremos de la siguiente manera:
- *Edit Configurations -> Environment variables -> ENVIRONMENT=DEV(o PROD)*
- "Build and run" donde aparece la versión de java, en el campo VM options(lo sabremos poniendo el ratón encima) escribimos lo siguiente *-ea -DENV=dev* o  *-ea -DENV=prod*

3. [Configuración swagger] [Configuración swagger]


[Configuración swagger]: https://github.com/irinacadu/TDD-Course/blob/a7d3ce9986dc0fae5b44a6fea9fe311e8d6904a9/src/main/java/JUnitMockitoProject/SwaggerConfig.java#L11

