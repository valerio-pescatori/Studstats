package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalizzatoreAnnoDiploma implements Analizzatore
{
	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.ANNO_DIPLOMA;
	}

	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		Map<String, Long> mappa = studs.stream().map(x -> x.get("Anno Diploma")).sorted(Comparator.reverseOrder()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return new Rapporto(mappa, PossibiliRapporti.ANNO_DIPLOMA);
	}
}
