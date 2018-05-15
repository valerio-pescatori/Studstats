package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;
import java.util.HashMap;

public class AnalizzatoreSegretoSegretissimo implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return new Rapporto<>(new HashMap<String, String>(), getTipo());
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.ANNO_DIPLOMA;
	}
}
