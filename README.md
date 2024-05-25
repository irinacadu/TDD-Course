### Configuraciones iniciales de los entornos de test
A. Para cada uno de los tests tenemos que **configurar el entorno**, ya que la propiedad ENVIRONMENT no existe lo haremos de la siguiente manera:
    1. *Edit Configurations -> Environment variables -> ENVIRONMENT=DEV(o PROD)*
    2. "Build and run" donde aparece la versión de java, en el campo VM options(lo sabremos poniendo el ratón encima) escribimos lo siguiente *-ea -DENV=dev* o  *-ea -DENV=prod*

B. **Añadimos el plugin** 
<code> 
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.2.5</version>
    </plugin>
</code>

Para poder ejecutar los tests en terminal
