package com.example.computer.moovsound.Service;

import com.example.computer.moovsound.Model.Album;
import com.example.computer.moovsound.Model.BaiHat;
import com.example.computer.moovsound.Model.Banner;
import com.example.computer.moovsound.Model.ChuDe;
import com.example.computer.moovsound.Model.ChuDeVaTheLoaiTrongNgay;
import com.example.computer.moovsound.Model.Playlist;
import com.example.computer.moovsound.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IDataService {

    @GET("songbanner.php")
    Call<List<Banner>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistcurrentday();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeVaTheLoaiTrongNgay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    //Sử dụng phương thức POST để lấy và trả về dữ liệu từ phía server
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
        //từ khóa gửi lên và từ khóa trong server phải trùng với nhau @idplaylist
    Call<List<BaiHat>> GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachplaylist_them.php")
    Call<List<Playlist>> GetDanhsachcacplaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheochude(@Field("idchude")String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllalbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheoalbum(@Field("idalbum")String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotthich(@Field("luotthich")String luotthich,@Field("idbaihat")String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> GetSearchbaihat(@Field("tukhoa")String tukhoa);
}
