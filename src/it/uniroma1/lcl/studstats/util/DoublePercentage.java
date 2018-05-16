package it.uniroma1.lcl.studstats.util;

import it.uniroma1.lcl.studstats.dati.AnalizzatoreBonus;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe che permette di trattare normalmente un double per poi calcolare la sua percentuale su un certo totale.<br>
 * Sono stati implementati solo i metodi necessari all'implementazione dell'
 * {@link AnalizzatoreBonus Analizzatore Bonus}, ma ovviamente si possono
 * aggiungere molti altri metodi e implementare varie interfacce per poter trattare gli oggetti di questa classe come fossero oggetti
 * della classe {@link Double}.
 *
 * @author Valerio Pescatori
 */
public class DoublePercentage extends Number
{
	private double num;

	/**
	 * Costruttore publico della classe, incapsula al suo interno l'intero preso in input.<br>
	 * Viene utilizzato {@link BigDecimal} per far sì che i valori abbiano al massimo 2 cifre decimali.
	 *
	 * @param num inter con il quale si vuole costruire l'oggetto.
	 */
	public DoublePercentage(double num)
	{
		this.num = num;
	}

	@Override
	public String toString() { return num + "%"; }

	/**
	 * incrementa di 1 il numero
	 */
	public void inc() { num++; }

	/**
	 * Permette di calcolare la percentuale del numero contenuto nell'oggetto relativamente a un certo totale.<br>
	 * Per far sì che la percentuale calcolata non abbia più di 2 cifre decimali viene utilizzata la classe {@link BigDecimal}.
	 *
	 * @param tot totale rispetto al quale calcolare la percentuale
	 */
	public void calculatePercent(int tot)
	{
		num = new BigDecimal((num / tot) * 100).setScale(2, RoundingMode.FLOOR).stripTrailingZeros().doubleValue();
	}

	@Override
	public int intValue() { return (int) num; }

	@Override
	public long longValue() { return (long) num; }

	@Override
	public float floatValue() { return (float) num; }

	@Override
	public double doubleValue() { return num; }
}
