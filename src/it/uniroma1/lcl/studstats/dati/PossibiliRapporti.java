package it.uniroma1.lcl.studstats.dati;

/**
 * Enum estensibile che implementa l'interfaccia {@link TipoRapporto}.
 * Fornisce tutti i possibili tipi di {@link it.uniroma1.lcl.studstats.dati.Rapporto Rapporto}
 */
public enum PossibiliRapporti implements TipoRapporto
{
	/**
	 * Tipo di rapporto collegato all'{@link it.uniroma1.lcl.studstats.dati.AnalizzatoreAnnoDiploma AnalizzatoreAnnoDiploma}
	 */
	ANNO_DIPLOMA,
	/**
	 * Tipo di rapporto collegato all'{@link it.uniroma1.lcl.studstats.dati.AnalizzatoreIstituti AnalizzatoreIstituti}
	 */
	ISTITUTI,
	/**
	 * Tipo di rapporto collegato all'{@link it.uniroma1.lcl.studstats.dati.AnalizzatoreSesso AnalizzatoreSesso}
	 */
	SESSO,
	/**
	 * Tipo di rapporto collegato all'{@link it.uniroma1.lcl.studstats.dati.AnalizzatoreVoto AnalizzatoreVoto}
	 */
	VOTO,
	/**
	 * Tipo di rapporto collegato all'{@link it.uniroma1.lcl.studstats.dati.AnalizzatoreTitoloDiStudio AnalizzatoreTitoloDiStudio}
	 */
	TITOLO,
	/**
	 * Tipo di rapporto collegato
	 * all'{@link it.uniroma1.lcl.studstats.dati.AnalizzatoreStudentiVotoMaggiore AnalizzatoreStudentiVotoMaggiore}
	 */
	STUDENTI_VOTO_MAGGIORE,
	/**
	 * Tipo di rapporto collegato all'{@link it.uniroma1.lcl.studstats.dati.AnalizzatoreBonus AnalizzatoreBonus}
	 */
	RAPPORTO_BONUS;
}
