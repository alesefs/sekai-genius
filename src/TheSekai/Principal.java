package TheSekai;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class Principal
{
    public static void main(String[] args)
    {
        JFrame geniusFrame = new JFrame();

        geniusFrame.setTitle("Genius");
        geniusFrame.setSize(450, 450);
        geniusFrame.setLocationRelativeTo(null);
        geniusFrame.setIgnoreRepaint(true);
        geniusFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        geniusFrame.setVisible(true);
        geniusFrame.createBufferStrategy(2);
        executarLoopDoJogo(geniusFrame);
    }

    private static void executarLoopDoJogo(JFrame geniusFrame)
    {
        Genius genius = new Genius();

        registraListeners(geniusFrame, genius);

        long ultimosSegundos = System.currentTimeMillis();
        while (!genius.terminou())
        {
            long delay = System.currentTimeMillis() - ultimosSegundos;
            ultimosSegundos = System.currentTimeMillis();

            genius.processar(delay);

            genius.desenhar((Graphics2D) geniusFrame.getBufferStrategy().getDrawGraphics());

            geniusFrame.getBufferStrategy().show();

            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
            }
        }

        geniusFrame.dispose();
        System.exit(0);
    }

    private static void registraListeners(JFrame geniusFrame,
            final Genius genius)
    {
        geniusFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                genius.processarEventoDeJanela(e);
            }
        });

        geniusFrame.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                genius.processarCliqueDoMouse(e);
            }
        });
    }

}
