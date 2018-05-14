package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;

/**
 * Interfaccia Analizzatore che fornisce due metodi:
 * {@link Analizzatore#generaRapporto generaRapporto} che deve specificare come deve essere formato l'analizzatore
 * e {@link Analizzatore#getTipo() getTipo} che deve ritornare il tipo specifico di questo Analizzatore.
 * N.B.: il
 */
public interface Analizzatore
{

	Rapporto generaRapporto(Collection<Studente> studs);


	/**
	 * Restituisce il tipo di rapporto che genera l’analizzatore
	 * NOTA BENE: questo metodo può essere implementato di default
	 * utilizzando le annotazioni che saranno viste a lezione
	 * venerdì (e, per la teledidattica e gli assenti, spiegate su
	 * diapositive) OPPURE può essere lasciato astratto e
	 * implementato in ciascuna sottoclasse (richiedendo la
	 * specifica in ciascuna implementazione di Analizzatore). In
	 * questo secondo caso non sara’ possibile utilizzare le lambda
	 * per implementare gli analizzatori base.
	 */
	TipoRapporto getTipo();

}
