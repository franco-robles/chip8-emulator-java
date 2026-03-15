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
        //muevo los valores del pcPart1 8 posiciones a la izquierda
        pcPart1 <<= 8;
        int opcode =  pcPart1 | pcPart2; //esto une ambas parde del opcode
        return opcode;
    }

    public void decode(int opcode){
        //en esta parte lo que hago es aislar los parametros del opcode
        //0011 1010 0001 0101 byte ejemplo
        int nnn = opcode & 0x0FFF;
        int nn = opcode & 0x00FF;
        int n = opcode & 0x000F;
        int y = (opcode & 0x00F0) >> 4;
        int x = (opcode & 0x0F00) >> 8;
        int instruccion = opcode & 0xF000;

        switch (instruccion){
            case 0x1000:
                //la instruccion uno me dice que tengo que asignar NNN al PC
                pc = nnn;
                System.out.println("JUMP: asigne al PC la dieccion NNN" );
                break;
            case 0x2000:
                //saltar a una subrutina en a direccion NNN y guardo el PC en el stack donde diga el sp
                stack[sp] = pc;
                sp+=1;
                pc = nnn;
                System.out.println("CALL: guardo el pc en stack y salta a la subrutina en la direccion NNN");
                break;
            case 0x0000:
                //operacion invesa del CALL: devolvemos al punto anterios al hacer el call
                if(opcode == 0x00EE){
                    sp-=1;
                    pc = stack[sp];
                    System.out.println("RETURN: Sata a otra posicion");
                }

                break;
            default:
                System.out.println("OPCODE no reconocido o no implementado");
        }

    }
}
