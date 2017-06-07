package dankmemes.myleswh.dankmemes.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by myleswh on 07/06/2017.
 */

public class ShareUtils {
    public static void shareUrl(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, url);
        context.startActivity(Intent.createChooser(i, "Share URL"));
    }
}
