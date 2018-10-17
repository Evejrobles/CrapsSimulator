package deepdive.edu.cnm.crapssimulator.view;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import deepdive.edu.cnm.crapssimulator.R;
import deepdive.edu.cnm.crapssimulator.model.Game.Roll;
import deepdive.edu.cnm.crapssimulator.model.Game.State;

public class RollAdapter extends ArrayAdapter<Roll> {

  private Drawable[] faces;

  public RollAdapter(@NonNull Context context) {
    super(context, R.layout.roll_item);
    Resources res = context.getResources();
    String pkg = context.getPackageName();
    faces = new Drawable[6];
    for (int i = 0; i < 6; i++) {
      faces[i] =
          ContextCompat.getDrawable(context, res.getIdentifier("face_" + (i + 1), "drawable", pkg)
          );
    }
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    Roll roll = getItem(position);
    int[] dice = roll.getDice();
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.roll_item, parent, false);
    }
    ImageView die0 = convertView.findViewById(R.id.die_0);
    die0.setImageDrawable(faces[dice[0] - 1]);
    ImageView die1 = convertView.findViewById(R.id.die_1);
    die1.setImageDrawable(faces[dice[1] - 1]);
    TextView total = convertView.findViewById(R.id.total);
    total.setText(getContext().getString(R.string.dice_total_format, dice[0] + dice[1]));
    if (position == 0 && getCount() > 1) {
      convertView.setBackgroundColor(Color.argb(128, 128, 128, 128));
    } else if (roll.getState() == State.WIN) {
      convertView.setBackgroundColor(Color.argb(128, 0, 128, 0));
    } else if (roll.getState() == State.LOSS) {
      convertView.setBackgroundColor(Color.argb(128, 255, 0, 0));
    } else {
      convertView.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }
    return convertView;
  }

}
