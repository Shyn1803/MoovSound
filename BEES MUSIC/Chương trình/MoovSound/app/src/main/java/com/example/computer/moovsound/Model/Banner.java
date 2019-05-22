package com.example.computer.moovsound.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
//Kế thừa dữ liệu và chuyển dưới dạng object sang page khác dùng Serializable
public class Banner implements Serializable{


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("NoiDung")
    @Expose
    private String noiDung;
    @SerializedName("idBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }

}
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Banner {
//
//    @SerializedName("IDQuangCao")
//    @Expose
//    private String iDQuangCao;
//    @SerializedName("HinhAnh")
//    @Expose
//    private String hinhAnh;
//    @SerializedName("NoiDung")
//    @Expose
//    private String noiDung;
//    @SerializedName("IDBaiHat")
//    @Expose
//    private String iDBaiHat;
//    @SerializedName("TenBaiHat")
//    @Expose
//    private String tenBaiHat;
//    @SerializedName("HinhBaiHat")
//    @Expose
//    private String hinhBaiHat;
//
//    public String getIDQuangCao() {
//        return iDQuangCao;
//    }
//
//    public void setIDQuangCao(String iDQuangCao) {
//        this.iDQuangCao = iDQuangCao;
//    }
//
//    public String getHinhAnh() {
//        return hinhAnh;
//    }
//
//    public void setHinhAnh(String hinhAnh) {
//        this.hinhAnh = hinhAnh;
//    }
//
//    public String getNoiDung() {
//        return noiDung;
//    }
//
//    public void setNoiDung(String noiDung) {
//        this.noiDung = noiDung;
//    }
//
//    public String getIDBaiHat() {
//        return iDBaiHat;
//    }
//
//    public void setIDBaiHat(String iDBaiHat) {
//        this.iDBaiHat = iDBaiHat;
//    }
//
//    public String getTenBaiHat() {
//        return tenBaiHat;
//    }
//
//    public void setTenBaiHat(String tenBaiHat) {
//        this.tenBaiHat = tenBaiHat;
//    }
//
//    public String getHinhBaiHat() {
//        return hinhBaiHat;
//    }
//
//    public void setHinhBaiHat(String hinhBaiHat) {
//        this.hinhBaiHat = hinhBaiHat;
//    }
//
//}
