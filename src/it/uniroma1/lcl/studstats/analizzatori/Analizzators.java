package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;

/**
 * Classe che fornisce metodi statici di utilità per gli Analizzatori, per facilitare il riuso del codice
 *
 */
public class Analizzators
{
	static LinkedHashMap<String, Long> groupAndCountByUnsorted(Collection<Studente> studs,String field)
	{
		return studs.stream().collect(groupingBy(x -> x.get(field), LinkedHashMap::new, counting()));
	}
}
