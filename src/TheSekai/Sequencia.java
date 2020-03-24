package TheSekai;

import java.util.ArrayList;
import java.util.List;

public class Sequencia
{
    private List<Cor> sequencia = new ArrayList<Cor>();
    private int indiceSequencia = -1;

    public Sequencia()
    {
        adicionar();
    }

    public void adicionar()
    {
        sequencia.add(Cor.sortear());
    }

    public boolean estaCorreta(int indice, Cor corPressionadaPeloJogador)
    {
        return sequencia.get(indice).equals(corPressionadaPeloJogador);
    }

    public void limpar()
    {
        sequencia.clear();
    }

    public void processar(List<Botao> botoes)
    {

        if (indiceSequencia == -1)
        {
            indiceSequencia++;
            return;
        }

        int indBotao = sequencia.get(indiceSequencia).ordinal();

        int indUltBotao = indiceSequencia == 0 ? -1 : sequencia.get(
                indiceSequencia - 1).ordinal();
        if (indUltBotao >= 0 && botoes.get(indUltBotao).estaPressionado())
            return;

        botoes.get(indBotao).pressionar();
        indiceSequencia++;

        if (indiceSequencia == sequencia.size())
            indiceSequencia = -1;
    }

    public boolean acabou()
    {
        return indiceSequencia == -1;
    }

    public int tamanho()
    {
        return sequencia.size();
    }
}
