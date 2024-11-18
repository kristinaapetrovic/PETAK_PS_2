/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Osoba;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleOsoba extends AbstractTableModel {

    List<Osoba> lista=new ArrayList<>();
    String kolone[]={"Ime", "Prezime"};
    
    public ModelTabeleOsoba(List<Osoba> lista) {
        this.lista = lista;
    }

    public List<Osoba> getLista() {
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
        Osoba o=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return o.getIme();
            case 1: return o.getPrezime();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
}
