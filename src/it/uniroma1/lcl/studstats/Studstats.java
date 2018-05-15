package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.analizzatori.*;
import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.RapportiAggiuntivi;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;
import it.uniroma1.lcl.studstats.util.MyCsvParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Studstats implements AggregatoreStatistico
{
	private List<Studente> studList;
	private List<Analizzatore> listaAnalizzatori = new ArrayList<>();

	private Studstats(List<Studente> studList) { this.studList = studList; }

	public static Studstats fromFile(String source) { return new Studstats(MyCsvParser.parse(Paths.get(source))); }

	public static Studstats fromFile(Path path) { return new Studstats(MyCsvParser.parse(path));}

	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for (Studente stud : studList)
			s.append(stud.toString()).append("\n");
		return s.toString();
	}

	@Override
	public void add(Studente s) { studList.add(s); }

	@Override
	public void add(Analizzatore an) { listaAnalizzatori.add(an); }

	@Override
	public List<Rapporto> generaRapporti(TipoRapporto... tipiRapporto)
	{
		List<Rapporto> toReturn = new ArrayList<>();
		if (tipiRapporto.length == 0)
		{
			for (Analizzatore an : listaAnalizzatori)
				toReturn.add(an.generaRapporto(studList));
			return toReturn;
		}
		//check sugli analizzatori in tiporapporto (else implicito)
		Set<TipoRapporto> tipoRapportoSet = Set.of(tipiRapporto);
		for (Analizzatore an : listaAnalizzatori)
			if (tipoRapportoSet.contains(an.getTipo()))
				toReturn.add(an.generaRapporto(studList));
		return toReturn;
	}

	@Override
	public int numeroAnalizzatori()
	{
		return listaAnalizzatori.size();
	}

	public static void main(String[] args)
	{
		Studstats stats1 = fromFile("src/dasd.csv");
		stats1.addAll(new AnalizzatoreBonus());
		List<Rapporto> res = stats1.generaRapporti(RapportiAggiuntivi.RAPPORTO_SEGRETO);
		res.forEach(System.out::println);
	}
}
