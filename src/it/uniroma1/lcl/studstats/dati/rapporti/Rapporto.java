package it.uniroma1.lcl.studstats.dati.rapporti;

import java.util.HashMap;
import java.util.Map;

public class Rapporto<K, V>
{
	private TipoRapporto tipoRapporto;
	private Map<K, V> mappaRapporto;

	public Rapporto(Map<K, V> mappaRapporto, TipoRapporto tipoRapporto)
	{
		this.tipoRapporto = tipoRapporto;
		this.mappaRapporto = mappaRapporto;
	}

	@Override
	public String toString() { return mappaRapporto.toString(); }

	public Map<K, V> getMappaRapporto() {	return mappaRapporto; }
}
