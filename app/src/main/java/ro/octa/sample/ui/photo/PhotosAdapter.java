package ro.octa.sample.ui.photo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.octa.sample.R;
import ro.octa.sample.commons.model.Photo;

/**
 * Created by Octa
 */
public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int VIEW_TYPE_ITEM = 1;

  private List<Photo> mPhotos;

  public PhotosAdapter() {
    mPhotos = new ArrayList<>();
  }

  public void clear() {
    mPhotos.clear();
    notifyDataSetChanged();
  }

  public void remove(int position) {
    mPhotos.remove(position);
    notifyDataSetChanged();
  }

  public void addItem(Photo photo) {
    mPhotos.add(photo);
    notifyItemInserted(mPhotos.size() - 1);
  }

  public void addItems(List<Photo> albums) {
    if (null != albums) {
      mPhotos.addAll(albums);
    }
    notifyDataSetChanged();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.photo_list_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    final ViewHolder holder = (ViewHolder) viewHolder;

    final Photo photo = mPhotos.get(position);
    final Context context = viewHolder.itemView.getContext();

    holder.photoName.setText(photo.getTitle());
    Picasso.with(context).load(photo.getThumbnailUrl()).fit().centerCrop().into(holder.photoCover);
  }

  @Override public int getItemCount() {
    return mPhotos.size();
  }

  @Override public int getItemViewType(int position) {
    return VIEW_TYPE_ITEM;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.list_item_home_text) TextView photoName;
    @Bind(R.id.list_item_home_cover) ImageView photoCover;

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

}