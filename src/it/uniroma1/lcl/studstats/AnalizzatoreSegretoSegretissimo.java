package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.dati.*;
import it.uniroma1.lcl.studstats.util.AbstractAnalizzatore;

import java.util.Collection;
import java.util.Map;

public class AnalizzatoreSegretoSegretissimo extends AbstractAnalizzatore implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return new Rapporto(Map.of("aa", Map.of("BB", 2)), getTipo());
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.ANNO_DIPLOMA;
	}
}
