### Configuraciones iniciales de los entornos de test
Para cada uno de los tests tenemos que configurar el entorno ya que la propiedad ENVIRONMENT no existe lo haremos de la siguiente manera
1. *Edit Configurations -> Environment variables -> ENVIRONMENT=DEV(o PROD)*
2. "Build and run" donde aparece la versión de java, en el campo VM options(lo sabremos poniendo el ratón encima) escribimos lo siguiente *-ea -DENV=dev* o  *-ea -DENV=prod*
