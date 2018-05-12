package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.util.Collection;

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
