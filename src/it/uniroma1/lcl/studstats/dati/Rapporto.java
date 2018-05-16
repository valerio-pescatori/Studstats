package it.uniroma1.lcl.studstats.dati;

import java.util.Map;

public class Rapporto
{
	private TipoRapporto tipoRapporto;
	private Map<String, ? extends Map<String, ? extends Number>> mappaRapporto;

	public Rapporto(Map<String, ? extends Map<String, ? extends Number>> mappaRapporto, TipoRapporto tipoRapporto)
	{
		this.tipoRapporto = tipoRapporto;
		this.mappaRapporto = mappaRapporto;
	}

	@Override
	public String toString() { return mappaRapporto.toString(); }

	public Map<String, ? extends Map<String, ? extends Number>> getMappaRapporto() { return mappaRapporto; }
}
