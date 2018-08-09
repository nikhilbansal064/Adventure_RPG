package player;//class represent a superpower

import java.io.Serializable;

//class represent superPower
public class SuperPower implements Serializable {

    private  String name;
    private PowerCategory category;

    public SuperPower(String name, PowerCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public  enum PowerCategory{
        LOW(1), MEDIUM(2), HIGH(3);

       PowerCategory(int damageLevel) {
       }

   }
}
