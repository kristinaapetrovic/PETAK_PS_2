/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.List;
import model.Instrument;
import model.Osoba;
import model.Svira;

/**
 *
 * @author Korisnik
 */
public class Controller {
    private static Controller instance;

    
    private DBBroker dbb=new DBBroker();
    
    private Controller() {
    }

    public static Controller getInstance() {
        if(instance==null) instance=new Controller();
        return instance;
    }

    public List<Osoba> vratiOsobe() {
       return dbb.vratiOsobe();
    }

    public List<Svira> vratiSviraPremaOsobi(Osoba o) {
        return dbb.vratiSviraPremaOsobi(o);
    }

    public List<Instrument> vratiInstrumente() {
        return dbb.vratiInstrumente();
    }

    public boolean daLiSvira(Osoba o, Instrument i) {
        return dbb.daLiSvira(o,i);
    }

    public List<Osoba> vratiOsobeInstrument(Instrument i) {
        return dbb.vratiOsobeInstrument(i);
    }

    public boolean obrisiSviranje(Instrument odabrani, List<Osoba> lista) {
        return dbb.obrisiSviranje(odabrani, lista);
    }

    public int brojInstrumenata(Osoba o) {
        return dbb.brojInstrumenata(o);
    }

    
   
    
    
}
