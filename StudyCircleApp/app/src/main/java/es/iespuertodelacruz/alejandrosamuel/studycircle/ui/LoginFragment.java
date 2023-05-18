package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentLoginBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.TextViewUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentLoginBinding binding;
    private MainActivityViewModel viewModel;
    private EditText dtUsername;
    private EditText dtPassword;
    private TextView btnIniciarSesion;
    private TextView btnNavRecuperarContrasena;
    private TextView btnNavRegistrarse;
    private ImageView btnEmail;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        dtUsername = binding.dtUsername;
        dtPassword = binding.dtPassword;
        btnIniciarSesion = binding.btnIniciarSesion;
        btnNavRecuperarContrasena = binding.btnNavRecuperarContrasena;
        btnNavRegistrarse = binding.btnNavRegistrarse;
        btnEmail = binding.btnEmail;

        if(Objects.nonNull(viewModel.getRegisterSuccessMessage())) {
            RelativeLayout relativeLayout = (RelativeLayout) dtUsername.getParent();
            ViewGroup parentView = (ViewGroup) relativeLayout.getParent();  // Obtener el ViewGroup padre del LinearLayout
            int index = parentView.indexOfChild(relativeLayout);  // Obtener el índice del LinearLayout en el ViewGroup padre

            parentView.addView(TextViewUtils.getTextViewLinearLayoutSuccessMessage(requireContext(), viewModel.getRegisterSuccessMessage()), index);
            viewModel.setRegisterSuccessMessage(null);
        }

        btnNavRegistrarse.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment));

        btnNavRecuperarContrasena.setOnClickListener(view -> {

        });

        btnEmail.setOnClickListener(view -> {
            String mailtoUri = "mailto:stdycircleofficial@gmail.com";

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse(mailtoUri));
            if (Objects.nonNull(intent.resolveActivity(getActivity().getPackageManager()))) {
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}