package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalizzatoreSesso implements Analizzatore
{
	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.SESSO;
	}

	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return new Rapporto(Map.of("SESSO", studs.stream().collect(Collectors.groupingBy(x -> x.get("Sesso"),
				HashMap::new, Collectors.counting()))), getTipo());
	}

}
