package ro.octa.sample.ui.album;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.octa.sample.R;
import ro.octa.sample.commons.model.Album;
import ro.octa.sample.ui.photo.PhotosActivity;

/**
 * Created by Octa
 */
public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int VIEW_TYPE_ITEM = 1;

  private List<Album> mAlbums;

  public AlbumsAdapter() {
    mAlbums = new ArrayList<>();
  }

  public void clear() {
    mAlbums.clear();
    notifyDataSetChanged();
  }

  public void remove(int position) {
    mAlbums.remove(position);
    notifyDataSetChanged();
  }

  public void addItem(Album album) {
    mAlbums.add(album);
    notifyItemInserted(mAlbums.size() - 1);
  }

  public void addItems(List<Album> albums) {
    if (null != albums) {
      mAlbums.addAll(albums);
    }
    notifyDataSetChanged();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.album_list_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    final ViewHolder holder = (ViewHolder) viewHolder;

    final Album album = mAlbums.get(position);
    final Context context = viewHolder.itemView.getContext();

    holder.albumName.setText(album.getTitle());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        context.startActivity(PhotosActivity.makeIntent(context, album.getId()));
      }
    });
  }

  @Override public int getItemCount() {
    return mAlbums.size();
  }

  @Override public int getItemViewType(int position) {
    return VIEW_TYPE_ITEM;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.list_item) TextView albumName;

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

}