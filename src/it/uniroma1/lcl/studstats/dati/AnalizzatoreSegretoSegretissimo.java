package it.uniroma1.lcl.studstats.dati;

import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.util.AbstractAnalizzatore;

import java.util.Collection;
import java.util.Map;

public class AnalizzatoreSegretoSegretissimo extends AbstractAnalizzatore implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs) { return new Rapporto(Map.of("boh", Map.of("boh", 1)),
			getTipo()); }

	@Override
	public TipoRapporto getTipo() { return PossibiliRapporti.ANNO_DIPLOMA; }
}
