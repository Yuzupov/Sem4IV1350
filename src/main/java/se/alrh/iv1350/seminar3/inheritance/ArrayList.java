package se.alrh.iv1350.seminar3.inheritance;

import se.alrh.iv1350.seminar3.model.SaleObserver;
import java.util.*;

public class ArrayList {
    private static final int SIZE = 100;
    private ArrayList content = new ArrayList();
    int freeIndex = 0;

    public void add(Object element){
        content.add(element);
    }

    public void addAll(ArrayList list){
        content.addAll(list);
    }
}
