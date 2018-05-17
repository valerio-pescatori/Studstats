package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.dati.Analizzatore;
import it.uniroma1.lcl.studstats.dati.Rapporto;
import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.TipoRapporto;
import it.uniroma1.lcl.studstats.util.MyCsvParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Studstats implements AggregatoreStatistico
{
	private List<Studente> studList;
	private LinkedHashSet<Analizzatore> analizzatoreSet = new LinkedHashSet<>();

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
}
