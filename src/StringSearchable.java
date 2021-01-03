import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import  com.jidesoft.swing.*;

public class StringSearchable implements Searchable<String,String>{



    private List<String> cards = new ArrayList<String>();



    /**

     * Constructs a new object based upon the parameter terms.

     * @param cards The inventory of terms to search.

     */

    public StringSearchable(List<String> cards){

        this.cards.addAll(cards);

    }



    @Override

    public Collection<String> search(String value) {

        List<String> founds = new ArrayList<String>();



        for ( String s : cards ){

            if ( s.indexOf(value) == 0 ){

                founds.add(s);

            }

        }

        return founds;

    }



}



