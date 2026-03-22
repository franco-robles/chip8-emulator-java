package com.francorobles.chip8;

import com.francorobles.chip8.core.CPU;
import com.francorobles.chip8.core.Memory;
import com.francorobles.chip8.ui.Pantalla;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Inciando emulador CHIP-8... ");

        Memory memory = new Memory();
        CPU cpu = new CPU(memory);
        Pantalla scream =  new Pantalla(cpu.getVram());

        JFrame ventana = new JFrame("Emulador CHIP-8 en Java");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que el programa termine al cerrar la ventana
        ventana.setResizable(false); // Evitamos que el usuario deforme los píxeles

        // 4. Armar y mostrar
        ventana.add(scream);
        ventana.pack(); // Ajusta el tamaño de la ventana al tamaño preferido del panel
        ventana.setLocationRelativeTo(null); // Centra la ventana en el monitor
        ventana.setVisible(true); // ¡Que se haga la luz!

 
    }
}