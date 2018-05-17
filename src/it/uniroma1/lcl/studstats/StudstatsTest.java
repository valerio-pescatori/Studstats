package it.uniroma1.lcl.studstats;

import it.uniroma1.lcl.studstats.dati.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe di test JUnit 5
 * @author navigli
 *
 */
class StudstatsTest
{
	public static final String PERCORSO = "IMMATRICOLATI_INFORMATICA_SAPIENZA_2018_randomizzato.csv";

	private Studstats stats;
	private int counter;

	@BeforeEach
	void setUp() throws Exception
	{
		if (counter++ % 2 == 0)
			stats = Studstats.fromFile(PERCORSO);
		else
			stats = Studstats.fromFile(Paths.get(PERCORSO));
	}

	@Test
	void testAdd()
	{
		System.out.print("-- testAdd: ");
		stats.addAll(Analizzatori.allBasic());
		stats.add(new AnalizzatoreStudentiVotoMaggiore(80));
		stats.add(new AnalizzatoreStudentiVotoMaggiore(70));
		try {
			assertEquals(stats.numeroAnalizzatori(), 7);
			System.out.println("OK!");
		} catch(Exception e) { System.out.println("NO"); }    }

	@Test
	void testEqualsHashCode()
	{
		System.out.print("-- testEqualsHashCode: ");
		stats.addAll(Analizzatori.allBasic());
		stats.add(new AnalizzatoreStudentiVotoMaggiore(80));
		stats.add(new AnalizzatoreStudentiVotoMaggiore(80, new AnalizzatoreSesso()));
		stats.add(new AnalizzatoreStudentiVotoMaggiore(80, new AnalizzatoreIstituti()));
		stats.add(new AnalizzatoreStudentiVotoMaggiore(70));
		try {
			assertEquals(stats.numeroAnalizzatori(), 8);
			System.out.println("OK!");
		} catch(Exception e) { System.out.println("NO"); }
	}

	@Test
	void testBonus()
	{
		System.out.print("-- testBonus: ");
		Optional<Analizzatore> bonus = Analizzatori.analizzatoreBonus();
		bonus.ifPresent(stats::add);
		System.out.print(stats.generaRapporti());
		System.out.println(" OK! [da verificare a mano]");
	}

