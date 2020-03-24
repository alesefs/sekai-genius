package TheSekai;

import java.applet.AudioClip;
import java.awt.Color;
import java.util.Random;

public enum Cor
{
    AMARELO(Color.YELLOW, "/recursos/som1.wav"), 
    VERMELHO(Color.RED, "/recursos/som2.wav"), 
    VERDE(Color.GREEN, "/recursos/som3.wav"), 
    AZUL(Color.BLUE, "/recursos/som4.wav");

    private Color cor;
    private AudioClip som;

    private Cor(Color cor, String som)
    {
        this.cor = cor;
        this.som = java.applet.Applet.newAudioClip(getClass().getResource(som));
    }

    public static Cor sortear()
    {
        Random random = new Random();
        int ind = random.nextInt(values().length);
        return values()[ind];
    }

    public void tocarSom()
    {
        som.play();
    }

    public Color getCorPressionado()
    {
        return cor;
    }
    
    public Color getCorSolto()
    {
        return cor.darker().darker();
    }
}

