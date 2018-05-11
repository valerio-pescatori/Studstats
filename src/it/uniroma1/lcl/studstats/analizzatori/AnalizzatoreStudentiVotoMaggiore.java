package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;

public class AnalizzatoreStudentiVotoMaggiore implements Analizzatore
{
	public AnalizzatoreStudentiVotoMaggiore(int voto) {}

	public AnalizzatoreStudentiVotoMaggiore(int voto, Analizzatore analizzatore) {}

	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return null;
	}

	@Override
	public TipoRapporto getTipo()
	{
		return null;
	}
}
