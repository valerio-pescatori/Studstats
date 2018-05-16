package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.dati.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe di test
 * @author navigli
 *
 */
class StudstatsTest
{
	public static final String PERCORSO = "IMMATRICOLATI_INFORMATICA_SAPIENZA_2018_randomizzato.csv";

	private Studstats stats;

	@BeforeEach
	void setUp() throws Exception
	{
		stats = Studstats.fromFile(PERCORSO);
		//Studstats stats = Studstats.fromFile(Paths.get(PERCORSO));
		stats.addAll(Analizzatori.allBasic());
	}

	@Test
	void testAdd()
	{
		stats.add(new AnalizzatoreStudentiVotoMaggiore(80));
		stats.add(new AnalizzatoreStudentiVotoMaggiore(80,
				new AnalizzatoreSesso()));
		assertEquals(stats.numeroAnalizzatori(), 7);
	}

	@Test
	void testGeneraRapporti1()
	{
		System.out.println("=== testGeneraRapporti1 ===");
		for (Rapporto r : stats.generaRapporti())
			System.out.println(r);
	}

	@Test
	void testGeneraRapporti2()
	{
		System.out.println("=== testGeneraRapporti2 ===");
		Rapporto r = stats.generaRapporti(new AnalizzatoreSesso().getTipo())
				.get(0);
		System.out.println(r);
		assertEquals("{SESSO={F=26, M=288}}", r.toString());
	}

	@Test
	void testGeneraRapporti3()
	{
		System.out.println("=== testGeneraRapporti3 ===");
		// questa classe sara’ creata da me (createne una semplice)
		stats.add(new AnalizzatoreSegretoSegretissimo());
		for (Rapporto r : stats.generaRapporti(
				new AnalizzatoreVoto().getTipo(),
				new AnalizzatoreSegretoSegretissimo().getTipo()))
			System.out.println(r);
	}
}
