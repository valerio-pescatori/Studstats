package it.uniroma1.lcl.studstats.analizzatori;

import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.RapportiAggiuntivi;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;
import it.uniroma1.lcl.studstats.util.DoublePercentage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Analizzatore bonus: per ogni titolo di studio, si suddivide il totale degli studenti (di quel titolo di studio) in 4 fasce a
 * seconda del voto:
 * <ul>
 * <li> voto < 70;
 * <li> 70 <= voto < 80;
 * <li> 80 <= voto < 90;
 * <li> voto > 90;
 * </ul>
 * Si trasforma poi il numero in percentuale, relativamente al totale di studenti per il titolo di studio associato.<br>
 * Per poter trattare il numero come fosse un normale double e poi essere trasformato in percentuale, viene utilizzata la classe
 * {@link DoublePercentage} creata appositamente per questo.
 * @author Valerio Pescatori
 */
public class AnalizzatoreBonus implements Analizzatore
{
	private final String SESSANTA = "voto < 70";
	private final String SETTANTA = "70 <= voto < 80";
	private final String OTTANTA = "80 <= voto < 90";
	private final String NOVANTA = "voto >= 90";

	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		HashMap<String, HashMap<String, DoublePercentage>> toReturn = new HashMap<>();
		/* Genero una mappa contente come chiave un titolo di studio e come
		 * argomento il totale degli studenti per quell'istituto, diviso per 4 fasce di voto
		 */
		for (Studente s : studs)
		{
			String titolo = s.get("Titolo Di Studio");
			toReturn.putIfAbsent(titolo, mapGen());
			int voto = Integer.parseInt(s.get("Voto"));
			if (voto < 70) toReturn.get(titolo).get(SESSANTA).inc();
			else if (voto < 80) toReturn.get(titolo).get(SETTANTA).inc();
			else if (voto < 90) toReturn.get(titolo).get(OTTANTA).inc();
			else toReturn.get(titolo).get(NOVANTA).inc();
		}
		/*
		 * sostituisco ai valori numerici, i valori in percentuale
		 */
		for (Map.Entry<String, HashMap<String, DoublePercentage>> istituti : toReturn.entrySet())
		{
			int totStudenti = 0;
			Set<Map.Entry<String, DoublePercentage>> fasce = istituti.getValue().entrySet();
			for (Map.Entry<String, DoublePercentage> fascia : fasce)
				totStudenti += fascia.getValue().toInt();

			for (Map.Entry<String, DoublePercentage> fascia : fasce)
				fascia.getValue().calculatePercent(totStudenti);
		}
		return new Rapporto<>(toReturn, getTipo());
	}

	private HashMap<String, DoublePercentage> mapGen()
	{
		HashMap<String, DoublePercentage> map = new HashMap<>(4);
		map.put(SESSANTA, new DoublePercentage(0));
		map.put(SETTANTA, new DoublePercentage(0));
		map.put(OTTANTA, new DoublePercentage(0));
		map.put(NOVANTA, new DoublePercentage(0));
		return map;
	}

	@Override
	public TipoRapporto getTipo()
	{
		return RapportiAggiuntivi.RAPPORTO_SEGRETO;
	}
}
