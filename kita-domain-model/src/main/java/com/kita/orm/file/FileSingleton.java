package com.kita.orm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import com.kita.Person;
import com.kita.TournamentEvent;

/**
 * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
 *
 * @since 14.05.2018
 *
 */
public class FileSingleton {

	private String fileName = "data.kita";

	private static final class InstanceHolder {
		static final FileSingleton INSTANCE = new FileSingleton();
	}

	private FileSingleton() {
		initFile();
	}

	public static FileSingleton getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public void setFileName(String aFileName) {
		fileName = aFileName;
	}

	private String getFileName() {
		return fileName;
	}

	private void initFile() {
		File file = new File(getFileName());
		if (!file.exists()) {
			BigData bigData = BigData.newInstance();
			List<TournamentEvent> relayEvents = new ArrayList<>();
			bigData.setTournamentEvents(relayEvents);
			set(bigData);
		}
	}

	public void clear() {
		set(BigData.newInstance());
	}

	private void set(BigData aBigData) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(getFileName())) {
			SerializationUtils.serialize(aBigData, fileOutputStream);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException ", e);
		}
	}

	private BigData getBigData() {
		BigData bigData;
		try (FileInputStream fileInputStream = new FileInputStream(getFileName())) {
			bigData = SerializationUtils.deserialize(fileInputStream);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error - FileNotFoundException", e);
		} catch (IOException e) {
			throw new RuntimeException("Error - IOException", e);
		}
		return bigData;
	}

	public List<TournamentEvent> getTournamentEvents() {
		List<TournamentEvent> eventsAsList = new ArrayList<>();
		eventsAsList.addAll(getBigData().getTournamentEvents());
		return eventsAsList;
	}

	public void setTournamentEvents(List<TournamentEvent> someTournamentEvents) {
		BigData bigData = getBigData();
		bigData.setTournamentEvents(someTournamentEvents);
		set(bigData);
	}

	public List<Person> getPersons() {
		List<Person> personsAsList = new ArrayList<>();
		personsAsList.addAll(getBigData().getPersons());
		return personsAsList;
	}

	public void setPersons(List<Person> somePersons) {
		BigData bigData = getBigData();
		bigData.setPersons(somePersons);
		set(bigData);
	}

	public void reset() {
		File file = new File(getFileName());
		file.delete();
		initFile();
	}

}