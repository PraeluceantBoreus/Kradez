package io.github.praeluceantboreus.translators;

import java.util.ArrayList;

import io.github.praeluceantboreus.units.Translateable;

public interface Translator<T extends Translateable>
{
	public T generate(ArrayList<String> lines);
}
