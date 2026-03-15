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

    // Arreglo con los 80 bytes que representan los caracteres del 0 a la F
    private final int[] fontset = {
            0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
            0x20, 0x60, 0x20, 0x20, 0x70, // 1
            0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
            0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
            0x90, 0x90, 0xF0, 0x10, 0x10, // 4
            0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
            0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
            0xF0, 0x10, 0x20, 0x40, 0x40, // 7
            0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
            0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
            0xF0, 0x90, 0xF0, 0x90, 0x90, // A
            0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
            0xF0, 0x80, 0x80, 0x80, 0xF0, // C
            0xE0, 0x90, 0x90, 0x90, 0xE0, // D
            0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
            0xF0, 0x80, 0xF0, 0x80, 0x80  // F
    };


    public CPU(){
        this.memory = new Memory();
        this.v = new int[16];
        this.stack =  new int[16];

        for(int j = 0; j <fontset.length; j++) {
            memory.write(j, (byte)fontset[j]);
        }

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

    public int fetch(){
        int pcPart1 = memory.read(pc);
        pc+=1; //Muevo el PC una posicion adelante para compiar la segunda parte del opcode
        int pcPart2 = memory.read(pc);
        pc+=1;
        //tengo que unir ambos para fomar el opcode de 16 bits (2 bytes)
        //muevo los valores del pcPart1 9 posiciones a la izquierda
        pcPart1 <<= 8;
        int opcode =  pcPart1 | pcPart2; //esto une ambas parde del opcode
        return opcode;
    }

    public void decode(int opcode){
        int instruccion = opcode & 0xF000;
        switch (instruccion){
            case 0x1000:
                //pasa algo
                System.out.println("Categoría: Salto JUMP");
                break;
            case 0xA000:
                //pasa algo
                System.out.println("Categoría: Setear registro I");
                break;
            case 0xF000:
                //pasa algo
                System.out.println("Categoría: Sata a otra posicion");
                break;
            default:
                System.out.println("OPCODE no reconocido o no implementado");
        }

    }
}
