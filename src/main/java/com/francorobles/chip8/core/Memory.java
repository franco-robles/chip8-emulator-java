package com.francorobles.chip8.core;

import java.util.ArrayList;

public class Memory {
    //4096 posicioes de 8bits cada una
    private byte[] ram;

    public Memory (){
        this.ram = new byte[4096];

    }

    public void write(int address, byte value){

        if(address>=0 && address<4096){
            this.ram[address] = value;
        }else{
            System.err.println("Intento de escritura fuera de la memoria en: " + address);
        }
    }

    public int read(int address){
        // Se aplica una máscara a nivel de bits (bitwise AND)
        // para convertir el byte con signo a un valor sin signo
        if(address>=0 && address<4096){
            return this.ram[address] & 0xFF;
        }else{
            System.err.println("Intento de escritura fuera de la memoria en: " + address);
            return 0;
        }

    }
}
