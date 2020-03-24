package TheSekai;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Genius
{
    private enum Estado
    {
        TOCANDO, OUVINDO
    };

    private Estado estado = Estado.TOCANDO;
    private Sequencia sequencia = new Sequencia();

    private List<Botao> botoes = new ArrayList<Botao>();

    private boolean terminou = false;

    private Cor ultCorPressionada = null;

    private boolean botaoPressionado = false;

    private int indPressionamento = -1;

    public Genius()
    {
        int x[] = new int[] {50, 300, 50, 300};
        int y[] = new int[] {50, 50, 300, 300};

        Cor[] cores = Cor.values();
        for (int i = 0; i < cores.length; i++)
            botoes.add(new Botao(this, cores[i], x[i], y[i], 100));
    }

    public boolean terminou()
    {
        return terminou;
    }

    private boolean temBotaoPressionado()
    {
        for (Botao botao : botoes)
            if (botao.estaPressionado())
                return true;

        return false;
    }

    public void processarCliqueDoMouse(MouseEvent e)
    {
        if (estado == Estado.TOCANDO)
            return;

            if (temBotaoPressionado())
            return;

        for (Botao botao : botoes)
            if (botao.contem(e.getPoint()))
            {
                indPressionamento++;
                botao.pressionar();
                botaoPressionado = true;
            }
    }

    public void processarEventoDeJanela(WindowEvent e)
    {
        terminou = true;
    }


    public void processar(long delay)
    {
        for (Botao btn : botoes)
            btn.processar(delay);
        
        switch (estado)
        {
            case TOCANDO:
                sequencia.processar(botoes);
                
                if (sequencia.acabou())
                    trocarEstado(Estado.OUVINDO);
                break;

            case OUVINDO:
                if (botaoPressionado)
                    return;

                if (indPressionamento == -1)
                    return;

  
                if (!sequencia.estaCorreta(indPressionamento, ultCorPressionada))
                {
                    JOptionPane.showMessageDialog(null, "Sequencia errada!");
                    terminou = true;
                    return;
                }


                if (indPressionamento == sequencia.tamanho() - 1)
                {
                    trocarEstado(Estado.TOCANDO);
                    return;
                }

                break;
        }
    }

    private void trocarEstado(Estado ouvindo)
    {
        if (estado == Estado.TOCANDO)
        {
            estado = Estado.OUVINDO;
            indPressionamento = -1;
            ultCorPressionada = null;
            return;
        }

        if (estado == Estado.OUVINDO)
        {

            estado = Estado.TOCANDO;
            sequencia.adicionar();
        }
    }

    public void desenhar(Graphics2D graphics2D)
    {
        graphics2D.setBackground(Color.BLACK);
        graphics2D.clearRect(0, 0, 640, 480);

        for (Botao botao : botoes)
            botao.desenhar(graphics2D);
    }

    public void botaoSoltou(Botao botao)
    {
        if (estado == Estado.TOCANDO)
            return;

        botaoPressionado = false;
        ultCorPressionada = botao.getCor();
    }

}

