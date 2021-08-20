package ru.kuryakin.meteo.models.weather;

/**
 * Возможные местоположения.
 */
public enum Location {
    STREET("street"),
    BALCONY("balcony"),
    ROOM("room");

    private final String name;

    Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
