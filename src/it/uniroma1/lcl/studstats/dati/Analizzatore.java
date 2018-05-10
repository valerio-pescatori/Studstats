package it.uniroma1.lcl.studstats.dati;

import java.util.Collection;

@FunctionalInterface
public interface Analizzatore
{
	Rapporto generaRapporto(Collection<Studente> studs);

	/* TODO:
	generaRapporti -> PER PRE-CHECKARE IL TIPO DI RITORNO DI UN ANALIZZATORE QUALSIASI AGGIUNGO L'ANNOTAZIONE @TIPORAPPORTO (?)
	COSI' NON DEVO ESEGUIRE IL METODO GENERARAPPORTO() PER CONTROLLARE POI IL TIPO DI RITORNO
	FACCIO UNA CLASSE/ENUM/INTERFACE CON CAMPI STATICI E GENERO GLI ANALIZZATORI CON LE LAMBDA (I PRIMI 4)
	PER L'ULTIMO ANALIZZATORE DEVO CREARE PER FORZA UNA CLASSE
	VEDI GLI STREAM PER IL SORTING DELLE MAPPE DEI RAPPORTI
	 */
}
