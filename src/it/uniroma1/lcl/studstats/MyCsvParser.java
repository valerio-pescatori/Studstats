package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.dati.Studente;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe parser che con il metodo statico {@link MyCsvParser#parse(Path, String) parse} permette di parsare un documento *.csv
 * contenente informazioni sugli studenti immatricolati
 *
 * @author Valerio Pescatori
 */
public class MyCsvParser
{
	/**
	 * Equivale ad invocare {@link MyCsvParser#parse(Path, String) MyCsvParser.parse} utilizzando come separatore dei campi ';'.
	 *
	 * @param p il {@link Path} del documento
	 * @return l'insieme degli studenti se il documento è valido, {@code null} altrimenti.
	 */
	public static HashSet<Studente> parse(Path p)
	{
		return parse(p, ";");
	}

	/**
	 * Esegue il parsing del documento che contiene le informazioni sugli studenti, generando un {@link HashSet} contenente oggetti di tipo {@link Studente}.
	 *
	 * @param p   il {@link Path} del documento
	 * @param sep il separatore dei campi nel documento
	 * @return l'insieme degli studenti se il documento è valido, {@code null} altrimenti.
	 */
	public static HashSet<Studente> parse(Path p, String sep)
	{
		try(BufferedReader fileReader = Files.newBufferedReader(p))
		{
			String firstLine = "";
			ArrayList<String> keys = new ArrayList<>();
			HashSet<Studente> toRet = new HashSet<>();

			/* mi genero un array contenente le chiavi della mappa di ogni studente
			   in questo modo la mappa di ogni studente avrà tante chiavi quanti sono i campi della tabella */
			if (fileReader.ready())
				firstLine = fileReader.readLine();
			String[] values = firstLine.split(sep);
			for (String value : values)
				keys.add(value);
			keyNormalizer(keys);
			while (fileReader.ready())
			{
				/* per ogni studente associo alla chiave il rispettivo valore */
				String[] studValues = fileReader.readLine().split(sep);
				Studente stud = new Studente();
				for (int i = 0; i < studValues.length; i++)
				{
					stud.put(keys.get(i), studValues[i]);
				}
				// aggiungo poi lo studente all'insieme degli studenti da ritornare
				toRet.add(stud);
			}
			return toRet;
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Normalizza le stringhe nell'array, esempio:
	 * <blockquote>
	 * {@code "COMUNE_ISTITUTO"} viene trasformato in {@code "Comune Istituto"}
	 * </blockquote>
	 *
	 * @param array array delle stringhe da normalizzare
	 */
	private static void keyNormalizer(ArrayList<String> array)
	{
		for (int i = 0; i < array.size(); i++)
		{
			if (array.get(i).toLowerCase().contains("voto"))
				array.set(i, "Voto");
			else
			{
				String s = array.get(i).toLowerCase();
				String[] sarr = s.split("_");
				for (int c = 0; c < sarr.length; c++)
				{
					sarr[c] = sarr[c].replaceFirst(sarr[c].substring(0, 1), sarr[c].substring(0, 1).toUpperCase());
				}
				StringBuilder news = new StringBuilder();
				for (String s2 : sarr)
					news.append(s2).append(" ");
				array.set(i, news.toString().trim());
			}
		}
	}
}
