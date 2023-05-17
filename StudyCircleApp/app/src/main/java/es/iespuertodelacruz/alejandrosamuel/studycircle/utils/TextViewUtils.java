package es.iespuertodelacruz.alejandrosamuel.studycircle.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextViewUtils {

    public static TextView getTextViewLinearLayoutErrorMessage(Context context, String texto) {
        TextView textView = new TextView(context);
        textView.setText(texto);
        textView.setWidth(convertDpToPx(context, 320));
        textView.setHeight(convertDpToPx(context, 20));
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,  // Ancho deseado, puede ser WRAP_CONTENT, MATCH_PARENT o un valor específico en píxeles
                LinearLayout.LayoutParams.WRAP_CONTENT   // Alto deseado, puede ser WRAP_CONTENT, MATCH_PARENT o un valor específico en píxeles
        );
        layoutParams.setMargins(convertDpToPx(context, 62), convertDpToPx(context, 5), convertDpToPx(context, 62), 0);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(Color.RED);
        textView.setTextSize(12);
        return textView;
    }

    private static int convertDpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}