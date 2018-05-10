package it.uniroma1.lcl.studstats.dati;

import java.util.Collection;

@FunctionalInterface
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
	/*
	default TipoRapporto getTipo()
	{
		da implementare
	}*/


	/* TODO:
	generaRapporti -> PER PRE-CHECKARE IL TIPO DI RITORNO DI UN ANALIZZATORE QUALSIASI AGGIUNGO L'ANNOTAZIONE @TIPORAPPORTO (?)
	COSI' NON DEVO ESEGUIRE IL METODO GENERARAPPORTO() PER CONTROLLARE POI IL TIPO DI RITORNO
	FACCIO UNA CLASSE/ENUM/INTERFACE CON CAMPI STATICI E GENERO GLI ANALIZZATORI CON LE LAMBDA (I PRIMI 4)
	PER L'ULTIMO ANALIZZATORE DEVO CREARE PER FORZA UNA CLASSE
	VEDI GLI STREAM PER IL SORTING DELLE MAPPE DEI RAPPORTI
	 */
}
