package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.Map;

import static it.uniroma1.lcl.studstats.analizzatori.Analizzators.groupAndCountBy;
import static it.uniroma1.lcl.studstats.analizzatori.Analizzators.orderByValueReversed;

public class AnalizzatoreTitoloDiStudio implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		Map<String, Long> map = groupAndCountBy(studs, "Titolo Di Studio");
		return new Rapporto(Map.of("TITOLO", orderByValueReversed(map)),getTipo());
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.TITOLO;
	}
}
