# Conversor de Monedas en Java 💱

Este proyecto es una aplicación desarrollada en **Java** que permite convertir entre diferentes monedas utilizando tasas de cambio en tiempo real obtenidas desde una API externa. Es una herramienta práctica y educativa, ideal para entender cómo consumir servicios web, procesar datos JSON y construir aplicaciones funcionales.

---

## 🧩 ¿Para qué sirve?

El Conversor de Monedas permite al usuario:

- Consultar el valor actual de distintas divisas.
- Convertir montos entre monedas seleccionadas.
- Ver información precisa basada en tasas de cambio reales.

Esta herramienta es útil tanto para fines educativos como para integrar funcionalidades similares en sistemas financieros o aplicaciones de comercio internacional.

---

## ⚙️ Funcionalidades Principales

- **Consumo de API de tasas de cambio**  
  Se conecta a un servicio externo (API) que proporciona valores de cambio actualizados.

- **Análisis y procesamiento de datos JSON**  
  Extrae y organiza la información relevante de la respuesta de la API.

- **Interfaz interactiva por consola**  
  Permite al usuario seleccionar las monedas y realizar conversiones desde la terminal de manera clara y sencilla.

- **Filtrado de monedas de interés**  
  Muestra solo las monedas deseadas, facilitando la búsqueda y la comprensión.

---

## 📘 Instrucciones de Uso

1. Primero, el programa pedirá la **cantidad** que deseas convertir a otro tipo de moneda.  
2. Luego, solicitará la **moneda base**. Si no estás seguro del nombre exacto, puedes escribir solo las **iniciales** o una **parte del nombre**.  
3. A continuación, pedirá la **moneda de destino**. Aplica el mismo criterio: escribe una parte del nombre o las iniciales si no lo sabes completo.  
4. Después de cada conversión, se te preguntará si deseas **ver el historial de conversiones**:  
   - Escribe `'s'` para sí.  
   - Escribe `'n'` para no.  
   - El historial se almacena en un archivo `.json` que es leído e interpretado para mostrar las consultas realizadas a la API.  
5. Finalmente, podrás indicar si deseas **realizar otra conversión**. En caso afirmativo, se reiniciará el proceso.

---

## 🧪 Tecnologías Utilizadas

- **Lenguaje:** Java (versión 8 o superior)
- **Librerías:** 
  -  `HttpURLConnection` para solicitudes HTTP
  - `Gson` para parseo JSON
- **Diseño:** 
  - Enfocado en modularidad y claridad del flujo de ejecución.
  - Separación lógica de responsabilidades: consumo de API, procesamiento de datos y presentación.

---
## 👨‍💻 Autor

**Nombre:** Joel Narvaez Martinez  
**Correo:** Joelnarmar20@gmail.com <br>
**GitHub:** https://github.com/JoelNarvaez

---

## 🚀 ¡Empieza ahora!

Clona el repositorio, configura tu entorno Java y comienza a convertir monedas con datos en tiempo real.  
¡Diviértete programando y aprendiendo!




