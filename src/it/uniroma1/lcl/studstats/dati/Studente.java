package it.uniroma1.lcl.studstats.dati;

import java.util.HashMap;

/**
 * Gli oggetti di questa classe rappresentano un singolo studente.<br>
 * Sono costruiti con una mappa che contiene come chiavi il tipo di informazione e come valore il valore dell'informazione relativa
 * ad ogni singolo studente.<br>
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
}

