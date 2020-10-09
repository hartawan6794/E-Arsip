package com.cameltech.easrip.Model;

import java.io.Serializable;

public class SuratKeluar implements Serializable {

    String no;
    String email;
    String instansi;
    String alamatInstansi;
    String lampiran;
    String perihal;
    String tanggal;
    String namaSurat;
    String statusValid;
    String deskripsi;
    String key;

    public SuratKeluar(){

    }
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getAlamatInstansi() {
        return alamatInstansi;
    }

    public void setAlamatInstansi(String alamatInstansi) {
        this.alamatInstansi = alamatInstansi;
    }


    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaSurat() {
        return namaSurat;
    }

    public void setNamaSurat(String namaSurat) {
        this.namaSurat = namaSurat;
    }

    public String getStatusValid() {
        return statusValid;
    }

    public void setStatusValid(String valid) {
        this.statusValid = valid;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SuratKeluar(String email,String no, String instansi, String alamatInstansi, String lampiran, String perihal, String tanggal,
                       String namaSurat, String valid, String deskripsi) {
        this.email = email;
        this.no = no;
        this.instansi = instansi;
        this.alamatInstansi = alamatInstansi;
        this.lampiran = lampiran;
        this.perihal = perihal;
        this.tanggal = tanggal;
        this.namaSurat = namaSurat;
        this.statusValid = valid;
        this.deskripsi = deskripsi;
    }
}
