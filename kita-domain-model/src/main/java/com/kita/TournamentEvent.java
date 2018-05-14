package com.kita;

import java.io.Serializable;
import java.util.UUID;

import com.kita.attributes.EventDay;
import com.kita.attributes.Eventname;

/**
 * @author schmollc
 * @since 08.03.2018
 *
 */
public class TournamentEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private Eventname name = Eventname.newInstance();
	private EventDay eventDay = EventDay.today();

	private TournamentEvent() {
		uuid = UUID.randomUUID();
	}

	private TournamentEvent(Eventname anEventName, EventDay anEventDay) {
		uuid = UUID.randomUUID();
		name = anEventName;
		eventDay = anEventDay;
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

	public EventDay getEventDay() {
		return eventDay;
	}

	public void setEventDay(EventDay day) {
		eventDay = day;
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
		return name + ", " + eventDay;
	}
}