package adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.heropractice.R;

import java.io.InputStream;
import java.util.List;

import model.Heroes;
import model.ImageResponse;
import url.Url;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolder> {
    private List<Heroes> heroesList;
    private Context context;

    public HeroesAdapter(List<Heroes> heroesList,Context context){
        this.heroesList=heroesList;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.heroes,viewGroup,false);
        return new ViewHolder(view);
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Heroes heroes =heroesList.get(i);
        String imgPath= Url.BASE_URL+"uploads/"+heroes.getImage();
        StrictMode();

        try{
            Url url=new Url(imgPath);
            viewHolder.imgProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));




        }catch (Exception e){
            e.printStackTrace();
        }
        viewHolder.tvName.setText(heroes.getName());
        viewHolder.tvDesc.setText(heroes.getDesc());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        TextView tvName,tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile=itemView.findViewById(R.id.imgPhoto);
            tvName=itemView.findViewById(R.id.tvName);
            tvDesc=itemView.findViewById(R.id.tvDesc);
        }
    }
}
