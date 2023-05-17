package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentRegisterBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.ObjectsUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.TextViewUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentRegisterBinding binding;
    private MainActivityViewModel viewModel;
    private EditText dtNombreCompleto;
    private EditText dtUsername;
    private EditText dtEmail;
    private EditText dtPassword;
    private EditText dtPasswordConfirmation;
    private TextView btnNavIniciarSesion;
    private TextView txtErrorMessage;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        dtNombreCompleto = binding.dtNombreCompleto;
        dtUsername = binding.dtUsername;
        dtEmail = binding.dtEmail;
        dtPassword = binding.dtPassword;
        dtPasswordConfirmation = binding.dtPasswordConfirmation;
        btnNavIniciarSesion = binding.btnNavIniciarSesion;
        View view = binding.getRoot();

        btnNavIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        dtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void afterTextChanged(Editable editable) {
                String passwordConfirmation = dtPasswordConfirmation.getText().toString();
                RelativeLayout relativeLayoutPasswordConfirmation = (RelativeLayout) dtPasswordConfirmation.getParent();
                relativeLayoutPasswordConfirmation.setBackground(getResources().getDrawable(R.drawable.blue_border_rounded_cornwe, requireContext().getTheme()));
                if(ObjectsUtils.notNullNorEmpty(passwordConfirmation, editable.toString())) {
                    if (passwordConfirmation.equals(editable.toString()))
                        relativeLayoutPasswordConfirmation.setBackground(getResources().getDrawable(R.drawable.green_border_rounded_cornwe, requireContext().getTheme()));
                    else
                        relativeLayoutPasswordConfirmation.setBackground(getResources().getDrawable(R.drawable.red_border_rounded_cornwe, requireContext().getTheme()));
                }
            }
        });

        dtPasswordConfirmation.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void afterTextChanged(Editable editable) {
                String password = dtPassword.getText().toString();
                RelativeLayout relativeLayoutPasswordConfirmation = (RelativeLayout) dtPasswordConfirmation.getParent();
                relativeLayoutPasswordConfirmation.setBackground(getResources().getDrawable(R.drawable.blue_border_rounded_cornwe, requireContext().getTheme()));
                if(ObjectsUtils.notNullNorEmpty(password, editable.toString()))
                    if(password.equals(editable.toString()))
                        relativeLayoutPasswordConfirmation.setBackground(getResources().getDrawable(R.drawable.green_border_rounded_cornwe, requireContext().getTheme()));
                    else
                        relativeLayoutPasswordConfirmation.setBackground(getResources().getDrawable(R.drawable.red_border_rounded_cornwe, requireContext().getTheme()));
            }
        });

        binding.btnRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.nonNull(txtErrorMessage)) {
                    if(txtErrorMessage.getParent() instanceof RelativeLayout) {
                        RelativeLayout relativeLayout = (RelativeLayout) txtErrorMessage.getParent();
                        relativeLayout.removeView(txtErrorMessage);
                    } else if (txtErrorMessage.getParent() instanceof LinearLayout) {
                        LinearLayout linearLayout = (LinearLayout) txtErrorMessage.getParent();
                        linearLayout.removeView(txtErrorMessage);
                    }
                }
                String nombreCompleto = dtNombreCompleto.getText().toString();
                String email = dtEmail.getText().toString();
                String username = dtUsername.getText().toString();
                String password = dtPassword.getText().toString();
                if(ObjectsUtils.notNullNorEmpty(nombreCompleto, email, username, password)) {
                    UsuarioRegisterDTO usuarioRegisterDTO = new UsuarioRegisterDTO();
                    usuarioRegisterDTO.setNombre(nombreCompleto);
                    usuarioRegisterDTO.setEmail(email);
                    usuarioRegisterDTO.setUsername(username);
                    usuarioRegisterDTO.setClave(password);
                    viewModel.register(usuarioRegisterDTO);
                    viewModel.getResponseLiveData().observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object response) {
                            if(response instanceof UsuarioDTO) {

                            }else if(response instanceof RespuestasRegister) {
                                switch ((RespuestasRegister)response) {
                                    case INVALID_EMAIL:
                                        txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasRegister.INVALID_EMAIL.getDescripcion());
                                        RelativeLayout relativeLayout = (RelativeLayout) dtEmail.getParent();
                                        ViewGroup parentView = (ViewGroup) relativeLayout.getParent();  // Obtener el ViewGroup padre del LinearLayout
                                        int index = parentView.indexOfChild(relativeLayout);  // Obtener el índice del LinearLayout en el ViewGroup padre

                                        parentView.addView(txtErrorMessage, index + 1);
                                        break;
                                    case INVALID_NAME:
                                        TextView txtErrorName = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasRegister.INVALID_NAME.getDescripcion());
                                    default:
                                }
                            }
                        }
                    });
                }else {
                    txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), "Hay campos sin cumplimentar");
                    RelativeLayout relativeLayout = (RelativeLayout) dtPasswordConfirmation.getParent();
                    ViewGroup parentView = (ViewGroup) relativeLayout.getParent();  // Obtener el ViewGroup padre del LinearLayout
                    int index = parentView.indexOfChild(relativeLayout);  // Obtener el índice del LinearLayout en el ViewGroup padre

                    parentView.addView(txtErrorMessage, index + 1);
                }
            }
        });

        return binding.getRoot();
    }
}