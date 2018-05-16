package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.dati.*;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.util.MyCsvParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Studstats implements AggregatoreStatistico
{
	private List<Studente> studList;
	private Set<Analizzatore> analizzatoreSet = new HashSet<>();

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
	public void add(Analizzatore an) { analizzatoreSet.add(an); }

	@Override
	public List<Rapporto> generaRapporti(TipoRapporto... tipiRapporto)
	{
		List<Rapporto> toReturn = new ArrayList<>();
		if (tipiRapporto.length == 0)
		{
			for (Analizzatore an : analizzatoreSet)
				toReturn.add(an.generaRapporto(studList));
			return toReturn;
		}
		//check sugli analizzatori in tiporapporto (else implicito)
		List<TipoRapporto> tipoRapportoList = List.of(tipiRapporto);
		for (Analizzatore an : analizzatoreSet)
			if (tipoRapportoList.contains(an.getTipo())) toReturn.add(an.generaRapporto(studList));
		return toReturn;
	}

	@Override
	public int numeroAnalizzatori()
	{
		return analizzatoreSet.size();
	}

	public static void main(String[] args)
	{
		Studstats s1 = fromFile("IMMATRICOLATI_INFORMATICA_SAPIENZA_2018_randomizzato.csv");
		s1.addAll(Analizzatori.allBasic());
		List<Rapporto> rapportoList= s1.generaRapporti(PossibiliRapporti.values());
		rapportoList.forEach(System.out::println);

	}
}
