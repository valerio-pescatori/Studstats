package it.uniroma1.lcl.studstats.dati;

import java.util.HashMap;

/**
 * Gli oggetti di questa classe rappresentano un singolo studente.<br>
 * Sono costruiti con una mappa che contiene come chiavi il tipo di informazione e come valore il valore dell'informazione relativa
 * ad ogni singolo studente.<br>
 * Due studenti sono considerati uguali quando le loro mappe sono uguali, cioè quando tutte le informazioni di uno sono uguali
 * alle informazioni dell'altro ( es. : stessa scuola, stessa data di nascita, stesso voto ecc.) anche se non è detto che siano lo
 * stesso studente.
 */
public class Studente
{
	HashMap<String, String> map = new HashMap<>();

	public void put(String k, String v) { map.put(k, v);}

	@Override
	public String toString()
	{
		return map.toString();
	}

	public String get(String key) { return map.getOrDefault(key, ""); }

	@Override
	public int hashCode() { return map.hashCode(); }

	@Override
	public boolean equals(Object obj)
	{
		Studente s;
		if (obj == null) return false;
		if (obj instanceof Studente)
		{
			s = (Studente) obj;
			return map.equals(s.map);
		}
		return false;
	}
}

