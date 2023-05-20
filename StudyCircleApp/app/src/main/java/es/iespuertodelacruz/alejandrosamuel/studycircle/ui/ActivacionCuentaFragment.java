package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentActivacionCuentaBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivacionCuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivacionCuentaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentActivacionCuentaBinding binding;
    private MainActivityViewModel viewModel;
    private ImageView btnSalir;
    private TextView btnReenviarEmail;
    private TextView btnVerificado;
    private LinearLayout parentView;
    private ProgressBar progressBar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActivacionCuentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivacionCuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivacionCuentaFragment newInstance(String param1, String param2) {
        ActivacionCuentaFragment fragment = new ActivacionCuentaFragment();
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

    private void desactivarLayout(boolean desactivar) {
        if(desactivar) {
            parentView.setVisibility(View.INVISIBLE);
            btnSalir.setVisibility(View.INVISIBLE);
        }else {
            parentView.setVisibility(View.VISIBLE);
            btnSalir.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentActivacionCuentaBinding.inflate(inflater, container, false);
        ((MainActivity) requireActivity()).enableDrawer(false);
        View view = binding.getRoot();
        btnSalir = binding.btnSalir;
        btnVerificado = binding.btnVerficado;
        btnReenviarEmail = binding.btnReenviarEmail;
        progressBar = binding.progressBar;
        progressBar.setVisibility(View.INVISIBLE);
        parentView = (LinearLayout) btnReenviarEmail.getParent();

        /*
            ClickListener para el botón que comprueba la verificación del usuario.
            Se comprueba el status del usuario y se actúa en consecuencia, si ya se encuentra verificado se
            redirige al home, en caso contrario se muestra mensaje informativo
         */
        btnVerificado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desactivarLayout(true);
                progressBar.setVisibility(View.VISIBLE);
                LiveData<Object> estadoUsuario = viewModel.getEstadoUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));
                estadoUsuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o.equals(EstadosUsuario.STATUS_ACTIVE.getName())) {
                            Navigation.findNavController(container).navigate(R.id.action_activacionCuentaFragment_to_homeFragment);
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                            builder.setTitle("Usuario no verificado");
                            builder.setMessage("Por favor, verifique su cuenta. Puede solicitar un nuevo email pulsando en 'Reenviar email'");
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
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
                            Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                            Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                            positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                            negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                            progressBar.setVisibility(View.INVISIBLE);
                            desactivarLayout(false);
                        }
                    }
                });
            }
        });

        /*
            ClickListener para el botón que reenvía email de verificación.
            En caso de encontrarse ya verificado el usuario se envía al home, en caso contrario se envía correo
            y se muestra mensaje informativo
         */
        btnReenviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desactivarLayout(true);
                progressBar.setVisibility(View.VISIBLE);
                LiveData<Object> resendEmail = viewModel.resendEmail(viewModel.recuperarTokenSharedPreferences(getContext()));
                resendEmail.observe(getViewLifecycleOwner(), new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o.equals(EstadosUsuario.STATUS_ACTIVE.getName())) {
                            Navigation.findNavController(container).navigate(R.id.action_activacionCuentaFragment_to_homeFragment);
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                            builder.setTitle("Email enviado");
                            builder.setMessage("Se ha enviado un correo de confirmación a su dirección de correo electrónico");
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
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
                            Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                            Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                            positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                            negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                            progressBar.setVisibility(View.INVISIBLE);
                            desactivarLayout(false);
                        }
                    }
                });
            }
        });

        /*
            ClickListener para el botón que hace log out.
            Se muestra alerta al usuario pidiendo confirmación, se elimina el token del SharedPrederences y se reenvía al login
         */
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                builder.setTitle("Salir");
                builder.setMessage("Pulse 'ACEPTAR' para cerrar la sesión");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.limpiarTokenSharedPreferences(getContext());
                        Navigation.findNavController(container).navigate(R.id.action_activacionCuentaFragment_to_loginFragment);
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
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
            }
        });
        return view;
    }
}