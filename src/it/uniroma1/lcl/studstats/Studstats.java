package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.dati.Analizzatore;
import it.uniroma1.lcl.studstats.dati.Rapporto;
import it.uniroma1.lcl.studstats.dati.Studente;
import it.uniroma1.lcl.studstats.dati.TipoRapporto;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Studstats implements AggregatoreStatistico
{
	private HashSet<Studente> studSet;
	private List<Analizzatore> listaAnalizzatori = new ArrayList<>();
	private List<Rapporto> listaRapporti = new ArrayList<>();

	private Studstats(HashSet<Studente> studSet) { this.studSet = studSet; }

	public static Studstats fromFile(String source) { return new Studstats(MyCsvParser.parse(Paths.get(source))); }

	public static Studstats fromFile(Path path) { return new Studstats(MyCsvParser.parse(path));}

	public static void main(String[] args)
	{
		Studstats stats1 = fromFile("src/IMMATRICOLATI.csv");
		System.out.println(stats1);
	}

	@Override
	public String toString()
	{
		String s = "";
		for (Studente stud : studSet)
			s += stud.toString() + "\n";
		return s;
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


	}
}
