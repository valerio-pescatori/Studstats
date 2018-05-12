package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalizzatoreTitoloDiStudio implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		Map<String, Long> map = studs.stream().collect(Collectors.groupingBy(x -> x.get("Titolo Di Studio"), LinkedHashMap::new,
				Collectors.counting()));
		return new Rapporto(Map.of("TITOLO", map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> x, LinkedHashMap::new))),getTipo());
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.TITOLO;
	}
}
