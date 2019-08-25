/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recipe;

/**
 *
 * @author Bernd
 */
public class Ingredient {
    private String name;
    private float amount;
    private String unit;
    private String state;

    public Ingredient(String name, float amount, String unit, String state) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.state = state;
    }
    
    public Ingredient(String name, float amount, String unit) {
        this(name,amount,unit,"");
    }
    
    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }
    
    public String getState() {
        return state;
    }
    
    @Override
    public String toString() {
        return amount + " " + unit + " " + name + " " + state;
    }
}
