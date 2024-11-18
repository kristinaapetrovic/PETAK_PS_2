/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.util.ArrayList;
import java.util.List;
import model.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Svira;

/**
 *
 * @author Korisnik
 */
public class DBBroker {

    public List<Osoba> vratiOsobe() {

        try {
            List<Osoba> lista = new ArrayList<>();

            String upit = "SELECT * FROM osoba";

            Statement st = Konekcija.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int jmbg = rs.getInt("jmbg");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                Osoba o = new Osoba(jmbg, ime, prezime);
                lista.add(o);

            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Svira> vratiSviraPremaOsobi(Osoba o) {
        try {
            List<Svira> lista = new ArrayList<>();

            String upit = "SELECT * FROM svira s JOIN osoba o ON o.jmbg=s.osoba JOIN instrument i on i.id=s.instrument WHERE osoba=" + o.getJmbg();

            Statement st = Konekcija.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int jmbg = rs.getInt("jmbg");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                Osoba osoba = new Osoba(jmbg, ime, prezime);

                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                Instrument i = new Instrument(id, naziv);

                Svira s = new Svira(osoba, i);
                lista.add(s);

            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Instrument> vratiInstrumente() {
        try {
            List<Instrument> lista = new ArrayList<>();

            String upit = "SELECT * FROM instrument";

            Statement st = Konekcija.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");

                Instrument i = new Instrument(id, naziv);
                lista.add(i);

            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean daLiSvira(Osoba o, Instrument i) {

        try {
            String upit = "SELECT * FROM svira WHERE osoba=" + o.getJmbg()+" AND instrument="+i.getId();

            Statement st = Konekcija.getInstance().getConnection().createStatement();

            ResultSet rs = st.executeQuery(upit);

            
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Osoba> vratiOsobeInstrument(Instrument i) {
        try {
            List<Osoba> lista=new ArrayList<>();
            
            String upit="SELECT * FROM svira s JOIN osoba o ON o.jmbg=s.osoba JOIN instrument i on i.id=s.instrument WHERE instrument=" + i.getId();
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int jmbg = rs.getInt("jmbg");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                Osoba osoba = new Osoba(jmbg, ime, prezime);
                lista.add(osoba);
            }
            
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean obrisiSviranje(Instrument odabrani, List<Osoba> lista) {
   
        try {
            String upit="DELETE FROM svira WHERE instrument=? AND osoba=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            
            for(int i=0;i<lista.size();i++){
                ps.setInt(1, odabrani.getId());
                ps.setInt(2, lista.get(i).getJmbg());
                
                ps.addBatch();
            }
            
            ps.executeBatch();
            Konekcija.getInstance().getConnection().commit();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }

    public int brojInstrumenata(Osoba o) {
  
        try {
            int broj=0;
            String upit="SELECT COUNT(*) FROM svira WHERE osoba="+o.getJmbg()+" GROUP BY osoba";
            Statement st=Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs=st.executeQuery(upit);
            
            if(rs.next()){
                broj=rs.getInt(1);
            }
            
            return broj;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

}
