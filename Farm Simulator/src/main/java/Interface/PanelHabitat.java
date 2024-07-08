package Interface;

import Logic.*;

import javax.swing.*;
import java.awt.*;

public class PanelHabitat extends JPanel {
    private static boolean resized = false;
    private Habitat habitat;
    private HitboxAnimal hitboxAnimal = null;
    private HitboxDeposit hitboxDeposit;
    public PanelHabitat(){
        setOpaque(false);
        habitat = new Habitat();
        habitat.setHitboxDeposit(new HitboxDeposit(this.getLocationOfHabitat()));
    }

    public Habitat getLogicHabitat(){
        return habitat;
    }

    public Point getLocationOfHabitat(){
        return this.getLocation();
    }

    public Point getBottomRightLocationOfHabitat(){
        Dimension size = this.getSize();
        return new Point((int)(this.getLocationOfHabitat().getX() + size.getWidth() - 1) , (int)(this.getLocationOfHabitat().getY() + size.getHeight() - 1));
    }

    public void updateAnimalMovements(){
        for (int j = 0 ; j <  getLogicHabitat().getAnimalsInTheHabitat().size(); j++) {
            getLogicHabitat().getAnimalsInTheHabitat().get(j).randomMovement();
        }
    }

    public void setHitbox(){
        for (int j = 0; j < habitat.getAnimalsInTheHabitat().size(); j++) {
            Point location = habitat.getAnimalsInTheHabitat().get(j).getLocation();
            if(habitat.getAnimalsInTheHabitat().get(j).getHitboxAnimal() == null) {
                this.hitboxAnimal = new HitboxAnimal(location);
                habitat.getAnimalsInTheHabitat().get(j).setHitboxAnimal(this.hitboxAnimal);
                PanelGame.getInstance().cursorIsOnHitbox(this.hitboxAnimal, habitat, habitat.getAnimalsInTheHabitat().get(j));
            }
            else{
                habitat.getAnimalsInTheHabitat().get(j).getHitboxAnimal().updateHitbox(location);
            }
        }
    }

    public void paintFoodDeposits(Graphics g) {
        Point location = this.getLocationOfHabitat();
        g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("DepositoComida.png")).getImage(), (int) location.getX() +3, (int) location.getY() +118, null);
    }

    public void paintAnimals(Graphics g){
        for (int j = 0; j < habitat.getAnimalsInTheHabitat().size(); j++) {
            Point location = habitat.getAnimalsInTheHabitat().get(j).getLocation();
            switch (habitat.getHabitatType()) {
                case VACAS -> g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("Vaca.png")).getImage(),
                        (int) location.getX(),
                        (int) location.getY(),
                        null);
                case CERDO -> g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("Cerdo.png")).getImage(), (int) location.getX(), (int) location.getY(), null);
                case GALLINA -> g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("Pollo.png")).getImage(), (int) location.getX(), (int) location.getY(), null);
                case CABRA -> g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("Cabra.png")).getImage(), (int) location.getX(), (int) location.getY(), null);
                case OVEJA -> g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("Oveja.png")).getImage(), (int) location.getX(), (int) location.getY(), null);
                case CABALLO -> g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("Caballo.png")).getImage(), (int) location.getX(), (int) location.getY(), null);
            }
        }
    }

}