package guimaze;

import java.util.List;
import java.util.ArrayList;

public class Relation{
    /**
     * @author sam.fleming
     * @version 1
     *
     */
    public List<int[]> rel;

    public Relation(int[] a, int[] b){
        /**
         * Represent a relationship between two cells who have an open wall
         * @param a first cell
         * @param b second cell
         */
        rel = new ArrayList<int[]>();
        rel.add(a);
        rel.add(b);
    }

    public void printRel(){
        System.out.print("{ (" + this.rel.get(0)[0] + "," + this.rel.get(0)[1] + "), (" + this.rel.get(1)[0] + "," + this.rel.get(1)[1] + ") }, ");
    }
}
