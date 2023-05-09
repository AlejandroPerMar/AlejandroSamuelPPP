package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.ActivityMainBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
        UsuarioRegisterDTO usuarioRegisterDTO = new UsuarioRegisterDTO();
        usuarioRegisterDTO.setNombre("nombre prueba");
        usuarioRegisterDTO.setUsername("prueba");
        usuarioRegisterDTO.setEmail("basal72606@larland.com");
        usuarioRegisterDTO.setClave("Prueba1@");
        viewModel.register(usuarioRegisterDTO);
        */

        /*
        UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
        usuarioLoginDTO.setUsername("str");
        usuarioLoginDTO.setClave("Samuel1@");
        viewModel.getLiveDataToken(usuarioLoginDTO);
        */

        //viewModel.resendEmail("eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicHJ1ZWJhIiwiZXhwIjoxNjgzMzI2Mjg2fQ.yNgG7IOAiJp8-npbzMSQ4vAqNFwYG1OElBKG_O9FC9k");

        /*
        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(1);
        MateriaDTO materiaDTO2 = new MateriaDTO();
        materiaDTO2.setId(4);
        NivelEstudiosDTO nivelEstudiosDTO = new NivelEstudiosDTO();
        nivelEstudiosDTO.setId(2);
        AlumnoDTO alumnoDTO = new AlumnoDTO();
        alumnoDTO.setNivelEstudios(nivelEstudiosDTO);
        List<MateriaDTO> materias = new ArrayList<>();
        materias.add(materiaDTO);
        materias.add(materiaDTO2);
        alumnoDTO.setMaterias(materias);
        viewModel.createStudentProfile(alumnoDTO, "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicHJ1ZWJhIiwiZXhwIjoxNjgzMzI2Mjg2fQ.yNgG7IOAiJp8-npbzMSQ4vAqNFwYG1OElBKG_O9FC9k");
        */

        /*
        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(1);
        MateriaDTO materiaDTO2 = new MateriaDTO();
        materiaDTO2.setId(4);
        List<MateriaDTO> materias = new ArrayList<>();
        materias.add(materiaDTO);
        materias.add(materiaDTO2);
        viewModel.createTutorProfile(materias, "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoicHJ1ZWJhIiwiZXhwIjoxNjgzMzI2Mjg2fQ.yNgG7IOAiJp8-npbzMSQ4vAqNFwYG1OElBKG_O9FC9k");
        */



        //viewModel.removeAlumnoToCurso(10, 12, "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3RyIiwiZXhwIjoxNjgzNTY4ODM4fQ.-L0uY0tQu7061c8dPQtEhK9qZOBpyv3gKqTQRV1u3UU");
        //viewModel.addAlumnoToCurso(10, 12, "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3RyIiwiZXhwIjoxNjgzNTY4ODM4fQ.-L0uY0tQu7061c8dPQtEhK9qZOBpyv3gKqTQRV1u3UU");


        //LiveData<Object> cursosTutor = viewModel.findCursosTutor("eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3RyIiwiZXhwIjoxNjgzNjY1NTczfQ.B1q_NdxyMZYKm6DcE0_8AGcBAw6x8yXaqn3f0KhCdBk");
        //Object value = cursosTutor.getValue();
        //List<CursoDTO> value1 = (List<CursoDTO>) value;
        //value1.forEach(c -> System.out.printf(c.getTitulo()));
        //viewModel.changeTituloCurso(10, "nuevo titulo", "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3RyIiwiZXhwIjoxNjgzNzUzMjU0fQ.lHVd-lnTse17t5xGP48aTgfJfMH_5sXA1bP0IGaSsbM");
        viewModel.findMateriasByTutor("eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3RyIiwiZXhwIjoxNjgzNzU1MjQ2fQ.8GexBXYVcfCXbMv8Gb4u8yc_5V7huxt09KaIdOqNNXI");
    }
}