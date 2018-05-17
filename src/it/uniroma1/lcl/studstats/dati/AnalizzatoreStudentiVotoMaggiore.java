package it.uniroma1.lcl.studstats.dati;

import it.uniroma1.lcl.studstats.dati.rapporti.PossibiliRapporti;
import it.uniroma1.lcl.studstats.util.AbstractAnalizzatore;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnalizzatoreStudentiVotoMaggiore extends AbstractAnalizzatore implements Analizzatore
{
	private int voto;
	private Analizzatore analizzatore;

	public AnalizzatoreStudentiVotoMaggiore(int voto) { this(voto, new AnalizzatoreSesso()); }

	public AnalizzatoreStudentiVotoMaggiore(int voto, Analizzatore analizzatore)
	{
		this.voto = voto;
		this.analizzatore = analizzatore;
	}

	@Override
	public Rapporto generaRapporto(Collection<Studente> studs)
	{
		return analizzatore.generaRapporto(studs.stream().
				filter(x -> Integer.parseInt(x.get("Voto")) >= voto).collect(Collectors.toSet()));
	}

	@Override
	public TipoRapporto getTipo()
	{
		return PossibiliRapporti.STUDENTI_VOTO_MAGGIORE;
	}

	@Override
	public int hashCode() {	return Objects.hash(analizzatore, voto); }

	@Override
	public boolean equals(Object obj)
	{
		AnalizzatoreStudentiVotoMaggiore an;
		if ( obj instanceof AnalizzatoreStudentiVotoMaggiore)
		{
			an = (AnalizzatoreStudentiVotoMaggiore) obj;
			return this.voto==an.voto && this.analizzatore.equals(an.analizzatore);
		}
		return false;
	}
}
