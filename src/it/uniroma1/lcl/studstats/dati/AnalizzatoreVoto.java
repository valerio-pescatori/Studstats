package it.uniroma1.lcl.studstats.dati;

import it.uniroma1.lcl.studstats.util.AbstractAnalizzatore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.toList;

public class AnalizzatoreVoto extends AbstractAnalizzatore implements Analizzatore
{
	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		//studList -> lista ordinata dei voti
		List<Integer> studList = studs.stream().map(x -> Integer.parseInt(x.get("Voto"))).sorted().collect(toList());
		// voto medio
		double votoMedio = studList.stream().collect(averagingDouble(x -> x));
		//voto mediano
		int votoMediano;
		int size = studList.size();
		if (size % 2 == 0) votoMediano = (studList.get(size / 2) + studList.get((size / 2) + 1)) / 2;
		else votoMediano = studList.get(size / 2);
		return new Rapporto(Map.of("VOTO",Map.of(
				"VOTO_MEDIO", new BigDecimal(votoMedio).setScale(2, RoundingMode.CEILING),
				"VOTO_MAX", studList.get(size - 1),
				"VOTO_MIN", studList.get(0),
				"VOTO_MEDIANO", votoMediano)), getTipo());
	}

	@Override
	public TipoRapporto getTipo() { return PossibiliRapporti.VOTO; }
}

