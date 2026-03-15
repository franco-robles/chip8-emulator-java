package com.francorobles.chip8.core;

public class CPU {
    // Referencia a la memoria RAM que creamos antes
    private Memory memory;

    // 16 Registros de propósito general (V0 a VF)
    private int[] v;

    // Registro de índice (I)
    private int i;

    // Program Counter (PC)
    private int pc;

    // La Pila (Stack) de 16 niveles y su puntero (Stack Pointer)
    private int[] stack;
    private int sp;

    // Temporizadores (a 60Hz)
    private int delayTimer;
    private int soundTimer;

    public CPU(){
        this.memory = new Memory();
        this.v = new int[16];
        this.stack =  new int[16];

        reset();

    }

    // Método para reiniciar la máquina a su estado de fábrica
    public void reset(){
        // Limpiamos los registros
        for(int i=0; i< v.length; i++){
            v[i]=0;
        }

        // ¡Dato crucial! Los juegos de CHIP-8 siempre empiezan en la dirección 0x200 (512)
        pc = 0x200;

        //limpiamos la pila
        for(int j=0; j< stack.length; j++){
            stack[j]=0;
        }

        sp=0;

        // Reiniciamos timers
        delayTimer = 0;
        soundTimer = 0;

    }

}
