package com.kita;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import com.kita.attributes.EventDay;
import com.kita.attributes.Eventname;
import com.kita.attributes.TournamentStatus;

/**
 * @since 13.05.2018
 *
 */
public class TournamentEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private Eventname name = Eventname.newInstance();
	private EventDay day = EventDay.today();
	private Collection<Participant> participants = new HashSet<>();
	// TODO -small- Diskutieren: Wenn ich nun noch die Liste mit den Teams hier aufnehme
	// dann habe ich ja die Participants doppelt drin! Einmal als Participant und einmal als
	// Team. Mmm.. Müssen wir hier auch nocheinmal den Member einführen? Denn ich kann als Participant
	// ggf in zwei Teams sein?
	// Wäre allerdings in diesem Fall nicht wirklich schön... und wenn ja auch nur EIN Sonderfall, da bei ZWEI
	// Fällen man diese beiden Teams zusammenlegen könnte und somit die beiden Sonderfälle wegfallen werden.

	private boolean active;

	private TournamentStatus status = TournamentStatus.OPEN;

	private TournamentEvent() {
		uuid = UUID.randomUUID();
	}

	private TournamentEvent(Eventname aName, EventDay aDay) {
		uuid = UUID.randomUUID();
		name = aName;
		day = aDay;
	}

	public static TournamentEvent newInstance() {
		return new TournamentEvent();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static TournamentEvent newInstance(Eventname anEventName, EventDay anEventDay) {
		return new TournamentEvent(anEventName, anEventDay);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID anUuid) {
		uuid = anUuid;
	}

	public Eventname getName() {
		return name;
	}

	public void setName(Eventname eventName) {
		name = eventName;
	}

	public EventDay getDay() {
		return day;
	}

	public void setDay(EventDay aDay) {
		day = aDay;
	}

	public void setActive(boolean anActiveFlag) {
		active = anActiveFlag;
	}

	public boolean isActive() {
		return active;
	}

	public TournamentStatus getStatus() {
		return status;
	}

	public void start() {
		status = TournamentStatus.RUNNING;
	}

	public void close() {
		status = TournamentStatus.CLOSED;
	}

	public void addParticipant(Participant aParticipant) {
		participants.add(aParticipant);
	}

	public void removeParticipant(Participant aParticipant) {
		participants.remove(aParticipant);
	}

	public Collection<Participant> getParticipants() {
		return Collections.unmodifiableCollection(participants);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TournamentEvent other = (TournamentEvent) obj;
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name + ", " + day;
	}

	/**
	 * TODO -small- Diskussion: Ich möchte eigentlich die Logik mit dem Status gerne über die Methoden
	 * start/close abbilden. Es soll also kein Getter geben.
	 * Wenn ich allerdings persistiere benötige ich allerdings zugriff auf die Werte um target->source machen zu können
	 *
	 * Eine Idee: Der Mapper ist im gleichen package und das Attribut ist package Protected
	 * Oder mal ganz was anderes: TournamentPOJO hat "nur" dumme Attribute und getter/setter.
	 * Dieses wird ins TournamentEvent Domain Objekt mit genau einem get/set gepackt .
	 * Das Tournemant Domain Objekt dient dann als Decorator nach aussen....
	 *
	 */
	public void setTournamentStatus(TournamentStatus aStatus) {
		status = aStatus;
	}

	public TournamentStatus getTournamentStatus() {
		return status;
	}

	public boolean isOpen() {
		return status == TournamentStatus.OPEN;
	}
}