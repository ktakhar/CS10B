class IllegalElevationException extends Exception {
    public IllegalElevationException(String message) {
        super(message);
    }
}

class Mountains {
    private String name;
    private int elevation;
    private String location;

    public Mountains(String name, int elevation, String location) throws IllegalElevationException {
        if (elevation < 0) {
            throw new IllegalElevationException(String.format("elevation: %d", elevation));
        }
        this.name = name;
        this.elevation = elevation;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public int getElevation() {
        return this.elevation;
    }

    public String getLocation() {
        return this.location;
    }

    public String toString() {
        return String.format("Name: %s\nElevation: %d\nLocation: %s", this.name, this.elevation, this.location);
    }
}

class HasSnow extends Mountains {
    protected boolean hasSnow;

    public HasSnow(String name, int elevation, String location, boolean hasSnow) throws IllegalElevationException {
        super(name, elevation, location);
        this.hasSnow = hasSnow;
    }
}

