package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAuthToken;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentLoginBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AuthTokenRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.ObjectsUtils;
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
    private AuthTokenRepository authTokenRepository;
    private FragmentLoginBinding binding;
    private MainActivityViewModel viewModel;
    private EditText dtUsername;
    private EditText dtPassword;
    private TextView btnIniciarSesion;
    private TextView btnNavRecuperarContrasena;
    private TextView btnNavRegistrarse;
    private TextView txtErrorMessage;
    private ImageView btnEmail;
    private LinearLayout parentView;
    private ProgressBar progressBar;

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

    private void setVisibleLayout(boolean visible) {
        if(visible) {
            parentView.setVisibility(View.VISIBLE);
        }else {
            parentView.setVisibility(View.INVISIBLE);
        }
    }

    private void closeKeyboard() {
        InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        ((MainActivity) requireActivity()).enableDrawer(false);
        ((MainActivity) requireActivity()).setBottomNavVisibility(View.GONE);
        authTokenRepository = new AuthTokenRepository();
        dtUsername = binding.dtUsername;
        dtPassword = binding.dtPassword;
        btnIniciarSesion = binding.btnIniciarSesion;
        btnNavRecuperarContrasena = binding.btnNavRecuperarContrasena;
        btnNavRegistrarse = binding.btnNavRegistrarse;
        btnEmail = binding.btnEmail;
        progressBar = binding.progressBar;
        parentView = (LinearLayout) btnIniciarSesion.getParent();
        setVisibleLayout(false);
        progressBar.setVisibility(View.VISIBLE);
        if(Objects.nonNull(viewModel.recuperarTokenSharedPreferences(getContext()))) {
            LiveData<Object> estadoUsuario = viewModel.getEstadoUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));
            estadoUsuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    if(Objects.nonNull(o)) {
                        if(EstadosUsuario.STATUS_PENDING_VERIFICATION.getName().equals(o)) {
                            Navigation.findNavController(container).navigate(R.id.action_loginFragment_to_activacionCuentaFragment);
                        }else if(EstadosUsuario.STATUS_ACTIVE.getName().equals(o)){
                            Navigation.findNavController(container).navigate(R.id.action_loginFragment_to_homeFragment);
                        }else {
                            viewModel.limpiarTokenSharedPreferences(getContext());
                            progressBar.setVisibility(View.INVISIBLE);
                            setVisibleLayout(true);
                        }
                    }else {
                        viewModel.limpiarTokenSharedPreferences(getContext());
                        progressBar.setVisibility(View.INVISIBLE);
                        setVisibleLayout(true);
                    }
                }
            });
        }else {
            progressBar.setVisibility(View.INVISIBLE);
            setVisibleLayout(true);
        }
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
            String mailtoUri = "stdycircleofficial@gmail.com";

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailtoUri});
            startActivity(Intent.createChooser(intent, "Send Email"));
        });

        /*
            ClickListener para realizar el intento de inicio de sesión del usuario
         */
        btnIniciarSesion.setOnClickListener(view -> {
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
            setVisibleLayout(false);
            progressBar.setVisibility(View.VISIBLE);
            String username = dtUsername.getText().toString();
            String password = dtPassword.getText().toString();
            if(ObjectsUtils.notNullNorEmpty(username, password)) {
                UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
                usuarioLoginDTO.setUsername(username);
                usuarioLoginDTO.setClave(password);
                LiveData<Object> login = viewModel.getLiveDataToken(usuarioLoginDTO);
                /*
                    Realizamos la petición Retrofit para obtener el token de Autorización para el usuario
                 */
                login.observe(getViewLifecycleOwner(), new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o instanceof String) {
                            //Guardamos el token cifrado en SharedPreferences
                            viewModel.guardarTokenSharedPreferences(getContext(), (String) o);
                            String s = viewModel.recuperarTokenSharedPreferences(getContext());
                            Log.d("TAG", s != null ? s : "");
                            LiveData<Object> usuario = viewModel.getUsuario((String) o);
                            usuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                @Override
                                public void onChanged(Object o) {
                                    if(o instanceof UsuarioDTO) {
                                        viewModel.setUsuarioDTO((UsuarioDTO) o);
                                    }
                                }
                            });
                            LiveData<Object> objectLiveData = viewModel.getEstadoUsuario((String) o);
                            /*
                                Realizamos una petición para reenviar el correo de verficación.
                                Si el usuario se encuentra activo redirigimos a inicio,
                                si llega el token de verificación redirigimos

                             */
                            objectLiveData.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                @Override
                                public void onChanged(Object o) {
                                    if (o instanceof String) {
                                        if(o.equals(EstadosUsuario.STATUS_PENDING_VERIFICATION.getName()))
                                            Navigation.findNavController(container).navigate(R.id.action_loginFragment_to_activacionCuentaFragment);
                                        if(o.equals(EstadosUsuario.STATUS_ACTIVE.getName()))
                                            Navigation.findNavController(container).navigate(R.id.action_loginFragment_to_homeFragment);
                                    }
                                }
                            });
                        }else if(o instanceof RespuestasAuthToken) {
                            progressBar.setVisibility(View.INVISIBLE);
                            setVisibleLayout(true);
                            txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(view.getContext(),
                                    RespuestasAuthToken.INVALID_USERNAME_OR_PASSWORD.getDescripcion(), 20);
                            LinearLayout linearLayout = (LinearLayout) btnIniciarSesion.getParent();
                            int index = linearLayout.indexOfChild(btnIniciarSesion);  // Obtener el índice del LinearLayout en el ViewGroup padre

                            parentView.addView(txtErrorMessage, index);
                            progressBar.setVisibility(View.INVISIBLE);
                            setVisibleLayout(true);
                        }
                    }
                });
            }else {
                txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), "Hay campos sin cumplimentar", 20);
                LinearLayout linearLayout = (LinearLayout) btnIniciarSesion.getParent();
                int index = linearLayout.indexOfChild(btnIniciarSesion);  // Obtener el índice del LinearLayout en el ViewGroup padre

                linearLayout.addView(txtErrorMessage, index + 1);
                progressBar.setVisibility(View.INVISIBLE);
                setVisibleLayout(true);
            }
        });

        btnNavRecuperarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                builder.setTitle("¿Contraseña olvidada?");
                builder.setMessage("Mala suerte   ☜(ˆ▿ˆc)");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                AlertDialog dialog = builder.create();
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                dialog.show();
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
            }
        });

        return binding.getRoot();
    }
}