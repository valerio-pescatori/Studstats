package it.uniroma1.lcl.studstats.dati;

import java.util.Map;

/**
 * Classe che rappresenta un rapporto (l'informazione prodotta da un {@link Analizzatore}, sotto forma di mappa.
 */
public class Rapporto
{
	private TipoRapporto tipoRapporto;
	private Map<String, ? extends Map<String, ? extends Number>> mappaRapporto;

	/**
	 * unico costruttore della classe.
	 *
	 * @param mappaRapporto la mappa del rapporto
	 * @param tipoRapporto  il tipo del rapporto
	 */
	public Rapporto(Map<String, ? extends Map<String, ? extends Number>> mappaRapporto, TipoRapporto tipoRapporto)
	{
		this.tipoRapporto = tipoRapporto;
		this.mappaRapporto = mappaRapporto;
	}

	@Override
	public String toString() { return mappaRapporto.toString(); }

	public Map<String, ? extends Map<String, ? extends Number>> getMappaRapporto() { return mappaRapporto; }
}
