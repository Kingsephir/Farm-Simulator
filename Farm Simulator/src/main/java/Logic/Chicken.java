package Logic;

import java.awt.*;

public class Chicken extends Animal{
    public Chicken(Point initialLimits, Point finalLimits) {
        super(initialLimits, finalLimits);
    }

    @Override
    public AnimalTypes getAnimalType() {
        return null;
    }
}