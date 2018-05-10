package it.uniroma1.lcl.studstats.dati;

import java.util.HashMap;

public class Rapporto
{
	private TipoRapporto tipoRapporto;
	private HashMap mappaRapporto;

	public Rapporto(TipoRapporto tipoRapporto, HashMap mappaRapporto)
	{
		this.tipoRapporto=tipoRapporto;
		this.mappaRapporto=mappaRapporto;
	}

	public TipoRapporto getTipoRapporto() { return tipoRapporto; }

	@Override
	public String toString() { return mappaRapporto.toString(); }
}
