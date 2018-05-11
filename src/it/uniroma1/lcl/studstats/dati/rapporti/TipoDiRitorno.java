package it.uniroma1.lcl.studstats.dati.rapporti;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TipoDiRitorno
{
	String value();
}
