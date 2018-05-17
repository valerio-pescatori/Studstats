package it.uniroma1.lcl.studstats.util;

import it.uniroma1.lcl.studstats.dati.Studente;

import java.util.*;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;

/**
 * Classe astratta, superclasse di tutti gli analizzatori, fornisce un implementazione comune per {@link AbstractAnalizzatore#hashCode()} e
 * {@link AbstractAnalizzatore#equals(Object)} oltre a vari metodi statici di comodo con visibilità {@code protected}.
 * @see it.uniroma1.lcl.studstats.dati.Analizzatore Analizzatore
 */
public abstract class AbstractAnalizzatore
{
	@Override
	public int hashCode() { return Objects.hash(getClass()); }

	@Override
	public boolean equals(Object obj) { return obj.getClass() == this.getClass(); }

	/**
	 * <p> Metodo statico che prende in input una collezione di oggetti Studente (o di una sua sottoclasse), raggruppa per il campo
	 * {@code field} tutti i diversi valori che compaiono nei vari studenti e conta le occorrenze di ogni valore.</p>
	 * <p> I dati calcolati vengono collezionati in una mappa, le cui chiavi sono i diversi valori che assume il campo {@code field}
	 * e i cui valori sono le occorrenze di tali valori.
	 * </p>
	 *
	 * @param studs collezione della quale si vuole ottenere la mappa
	 * @param field il campo del quale si vogliono contare le occorrenze dei diversi valori
	 * @param <T>   il tipo della Collection alla quale si vuole applicare il metodo (Studente o una sua sottoclasse)
	 * @return la mappa sopra specificata.
	 */
	protected static <T extends Studente> HashMap<String, Long> groupAndCountBy(Collection<T> studs, String field)
	{
		return studs.stream().collect(groupingBy(x -> x.get(field), HashMap::new, counting()));
	}

	/**
	 * <p>Metodo che raggruppa tutti i valori del campo {@code field} presenti nei diversi studenti e conta le occorrenze di ogni
	 * valore.</p>
	 * <p>Restituisce una mappa ordinata secondo il criterio specificato in {@code comparator} le cui chiavi sono i diversi
	 * valori del campo {@code field} e i cui valori sono le occorrenze di tali valori.</p>
	 *
	 * @param studs collezione della quale si vuole ottenere la mappa
	 * @param field il campo per il quale si vuole raggruppare e contare
	 * @param <T>   il tipo della Collection alla quale si vuole applicare il metodo (Studente o una sua sottoclasse)
	 * @return la mappa sopra specificata
	 */
	protected static <T extends Studente> LinkedHashMap<String, Long> groupAndCountBySorted(Collection<T> studs, String field,
	                                                                                        Comparator<T> comparator)
	{
		return studs.stream().sorted(comparator).collect(groupingBy(x -> x.get(field), LinkedHashMap::new, counting()));
	}

	/**
	 * Metodo statico che prende in input una mappa e restituisce una {@code LinkedHashMap} ordinata per valori in modo decrescente
	 * (usando {@link Comparator#reverseOrder() reverseOrder}).
	 *
	 * @param map la mappa da ordinare
	 * @return la mappa ordinata per valori in modo decrescente.
	 */
	protected static <K, V extends Comparable<V>> LinkedHashMap<K, V> orderByValueReversed(Map<K, V> map)
	{
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(reverseOrder())).collect(toMap(Map.Entry::getKey, Map
				.Entry::getValue, (x, y) -> x, LinkedHashMap::new));
	}
}
