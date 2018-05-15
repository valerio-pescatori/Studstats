package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static it.uniroma1.lcl.studstats.analizzatori.Analizzators.groupAndCountBySorted;

public class AnalizzatoreAnnoDiploma implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return new Rapporto<String, HashMap<String, Long>>(Map.of("ANNI_DIPLOMA", groupAndCountBySorted(studs, "Anno Diploma" ,
				(a, b) -> b.get("Anno Diploma").compareTo(a.get("Anno Diploma")))), getTipo());
	}

	@Override
	public TipoRapporto getTipo() { return PossibiliRapporti.ANNO_DIPLOMA; }
}
