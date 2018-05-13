package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static it.uniroma1.lcl.studstats.analizzatori.Analizzators.groupAndCountByUnsorted;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toMap;

public class AnalizzatoreIstituti implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		HashMap<String, Long> map = groupAndCountByUnsorted(studs, "Istituto Superiore");
		return new Rapporto(Map.of("ISTITUTI", map.entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder())).collect
				(toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new))), getTipo());
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.ISTITUTI;
	}
}
