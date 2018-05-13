package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.toList;

public class AnalizzatoreVoto implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		//studList -> lista ordinata dei voti
		List<Integer> studList = studs.stream().map(x -> x.get("Voto")).map(Integer::parseInt).sorted().collect(toList());
		// voto medio
		double votoMedio = studList.stream().collect(averagingDouble(x -> x));
		//voto mediano
		int votoMediano = 0; int size = studList.size();
		if (size % 2 == 0) votoMediano = (studList.get(size / 2) + studList.get((size / 2) + 1)) / 2;
		else votoMediano = studList.get(size / 2);
		return new Rapporto(Map.of
			   ("VOTO_MEDIO", new BigDecimal(votoMedio).setScale(2, RoundingMode.FLOOR),
				"VOTO_MAX", studList.stream().max(naturalOrder()).orElse(-1),
				"VOTO_MIN", studList.stream().min(naturalOrder()).orElse(-1),
				"VOTO_MEDIANO", votoMediano), getTipo());
	}

	@Override
	public TipoRapporto getTipo() { return PossibiliRapporti.VOTO; }
}

