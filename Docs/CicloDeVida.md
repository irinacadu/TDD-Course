## Ciclo de vida
- Proceso en el cual se crea una instancia, se administra y se destruye. Lo solemos hacer con new
- JUnit crea el ciclo de vida de la instancia.
- Con JUnit podemos ejecutar una pieza de código espécifico en cualquier momento del ciclo de vida. Esto lo hace con los eventos.
- Los métodos de una clase de test también forman parte del ciclo de vida.
- Por cada método se crea una nueva instancia de la clase. El orden de ejecución de los métodos es aleatorio y no hay garantía de cuál se va a ejecutar primero.
- Se puede crear una instancia sola para toda la clase.
- Se puede establecer unas normas de ordenación de los métodos de las clases de test.
- No es una buena parte realizar ordenación de métodos dependientes de otros, ya que se puede cambiar el comportamiento de los tests y no obtener los resultados esperados.
- Se pueden ordenar de manera visual pero no para crear dependencias.