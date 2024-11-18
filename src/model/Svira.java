/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class Svira {
    private Osoba osoba;
    private Instrument instrument;

    public Svira() {
    }

    public Svira(Osoba osoba, Instrument instrument) {
        this.osoba = osoba;
        this.instrument = instrument;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Svira other = (Svira) obj;
        if (!Objects.equals(this.osoba, other.osoba)) {
            return false;
        }
        return Objects.equals(this.instrument, other.instrument);
    }
    
}
