package gulajava.tesjsonparser;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Kucing Imut on 8/21/15.
 */
public class ViewHolders {

    private View view;
    private TextView teksjudul = null;


    public ViewHolders(View view) {
        this.view = view;
    }

    public TextView getTeksjudul() {

        if (teksjudul == null) {

            teksjudul = (TextView) view.findViewById(R.id.teksjudul);
        }

        return teksjudul;
    }
}
