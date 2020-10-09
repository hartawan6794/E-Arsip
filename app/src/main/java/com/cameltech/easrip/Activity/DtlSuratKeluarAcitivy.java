package com.cameltech.easrip.Activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cameltech.easrip.Adapter.AdapterDaftarSuratKeluar;
import com.cameltech.easrip.Adapter.AdapterDaftarSuratKeluarPengguna;
import com.cameltech.easrip.Model.SuratKeluar;
import com.cameltech.easrip.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.HashMap;

public class DtlSuratKeluarAcitivy extends AppCompatActivity {

    private static final String TAG = "DtlSuratKeluarAcitivy";
    TextView tvNama, tvStatus, tvNo, tvTgl, tvDeskripsi, tvBoxTempatArsip, tvInstansi, edStatus, tvMail;
    Button downld, accArsip;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    ActionBar actionBar;
    String dataPennguna, dataAdmin;
    String getMail;
    private String hasil, getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtl_surat_keluar_acitivy);
        initToolbars();
        tvNama = (TextView) findViewById(R.id.tvNmaDokumen);
        tvNo = (TextView) findViewById(R.id.tvNoDokumen);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvTgl = (TextView) findViewById(R.id.tvTgl);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsi);
        tvBoxTempatArsip = (TextView) findViewById(R.id.tvBox);
        tvInstansi = (TextView) findViewById(R.id.tvInstansi);
        accArsip = (Button) findViewById(R.id.btnAcc);
        edStatus = (TextView) findViewById(R.id.edStatus);
        tvMail = (TextView) findViewById(R.id.tvEmail);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        getMail = user.getEmail();
        Log.d(TAG, "Email: " + getMail);

        Intent i = getIntent();
        dataAdmin = i.getStringExtra(AdapterDaftarSuratKeluar.DATA);
        dataPennguna = i.getStringExtra(AdapterDaftarSuratKeluarPengguna.DATA);
        if (dataPennguna != null) {
            readArsip(dataPennguna);
        }
        if (dataAdmin != null) {
            readArsip(dataAdmin);
        }

    }

    private void initToolbars() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Detail Surat Keluar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void accArsip(View v) {

        if (edStatus.getText().toString().equals("0")) {
            if (getMail.equals("ahsanal.huda@gmail.com") || getMail.equals("amel.021297@gmail.com")) {
                TastyToast.makeText(getApplicationContext(), "Surat Disetujui oleh Sekertaris", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                edStatus.setText("1");
                HashMap<String, Object> updateStatus = new HashMap<>();
                updateStatus.put("statusValid", edStatus.getText().toString());
                databaseReference.child("SuratKeluar").child(getId).updateChildren(updateStatus).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }
                });
            }
            if (getMail.equals("ellykasim@gmail.com")) {
                TastyToast.makeText(getApplicationContext(), "Menunggu Persetujuan oleh Sekertaris", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            }
        }
        if (edStatus.getText().toString().equals("1")) {
            if (getMail.equals("ahsanal.huda@gmail.com") || getMail.equals("amel.021297@gmail.com")) {
                TastyToast.makeText(getApplicationContext(), "Menunggu Persetujuan Direktur Utama", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            }
            if (getMail.equals("ellykasim@gmail.com")) {
                TastyToast.makeText(getApplicationContext(), "Surat Disetujui oleh Direktur Utama", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                edStatus.setText("2");
                HashMap<String, Object> updateStatus = new HashMap<>();
                updateStatus.put("statusValid", edStatus.getText().toString());
                databaseReference.child("SuratKeluar").child(getId).updateChildren(updateStatus).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }
                });
            }
        }

    }


    private void readArsip(String namaSurat) {
        databaseReference.child("SuratKeluar").orderByChild("namaSurat").equalTo(namaSurat).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    SuratKeluar arsip = ds.getValue(SuratKeluar.class);
                    tvNama.setText(arsip.getNamaSurat());
                    tvNo.setText(arsip.getNo());
                    tvDeskripsi.setText(arsip.getDeskripsi());
                    tvTgl.setText(arsip.getTanggal());
                    tvInstansi.setText(arsip.getInstansi());
                    hasil = arsip.getStatusValid();
                    tvMail.setText(arsip.getEmail());
                    edStatus.setText(hasil);
                    getId = ds.getKey();
                    Log.d(TAG, "Key : " + getId);
                    if (hasil.equals("0")) {
                        tvStatus.setTextColor(getResources().getColor(R.color.colorStatusRed));
                        tvStatus.setText("Menunggu Persetujuan Sekertaris");
                    }
                    if (hasil.equals("1")) {
                        tvStatus.setTextColor(getResources().getColor(R.color.colorStatusRed));
                        tvStatus.setText("Menunggu Persetujuan Direktur");
                    }
                    if (hasil.equals("2")) {
                        tvStatus.setTextColor(getResources().getColor(R.color.colorStatusGreen));
                        tvStatus.setText("Surat Disetujui");
                        tvStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_all_black_24dp, 0, 0, 0);
                        accArsip.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DtlSuratKeluarAcitivy.class);
    }
}
