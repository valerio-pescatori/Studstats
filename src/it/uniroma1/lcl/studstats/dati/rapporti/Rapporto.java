package it.uniroma1.lcl.studstats.dati.rapporti;

import java.util.Map;

public class Rapporto
{
	private TipoRapporto tipoRapporto;
	private Map mappaRapporto;

	public Rapporto(Map mappaRapporto, TipoRapporto tipoRapporto)
	{
		this.tipoRapporto = tipoRapporto;
		this.mappaRapporto = mappaRapporto;
	}

	@Override
	public String toString() { return mappaRapporto.toString(); }
}
