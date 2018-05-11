package it.uniroma1.lcl.studstats.dati;

import java.util.HashMap;

public class Studente
{
	HashMap<String, String> map = new HashMap<>();

	public void put(String k, String v) { map.put(k, v);}

	@Override
	public String toString()
	{
		return map.toString();
	}

	public String get(String key) { return map.get(key); }
}

