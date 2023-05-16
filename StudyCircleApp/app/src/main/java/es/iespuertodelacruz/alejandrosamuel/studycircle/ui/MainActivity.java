package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAuthToken;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasCursos;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaTutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
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

                //viewModel.resendEmail("");

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
        viewModel.createStudentProfile(alumnoDTO, "");
        */

        /*
        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(1);
        MateriaDTO materiaDTO2 = new MateriaDTO();
        materiaDTO2.setId(4);
        List<MateriaDTO> materias = new ArrayList<>();
        materias.add(materiaDTO);
        materias.add(materiaDTO2);
        viewModel.createTutorProfile(materias, "");
        */


                //viewModel.removeAlumnoToCurso(10, 12, "");
                //viewModel.addAlumnoToCurso(10, 12, "");


        viewModel.findCursosTutor("eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3RyIiwiZXhwIjoxNjg0MzMxMDc0fQ.xvjc9jhrQuPcufxi7gSWwcHB8-WN8IltKpEvX6aDXFI").observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof List) {
                    List<CursoDTO> cursosDTO = (List<CursoDTO>) o;
                    cursosDTO.forEach(System.out::println);
                }else {
                    RespuestasCursos respuestasCursos = (RespuestasCursos) o;
                    System.out.println(respuestasCursos.getDescripcion());
                }
            }
        });;
                //viewModel.changeTituloCurso(10, "nuevo titulo", "");
                //viewModel.findMateriasByTutor("");

        /*
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setTitulo("prueba");
        MateriaTutorDTO materiaTutorDTO = new MateriaTutorDTO();
        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(2);
        materiaTutorDTO.setMateria(materiaDTO);
        cursoDTO.setMateriaTutor(materiaTutorDTO);
        AlumnoDTO alumno1DTO = new AlumnoDTO();
        alumno1DTO.setId(4);
        AlumnoDTO alumno2DTO = new AlumnoDTO();
        alumno2DTO.setId(12);
        cursoDTO.setAlumnos(Stream.of(alumno1DTO, alumno2DTO).collect(Collectors.toList()));
        viewModel.createCurso(cursoDTO, "");
        */

    }
}