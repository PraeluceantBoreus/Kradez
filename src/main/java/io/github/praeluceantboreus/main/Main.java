package io.github.praeluceantboreus.main;

import io.github.praeluceantboreus.fetchers.LocalFetcher;

import java.io.File;

public class Main
{

	public static void main(String[] args)
	{
		System.out.println(LocalFetcher.fetch(new File("/home/richi/c00050.htm")));
	}

}
