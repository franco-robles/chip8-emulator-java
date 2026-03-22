package com.francorobles.chip8.io;

import com.francorobles.chip8.core.CPU;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyListenerTeclas implements KeyListener {

    private CPU cpu;

    public KeyListenerTeclas (CPU cpu){
        this.cpu = cpu;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        manejarTeclaPresionada(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        manejarTeclaSoltada(e.getKeyCode());
    }

    void manejarTeclaPresionada(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_1:
                cpu.presionarTecla(1);
            break;
            case KeyEvent.VK_2:
                cpu.presionarTecla(2);
                break;
            case KeyEvent.VK_3:
                cpu.presionarTecla(3);
                break;
            case KeyEvent.VK_4:
                cpu.presionarTecla(0xC);
                break;
            case KeyEvent.VK_X:
                cpu.presionarTecla(0x0);
                break;
            case KeyEvent.VK_Q:
                cpu.presionarTecla(0x4);
                break;
            case KeyEvent.VK_W:
                cpu.presionarTecla(0x5);
                break;
            case KeyEvent.VK_E:
                cpu.presionarTecla(0x6);
                break;
            case KeyEvent.VK_R:
                cpu.presionarTecla(0xD);
                break;
            case KeyEvent.VK_A:
                cpu.presionarTecla(0x7);
                break;
            case KeyEvent.VK_S:
                cpu.presionarTecla(0x8);
                break;
            case KeyEvent.VK_D:
                cpu.presionarTecla(0x9);
                break;
            case KeyEvent.VK_F:
                cpu.presionarTecla(0xE);
                break;
            case KeyEvent.VK_Z:
                cpu.presionarTecla(0xA);
                break;
            case KeyEvent.VK_C:
                cpu.presionarTecla(0xB);
                break;
            case KeyEvent.VK_V:
                cpu.presionarTecla(0xF);
                break;
        }
    }

    void manejarTeclaSoltada(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_1:
                cpu.soltarTecla(1);
                break;
            case KeyEvent.VK_2:
                cpu.soltarTecla(2);
                break;
            case KeyEvent.VK_3:
                cpu.soltarTecla(3);
                break;
            case KeyEvent.VK_4:
                cpu.soltarTecla(0xC);
                break;
            case KeyEvent.VK_X:
                cpu.soltarTecla(0x0);
                break;
            case KeyEvent.VK_Q:
                cpu.soltarTecla(0x4);
                break;
            case KeyEvent.VK_W:
                cpu.soltarTecla(0x5);
                break;
            case KeyEvent.VK_E:
                cpu.soltarTecla(0x6);
                break;
            case KeyEvent.VK_R:
                cpu.soltarTecla(0xD);
                break;
            case KeyEvent.VK_A:
                cpu.soltarTecla(0x7);
                break;
            case KeyEvent.VK_S:
                cpu.soltarTecla(0x8);
                break;
            case KeyEvent.VK_D:
                cpu.soltarTecla(0x9);
                break;
            case KeyEvent.VK_F:
                cpu.soltarTecla(0xE);
                break;
            case KeyEvent.VK_Z:
                cpu.soltarTecla(0xA);
                break;
            case KeyEvent.VK_C:
                cpu.soltarTecla(0xB);
                break;
            case KeyEvent.VK_V:
                cpu.soltarTecla(0xF);
                break;
        }
    }
}
