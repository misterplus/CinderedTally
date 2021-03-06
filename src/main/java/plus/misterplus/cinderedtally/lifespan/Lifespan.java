package plus.misterplus.cinderedtally.lifespan;

public class Lifespan {
    /**
     * The value of a lifespan, in real life seconds.
     */
    private final int value;

    /**
     * @param value The value of a lifespan, in in-game ticks.
     */
    public Lifespan(int value) {
        this.value = value / 20;
    }
}
