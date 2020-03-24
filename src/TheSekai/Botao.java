package TheSekai;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Botao
{
    private enum Estado
    {
        PRESSIONADO, SOLTO
    };

    private Cor cor;
    private Estado estado;
    private Ellipse2D forma;
    private long tempoPressionado;
    private Genius genius;

    private static final long MAX_TEMPO_PRESSIONADO = 1000;

    public Botao(Genius genius, Cor cor, int x, int y, int raio)
    {
        this.cor = cor;
        this.forma = new Ellipse2D.Float(x, y, raio, raio);
        this.estado = Estado.SOLTO;
        this.genius = genius;
    }

    public void pressionar()
    {
        if (estado == Estado.PRESSIONADO)
            return;

        estado = Estado.PRESSIONADO;
   
        cor.tocarSom();

        tempoPressionado = 0;
    }

    public void processar(long delay)
    {
        if (estado == Estado.SOLTO)
            return;

        tempoPressionado += delay;

        if (tempoPressionado > MAX_TEMPO_PRESSIONADO)
        {
            estado = Estado.SOLTO;
            genius.botaoSoltou(this);
        }
    }

    public void desenhar(Graphics2D graphics)
    {

        Graphics2D g2d = (Graphics2D) graphics.create();


        if (estado == Estado.SOLTO)
            g2d.setColor(cor.getCorSolto());
        else
            g2d.setColor(cor.getCorPressionado());

        g2d.fill(forma);

        g2d.dispose();
    }

    public boolean contem(Point2D point)
    {
        return forma.contains(point);
    }

    public boolean estaPressionado()
    {
        return estado == Estado.PRESSIONADO;
    }

    public Cor getCor()
    {
        return cor;
    }

}

