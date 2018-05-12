package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;

public class AnalizzatoreStudentiVotoMaggiore implements Analizzatore
{
	int voto;
	Analizzatore analizzatore;

	public AnalizzatoreStudentiVotoMaggiore(int voto) { this(voto, new AnalizzatoreSesso()); }

	public AnalizzatoreStudentiVotoMaggiore(int voto, Analizzatore analizzatore)
	{
		this.voto=voto;
		this.analizzatore=analizzatore;
	}
	// TODO: implementa il generaRapporto
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return null;
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.STUDENTI_VOTO_MAGGIORE;
	}
}
