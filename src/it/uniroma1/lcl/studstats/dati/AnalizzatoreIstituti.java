package it.uniroma1.lcl.studstats.dati;

import it.uniroma1.lcl.studstats.util.AbstractAnalizzatore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AnalizzatoreIstituti extends AbstractAnalizzatore implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		HashMap<String, Long> map = groupAndCountBy(studs, "Istituto Superiore");
		return new Rapporto(Map.of("ISTITUTI", orderByValueReversed(map)), getTipo());
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.ISTITUTI;
	}
}
