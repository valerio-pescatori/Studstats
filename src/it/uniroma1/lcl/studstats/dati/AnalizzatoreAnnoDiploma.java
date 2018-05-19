package it.uniroma1.lcl.studstats.dati;

import it.uniroma1.lcl.studstats.util.AbstractAnalizzatore;

import java.util.Collection;
import java.util.Map;

public class AnalizzatoreAnnoDiploma extends AbstractAnalizzatore implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return new Rapporto(Map.of("ANNI_DIPLOMA", groupAndCountBySorted(studs, "Anno Diploma" ,
				(a, b) -> b.get("Anno Diploma").compareTo(a.get("Anno Diploma")))), getTipo() );
	}

	@Override
	public TipoRapporto getTipo() { return PossibiliRapporti.ANNO_DIPLOMA; }
}
