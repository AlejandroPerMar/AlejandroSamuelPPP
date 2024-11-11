package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
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
    private TextView btnRegistrarme;
    private ProgressBar progressBar;
    private ImageView btnEmail;
    private LinearLayout lytCamposRegistro;
    private LinearLayout lytCampoEmail;


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

    private void setVisibleLayout(boolean visible) {
        if(visible) {
            lytCampoEmail.setVisibility(View.VISIBLE);
            lytCamposRegistro.setVisibility(View.VISIBLE);
        }else {
            lytCampoEmail.setVisibility(View.INVISIBLE);
            lytCamposRegistro.setVisibility(View.INVISIBLE);
        }
    }

    private void closeKeyboard() {
        InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        ((MainActivity) requireActivity()).enableDrawer(false);
        ((MainActivity) requireActivity()).setBottomNavVisibility(View.GONE);
        dtNombreCompleto = binding.dtNombreCompleto;
        dtUsername = binding.dtUsername;
        dtEmail = binding.dtEmail;
        dtPassword = binding.dtPassword;
        dtPasswordConfirmation = binding.dtPasswordConfirmation;
        btnNavIniciarSesion = binding.btnNavIniciarSesion;
        btnRegistrarme = binding.btnRegistrarme;
        progressBar = binding.progressBar;
        btnEmail = binding.btnEmail;
        lytCamposRegistro = (LinearLayout) dtNombreCompleto.getParent().getParent();
        lytCampoEmail = (LinearLayout) btnEmail.getParent().getParent();
        View view = binding.getRoot();

        progressBar.setVisibility(View.INVISIBLE);

        /*
            ClickListener para navegar al Fragment fragment_login
         */
        btnNavIniciarSesion.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_registerFragment_to_loginFragment));

        /*
            TextChangedListener para vigilar y comparar los campos dtPassword y dtPasswordConfirmation e indicar
            al usuario visualmente si las contraseñas coinciden
         */
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
                if(ObjectsUtils.notNullNorEmpty(editable.toString())) {
                    if (passwordConfirmation.equals(editable.toString()))
                        relativeLayoutPasswordConfirmation.setBackground(getResources().getDrawable(R.drawable.green_border_rounded_cornwe, requireContext().getTheme()));
                    else
                        relativeLayoutPasswordConfirmation.setBackground(getResources().getDrawable(R.drawable.red_border_rounded_cornwe, requireContext().getTheme()));
                }
            }
        });

        /*
            TextChangedListener para vigilar y comparar los campos dtPassword y dtPasswordConfirmation e indicar
            al usuario visualmente si las contraseñas coinciden
         */
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

        /*
            ClickListener para realizar el intento de registro del nuevo usuario
         */
        btnRegistrarme.setOnClickListener(view13 -> {
            closeKeyboard();
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
            String passwordConfirmation = dtPasswordConfirmation.getText().toString();
            if(ObjectsUtils.notNullNorEmpty(nombreCompleto, email, username, password, passwordConfirmation)) {
                UsuarioRegisterDTO usuarioRegisterDTO = new UsuarioRegisterDTO();
                usuarioRegisterDTO.setNombre(nombreCompleto);
                usuarioRegisterDTO.setEmail(email);
                usuarioRegisterDTO.setUsername(username);
                usuarioRegisterDTO.setClave(password);
                LiveData<Object> register = viewModel.register(usuarioRegisterDTO);
                setVisibleLayout(false);
                progressBar.setVisibility(View.VISIBLE);

                register.observe(getViewLifecycleOwner(), response -> {
                    if(response instanceof UsuarioDTO) {
                        viewModel.setRegisterSuccessMessage("!Cuenta creada con éxito!\nInicie sesión");
                        Navigation.findNavController(view13).navigate(R.id.action_registerFragment_to_loginFragment);
                    }else if(response instanceof RespuestasRegister) {
                        progressBar.setVisibility(View.INVISIBLE);
                        setVisibleLayout(true);
                        RelativeLayout relativeLayout;
                        ViewGroup parentView;
                        int index;
                        switch ((RespuestasRegister)response) {
                            case INVALID_EMAIL:
                                txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasRegister.INVALID_EMAIL.getDescripcion(), 20);
                                relativeLayout = (RelativeLayout) dtEmail.getParent();
                                parentView = (ViewGroup) relativeLayout.getParent();  // Obtener el ViewGroup padre del LinearLayout
                                index = parentView.indexOfChild(relativeLayout);  // Obtener el índice del LinearLayout en el ViewGroup padre

                                parentView.addView(txtErrorMessage, index + 1);
                                break;
                            case INVALID_NAME:
                                txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasRegister.INVALID_NAME.getDescripcion(), 20);
                                relativeLayout = (RelativeLayout) dtNombreCompleto.getParent();
                                parentView = (ViewGroup) relativeLayout.getParent();  // Obtener el ViewGroup padre del LinearLayout
                                index = parentView.indexOfChild(relativeLayout);  // Obtener el índice del LinearLayout en el ViewGroup padre

                                parentView.addView(txtErrorMessage, index + 1);
                                break;
                            case NOT_MINIMUN_REQUIREMENTS_PASSWORD:
                                txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasRegister.NOT_MINIMUN_REQUIREMENTS_PASSWORD.getDescripcion(), 60);
                                relativeLayout = (RelativeLayout) dtPassword.getParent();
                                parentView = (ViewGroup) relativeLayout.getParent();  // Obtener el ViewGroup padre del LinearLayout
                                index = parentView.indexOfChild(relativeLayout);  // Obtener el índice del LinearLayout en el ViewGroup padre

                                parentView.addView(txtErrorMessage, index + 1);
                                break;
                            case NOT_AVAILABLE_USERNAME:
                                txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasRegister.NOT_AVAILABLE_USERNAME.getDescripcion(), 20);
                                relativeLayout = (RelativeLayout) dtUsername.getParent();
                                parentView = (ViewGroup) relativeLayout.getParent();  // Obtener el ViewGroup padre del LinearLayout
                                index = parentView.indexOfChild(relativeLayout);  // Obtener el índice del LinearLayout en el ViewGroup padre

                                parentView.addView(txtErrorMessage, index + 1);
                                break;
                            case NOT_AVAILABLE_EMAIL:
                                txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasRegister.NOT_AVAILABLE_EMAIL.getDescripcion(), 20);
                                relativeLayout = (RelativeLayout) dtEmail.getParent();
                                parentView = (ViewGroup) relativeLayout.getParent();  // Obtener el ViewGroup padre del LinearLayout
                                index = parentView.indexOfChild(relativeLayout);  // Obtener el índice del LinearLayout en el ViewGroup padre

                                parentView.addView(txtErrorMessage, index + 1);
                            default:
                        }
                    }
                });
            }else {
                txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), "Hay campos sin cumplimentar", 20);
                LinearLayout linearLayout = (LinearLayout) btnRegistrarme.getParent();
                int index = linearLayout.indexOfChild(btnRegistrarme);  // Obtener el índice del LinearLayout en el ViewGroup padre

                linearLayout.addView(txtErrorMessage, index + 1);
            }
        });

        btnEmail.setOnClickListener(view12 -> {
            String mailtoUri = "stdycircleofficial@gmail.com";

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailtoUri});
            startActivity(Intent.createChooser(intent, "Send Email"));
        });

        return binding.getRoot();
    }
}