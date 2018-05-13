package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.stream.Collectors;

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

	@Override
	public Rapporto generaRapporto(Collection<Studente> studs) { return analizzatore.generaRapporto(studs.stream().
			filter(x -> Integer.parseInt(x.get("Voto"))>=voto).collect(Collectors.toSet())); }

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.STUDENTI_VOTO_MAGGIORE;
	}
}