	@Test
	void testGeneraRapportiBasic()
	{
		System.out.print("-- testGeneraRapportiBasic: ");
		stats.addAll(Analizzatori.allBasic());
		List<String> l = stats.generaRapporti()
				.stream()
				.map(Object::toString)
				.map(StudstatsTest::toMapString)
				.collect(Collectors.toList());
		List<String> l2 = List.of("{ANNI_DIPLOMA={2017=234, 2016=37, 2015=13, 2014=11, 2013=4, 2012=7, 2011=1, 2009=2, 2008=2, 2005=1, 2003=1, 2000=1}}",
				"{ISTITUTI={ALTRO=16, LABRIOLA=8, NETTUNO=7, MEUCCI=7, G. PEANO - MONTEROTONDO=7, PACINOTTI=7, =5, MORGAGNI=5, \"I.T.I.G.S. \"\"L. DA VINCI\"\"\"=5, AVOGADRO=5, ENRICO FERMI=4, NARNI LICEO SCIENTIFICO=4, PLINIO SENIORE=4, A. VOLTA (TIVOLI)=4, VITO VOLTERRA=4, NEWTON=4, FERRARIS=4, II=3, LICEO STATALE MARIA MONTESSORI=3, SANDRO PERTINI=3, RIGHI=3, E. FERMI=3, N. COPERNICO=3, XX - ROMA VIA TEANO, 223=3, ARMELLINI=3, VITERBO=3, \"L.S. \"\"EVANGELISTA TORRICELLI\"\"\"=3, TALETE=3, FRANCESCO D'ASSISI=3, ITI BARCELLONA COPERNICO=3, \"LICEO SCIENTIFICO-CLASSICO-SOCIOPEDAGOGICO \"\"LEONARDO DA VINCI\"\"\"=3, PASTEUR=3, \"I.T.I.S. \"\"G. MARCONI\"\"\"=3, IGNAZIO VIAN=3, ORAZIO=3, MAMIANI=2, GIORDANO BRUNO=2, VIA P. NENNI,48=2, LICEO ROCCI=2, J.VON NEUMANN=2, VITTORIO GASSMAN=2, BRUNO TOUSCHEK=2, VALLAURI=2, LIC.SCIENT. MAJORANA GUIDONIA=2, PACINOTTI - ARCHIMEDE=2, DEMOCRITO=2, \"LICEO SCIENTIFICO STATALE \"\"F. ENRIQUES\"\"\"=2, P. RUFFINI=2, LICEO STATALE ANTONIO MEUCCI=2, NOBEL=2, MAJORANA=2, FARADAY=2, MARCONI=2, VIA DEI GIOCHI ISTMICI - ROMA=2, G.CABOTO=2, G.DI VITTORIO=2, INNOCENZO XII=2, LICEO GAETANO DE SANCTIS=2, ROMA - VIA DELLA VASCA NAVALE, 58=2, F.DE PINEDO=2, G.VALLAURI=2, LEON BATTISTA ALBERTI=2, PAOLO BAFFI=2, BIAGIO PASCAL=2, CATTANEO CARLO=1, VITTORIA COLONNA=1, NOMENTANO=1, S.MARIA=1, \"S.S. \"\"DANTE ALIGHIERI\"\" FIUGGI\"=1, LEONARDO MURIALDO=1, M.IMMACOLATA=1, PIRANDELLO=1, LIVIA BOTTARDI=1, GIULIO CESARE=1, L.B.ALBERTI=1, LICEO GINNASIO STATALE VIRGILIO=1, SERAPHICUM=1, CRISTOFORO COLOMBO=1, G. FILANGIERI=1, ITC L.DE LIBERO=1, PIAZZA RISORGIMENTO, 1 SEGNI=1, \"ISTITUTO TECNICO INDUSTRIALE \"\"XX\"\"\"=1, FEDERICO CAFFE'=1, BENEDETTO CROCE=1, PIERO GOBETTI=1, LICEO LINGUISTICO F. HEGEL=1, VIA CASSIA, 931=1, \"\"\"GALILEO GALILEI\"\" PONTECORVO\"=1, MATTEUCCI=1, LICEO SCIENTIFICO GHILARZA=1, I.I.S. 'PIETRO BONFANTE'=1, \"\"\"PRIMO LEVI\"\"\"=1, DE LORENZO=1, G.PIAZZI=1, LICEO SCIENTIFICO STATALE XXIII=1, ETTORE MAJORANA=1, TULLIO LEVI-CIVITA D.P.R.=1, COLONNA=1, PAOLO SEGNERI=1, IMMANUEL KANT=1, \"CONVITTO NAZIONALE  \"\"Vittorio Emanuele II\"\"\"=1, AZZARITA=1, GALILEI - CIVITAVECCHIA=1, PANTALEONI=1, DANTE ALIGHIERI=1, \"\"\"A. VOLTA\"\"\"=1, GEORGE BOOLE=1, A. VOLTA GUIDONIA=1, \"LICEO CLASSICO \"\"LUCREZIO CARO\"\"\"=1, GIORGIO DE CHIRICO=1, EUGENIO MONTALE=1, G.MARCONI=1, TERNI FEDERICO CESI=1, LIC. SCIENT. SAVIANO=1, G. GALILEI=1, A. VOLTA=1, ARCHIMEDE=1, RONCIGLIONE=1, A. CALAMO - OSTUNI -=1, \"ISTITUTO TECNICO COMMERCIALE \"\"ARANGIO RUIZ\"\"\"=1, LICEO CLASSICO - PRAIA A MARE=1, TERNI R. DONATELLI=1, POLO LICEALE CISTERNA DI LATINA=1, A. LANDI=1, GIOVANNI PAOLO II=1, ISTITUTO TECNICO COMMERCIALE 'XXIII'=1, LICEO SCIENTIFICO PARITARIO F. HEGEL=1, LICEO SCIENTIFICO=1, LEONARDO DA VINCI=1, ITIS E LICEO SCIENT TECN  G  MARCONI=1, BLAISE PASCAL=1, ISTITUTO TALETE=1, \"I.S.S. \"\"GIUSEPPE COLASANTI\"\" (MATURITA' SCIENTIFICA)\"=1, \"\"\"GALILEI\"\" - TRENTO\"=1, \"LIC.CL.ANNESSO CONV.NAZ.\"\"V.EMANUELE II\"\"\"=1, LIC. SCIENT. A INDIRIZZO LINGUISTICO=1, CARLO JUCCI=1, VIVONA=1, \"L. C. NORCIA\"\" SEDE AGGR.\"=1, PIO IX=1, PEANO (ROMA)=1, IPSAR VINCENZO GIOBERTI=1, LICEO SCIENTIFICO DI ZAGAROLO=1, GIORGI=1, LATINA=1, \"LIC.SCIENT.\"\"ZALEUCO\"\" LOCRI\"=1, P.L. NERVI VALMONTONE=1, JOHANN VON NEUMANN=1, LICEO SCIENT. VIA ALBERGOTTI=1, LAZZARO SPALLANZANI=1, CARLO E NELLO ROSSELLI=1, ANIENE=1, GIOVANNI SULPICIO=1, \"I.T.COMMERCIALE \"\" L.PILLA\"\"\"=1, GIOACCHINO PELLECCHIA=1, LUIGI SICILIANI=1, TERNI L. ALLIEVI=1, \"ITC STATALE \"\"VINCENZO ARANGIO RUIZ\"\"\"=1, MALPIGHI=1, PACIFICI E DE MAGISTRIS=1, C. BATTISTI=1, \"I.T.COMM.\"\"L.PACIOLI\"\" CASSANO I.\"=1, RENATO CARTESIO=1, VIA SALVINI, 24=1, G. B. GRASSI=1, BERTRAND RUSSELL=1}}",
				"{SESSO={F=26, M=288}}",
				"{TITOLO={SCIENTIFICO=150, PERITO INDUSTRIALE CAPOTECNICO - specializzazione INFORMATICA=68, CLASSICO=22, RAGIONIERE PERITO COMMERCIALE E PROGRAMMATORE=13, TECNICO INDUSTRIALE (GENERICO)=7, LINGUISTICO=7, PERITO INDUSTRIALE CAPOTECNICO - specializzazione ELETTRONICA E TELECOMUNICAZIONE=5, TITOLO DI STUDIO STRANIERO=5, TECNICO (GENERICO)=5, PERITO INDUSTRIALE CAPOTECNICO - specializzazione ELETTROTECNICA E AUTOMAZIONE=4, RAGIONIERE E PERITO COMMERCIALE=4, TECNICO COMMERCIALE (GENERICO)=3, PERITO PER IL TURISMO=2, TECNICO GESTIONE AZIENDALE INFORMATICA=2, GEOMETRA=2, NAUTICO (GENERICO)=1, SPERIMENTALE GRAFICO VISIVO=1, LICEO ARTISTICO - DURATA QUINQUENNALE=1, AREONAUTICO (GENERICO)=1, ASPIRANTE ALLA DIREZIONE DI MACCHINE DI NAVI MERCANTILI=1, MATURITA' DI LICEO SOCIO-PSICO-PEDAGOGICO=1, PERITO AERONAUTICO SPECIALIZZATO: ASSISTENTE DI NAVIGAZIONE AEREA=1, TECNICO CINEMATOGRAFIA E TELEVISIONE=1, TECNICO DELLE INDUSTRIE ELETTRICHE  ED ELETTRONICHE=1, PROFESSIONALE (GENERICO)=1, TECNICO SERVIZI SOCIALI=1, TECNICO DEI SERVIZI DELLA RISTORAZIONE=1, TECNICO CHIMICO E BIOLOGICO=1, PERITO AZIENDALE E CORRISPONDENTE IN LINGUE ESTERE=1, SPERIMENTALE MUSICALE=1}}",
				"{VOTO={VOTO_MEDIO=76.81, VOTO_MAX=101, VOTO_MIN=60, VOTO_MEDIANO=75}}")
				.stream()
				.map(StudstatsTest::toMapString)
				.collect(Collectors.toList());
		try {
			assertEquals(l, l2);
			System.out.println("OK!");
		} catch(Exception e) { System.out.println("NO"); }
	}

