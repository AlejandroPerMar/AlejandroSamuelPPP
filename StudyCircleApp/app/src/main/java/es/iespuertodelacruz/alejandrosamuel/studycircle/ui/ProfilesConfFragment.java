package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentLoginBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentProfilesConfBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentTutorConfBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilesConfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilesConfFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentProfilesConfBinding binding;
    private MainActivityViewModel viewModel;
    private TextView btnConfAlumno;
    private TextView btnConfTutor;
    private ImageView btnSalir;

    public ProfilesConfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilesConfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilesConfFragment newInstance(String param1, String param2) {
        ProfilesConfFragment fragment = new ProfilesConfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfilesConfBinding.inflate(inflater, container, false);
        btnConfAlumno = binding.btnConfAlumno;
        btnConfTutor = binding.btnConfTutor;
        btnSalir = binding.btnSalir;
        ((MainActivity) requireActivity()).enableDrawer(false);
        ((MainActivity) requireActivity()).setBottomNavVisibility(View.INVISIBLE);
        binding.txtExplicacion.setText(Html.fromHtml(binding.txtExplicacion.getText().toString(), Html.FROM_HTML_MODE_COMPACT));

        btnConfAlumno.setOnClickListener(btn -> {
            Navigation.findNavController(container).navigate(R.id.action_profilesConfFragment_to_alumnoConfFragment);
        });

        btnConfTutor.setOnClickListener(btn -> {
            Navigation.findNavController(container).navigate(R.id.action_profilesConfFragment_to_tutorConfFragment);
        });

        btnSalir.setOnClickListener(btn -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
            builder.setTitle("Salir");
            builder.setMessage("Pulse ACEPTAR para cerrar la sesión");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    viewModel.limpiarTokenSharedPreferences(getContext());
                    Navigation.findNavController(container).navigate(R.id.action_profilesConfFragment_to_loginFragment);
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Lógica al hacer clic en el botón "Cancelar"
                }
            });

            AlertDialog dialog = builder.create();
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);
            dialog.show();
            TextView textViewMessage = (TextView) dialog.findViewById(android.R.id.message);
            Typeface typeface = ResourcesCompat.getFont(requireContext(), R.font.poppins_regular);
            textViewMessage.setTypeface(typeface);
            Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
            negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
        });

        return binding.getRoot();
    }
}