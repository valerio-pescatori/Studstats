package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class AnalizzatoreAnnoDiploma implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return new Rapporto(Map.of("ANNI_DIPLOMA", studs.stream().sorted((a, b) -> b.get("Anno Diploma").compareTo(a.get("Anno " +
				"Diploma"))).collect(groupingBy(x -> x.get("Anno Diploma"), LinkedHashMap::new, counting()))), getTipo());
	}

	@Override
	public TipoRapporto getTipo() { return PossibiliRapporti.ANNO_DIPLOMA; }
}
