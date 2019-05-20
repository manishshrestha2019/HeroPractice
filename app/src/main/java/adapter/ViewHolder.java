package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.heropractice.R;

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
