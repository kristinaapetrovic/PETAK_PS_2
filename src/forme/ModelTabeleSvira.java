/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Svira;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleSvira extends AbstractTableModel{

    List<Svira> lista=new ArrayList<>();
    String kolone[]={"Id", "Naziv"};
    public ModelTabeleSvira(List<Svira> lista) {
        this.lista = lista;
    }

    public List<Svira> getLista() {
        return lista;
    }
    
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Svira s=lista.get(rowIndex);
        
        switch(columnIndex){
            case 0: return s.getInstrument().getId();
            case 1: return s.getInstrument().getNaziv();
            default: return "n/a";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
}
