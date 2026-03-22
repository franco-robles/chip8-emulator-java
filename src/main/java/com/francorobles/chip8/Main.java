package com.francorobles.chip8;

import com.francorobles.chip8.core.CPU;
import com.francorobles.chip8.core.Memory;
import com.francorobles.chip8.io.KeyListenerTeclas;
import com.francorobles.chip8.ui.Pantalla;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        System.out.println("Inciando emulador CHIP-8... ");

        Memory memory = new Memory();
        CPU cpu = new CPU(memory);
        Pantalla scream =  new Pantalla(cpu.getVram());
        KeyListenerTeclas listener = new KeyListenerTeclas(cpu);

        JFrame ventana = new JFrame("Emulador CHIP-8 en Java");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que el programa termine al cerrar la ventana
        ventana.setResizable(false); // Evitamos que el usuario deforme los píxeles

        // 4. Armar y mostrar
        ventana.add(scream);
        ventana.pack(); // Ajusta el tamaño de la ventana al tamaño preferido del panel
        ventana.setLocationRelativeTo(null); // Centra la ventana en el monitor

        ventana.addKeyListener(listener);
        ventana.setFocusable(true);
        ventana.requestFocusInWindow();
        ventana.setVisible(true); // ¡Que se haga la luz!

        // 1. Cargamos el cartucho (ROM)
        try {
            // Lee todos los bytes del archivo binario
            byte[] rom = Files.readAllBytes(Paths.get("C:/Users/franco/Documents/EmuladorChip8/src/main/java/com/francorobles/chip8/roms/IBMLogo.ch8"));

            // 2. Inyectamos la ROM en la memoria de la CPU a partir de la posición 512 (0x200)
            for (int i = 0; i < rom.length; i++) {
                // Asumiendo que creaste un método en CPU para acceder a la memoria,
                // o puedes llamar directamente a memoria.write() si la instanciaste en el Main
                memory.write(0x200 + i, rom[i]);
            }
            System.out.println("ROM cargada con éxito. Tamaño: " + rom.length + " bytes.");

        } catch (IOException e) {
            System.err.println("Error al cargar la ROM: " + e.getMessage());
            System.exit(1); // Si no hay juego, cerramos el emulador
        }

        int contadorTimers = 0;
        while(true){
            cpu.ciclo();
            ventana.repaint();

            contadorTimers++;
            if (contadorTimers >= 11) {
                cpu.actualizarTimers();
                contadorTimers = 0;
            }

            // 4. Dormimos el hilo para frenar la velocidad (Control de FPS/Ciclos)
            try {
                Thread.sleep(1); // 1 milisegundo de pausa da aprox 1000Hz (muy jugable)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}