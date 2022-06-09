package guimaze;

import java.util.List;
import java.util.ArrayList;

public class Relation{
    public List<int[]> rel;

    public Relation(int[] a, int[] b){
        rel = new ArrayList<int[]>();
        rel.add(a);
        rel.add(b);
    }
}
