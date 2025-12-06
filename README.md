1. Implementar Listado y Creación de Tickets:

Para el listado, considerar:
DTO para la lista de tickets con campos: título del evento, descripción del evento, fecha del evento, lugar del evento, nombre del ticket, precio, cupo disponible. Considerar el botón de eliminación.

Para la creación, considerar:
- Botón "Crear tipo de ticket".
Atributos requeridos: evento (Selector), nombre, precio, cupo total, cupo disponible.
Validaciones: precio ≥ 0, cupo total ≥ 0, cupo disponible ≥ 0 y cupo disponible ≤ cupo total.
Tras crear, redirigir al listado.

2. Implementar Añadir a la Reserva y Listado de Reserva:

Para el listado, considerar:
DTO para listar las reservas con campos: título del evento, fecha del evento, usuario (nombres, apellidos), email, nombre del ticket, cantidad de reservas. Considerar el botón de eliminación (cancelación de reserva).

Para la creación de reservas:
Botón "Añadir Reserva".
Este botón debe aparecer en la parte superior del listado de reservas.
Validaciones: cupo disponible ≥ 0 y cantidad de tickets ≤ cupo disponible, fecha evento ≥ fecha actual.
Se debe poder seleccionar a qué usuario se le asigna la reserva, a qué evento y a qué nombre o tipo de ticket hace referencia.
Tras crear, redirigir al listado.

3. Implementar Listado y Creación de Eventos:

Para el listado, considerar:
- DTO para listar los Eventos con campos: título del evento, descripción del evento, fecha del evento y lugar del evento. Considerar el botón de eliminación.

Para la creación de Eventos:
Botón "Añadir Evento".
Este botón debe aparecer en la parte superior del listado de eventos.
Validaciones: fecha evento ≥ fecha actual.
Tras crear, redirigir al listado.