	@Test
	void testGeneraRapportiTipoSesso()
	{
		stats.addAll(Analizzatori.allBasic());

		System.out.print("-- testGeneraRapportiTipoSesso: ");
		Rapporto r = stats.generaRapporti(new AnalizzatoreSesso().getTipo())
				.get(0);

		try {
			assertEquals(toMap(r.toString()).toString(), "{SESSO={F=26, M=288}}");
			System.out.println("OK!");
		} catch(Exception e) { System.out.println("NO"); }
	}

	@Test
	void testGeneraRapportiSegretoSegretissimo()
	{
		System.out.print("-- testGeneraRapportiSegretoSegretissimo: ");
		stats.addAll(Analizzatori.allBasic());
		stats.add(new AnalizzatoreSegretoSegretissimo());
		for (Rapporto r : stats.generaRapporti(new AnalizzatoreVoto().getTipo(), new AnalizzatoreSegretoSegretissimo().getTipo()))
			System.out.print(r+"; ");
		System.out.println(" OK! [da verificare a mano]");
	}

	static String toMapString(String mappa)
	{
		return toMap(mappa).toString();
	}

	static Map<String, Map<String, String>> toMap(String mappa)
	{
		Pattern re = Pattern.compile("([A-Z_]+) *= *\\{([^\\}]+)\\}");
		Matcher m = re.matcher(mappa);
		Pattern re2 = Pattern.compile("([a-zA-Z0-9-]+) *= *([^,]+)");

		Map<String, Map<String, String>> result = new TreeMap<>();

		while(m.find())
		{
			String key = m.group(1);
			String value = m.group(2);
			Matcher m2 = re2.matcher(value);
			Map<String, String> valueMap = new TreeMap<>();
			while(m2.find())
				valueMap.put(m2.group(1), m2.group(2));
			result.put(key, valueMap);
		}

		return result;
	}
}
