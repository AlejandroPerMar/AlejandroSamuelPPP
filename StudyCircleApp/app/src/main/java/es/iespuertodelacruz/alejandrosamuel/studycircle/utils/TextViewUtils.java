package es.iespuertodelacruz.alejandrosamuel.studycircle.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;

public class TextViewUtils {


    /**
     * Método para obtener un TextView con un mensaje de error genérico
     * @param context   Contexto de la aplicación
     * @param texto     Texto a mostrar en el TextView
     * @return          TextView con el mensaje de error
     */
    public static TextView getTextViewLinearLayoutErrorMessage(Context context, String texto, int altura) {
        TextView textView = new TextView(context);
        textView.setText(texto);
        textView.setWidth(convertDpToPx(context, 320));
        textView.setHeight(convertDpToPx(context, altura));
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,  // Ancho deseado, puede ser WRAP_CONTENT, MATCH_PARENT o un valor específico en píxeles
                LinearLayout.LayoutParams.WRAP_CONTENT   // Alto deseado, puede ser WRAP_CONTENT, MATCH_PARENT o un valor específico en píxeles
        );
        layoutParams.setMargins(convertDpToPx(context, 62), convertDpToPx(context, 5), convertDpToPx(context, 62), 0);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        Typeface typeface = ResourcesCompat.getFont(context, R.font.poppins_regular);
        textView.setTypeface(typeface);
        textView.setTextColor(Color.parseColor("#a82839"));
        textView.setTextSize(12);
        return textView;
    }

    public static TextView getTextViewLinearLayoutSuccessMessage(Context context, String texto) {
        TextView textView = new TextView(context);
        textView.setText(texto);
        textView.setWidth(convertDpToPx(context, 320));
        textView.setHeight(convertDpToPx(context, 60));
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,  // Ancho deseado, puede ser WRAP_CONTENT, MATCH_PARENT o un valor específico en píxeles
                LinearLayout.LayoutParams.WRAP_CONTENT   // Alto deseado, puede ser WRAP_CONTENT, MATCH_PARENT o un valor específico en píxeles
        );
        layoutParams.setMargins(convertDpToPx(context, 62), convertDpToPx(context, 5), convertDpToPx(context, 62), 0);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        Typeface typeface = ResourcesCompat.getFont(context, R.font.poppins_medium_italic);
        textView.setTypeface(typeface);
        textView.setTextColor(Color.parseColor("#3695f5"));
        textView.setTextSize(20);
        return textView;
    }

    private static int convertDpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}