package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static it.uniroma1.lcl.studstats.analizzatori.Analizzators.groupAndCountBy;
import static it.uniroma1.lcl.studstats.analizzatori.Analizzators.orderByValueReversed;

public class AnalizzatoreIstituti implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		HashMap<String, Long> map = groupAndCountBy(studs, "Istituto Superiore");
		return new Rapporto<String, HashMap<String, Long>>(Map.of("ISTITUTI", orderByValueReversed(map)), getTipo());
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.ISTITUTI;
	}
}
