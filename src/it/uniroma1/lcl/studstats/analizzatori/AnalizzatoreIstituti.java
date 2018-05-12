package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;
import jdk.dynalink.linker.ConversionComparator;
import jdk.nashorn.api.tree.Tree;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalizzatoreIstituti implements Analizzatore
{
	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.ISTITUTI;
	}

	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		HashMap<String, Long> map = studs.stream().map(x -> x.get("Istituto Superiore")).collect(Collectors.groupingBy(Function
				.identity(), HashMap::new, Collectors.counting()));
		return new Rapporto(Map.of("ISTITUTI", map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a  , LinkedHashMap::new ))),
				PossibiliRapporti
				.ISTITUTI);
	}
}
