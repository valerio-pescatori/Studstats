package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.analizzatori.*;
import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.dati.rapporti.Rapporto;
import it.uniroma1.lcl.studstats.dati.rapporti.TipoRapporto;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Studstats implements AggregatoreStatistico
{
	private HashSet<Studente> studSet;
	private List<Analizzatore> listaAnalizzatori = new ArrayList<>();
	private List<Rapporto> listaRapporti = new ArrayList<>();

	private Studstats(HashSet<Studente> studSet) { this.studSet = studSet; }

	public static Studstats fromFile(String source) { return new Studstats(MyCsvParser.parse(Paths.get(source))); }

	public static Studstats fromFile(Path path) { return new Studstats(MyCsvParser.parse(path));}

	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for (Studente stud : studSet)
			s.append(stud.toString()).append("\n");
		return s.toString();
	}

	@Override
	public void add(Studente s) { studSet.add(s); }

	@Override
	public void add(Analizzatore an) { listaAnalizzatori.add(an); }

	@Override
	public List<Rapporto> generaRapporti(TipoRapporto... tipiRapporto)
	{
		List<Rapporto> toReturn = new ArrayList<>();
		if (tipiRapporto.length == 0)
		{
			for (Analizzatore an : listaAnalizzatori)
				toReturn.add(an.generaRapporto(studSet));
			return toReturn;
		}
		//check sugli analizzatori in tiporapporto (else implicito)
		Set<TipoRapporto> tipoRapportoArrayList = Set.of(tipiRapporto);
		for (Analizzatore an : listaAnalizzatori)
			if (tipoRapportoArrayList.contains(an.getTipo()))
				toReturn.add(an.generaRapporto(studSet));
		return toReturn;
	}

	@Override
	public int numeroAnalizzatori()
	{
		return listaAnalizzatori.size();
	}

	public static void main(String[] args)
	{
		Studstats stats1 = fromFile("IMMATRICOLATI_INFORMATICA_SAPIENZA_2018_randomizzato.csv");
		stats1.addAll(new AnalizzatoreVoto());
		System.out.println(stats1.generaRapporti(PossibiliRapporti.VOTO));
	}
}
