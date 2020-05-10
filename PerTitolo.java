import java.util.*;
public class PerTitolo implements Comparator<Libro>
{
   public int compare(Libro l1, Libro l2)
   {
       return l1.getTitolo().compareTo(l2.getTitolo());
   }
}

