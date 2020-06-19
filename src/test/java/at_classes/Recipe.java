package at_classes;

import java.util.Arrays;

public class Recipe {
    public String recipename;
    public Ingredient[] ingredlist;
    public int preptime;

    public Recipe(String recipename, Ingredient[] ingredlist, int preptime) {
        this.recipename = recipename;
        this.ingredlist = ingredlist;
        this.preptime = preptime;
    }

    public Recipe() {
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredlist=" + Arrays.toString(ingredlist) +
                '}';
    }
}
