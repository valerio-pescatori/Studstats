package it.uniroma1.lcl.studstats.dati;

import java.util.Collection;

/**
 * Interfaccia Analizzatore che fornisce due metodi:
 * {@link Analizzatore#generaRapporto(Collection)} che deve specificare come deve essere formato l'analizzatore
 * e {@link Analizzatore#getTipo()} che deve ritornare il tipo specifico di questo Analizzatore.
 * N.B.: il
 */
public interface Analizzatore
{
	/**
	 * Genera un {@link Rapporto} su una Collection di {@link Studente}
	 *
	 * @param studs la Collection sulla quale generare il rapporto
	 * @return il Rapporto.
	 */
	Rapporto generaRapporto(Collection<Studente> studs);

	/**
	 * Ritorna il tipo del Rapporto generato tramite il metodo {@link Analizzatore#generaRapporto(Collection) generaRapporto} <br>
	 * {@link TipoRapporto} è un interfaccia vuota;  la sua utilità è che con essa si può realizzare il design pattern
	 * dell'enum estensibile.<br>
	 * Cioè se si vuole un estendere un' enum, basta creare un'altra enum che estende la stessa interfaccia e
	 * dall'esterno di potrà accedere ai campi sia di una che dell'altra enum utilizzando un riferimento di tipo TipoRapporto.
	 */
	TipoRapporto getTipo();
}
