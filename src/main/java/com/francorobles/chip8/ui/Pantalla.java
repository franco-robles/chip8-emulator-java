package com.francorobles.chip8.ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
public class Pantalla extends JPanel{
        // Dimensiones originales del CHIP-8
        private final int ANCHO_ORIGINAL = 64;
        private final int ALTO_ORIGINAL = 32;

        // Escala para que no se vea del tamaño de una estampilla en monitores modernos
        private final int ESCALA = 10;

        // Referencia al arreglo vram de la CPU
        private int[] vram;

        // Constructor: Recibe la vram para saber qué dibujar
        public Pantalla(int[] vram) {
            this.vram = vram;

            // Configuramos el tamaño del panel multiplicando por la escala
            this.setPreferredSize(new Dimension(ANCHO_ORIGINAL * ESCALA, ALTO_ORIGINAL * ESCALA));
            this.setBackground(Color.BLACK); // Fondo negro por defecto
        }

        // Este método es llamado automáticamente por Java cuando necesita redibujar la ventana
        @Override
        protected void paintComponent(Graphics g) {
            // Siempre hay que llamar al super para que el panel limpie el fondo primero
            super.paintComponent(g);

            for(int i=0; i<vram.length; i++){
                if(vram[i]==1){
                    g.setColor(Color.WHITE);
                }
                int coordX = vram[i] % ANCHO_ORIGINAL;
                int coordY = vram[i] / ANCHO_ORIGINAL;
                g.fillRect(coordX*ESCALA,coordY*ESCALA,ESCALA, ESCALA);
            }

            // ¡Aquí es donde ocurre la magia!
            // Necesitamos recorrer tu arreglo unidimensional vram (los 2048 píxeles)
            // y pintar un rectángulo blanco si el valor es 1.
        }
    }
