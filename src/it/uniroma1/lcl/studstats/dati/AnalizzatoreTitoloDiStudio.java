package it.uniroma1.lcl.studstats.dati;

import it.uniroma1.lcl.studstats.util.AbstractAnalizzatore;

import java.util.Collection;
import java.util.Map;

public class AnalizzatoreTitoloDiStudio extends AbstractAnalizzatore implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		Map<String, Long> map = groupAndCountBy(studs, "Titolo Di Studio");
		return new Rapporto(Map.of("TITOLO", orderByValueReversed(map)), getTipo());
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.TITOLO;
	}
}
