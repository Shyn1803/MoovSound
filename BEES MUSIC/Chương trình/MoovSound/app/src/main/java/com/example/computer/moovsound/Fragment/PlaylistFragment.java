package com.example.computer.moovsound.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.computer.moovsound.Activity.DanhSachBaiHatActivity;
import com.example.computer.moovsound.Activity.DanhSachCacPlaylistActivity;
import com.example.computer.moovsound.Adapter.PlaylistAdapter;
import com.example.computer.moovsound.Model.Playlist;
import com.example.computer.moovsound.R;
import com.example.computer.moovsound.Service.APIService;
import com.example.computer.moovsound.Service.IDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistFragment extends Fragment {
    View view;
    ListView listViewPlaylist;
    TextView txtTitlePlaylist, txtXemThem;

    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> playlists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_playlist, container, false);
        AnhXa();
        GetData();
        txtXemThem.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( getActivity (),DanhSachCacPlaylistActivity.class);
                startActivity ( intent );
            }
        } );
        return view;
    }

    private void AnhXa() {
        listViewPlaylist = view.findViewById ( R.id.listviewplaylist );
        txtTitlePlaylist = view.findViewById( R.id.txt_title_playlist );
        txtXemThem = view.findViewById ( R.id.txt_moreplaylist );
    }

    private void GetData() {
        IDataService dataService = APIService.getService ();
        Call<List<Playlist>> callBack = dataService.GetPlaylistcurrentday ();
        callBack.enqueue ( new Callback<List<Playlist>> ( ) {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists = (ArrayList<Playlist>) response.body ();
                playlistAdapter = new PlaylistAdapter ( getActivity (), android.R.layout.simple_list_item_1, playlists );
                listViewPlaylist.setAdapter ( playlistAdapter );
                setListViewHeightBasedOnChildren ( listViewPlaylist );
                listViewPlaylist.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent ( getActivity (), DanhSachBaiHatActivity.class );
                        intent.putExtra ( "itemplaylist", playlists.get( position ) );
                        startActivity ( intent );
                    }
                } );
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        } );
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
