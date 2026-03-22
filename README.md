# 👾 Emulador CHIP-8 en Java

Un emulador funcional de la máquina virtual CHIP-8 de los años 70, escrito completamente desde cero en Java.

Este proyecto fue desarrollado para profundizar en la arquitectura de computadoras, el ciclo de instrucción (Fetch-Decode-Execute), el manejo de memoria y las operaciones a nivel de bits (*bitwise*), aplicando principios sólidos de Diseño Orientado a Objetos (OOP).

## ✨ Características implementadas

* **CPU Completa:** Emulación de los 35 opcodes originales del CHIP-8.
* **Gestión de Memoria:** Implementación precisa del mapa de memoria de 4KB (0x000 a 0xFFF).
* **Gráficos (Display):** Renderizado de la matriz de 64x32 píxeles utilizando Java Swing para una actualización eficiente a 60FPS.
* **Input (Teclado Hexadecimal):** Mapeo del teclado original de 16 teclas (0-F) a un teclado moderno QWERTY utilizando `KeyListener`.
* **Temporizadores (Timers):** Sincronización del *Delay Timer* y *Sound Timer* a 60Hz de forma independiente a la velocidad del procesador.
* **Arquitectura Desacoplada:** Separación clara entre los componentes lógicos (`CPU`, `Memory`) y la interfaz gráfica (`Pantalla`, `Teclado`).

## 🛠️ Tecnologías utilizadas

* **Lenguaje:** Java (Core)
* **Interfaz Gráfica:** Java Swing / AWT
* **Herramientas:** Operadores Bitwise, Threads (Sincronización de Game Loop)

## 🏗️ Arquitectura del Proyecto

El proyecto está estructurado de manera modular para separar el hardware emulado de la interfaz de usuario:

- `com.francorobles.chip8.core.CPU`: El "cerebro". Maneja los registros (V0-VF, I, PC, Stack), decodifica y ejecuta las instrucciones.
- `com.francorobles.chip8.core.Memory`: Simula la RAM de 4096 bytes con protecciones de lectura/escritura.
- `com.francorobles.chip8.ui.Pantalla`: Extiende `JPanel` para dibujar el estado del arreglo VRAM en la pantalla.
- `com.francorobles.chip8.io.KeyListenerTeclas`: Intercepta los eventos del teclado físico y los traduce al sistema hexadecimal del CHIP-8.

## 🚀 Cómo ejecutarlo

1. Clona este repositorio:
   ```bash
   git clone [https://github.com/tu-usuario/chip8-emulator-java.git](https://github.com/tu-usuario/chip8-emulator-java.git)